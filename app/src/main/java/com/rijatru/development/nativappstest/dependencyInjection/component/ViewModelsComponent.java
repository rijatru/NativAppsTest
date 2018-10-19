package com.rijatru.development.nativappstest.dependencyInjection.component;

import com.rijatru.development.nativappstest.dependencyInjection.scope.AppScope;
import com.rijatru.development.nativappstest.presentation.viewModels.BaseViewModel;

import dagger.Component;

@AppScope
@Component(dependencies = AppComponent.class)
public interface ViewModelsComponent extends AppComponent {

    void inject(BaseViewModel baseViewModel);
}
