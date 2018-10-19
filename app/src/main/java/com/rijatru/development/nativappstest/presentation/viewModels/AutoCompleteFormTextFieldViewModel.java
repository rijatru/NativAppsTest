package com.rijatru.development.nativappstest.presentation.viewModels;

import com.rijatru.development.nativappstest.data.api.model.search.get.Search;
import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.AutoCompleteFormTextFieldMvvm;
import com.rijatru.development.nativappstest.presentation.views.adapters.CustomAutoCompleteAdapter;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class AutoCompleteFormTextFieldViewModel extends BaseViewModel implements AutoCompleteFormTextFieldMvvm.ViewModel {

    private AutoCompleteFormTextFieldMvvm.View view;
    private String value;
    private String hint;
    private CustomAutoCompleteAdapter autoCompleteAdapter;

    public AutoCompleteFormTextFieldViewModel(AppView appView) {
        super(appView);
        this.view = (AutoCompleteFormTextFieldMvvm.View) appView;
        getComponent().inject(this);
        initAutoCompleteAdapter();
    }

    private void initAutoCompleteAdapter() {
        autoCompleteAdapter = new CustomAutoCompleteAdapter(view.getContext(), android.R.layout.select_dialog_item, new String[]{});
        view.setAutoCompleteAdapter(autoCompleteAdapter);
        subscribeToObservables();
    }

    private void subscribeToObservables() {
        disposables.add(apiManager.getSearchMoviesObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(searchMoviesResponse -> {
                    if (searchMoviesResponse != null && searchMoviesResponse.search != null) {
                        setAutoCompleteData(searchMoviesResponse.search);
                    }
                }).subscribe());
    }

    public void setAutoCompleteData(List<Search> data) {
        autoCompleteAdapter.setData(data);
        view.filter();
    }

    @Override
    public String getValue() {
        if (value == null) {
            value = "";
        }
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getHint() {
        return hint;
    }

    @Override
    public void setHint(String hint) {
        this.hint = hint;
    }

    @Override
    public void searchMovies(String searchQuery) {
        apiManager.searchMovies(searchQuery);
    }

    @Override
    public Search getItemAt(int position) {
        return autoCompleteAdapter.getObjectAt(position);
    }
}
