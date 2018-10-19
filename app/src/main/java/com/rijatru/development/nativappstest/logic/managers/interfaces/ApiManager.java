package com.rijatru.development.nativappstest.logic.managers.interfaces;

import com.rijatru.development.nativappstest.data.api.model.search.get.SearchMoviesResponse;

import io.reactivex.Observable;

public interface ApiManager {

    void initApi();

    void searchMovies(String searchQuery);

    Observable<SearchMoviesResponse> getSearchMoviesObservable();
}
