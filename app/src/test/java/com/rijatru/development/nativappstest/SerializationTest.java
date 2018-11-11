package com.rijatru.development.nativappstest;

import com.rijatru.development.nativappstest.data.api.model.search.get.SearchMoviesResponse;
import com.rijatru.development.nativappstest.data.serialization.GsonManager;
import com.rijatru.development.nativappstest.data.serialization.Serializer;

import org.junit.Assert;
import org.junit.Test;

public class SerializationTest {

    @Test
    public void serializeInvalidString() {
        Serializer serializer = new GsonManager<>();
        SearchMoviesResponse searchMoviesResponse = (SearchMoviesResponse) serializer.fromJson("{", SearchMoviesResponse.class);
        Assert.assertNull(searchMoviesResponse);
    }

    @Test
    public void serializeEmptyString() {
        Serializer serializer = new GsonManager<>();
        SearchMoviesResponse searchMoviesResponse = (SearchMoviesResponse) serializer.fromJson("", SearchMoviesResponse.class);
        Assert.assertNull(searchMoviesResponse);
    }

    @Test
    public void serializeValidString() {
        Serializer serializer = new GsonManager<>();

        String response = "{\n" +
                "\t\"Search\": [],\n" +
                "\t\"totalResults\": \"2\",\n" +
                "\t\"Response\": \"200\"\n" +
                "}";

        SearchMoviesResponse searchMoviesResponse = (SearchMoviesResponse) serializer.fromJson(response, SearchMoviesResponse.class);
        Assert.assertNotNull(searchMoviesResponse);
    }
}
