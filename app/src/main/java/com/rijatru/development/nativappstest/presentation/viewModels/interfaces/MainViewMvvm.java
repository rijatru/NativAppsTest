package com.rijatru.development.nativappstest.presentation.viewModels.interfaces;

import com.rijatru.development.nativappstest.presentation.views.interfaces.AppView;

public interface MainViewMvvm {

    interface View extends AppView {

    }

    interface ViewModel {

        String getSearchQuery();

        void setSearchQuery(String value);
    }
}
