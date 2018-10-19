package com.rijatru.development.nativappstest.presentation.views.interfaces;

import com.rijatru.development.nativappstest.data.api.model.search.get.Search;

public interface SearchResultsListener {

    void onSearchResultClicked(Search search);

    void onSearch();
}
