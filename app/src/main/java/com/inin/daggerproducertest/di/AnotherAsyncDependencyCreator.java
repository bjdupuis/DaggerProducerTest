package com.inin.daggerproducertest.di;

import com.inin.daggerproducertest.service.AnotherAsyncDependency;

import java.util.concurrent.TimeUnit;

/**
 * This creator simulates a dependency creator for a dependency that requires work to create, then
 * it can satisfy dependencies on it. An example is a LocalizationManager that loads localized
 * string from a network or cache, then produces a LocalizationProvider capable of handing back
 * localized strings.
 */
public class AnotherAsyncDependencyCreator extends AsyncDependencyCreator<AnotherAsyncDependency> {
    public AnotherAsyncDependencyCreator() {
        performLongAndTediousCreation();
    }

    private void performLongAndTediousCreation() {
        AnotherAsyncDependency dependency = new AnotherAsyncDependency();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(7));
            } catch (InterruptedException e) {
                // don't care
            }

            getAsyncDependencySatisfier().satisfyDependency(dependency);
        });
        thread.start();
    }
}