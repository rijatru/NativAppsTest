package com.rijatru.development.nativappstest.dependencyInjection.component;

import com.rijatru.development.nativappstest.dependencyInjection.scope.AppScope;
import com.rijatru.development.nativappstest.presentation.views.AutoCompleteFormTextField;
import com.rijatru.development.nativappstest.presentation.views.BaseAppView;

import dagger.Component;

@AppScope
@Component(dependencies = AppComponent.class)
public interface ViewsComponent extends AppComponent {

    void inject(BaseAppView baseAppView);

    void inject(AutoCompleteFormTextField autoCompleteFormTextField);
}
