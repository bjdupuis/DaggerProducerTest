package com.inin.daggerproducertest.di;


import com.google.common.util.concurrent.ListenableFuture;
import com.inin.daggerproducertest.data.CompositeSessionInfo;

import dagger.producers.ProductionComponent;

@ForSession
@ProductionComponent(modules = SessionModule.class, dependencies = AsyncDependencyCreatorComponent.class)
public interface SessionComponent {
    ListenableFuture<CompositeSessionInfo> getCompositeSessionInfoFuture();
}
