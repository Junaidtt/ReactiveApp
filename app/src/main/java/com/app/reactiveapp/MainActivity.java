package com.app.reactiveapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Observer;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create Observable

        Observable<String> animalsObservable = getAnimalOservable();


        //initiate Observer

        io.reactivex.Observer<String> observer = getAnimalsObserver();

        //observer subscribing to observable

        animalsObservable
                .observeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread()).subscribe(observer);

    }

    private io.reactivex.Observer<String> getAnimalsObserver() {
        return new io.reactivex.Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    private Observable<String> getAnimalOservable() {
        return Observable.just("Ant","Lion","Elephent","Tiger");
    }


}
