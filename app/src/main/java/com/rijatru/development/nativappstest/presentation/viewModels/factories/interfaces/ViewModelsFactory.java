package com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces;

import com.rijatru.development.nativappstest.presentation.viewModels.BaseViewModel;
import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.AppListItemViewModel;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppView;
import com.rijatru.development.nativappstest.presentation.views.interfaces.Item;

public interface ViewModelsFactory {

    BaseViewModel getViewModel(AppView view, int type);

    AppListItemViewModel getItemViewModel(AppView view, int type, Item item);
}
