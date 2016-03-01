package com.inin.daggerproducertest.di;

import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.CommonPrecursorAsyncDependency;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * This creator simulates a dependency creator for a dependency that requires work to create, then
 * it can satisfy dependencies on it. An example is a LocalizationManager that loads localized
 * string from a network or cache, then produces a LocalizationProvider capable of handing back
 * localized strings.
 */
public class AnotherAsyncDependencyCreator extends AsyncDependencyCreator<AnotherAsyncDependency> {
    private final CommonPrecursorAsyncDependency commonPrecursorAsyncDependency;
    AnotherAsyncDependency anotherAsyncDependency = null;

    public AnotherAsyncDependencyCreator(CommonPrecursorAsyncDependency commonPrecursorAsyncDependency) {
        this.commonPrecursorAsyncDependency = commonPrecursorAsyncDependency;
        performLongAndTediousCreation();
    }

    private void performLongAndTediousCreation() {
        if (anotherAsyncDependency != null) {
            getAsyncDependencySatisfier().satisfyDependency(anotherAsyncDependency);
            return;
        }
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(new Random().nextInt(7)));
            } catch (InterruptedException e) {
                // don't care
            }

            anotherAsyncDependency = new AnotherAsyncDependency(commonPrecursorAsyncDependency);
            getAsyncDependencySatisfier().satisfyDependency(anotherAsyncDependency);
        });
        thread.start();
    }
}
