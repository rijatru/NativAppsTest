package com.rijatru.development.nativappstest.presentation.viewModels.interfaces;

import android.arch.lifecycle.LiveData;

import com.rijatru.development.nativappstest.data.api.model.search.get.Search;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppView;

public interface MainViewMvvm {

    interface View extends AppView {

    }

    interface ViewModel {

        void setView(View view);

        void setSearch(Search search);

        LiveData<Search> getSearch();
    }
}
