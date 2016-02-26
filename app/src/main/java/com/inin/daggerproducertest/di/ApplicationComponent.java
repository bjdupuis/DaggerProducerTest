package com.inin.daggerproducertest.di;

import com.inin.daggerproducertest.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);
}
