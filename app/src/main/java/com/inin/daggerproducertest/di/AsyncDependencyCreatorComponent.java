package com.inin.daggerproducertest.di;

import dagger.Component;

@ForSession
@Component(modules = AsyncDependencyCreatorModule.class)
public interface AsyncDependencyCreatorComponent {

    AnotherAsyncDependencyCreator provideAnotherAsyncDependencyCreator();

    SomeAsyncDependencyCreator provideSomeAsyncDependencyCreator();
}
