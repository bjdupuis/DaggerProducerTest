package com.inin.daggerproducertest.di;

import com.inin.daggerproducertest.service.SomeAsyncDependency;

/**
 * This creator simulates a dependency creator for a dependency that has a long set up time
 * before it's ready to be used. The use-case I'm thinking of is a component that relies internally
 * on a bound Service. Until the Service is bound the dependency is not complete.
 */
public class SomeAsyncDependencyCreator extends AsyncDependencyCreator<SomeAsyncDependency> {
    public SomeAsyncDependencyCreator() {
        SomeAsyncDependency someAsyncDependency = new SomeAsyncDependency(getAsyncDependencySatisfier());
    }
}
