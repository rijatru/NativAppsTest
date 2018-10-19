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
                    setAutoCompleteData(new String[0]);
                    if (searchMoviesResponse != null && searchMoviesResponse.search != null) {
                        List<String> titles = new ArrayList<>();
                        for (Search search : searchMoviesResponse.search) {
                            titles.add(search.title);
                        }
                        String[] autoCompleteData = new String[titles.size()];
                        autoCompleteData = titles.toArray(autoCompleteData);
                        setAutoCompleteData(autoCompleteData);
                    }
                }).subscribe());
    }

    public void setAutoCompleteData(String[] data) {
        autoCompleteAdapter.setData(data);
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
}
