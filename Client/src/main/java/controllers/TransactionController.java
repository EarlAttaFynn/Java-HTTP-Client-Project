package controllers;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TransactionController {
    private String rootURL = "http://zipcode.rocks:8085";
    private OkHttpClient client;
    private MediaType mediaType;

    public TransactionController() throws IOException {
        this.client = new OkHttpClient().newBuilder()
//                .connectTimeout(5, TimeUnit.MINUTES)
//                .readTimeout(5, TimeUnit.MINUTES)
//                .writeTimeout(5, TimeUnit.MINUTES)
                .build();

        this.mediaType = MediaType.parse("application/json");
    }

    public String get(String path) throws IOException {
        Request request = new Request.Builder()
                .url(rootURL + path)
                .method("GET", null)
                //.addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String post(String urlExtension, String jpayload) throws IOException {
        RequestBody body = RequestBody.create(mediaType, jpayload);
        Request request = new Request.Builder()
                .url(rootURL + urlExtension)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String put(String urlExtension, String jpayload) throws IOException {
        RequestBody body = RequestBody.create(mediaType, jpayload);
        Request request = new Request.Builder()
                .url(rootURL + urlExtension)
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
