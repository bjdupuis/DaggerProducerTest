package com.inin.daggerproducertest.data;

import com.inin.daggerproducertest.di.ForSession;
import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.SomeAsyncDependency;

import dagger.Module;
import dagger.Provides;

@Module
public class SessionProvisionModule {
    private final SomeAsyncDependency someAsyncDependency;
    private final AnotherAsyncDependency anotherAsyncDependency;

    public SessionProvisionModule(SomeAsyncDependency someAsyncDependency, AnotherAsyncDependency anotherAsyncDependency) {
        this.someAsyncDependency = someAsyncDependency;
        this.anotherAsyncDependency = anotherAsyncDependency;
    }

    @ForSession
    @Provides
    public AnotherAsyncDependency getAnotherAsyncDependency() {
        return anotherAsyncDependency;
    }

    @ForSession
    @Provides
    public SomeAsyncDependency getSomeAsyncDependency() {
        return someAsyncDependency;
    }
}
