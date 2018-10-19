package com.rijatru.development.nativappstest.data.serialization;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class InternalIdModelExclusionStrategy implements ExclusionStrategy {

    public boolean shouldSkipClass(Class<?> arg0) {
        return false;
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(Exclude.class) != null;
    }
}
