package com.rijatru.development.nativappstest.data.api.model.search.get;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchMoviesResponse {

    @SerializedName("Search")
    @Expose
    public List<Search> search = null;
    @SerializedName("totalResults")
    @Expose
    public String totalResults;
    @SerializedName("Response")
    @Expose
    public String response;
}
