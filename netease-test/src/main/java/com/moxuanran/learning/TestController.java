package com.moxuanran.learning;

import cn.hutool.core.collection.ListUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author wutao
 * @date 2022/9/27 17:08
 */
@RestController
public class TestController {
    public List<String> imgaeHashs = Arrays.asList(
            "image_1_tw_1.jpg", "image_2_tw_2.jpg", "image_3_tw_3.jpg",
            "image_4_tw_4.jpg", "image_5_tw_5.jpg", "image_6_tw_6.jpg",
            "image_7_tw_7.jpg", "image_8_tw_8.jpg", "image_9_tw_9.jpg"
    );

    //分页测试
    @GetMapping("/page")
    public Object page(int pageNumber, int pageSize) {
        HashMap<Object, Object> result = new HashMap<>();
        List<String> page = ListUtil.page(pageNumber, pageSize, imgaeHashs);
        List<ModelTrainUrlResponse> resps = new ArrayList<>();
        for (String s : page) {
            ModelTrainUrlResponse model = new ModelTrainUrlResponse();
            model.setImageHash(s);
            model.setImageUrl("ttt");
            model.setImageName("ttt");
            resps.add(model);
        }
        result.put("code", 0);
        result.put("msg", "success");
        result.put("data", resps);
        result.put("total", imgaeHashs.size());
        return result;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        String appKey = "297b1e9c72bb4c38";
        String secret = "948479a1e2d54732";
        String url = "https://openapi.8.163.com/common/v1/upload";

        ClientDemo.postFile(appKey, secret, url, file);
        return "";
    }

    @PostMapping("log/add")
    public String addLog() throws Exception {
        String json = "{\n" +
                "    \"key\": \"test\",\n" +
                "    \"userInfo\": {\n" +
                "        \"userId\": \"raodongyu@corp.netease.com\",\n" +
                "        \"userName\": \"hxxhhs\",\n" +
                "        \"cardNo\": null,\n" +
                "        \"enterpriseName\": null,\n" +
                "        \"enterpriseNo\": null\n" +
                "    },\n" +
                "    \"eventType\": \"txt2img\",\n" +
                "    \"time\": \"2023-05-22 14:00:09\",\n" +
                "    \"prompt\": \"小男孩和狗1\",\n" +
                "    \"inputParams\": {\n" +
                "        \"prompt\": \"小男孩和狗1\",\n" +
                "        \"styleId\": 100879,\n" +
                "        \"outputSize\": {\n" +
                "            \"width\": 512,\n" +
                "            \"height\": 512,\n" +
                "            \"recommend\": null,\n" +
                "            \"type\": null\n" +
                "        },\n" +
                "        \"enhanceOutputSize\": null,\n" +
                "        \"txtCfgScale\": 1.0,\n" +
                "        \"imageCfgScale\": 0.0,\n" +
                "        \"inputImage\": null,\n" +
                "        \"inputMask\": null,\n" +
                "        \"negPrompt\": \"狗\",\n" +
                "        \"seed\": \"0\",\n" +
                "        \"hires\": \"{\\\"upscale_by\\\": 4, \\\"upscale\\\": [], \\\"steps\\\": 50, \\\"denoising_strength\\\": 1, \\\"is_hires\\\": false}\",\n" +
                "        \"modelHashs\": [\n" +
                "            \"ba4adf980fc403eb73bc9363b274fb9d4effad86b89bbdf8ca56a2caabae8b0bhhw1\"\n" +
                "        ],\n" +
                "        \"nImages\": 3,\n" +
                "        \"vae_style_id\": null,\n" +
                "        \"lora_style_list\": \"\",\n" +
                "        \"sampling_id\": 1,\n" +
                "        \"control_net_origin_image\": \"\",\n" +
                "        \"control_net_model_id\": 1,\n" +
                "        \"control_net_skeleton_image\": \"\",\n" +
                "        \"control_net_weight\": 0.0,\n" +
                "        \"control_net_guiding_strength\": 0.3,\n" +
                "        \"control_net_json\": \"\",\n" +
                "        \"num_inference_steps\": 10,\n" +
                "        \"webui_image_cfg_scale\": null,\n" +
                "        \"is_post_process\": false,\n" +
                "        \"is_face_restore\": true,\n" +
                "        \"is_super_resolution\": false,\n" +
                "        \"gan_type\": null,\n" +
                "        \"scale_factor\": null,\n" +
                "        \"pretreatment_id\": 1\n" +
                "    },\n" +
                "    \"outputParams\": {\n" +
                "        \"imageHashs\": [\n" +
                "            \"9e729daede6d2547ee91ccb008cc28cb4099e6bc2f7d4ec9a0103c3e4229db9917727a11\",\n" +
                "            \"734c8fc7a19bc6dd0e92075dd9ec197a62aad7942089075be04886052df5dc171992b11\",\n" +
                "            \"6ace9198dd720e3542a0bc97358cf572ba7fc94c349bb4877c9481310df8aa0610020c11\"\n" +
                "        ],\n" +
                "        \"imageNames\": [\n" +
                "            \"ga1n61_1684735192_1344829_scale1_realesrgan.png\",\n" +
                "            \"ga1n51_1684735198_880542_scale1_realesrgan.png\",\n" +
                "            \"ga1n41_1684735206_8543081_scale1_realesrgan.png\"\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        String appKey = "admin";
        String secret = "admin1234";
        String url = "https://openapi.8.163.com/aigc/api/log/add";
        ClientDemo.postJson(appKey, secret, url, json);
        return "";
    }
}
