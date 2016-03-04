package com.inin.daggerproducertest.di;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.common.util.concurrent.ListenableFuture;
import com.inin.daggerproducertest.App;
import com.inin.daggerproducertest.data.SessionProvisionModule;
import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.CommonPrecursorAsyncDependency;
import com.inin.daggerproducertest.service.SomeAsyncDependency;

import dagger.producers.ProducerModule;
import dagger.producers.Produces;

@ProducerModule
public class SessionProductionModule {

    @Produces
    @ForSession
    public ListenableFuture<CommonPrecursorAsyncDependency> produceCommonPrecursorAsyncDependency(CommonPrecursorAsyncDependencyCreator injector) {
        return injector.getAsyncDependencyCreatorListenableFuture();
    }

    @Produces
    @ForSession
    public ListenableFuture<SomeAsyncDependency> produceSomeAsyncDependency(SharedPreferences sharedPreferences, SomeAsyncDependencyCreator injector) {
        return injector.getAsyncDependencyCreatorListenableFuture();
    }

    @Produces
    @ForSession
    public ListenableFuture<AnotherAsyncDependency> produceAnotherAsyncDependency(AnotherAsyncDependencyCreator injector) {
        return injector.getAsyncDependencyCreatorListenableFuture();
    }

    @Produces
    @ForSession
    public SessionProvisionModule produceSessionProvisionModule(Application app, SomeAsyncDependency someAsyncDependency, AnotherAsyncDependency anotherAsyncDependency) {
        SessionProvisionModule module = new SessionProvisionModule(someAsyncDependency, anotherAsyncDependency);
        ((App) app).createSessionProvisionComponent(module);
        return module;
    }
}
