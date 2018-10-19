package com.rijatru.development.nativappstest.data.api;

import com.rijatru.development.nativappstest.data.api.model.search.get.SearchMoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OmdbApi {

    @GET("{url}&type=movie")
    Observable<SearchMoviesResponse> searchMovies(@Path(value = "url", encoded = true) String url, @Query("apikey") String apikey, @Query("s") String searchQuery);
}
