package com.inin.daggerproducertest.service;

public class AnotherAsyncDependency {
    private final CommonPrecursorAsyncDependency commonPrecursorAsyncDependency;

    public AnotherAsyncDependency(CommonPrecursorAsyncDependency commonPrecursorAsyncDependency) {
        this.commonPrecursorAsyncDependency = commonPrecursorAsyncDependency;
    }
}
