package com.interview.common.httpclient;

import com.interview.common.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;

@Slf4j
@Component
public class HttpConnectionClient {

    private final OkHttpClient httpClient = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public <T> T sendHttpPostRequest(String url, String payload, Class<T> responseClazz) throws Exception {
        String response = sendPost(url, payload);
        return (T) CommonUtil.mapJsonToObject(response, responseClazz);
    }

    private String sendPost(String url, String payload) throws Exception {
        log.info("[URL] : {}, [REQUEST] : {}", url, payload);
        RequestBody formBody = RequestBody.create(JSON, payload);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(formBody)
                .build();
        Response response = null;
        StringBuilder result = new StringBuilder("");
        try  {
            response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            result.append(response.body().string());
            log.info(result.toString());
        } catch (Exception ex) {
            log.error("[EXCEPTION] : {}", ex);
        }
        return result.toString();
    }

    public String sendGET(String url, String queryParams) throws IOException {
        Request request = new Request.Builder()
                .url(url+queryParams)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .build();

        log.info("Request : {}", request);
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
            return response.body().string();
        } catch (Exception ex) {
            log.error("[EXCEPTION]: {}", ex);
        }
        return null;
    }

    private RequestBody getRequestBody(Object clazz) throws IllegalAccessException {
        FormBody.Builder builder = new FormBody.Builder();
        Class<?> objClass = clazz.getClass();
        Field[] fields = objClass.getFields();
        for(Field field : fields) {
            String name = field.getName();
            Object value = field.get(clazz);
            System.out.println(name + ": " + value.toString());
            builder.add(name, (String) value);
        }
        RequestBody body = builder.build();
        return builder.build();
    }

}
