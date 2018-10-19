package com.rijatru.development.nativappstest.data.api.model.search.get;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Search {

    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Year")
    @Expose
    public String year;
    @SerializedName("imdbID")
    @Expose
    public String imdbID;
    @SerializedName("Type")
    @Expose
    public String type;
    @SerializedName("Poster")
    @Expose
    public String poster;
}
