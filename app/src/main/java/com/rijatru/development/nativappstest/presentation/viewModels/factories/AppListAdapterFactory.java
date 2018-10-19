package com.rijatru.development.nativappstest.presentation.viewModels.factories;

import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ListAdapterFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ViewsFactory;
import com.rijatru.development.nativappstest.presentation.views.adapters.AppBaseListAdapter;
import com.rijatru.development.nativappstest.presentation.views.adapters.ListAdapter;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppListView;

public class AppListAdapterFactory implements ListAdapterFactory {

    private ViewsFactory viewFactory;

    public AppListAdapterFactory(ViewsFactory viewFactory) {
        this.viewFactory = viewFactory;
    }

    @Override
    public ListAdapter getListAdapter(AppListView view) {
        return new AppBaseListAdapter(viewFactory, view);
    }
}
