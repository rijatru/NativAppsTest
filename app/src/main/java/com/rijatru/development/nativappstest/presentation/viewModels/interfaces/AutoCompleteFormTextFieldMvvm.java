package com.rijatru.development.nativappstest.presentation.viewModels.interfaces;

import android.widget.ArrayAdapter;

import com.rijatru.development.nativappstest.data.api.model.search.get.Search;
import com.rijatru.development.nativappstest.presentation.views.interfaces.AppView;

public interface AutoCompleteFormTextFieldMvvm {

    interface View extends AppView {

        String getValue();

        void setValue(String value);

        void setAutoCompleteAdapter(ArrayAdapter<String> adapter);

        void filter();
    }

    interface ViewModel {

        String getValue();

        void setValue(String value);

        String getHint();

        void setHint(String hint);

        void searchMovies(String searchQuery);

        Search getItemAt(int position);
    }
}
