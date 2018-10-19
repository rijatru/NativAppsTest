package com.rijatru.development.nativappstest.logic;

import android.os.Bundle;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus implements Bus {

    private Subject<Bundle> publishSubject = PublishSubject.create();

    @Override
    public void publish(Bundle bundle) {
        publishSubject.onNext(bundle);
    }

    @Override
    public Observable<Bundle> getObservable() {
        return publishSubject;
    }
}
