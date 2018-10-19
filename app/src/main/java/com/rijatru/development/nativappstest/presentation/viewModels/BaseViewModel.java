package com.rijatru.development.nativappstest.presentation.viewModels;

import android.arch.lifecycle.ViewModel;

import com.rijatru.development.nativappstest.AppImplementation;
import com.rijatru.development.nativappstest.dependencyInjection.component.DaggerViewModelsComponent;
import com.rijatru.development.nativappstest.dependencyInjection.component.ViewModelsComponent;
import com.rijatru.development.nativappstest.logic.managers.interfaces.ApiManager;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    private final AppView view;
    protected CompositeDisposable disposables;
    private ViewModelsComponent component;

    @Inject
    ApiManager apiManager;

    public BaseViewModel() {
        this.view = null;
    }

    public BaseViewModel(AppView view) {
        this.view = view;

        component = DaggerViewModelsComponent.builder()
                .appComponent(AppImplementation.getApp().getAppComponent())
                .build();

        component.inject(this);

        disposables = new CompositeDisposable();
    }

    protected ViewModelsComponent getComponent() {
        return component;
    }

    public void clearSubscriptions() {
        disposables.clear();
    }
}
