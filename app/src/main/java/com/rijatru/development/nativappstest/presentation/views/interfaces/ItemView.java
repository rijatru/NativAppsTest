package com.rijatru.development.nativappstest.presentation.views.interfaces;

public interface ItemView {

    void bind(Item item, int position);

    void setParentView(AppListView parentView);
}
