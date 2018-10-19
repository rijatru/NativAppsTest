package com.rijatru.development.nativappstest.logic.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rijatru.development.nativappstest.App;
import com.rijatru.development.nativappstest.data.factories.NullOnEmptyConverterFactory;
import com.rijatru.development.nativappstest.data.omdbApi.ApiConfig;
import com.rijatru.development.nativappstest.data.serialization.InternalIdModelExclusionStrategy;
import com.rijatru.development.nativappstest.logic.managers.interfaces.HttpManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitManager implements HttpManager {

    private final App app;
    private final ApiConfig apiConfig;

    public RetroFitManager(App app, ApiConfig apiConfig) {
        this.app = app;
        this.apiConfig = apiConfig;
    }

    @Override
    public Retrofit getOmdbRetrofit() {
        Gson gson = new GsonBuilder()
                .addSerializationExclusionStrategy(new InternalIdModelExclusionStrategy())
                .serializeNulls()
                .create();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        return new Retrofit.Builder()
                .baseUrl(apiConfig.getOmdbApiUrl())
                .addConverterFactory(new NullOnEmptyConverterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
