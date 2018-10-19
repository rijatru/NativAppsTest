package com.rijatru.development.nativappstest;

import android.app.Application;

import com.rijatru.development.nativappstest.dependencyInjection.component.AppComponent;
import com.rijatru.development.nativappstest.dependencyInjection.component.DaggerAppComponent;
import com.rijatru.development.nativappstest.dependencyInjection.module.AppModule;
import com.rijatru.development.nativappstest.dependencyInjection.module.DataModule;
import com.rijatru.development.nativappstest.dependencyInjection.module.ViewsModule;
import com.squareup.leakcanary.LeakCanary;

import io.reactivex.disposables.CompositeDisposable;

public class AppImplementation extends Application implements App {

    private static App app;
    private AppComponent appComponent;

    protected CompositeDisposable disposables;

    @Override
    public void onCreate() {
        super.onCreate();

        if (app == null) {
            app = this;
        }

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }

        LeakCanary.install(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .viewsModule(new ViewsModule())
                .build();

        disposables = new CompositeDisposable();
    }

    public static App getApp() {
        return app;
    }

    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
