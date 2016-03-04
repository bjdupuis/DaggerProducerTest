package com.inin.daggerproducertest.di;


import com.google.common.util.concurrent.ListenableFuture;
import com.inin.daggerproducertest.data.SessionProvisionModule;
import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.CommonPrecursorAsyncDependency;
import com.inin.daggerproducertest.service.SomeAsyncDependency;

import dagger.producers.ProductionComponent;

@ForSession
@ProductionComponent(modules = {SessionAcquisitionModule.class, AsyncDependencyCreatorModule.class, CommonPrecursorCreatorModule.class}, dependencies = ApplicationComponent.class)
public interface SessionAcquisitionComponent {
    ListenableFuture<CommonPrecursorAsyncDependency> getCommonPrecursorAsyncDependencyFuture();

    ListenableFuture<SomeAsyncDependency> getSomeAsyncDependencyFuture();

    ListenableFuture<AnotherAsyncDependency> getAnotherAsyncDependencyFuture();

    ListenableFuture<SessionProvisionModule> getCompositeSessionInfoFuture();
}
