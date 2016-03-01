package com.inin.daggerproducertest.di;

import com.inin.daggerproducertest.service.CommonPrecursorAsyncDependency;

public class CommonPrecursorAsyncDependencyCreator extends AsyncDependencyCreator<CommonPrecursorAsyncDependency> {
    public CommonPrecursorAsyncDependencyCreator() {
        new CommonPrecursorAsyncDependency(getAsyncDependencySatisfier());
    }
}
