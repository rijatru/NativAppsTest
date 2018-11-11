package com.rijatru.development.nativappstest.data.serialization;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.List;

public class GsonManager<T> implements Serializer {

    @Override
    public String toJson(Object object) {

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();

        return gson.toJson(object);
    }

    @Override
    public Object fromJson(String objectString, Class classType) {

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();

        Object object = null;

        try {
            object = gson.fromJson(objectString, classType);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage());
        }

        return object;
    }

    public Object fromJson(String objectString, Type classType) {

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();

        return gson.fromJson(objectString, classType);
    }

    public String getJsonArray(Object object, Type listOfTestObject) {

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();

        return gson.toJson(object, listOfTestObject);
    }

    public List<T> fromJsonArray(String string, Type listOfTestObject) {

        Gson gson = new GsonBuilder()
                .disableHtmlEscaping()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .serializeNulls()
                .create();

        return gson.fromJson(string, listOfTestObject);
    }
}
