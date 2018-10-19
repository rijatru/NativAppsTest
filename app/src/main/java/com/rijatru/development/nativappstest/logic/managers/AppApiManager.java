package com.rijatru.development.nativappstest.logic.managers;

import com.rijatru.development.nativappstest.App;
import com.rijatru.development.nativappstest.data.api.OmdbApi;
import com.rijatru.development.nativappstest.data.omdbApi.ApiConfig;
import com.rijatru.development.nativappstest.data.serialization.Serializer;
import com.rijatru.development.nativappstest.logic.Bus;
import com.rijatru.development.nativappstest.logic.managers.interfaces.ApiManager;
import com.rijatru.development.nativappstest.logic.managers.interfaces.ConnectivityStatusManager;
import com.rijatru.development.nativappstest.logic.managers.interfaces.HttpManager;

public class AppApiManager implements ApiManager {

    private final HttpManager httpManager;
    private final ApiConfig apiConfig;
    private final Serializer serializationManager;
    private final ConnectivityStatusManager connectivityStatusManager;

    private OmdbApi omdbApi;

    public AppApiManager(App app, Bus bus, HttpManager httpManager, ApiConfig apiConfig, Serializer serializer, ConnectivityStatusManager connectivityStatusManager) {
        this.httpManager = httpManager;
        this.apiConfig = apiConfig;
        this.serializationManager = serializer;
        this.connectivityStatusManager = connectivityStatusManager;
        initApi();
    }

    @Override
    public void initApi() {
        this.omdbApi = httpManager.getOmdbRetrofit().create(OmdbApi.class);
    }
}
