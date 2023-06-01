package com.moxuanran.learning.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

/**
 * @author wutao
 * @date 2023/5/31 14:30
 */
@SpringBootTest(classes = Application.class)
public class ElasticTest {

    @Test
    public void connect() {
        RestClient restClient = RestClient.builder(
                new HttpHost("127.0.0.1", 9200)
        ).build();

        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

        ElasticsearchClient client = new ElasticsearchClient(transport);
    }

    @Autowired
    private ElasticsearchClient client;

    @Test
    public void firstSearch() throws IOException {
        Reader queryJson = new StringReader("{\n" +
                "  \"size\": 0, \n" +
                "  \"aggs\": {\n" +
                "    \"aggs_name\": {\n" +
                "      \"terms\": {\n" +
                "        \"field\": \"name.keyword\",\n" +
                "        \"size\": 10\n" +
                "      },\n" +
                "      \"aggs\": {\n" +
                "        \"lv\": {\n" +
                "          \"terms\": {\n" +
                "            \"field\": \"lv.keyword\",\n" +
                "            \"size\": 10\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}");
        Map<String, Aggregate> aggregations = client.search(SearchRequest.of(b -> b.withJson(queryJson)), Void.class).aggregations();
        System.out.println(aggregations);
    }
}
