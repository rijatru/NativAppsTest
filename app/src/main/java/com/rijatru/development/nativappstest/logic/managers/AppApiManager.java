package com.rijatru.development.nativappstest.logic.managers;

import android.text.TextUtils;

import com.rijatru.development.nativappstest.App;
import com.rijatru.development.nativappstest.R;
import com.rijatru.development.nativappstest.data.api.OmdbApi;
import com.rijatru.development.nativappstest.data.api.model.search.get.SearchMoviesResponse;
import com.rijatru.development.nativappstest.data.omdbApi.ApiConfig;
import com.rijatru.development.nativappstest.data.serialization.Serializer;
import com.rijatru.development.nativappstest.logic.Bus;
import com.rijatru.development.nativappstest.logic.managers.interfaces.ApiManager;
import com.rijatru.development.nativappstest.logic.managers.interfaces.ConnectivityStatusManager;
import com.rijatru.development.nativappstest.logic.managers.interfaces.HttpManager;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class AppApiManager implements ApiManager {

    private final App app;
    private final HttpManager httpManager;
    private final ApiConfig apiConfig;
    private final Serializer serializationManager;
    private final ConnectivityStatusManager connectivityStatusManager;
    private final Subject<SearchMoviesResponse> searchMoviesResponseSubject = PublishSubject.create();

    private CompositeDisposable moviesSearchDisposable;

    private OmdbApi omdbApi;
    private String omdbApiKey;

    public AppApiManager(App app, Bus bus, HttpManager httpManager, ApiConfig apiConfig, Serializer serializer, ConnectivityStatusManager connectivityStatusManager) {
        this.app = app;
        this.httpManager = httpManager;
        this.apiConfig = apiConfig;
        this.serializationManager = serializer;
        this.connectivityStatusManager = connectivityStatusManager;
        initApi();
        this.moviesSearchDisposable = new CompositeDisposable();
    }

    @Override
    public void initApi() {
        this.omdbApi = httpManager.getOmdbRetrofit().create(OmdbApi.class);
        this.omdbApiKey = app.getString(R.string.omdb_api_key);
    }

    @Override
    public void searchMovies(String searchQuery) {
        if (!TextUtils.isEmpty(searchQuery)) {
            moviesSearchDisposable.clear();
            moviesSearchDisposable.add(Observable.just(searchQuery)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(onSearchQuery -> omdbApi.searchMovies(omdbApiKey, onSearchQuery)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.io())
                            .subscribe(searchMoviesResponseSubject::onNext)));
        }
    }

    @Override
    public Observable<SearchMoviesResponse> getSearchMoviesObservable() {
        return searchMoviesResponseSubject;
    }
}
