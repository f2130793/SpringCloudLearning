package com.moxuanran.learning.controller;

import com.google.gson.Gson;
import com.moxuanran.learning.entity.CarSerialBrand;
import com.moxuanran.learning.service.CarSerialBrandService;
import com.moxuanran.learning.util.ESClient;
import lombok.SneakyThrows;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 莫轩然
 * @date 2020/6/28 20:35
 */
@RestController
@RequestMapping("/car")
public class ClientController {
    RestHighLevelClient client = ESClient.getInstance().getHighLevelClient();

    @Autowired
    private CarSerialBrandService carService;

    @RequestMapping("/init")
    @SneakyThrows
    public Integer indexInit() {
        CreateIndexRequest request = new CreateIndexRequest("car_test1");
        request.settings(Settings.builder()
                .put("index.number_of_shards",3)
                .put("index.number_of_replicas",2)
                );
        List<CarSerialBrand> list = carService.list();

        BulkRequest bulkRequest = new BulkRequest("car_test");
        Gson gson = new Gson();
        for (int i = 0; i < list.size(); i++) {
            bulkRequest.add(new IndexRequest().id(Integer.toString(i)).source(gson.toJson(list.get(i)), XContentType.JSON));
        }

        BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println("数量:" + response.getItems().length);

        ESClient.getInstance().closeClient();

        return response.getItems().length;
    }

}
