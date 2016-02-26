package com.inin.daggerproducertest.di;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

public class AsyncDependencyCreator<T> {
    private final SettableFuture<T> asyncDependencyCreatorListenableFuture = SettableFuture.create();

    private void satisfyDependency(T dependency) {
        asyncDependencyCreatorListenableFuture.set(dependency);
    }

    public ListenableFuture<T> getAsyncDependencyCreatorListenableFuture() {
        return asyncDependencyCreatorListenableFuture;
    }

    protected AsyncDependencySatisfier<T> getAsyncDependencySatisfier() {
        return new AsyncDependencySatisfier<>(this);
    }

    public static class AsyncDependencySatisfier<T> {
        private final AsyncDependencyCreator<T> asyncDependencyCreator;

        public AsyncDependencySatisfier(AsyncDependencyCreator<T> asyncDependencyCreator) {
            this.asyncDependencyCreator = asyncDependencyCreator;
        }

        public void satisfyDependency(T dependency) {
            asyncDependencyCreator.satisfyDependency(dependency);
        }
    }
}
