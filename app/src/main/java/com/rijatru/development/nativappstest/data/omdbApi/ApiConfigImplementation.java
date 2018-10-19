package com.rijatru.development.nativappstest.data.omdbApi;

import com.rijatru.development.nativappstest.App;

public class ApiConfigImplementation implements ApiConfig {

    private App app;

    public ApiConfigImplementation(App app) {
        this.app = app;
    }

    @Override
    public String getOmdbApiUrl() {
        return null;
    }
}
