package com.moxuanran.learning;

import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class SignatureUtils {

    public static String signature(String secretKey,
                                   String httpMethod,
                                   String path,
                                   Map<String,String[]> queryParameters,
                                   String body,
                                   String[] signedHeaders,
                                   HeaderValve hv){
        String bodyHash256 = calculateContentHash(body);
        Arrays.sort(signedHeaders, String.CASE_INSENSITIVE_ORDER);
        StringBuilder canonicalRequestBuilder = new StringBuilder();
        canonicalRequestBuilder.append(httpMethod).
                append("\n").
                append(getCanonicalizedResourcePath(path)).
                append("\n").
                append(getCanonicalizedQueryString(queryParameters)).
                append("\n").
                append(getCanonicalizedHeaderString(hv,signedHeaders)).
                append("\n").
                append(getSignedHeadersString(signedHeaders)).
                append("\n").
                append(bodyHash256);
        String canonicalRequest = canonicalRequestBuilder.toString();
        StringBuilder stringToSignBuilder = new StringBuilder();
        stringToSignBuilder.append(toHex(hash(canonicalRequest)));
        String stringToSign = stringToSignBuilder.toString();

        byte[] signingKey = secretKey.getBytes(StandardCharsets.UTF_8);

        byte[] signature = sign(stringToSign.getBytes(StandardCharsets.UTF_8),signingKey);

        return buildAuthorizationHeader(signedHeaders,signature);
    }

    public interface HeaderValve {
        String extractValue(String name);
    }

    private static String buildAuthorizationHeader(String[] signedHeaders, byte[] signature) {
        String signerHeaders = "SignedHeaders=" + getSignedHeadersString(signedHeaders);
        String signatureHeader = "Signature=" + toHex(signature);
        StringBuilder authHeaderBuilder = new StringBuilder();
        authHeaderBuilder.append(signerHeaders).append(", ").append(signatureHeader);
        return authHeaderBuilder.toString();
    }

    private static byte[] sign(byte[] data, byte[] key) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key,"HmacSHA256"));
            return mac.doFinal(data);
        } catch (InvalidKeyException | NoSuchAlgorithmException var5) {
            return null;
        }
    }

    private static String getSignedHeadersString(String[] signedHeaders) {
        StringBuilder buffer = new StringBuilder();
        for (String header : signedHeaders) {
            if (buffer.length() > 0) {
                buffer.append(";");
            }
            buffer.append(header.toLowerCase());
        }
        return buffer.toString();
    }

    private static String getCanonicalizedHeaderString(HeaderValve request, String[] signedHeaders) {
        StringBuilder buffer = new StringBuilder();
        for (String header : signedHeaders) {
            String key = header.toLowerCase();
            String value = request.extractValue(header);
            buffer.append(key).append(":");
            if (value != null) {
                buffer.append(value.trim());
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }

    private static String getCanonicalizedQueryString(Map<String, String[]> parameters) {
        if(null == parameters || parameters.isEmpty()){
            return "";
        }
        SortedMap<String, List<String>> sorted = new TreeMap<>();
        for (Map.Entry<String, String[]> entry : parameters.entrySet()) {
            String[] paramValues = entry.getValue();
            List<String> encodedValues = new ArrayList<>(paramValues.length);
            encodedValues.addAll(Arrays.asList(paramValues));
            Collections.sort(encodedValues);
            sorted.put(entry.getKey(), encodedValues);
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : sorted.entrySet()) {
            String value;
            for (Iterator var13 = ((List) entry.getValue()).iterator(); var13.hasNext(); result.append(entry.getKey()).append("=").append(value)) {
                value = (String) var13.next();
                if (result.length() > 0) {
                    result.append("&");
                }
            }
        }
        return result.toString();
    }


    private static String getCanonicalizedResourcePath(String resourcePath) {
        if (resourcePath != null && !resourcePath.isEmpty()) {
            try {
                resourcePath = (new URI(resourcePath)).getPath();
            } catch (URISyntaxException var3) {
                return resourcePath;
            }

            String value = resourcePath;
            if (!value.startsWith("/")) {
                value = "/".concat(value);
            }

            if (!value.endsWith("/")) {
                value = value.concat("/");
            }

            return value;
        } else {
            return "/";
        }
    }


    private static byte[] hash(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(text.getBytes(StandardCharsets.UTF_8));
            return md.digest();
        } catch (NoSuchAlgorithmException var3) {
            return null;
        }
    }

    private static String calculateContentHash(String body){
        if(StringUtils.isEmpty(body)){
            body = "";
        }
        return toHex(hash(body));
    }

    private static String toHex(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length * 2);
        byte[] var2 = data;
        int var3 = data.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            String hex = Integer.toHexString(b);
            if (hex.length() == 1) {
                sb.append("0");
            } else if (hex.length() == 8) {
                hex = hex.substring(6);
            }

            sb.append(hex);
        }
        return sb.toString().toLowerCase(Locale.getDefault());
    }

}