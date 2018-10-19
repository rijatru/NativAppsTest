package com.rijatru.development.nativappstest.presentation.views;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.rijatru.development.nativappstest.AppImplementation;
import com.rijatru.development.nativappstest.R;
import com.rijatru.development.nativappstest.data.api.model.search.get.Search;
import com.rijatru.development.nativappstest.databinding.ActivityMainBinding;
import com.rijatru.development.nativappstest.dependencyInjection.component.DaggerViewsComponent;
import com.rijatru.development.nativappstest.dependencyInjection.component.ViewsComponent;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.AppViewModelsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.factories.interfaces.ViewModelsFactory;
import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.MainViewMvvm;
import com.rijatru.development.nativappstest.presentation.views.interfaces.SearchResultsListener;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainViewMvvm.View, SearchResultsListener {

    private ActivityMainBinding binding;
    private MainViewMvvm.ViewModel viewModel;

    @Inject
    ViewModelsFactory viewModelsFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewsComponent component = DaggerViewsComponent.builder().appComponent(AppImplementation.getApp().getAppComponent()).build();
        component.inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = (MainViewMvvm.ViewModel) viewModelsFactory.getViewModel(this, AppViewModelsFactory.MAIN_VIEW_VIEW_MODEL);
        viewModel.getSearch().observe(this, users -> binding.setViewModel(viewModel));

    }

    @Override
    public void onSearchResultClicked(Search search) {
        viewModel.setSearch(search);
    }

    @Override
    public void onSearch() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
