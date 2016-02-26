package com.inin.daggerproducertest.data;

import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.SomeAsyncDependency;

public class CompositeSessionInfo {
    private final SomeAsyncDependency someAsyncDependency;
    private final AnotherAsyncDependency anotherAsyncDependency;

    public CompositeSessionInfo(SomeAsyncDependency someAsyncDependency, AnotherAsyncDependency anotherAsyncDependency) {
        this.someAsyncDependency = someAsyncDependency;
        this.anotherAsyncDependency = anotherAsyncDependency;
    }

    public AnotherAsyncDependency getAnotherAsyncDependency() {
        return anotherAsyncDependency;
    }

    public SomeAsyncDependency getSomeAsyncDependency() {
        return someAsyncDependency;
    }
}
