package com.rijatru.development.nativappstest.logic.managers;

import com.rijatru.development.nativappstest.logic.managers.interfaces.ConnectivityStatusManager;

public class AppConnectivityManager implements ConnectivityStatusManager {

    private boolean isConnected;

    public AppConnectivityManager() {

    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    @Override
    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }
}
