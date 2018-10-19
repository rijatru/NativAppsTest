package com.rijatru.development.nativappstest.presentation.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.rijatru.development.nativappstest.AppImplementation;
import com.rijatru.development.nativappstest.dependencyInjection.component.DaggerViewsComponent;
import com.rijatru.development.nativappstest.dependencyInjection.component.ViewsComponent;
import com.rijatru.development.nativappstest.logic.Bus;
import com.rijatru.development.nativappstest.logic.managers.interfaces.ApiManager;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ViewModelsFactory;

import javax.inject.Inject;

public abstract class BaseAppView extends FrameLayout {

    private ViewsComponent component;
    protected Context context;

    @Inject
    ViewModelsFactory viewModelFactory;

    @Inject
    Bus bus;

    public BaseAppView(Context context) {
        super(context);
        init();
    }

    public BaseAppView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseAppView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void init() {
        component = DaggerViewsComponent.builder().appComponent(AppImplementation.getApp().getAppComponent()).build();
        component.inject(this);
    }

    protected ViewsComponent getComponent() {
        return component;
    }
}
