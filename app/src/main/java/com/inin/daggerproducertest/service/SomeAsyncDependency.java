package com.inin.daggerproducertest.service;


import com.inin.daggerproducertest.di.AsyncDependencyCreator;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SomeAsyncDependency {
    private AsyncDependencyCreator.AsyncDependencySatisfier<SomeAsyncDependency> asyncDependencySatisfier;

    public SomeAsyncDependency(AsyncDependencyCreator.AsyncDependencySatisfier<SomeAsyncDependency> asyncDependencySatisfier) {
        this.asyncDependencySatisfier = asyncDependencySatisfier;
        performLongTediousInitialization();
    }

    private void performLongTediousInitialization() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(new Random().nextInt(5)));
            } catch (InterruptedException e) {
                // don't care
            }

            asyncDependencySatisfier.satisfyDependency(this);
        });
        thread.start();
    }
}
