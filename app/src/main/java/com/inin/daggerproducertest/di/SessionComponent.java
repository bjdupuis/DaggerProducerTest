package com.inin.daggerproducertest.di;


import com.google.common.util.concurrent.ListenableFuture;
import com.inin.daggerproducertest.data.CompositeSessionInfo;
import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.SomeAsyncDependency;
import com.inin.daggerproducertest.ui.MainActivity;

import dagger.producers.ProductionComponent;

@ForSession
@ProductionComponent(modules = {SessionModule.class, AsyncDependencyCreatorModule.class}, dependencies = ApplicationComponent.class)
public interface SessionComponent {
    ListenableFuture<SomeAsyncDependency> getSomeAsyncDependencyFuture();

    ListenableFuture<AnotherAsyncDependency> getAnotherAsyncDependencyFuture();

    ListenableFuture<CompositeSessionInfo> getCompositeSessionInfoFuture();

    void inject(MainActivity mainActivity);
}
