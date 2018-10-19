package com.rijatru.development.nativappstest.dependencyInjection.module;

import com.rijatru.development.nativappstest.App;
import com.rijatru.development.nativappstest.logic.Bus;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.AppListAdapterFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.AppViewModelsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.AppViewsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ListAdapterFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ViewModelsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ViewsFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewsModule {

    @Singleton
    @Provides
    public ViewsFactory viewFactory(App app) {
        return new AppViewsFactory(app);
    }

    @Singleton
    @Provides
    public ListAdapterFactory listAdapterFactory(ViewsFactory viewFactory) {
        return new AppListAdapterFactory(viewFactory);
    }

    @Singleton
    @Provides
    public ViewModelsFactory viewModelFactory(Bus bus) {
        return new AppViewModelsFactory(bus);
    }
}
