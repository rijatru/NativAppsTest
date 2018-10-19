package com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces;

import com.rijatru.development.nativappstest.presentation.views.interfaces.AppListView;
import com.rijatru.development.nativappstest.presentation.views.interfaces.ItemView;

public interface ViewsFactory {

    ItemView getItemView(int type, AppListView parentView);

    Class getActivityClass(int type);
}
