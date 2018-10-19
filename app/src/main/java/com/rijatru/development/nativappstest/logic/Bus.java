package com.rijatru.development.nativappstest.logic;

import android.os.Bundle;

import io.reactivex.Observable;

public interface Bus {

    void publish(Bundle bundle);

    Observable<Bundle> getObservable();
}
