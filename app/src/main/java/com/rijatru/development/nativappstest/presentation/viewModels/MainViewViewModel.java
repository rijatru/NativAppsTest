package com.rijatru.development.nativappstest.presentation.viewModels;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rijatru.development.nativappstest.data.api.model.search.get.Search;
import com.rijatru.development.nativappstest.presentation.viewModels.interfaces.MainViewMvvm;

public class MainViewViewModel extends BaseViewModel implements MainViewMvvm.ViewModel {

    private MainViewMvvm.View view;
    private MutableLiveData<Search> search;

    public MainViewViewModel() {
        super();
    }

    @Override
    public void setView(MainViewMvvm.View view) {
        this.view = view;
        if (search == null) {
            search = new MutableLiveData<>();
        }
    }

    @Override
    public LiveData<Search> getSearch() {
        return search;
    }

    @Override
    public void setSearch(Search search) {
        this.search.setValue(search);
    }
}
