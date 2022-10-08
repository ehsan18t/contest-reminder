package dev.pages.ahsan.creminder.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;

public class JSON {
    private static final ObjectMapper mapper = getDefaultMapper();

    private static ObjectMapper getDefaultMapper() {
        return new ObjectMapper();
    }

    public static JsonNode parse(String data) throws JsonProcessingException {
        return mapper.readTree(data);
    }

    public static JsonNode parse(InputStream data) throws IOException {
        return mapper.readTree(data);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static JsonNode request(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JsonNode jNode = null;

        try {
            HttpResponse response = httpclient.execute(httpGet);
            InputStream is = response.getEntity().getContent();
            jNode = JSON.parse(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jNode;
    }
}
