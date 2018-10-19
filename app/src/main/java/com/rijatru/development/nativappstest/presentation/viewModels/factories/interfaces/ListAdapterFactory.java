package com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces;

import com.rijatru.development.nativappstest.presentation.views.adapters.ListAdapter;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppListView;

public interface ListAdapterFactory {

    ListAdapter getListAdapter(AppListView view);
}
