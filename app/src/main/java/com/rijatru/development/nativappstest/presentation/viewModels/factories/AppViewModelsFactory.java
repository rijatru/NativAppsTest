package com.rijatru.development.nativappstest.presentation.viewModels.factories;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;

import com.rijatru.development.nativappstest.logic.Bus;
import com.rijatru.development.nativappstest.presentation.viewModels.AutoCompleteFormTextFieldViewModel;
import com.rijatru.development.nativappstest.presentation.viewModels.BaseViewModel;
import com.rijatru.development.nativappstest.presentation.viewModels.MainViewViewModel;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ViewModelsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.AppListItemViewModel;
import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.MainViewMvvm;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppView;
import com.rijatru.development.nativappstest.presentation.views.interfaces.Item;

import javax.inject.Inject;

public class AppViewModelsFactory implements ViewModelsFactory {

    public static final int AUTO_COMPLETE_FORM_TEXT_FIELD_VIEW_MODEL = 100;
    public static final int MAIN_VIEW_VIEW_MODEL = 200;

    private final Bus bus;

    @Inject
    public AppViewModelsFactory(Bus bus) {
        this.bus = bus;
    }

    @Override
    public BaseViewModel getViewModel(AppView view, int type) {
        BaseViewModel viewModel;
        switch (type) {
            case AUTO_COMPLETE_FORM_TEXT_FIELD_VIEW_MODEL:
                viewModel = new AutoCompleteFormTextFieldViewModel(view);
                break;
            case MAIN_VIEW_VIEW_MODEL:
                viewModel = ViewModelProviders.of((AppCompatActivity) view).get(MainViewViewModel.class);
                ((MainViewMvvm.ViewModel) viewModel).setView((MainViewMvvm.View) view);
                break;
            default:
                viewModel = null;
        }
        return viewModel;
    }

    @Override
    public AppListItemViewModel getItemViewModel(AppView view, int type, Item item) {
        return null;
    }
}
