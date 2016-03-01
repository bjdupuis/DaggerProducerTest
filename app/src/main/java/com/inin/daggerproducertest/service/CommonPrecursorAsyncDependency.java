package com.inin.daggerproducertest.service;

import com.inin.daggerproducertest.di.AsyncDependencyCreator;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CommonPrecursorAsyncDependency {
    private final AsyncDependencyCreator.AsyncDependencySatisfier<CommonPrecursorAsyncDependency> satisfier;

    public CommonPrecursorAsyncDependency(AsyncDependencyCreator.AsyncDependencySatisfier<CommonPrecursorAsyncDependency> satisfier) {
        this.satisfier = satisfier;
        performLongTediousInitialization();
    }

    private void performLongTediousInitialization() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(new Random().nextInt(5)));
            } catch (InterruptedException e) {
                // don't care
            }

            satisfier.satisfyDependency(this);
        });
        thread.start();
    }

}
