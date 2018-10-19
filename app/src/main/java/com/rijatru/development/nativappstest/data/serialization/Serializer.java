package com.rijatru.development.nativappstest.data.serialization;

public interface Serializer {

    String toJson(Object value);

    Object fromJson(String infoString, Class objectClass);
}
