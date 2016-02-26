package com.inin.daggerproducertest.di;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModule.class)
public class AsyncDependencyCreatorModule implements AsyncDependencyCreatorComponent {

    @Provides
    @ForSession
    @Override
    public AnotherAsyncDependencyCreator provideAnotherAsyncDependencyCreator() {
        return new AnotherAsyncDependencyCreator();
    }

    @Provides
    @ForSession
    @Override
    public SomeAsyncDependencyCreator provideSomeAsyncDependencyCreator() {
        return new SomeAsyncDependencyCreator();
    }
}
