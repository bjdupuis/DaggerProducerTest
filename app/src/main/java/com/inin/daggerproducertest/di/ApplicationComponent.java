package com.inin.daggerproducertest.di;

import android.content.SharedPreferences;

import com.inin.daggerproducertest.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);

    SessionComponent plus(SessionComponent sessionComponent);

    SharedPreferences sharedPreferences();
}
