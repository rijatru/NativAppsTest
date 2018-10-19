package com.rijatru.development.nativappstest.dependencyInjection.module;

import com.rijatru.development.nativappstest.logic.Bus;
import com.rijatru.development.nativappstest.logic.RxBus;

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
}
