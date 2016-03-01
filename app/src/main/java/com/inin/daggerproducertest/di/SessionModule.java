package com.inin.daggerproducertest.di;

import com.google.common.util.concurrent.ListenableFuture;
import com.inin.daggerproducertest.data.CompositeSessionInfo;
import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.CommonPrecursorAsyncDependency;
import com.inin.daggerproducertest.service.SomeAsyncDependency;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;

@ProducerModule
public class SessionModule {

    @Produces
    @ForSession
    public ListenableFuture<CommonPrecursorAsyncDependency> produceCommonPrecursorAsyncDependency(CommonPrecursorAsyncDependencyCreator injector) {
        return injector.getAsyncDependencyCreatorListenableFuture();
    }

    @Produces
    @ForSession
    public ListenableFuture<SomeAsyncDependency> produceSomeAsyncDependency(SomeAsyncDependencyCreator injector) {
        return injector.getAsyncDependencyCreatorListenableFuture();
    }

    @Produces
    @ForSession
    public ListenableFuture<AnotherAsyncDependency> produceAnotherAsyncDependency(AnotherAsyncDependencyCreator injector) {
        return injector.getAsyncDependencyCreatorListenableFuture();
    }

    @Produces
    @ForSession
    public CompositeSessionInfo produceCompositeSessionInfo(SomeAsyncDependency someAsyncDependency, AnotherAsyncDependency anotherAsyncDependency) {
        return new CompositeSessionInfo(someAsyncDependency, anotherAsyncDependency);
    }
}
