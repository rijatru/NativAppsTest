package com.rijatru.development.nativappstest.dependencyInjection.module;

import com.rijatru.development.nativappstest.App;
import com.rijatru.development.nativappstest.data.omdbApi.ApiConfig;
import com.rijatru.development.nativappstest.data.omdbApi.ApiConfigImplementation;
import com.rijatru.development.nativappstest.data.serialization.GsonManager;
import com.rijatru.development.nativappstest.data.serialization.Serializer;
import com.rijatru.development.nativappstest.logic.Bus;
import com.rijatru.development.nativappstest.logic.RxBus;
import com.rijatru.development.nativappstest.logic.managers.AppApiManager;
import com.rijatru.development.nativappstest.logic.managers.AppConnectivityManager;
import com.rijatru.development.nativappstest.logic.managers.RetroFitManager;
import com.rijatru.development.nativappstest.logic.managers.interfaces.ApiManager;
import com.rijatru.development.nativappstest.logic.managers.interfaces.ConnectivityStatusManager;
import com.rijatru.development.nativappstest.logic.managers.interfaces.HttpManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Singleton
    @Provides
    public Bus bus() {
        return new RxBus();
    }

    @Singleton
    @Provides
    public HttpManager httpManager(App app, ApiConfig apiConfig) {
        return new RetroFitManager(app, apiConfig);
    }

    @Singleton
    @Provides
    public ApiConfig apiConfig(App app) {
        return new ApiConfigImplementation(app);
    }

    @Singleton
    @Provides
    public ApiManager apiManager(App app, Bus bus, HttpManager httpManager, ApiConfig apiConfig, Serializer serializationManager, ConnectivityStatusManager connectivityStatusManager) {
        return new AppApiManager(app, bus, httpManager, apiConfig, serializationManager, connectivityStatusManager);
    }

    @Singleton
    @Provides
    public Serializer serializationManager() {
        return new GsonManager<>();
    }

    @Singleton
    @Provides
    public ConnectivityStatusManager connectivityManager() {
        return new AppConnectivityManager();
    }
}
