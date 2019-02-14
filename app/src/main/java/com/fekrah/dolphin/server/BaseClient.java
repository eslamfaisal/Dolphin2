package com.fekrah.dolphin.server;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseClient {
    private static Retrofit retrofit = null;

    private static OkHttpClient buildClient() {
        return new OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    public static Retrofit getBaseClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(buildClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://dolphin.feckrah.com/")
                    .build();
        }
        return retrofit;
    }

    public static Apis getApi(){
        return (Apis) BaseClient.getBaseClient().create(Apis.class);
    }
}
