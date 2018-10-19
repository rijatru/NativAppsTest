package com.rijatru.development.nativappstest;

import android.content.Context;

import com.rijatru.development.nativappstest.dependencyInjection.component.AppComponent;

public interface App {

    AppComponent getAppComponent();

    Context getApplicationContext();

    String getString(int resourceId);
}
