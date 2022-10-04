package com.moxuanran.learning.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtils {

    private static final Logger logger = LoggerFactory.getLogger(IPUtils.class);

    private static final Pattern IPV4_PATTERN = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$");
    private static final Pattern IPV6_PATTERN = Pattern.compile("^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$");
    private static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    public static final String LOCALHOST = "127.0.0.1";
    public static final String ANYHOST = "0.0.0.0";
    public static boolean isIPV4(String addr) {
        return isMatch(addr, IPV4_PATTERN);
    }

    public static boolean isIPV6(String addr) {
        return isMatch(addr, IPV6_PATTERN);
    }

    private static boolean isMatch(String data, Pattern pattern) {
        if (StringUtils.isBlank(data)) {
            return false;
        }
        Matcher mat = pattern.matcher(data);
        return mat.find();
    }

    private static volatile InetAddress LOCAL_ADDRESS = null;
    public static String getLocalHost(){
        InetAddress address =getLocalAddress();
        return address == null ? LOCALHOST : address.getHostAddress();
    }

    public static InetAddress getLocalAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }
        InetAddress localAddress = getLocalAddress0();
        if(null == localAddress){
            throw new IllegalArgumentException("can not getLocalAddress");
        }
        LOCAL_ADDRESS = localAddress;
        return localAddress;
    }
    private static boolean isValidAddress(InetAddress address) {
        if (address == null || address.isLoopbackAddress()) {
            return false;
        }
        String name = address.getHostAddress();
        return (name != null
                && !ANYHOST.equals(name)
                && !LOCALHOST.equals(name)
                && IP_PATTERN.matcher(name).matches());
    }
    private static InetAddress getLocalAddress0() {
        InetAddress localAddress = null;
        try {
            localAddress = InetAddress.getLocalHost();
            if (isValidAddress(localAddress)) {
                return localAddress;
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (interfaces != null) {
                while (interfaces.hasMoreElements()) {
                    try {
                        NetworkInterface network = interfaces.nextElement();
                        Enumeration<InetAddress> addresses = network.getInetAddresses();
                        if (addresses != null) {
                            while (addresses.hasMoreElements()) {
                                try {
                                    InetAddress address = addresses.nextElement();
                                    if (isValidAddress(address)) {
                                        return address;
                                    }
                                } catch (Throwable e) {
                                    logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
                    }
                }
            }
        } catch (Throwable e) {
            logger.warn("Failed to retriving ip address, " + e.getMessage(), e);
        }
        logger.error("Could not get local host ip address, will use 127.0.0.1 instead.");
        return localAddress;
    }


    /**
     * 把精确的完整ip转换成int型
     * @param ip 10.10.10.10 这种
     * @return
     */
    public static long ip2Int(String ip){
        int result = 0;
        ip = ip.trim();
        String[] ipAddress = ip.split("\\.");
        if(ipAddress.length != 4){
            return 0;
        }
        for (int i = 3; i >= 0; i--){
            int col = Integer.parseInt(ipAddress[3 - i]);
            result |= col << (i * 8);
        }
        return Integer.toUnsignedLong(result);
    }



}
