package com.inin.daggerproducertest.di;

import dagger.producers.ProductionComponent;

@ForSession
@ProductionComponent(modules = AsyncDependencyCreatorModule.class)
public interface AsyncDependencyCreatorComponent {

}
