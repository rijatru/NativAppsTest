package com.rijatru.development.nativappstest.presentation.viewModels.factories;

import com.rijatru.development.nativappstest.App;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ViewsFactory;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppListView;
import com.rijatru.development.nativappstest.presentation.views.interfaces.ItemView;

public class AppViewsFactory implements ViewsFactory {

    private App app;

    public AppViewsFactory(App app) {
        this.app = app;
    }

    @Override
    public ItemView getItemView(int type, AppListView parentView) {
        return null;
    }

    @Override
    public Class getActivityClass(int type) {
        return null;
    }
}
