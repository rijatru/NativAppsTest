package com.rijatru.development.nativappstest.dependencyInjection.module;

import com.rijatru.development.nativappstest.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public App app() {
        return app;
    }
}
