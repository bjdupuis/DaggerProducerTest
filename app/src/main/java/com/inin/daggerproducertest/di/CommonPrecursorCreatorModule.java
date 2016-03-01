package com.inin.daggerproducertest.di;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonPrecursorCreatorModule implements CommonPrecursorCreatorComponent {
    @Provides
    @ForSession
    @Override
    public CommonPrecursorAsyncDependencyCreator provideCommonPrecursorAsyncDependencyCreator() {
        return new CommonPrecursorAsyncDependencyCreator();
    }

}
