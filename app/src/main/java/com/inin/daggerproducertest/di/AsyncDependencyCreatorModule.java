package com.inin.daggerproducertest.di;

import com.inin.daggerproducertest.service.CommonPrecursorAsyncDependency;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;

@ProducerModule
public class AsyncDependencyCreatorModule implements AsyncDependencyCreatorComponent {

    @Produces
    @ForSession
    public AnotherAsyncDependencyCreator provideAnotherAsyncDependencyCreator(CommonPrecursorAsyncDependency commonPrecursorAsyncDependency) {
        return new AnotherAsyncDependencyCreator(commonPrecursorAsyncDependency);
    }

    @Produces
    @ForSession
    public SomeAsyncDependencyCreator provideSomeAsyncDependencyCreator(CommonPrecursorAsyncDependency commonPrecursorAsyncDependency) {
        return new SomeAsyncDependencyCreator(commonPrecursorAsyncDependency);
    }
}
