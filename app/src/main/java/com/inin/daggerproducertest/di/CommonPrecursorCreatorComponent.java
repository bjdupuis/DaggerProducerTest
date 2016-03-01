package com.inin.daggerproducertest.di;

import dagger.Component;

@ForSession
@Component(modules = CommonPrecursorCreatorModule.class)
public interface CommonPrecursorCreatorComponent {
    CommonPrecursorAsyncDependencyCreator provideCommonPrecursorAsyncDependencyCreator();
}
