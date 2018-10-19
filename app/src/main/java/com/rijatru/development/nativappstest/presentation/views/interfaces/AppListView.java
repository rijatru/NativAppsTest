package com.rijatru.development.nativappstest.presentation.views.interfaces;

import com.rijatru.development.nativappstest.presentation.views.adapters.ListAdapter;

public interface AppListView extends AppView {

    void onGotItems();

    void onListAdapterReady(ListAdapter adapter);

    void onGetItemsError();
}
