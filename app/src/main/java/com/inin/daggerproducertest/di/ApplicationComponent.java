package com.inin.daggerproducertest.di;

import android.app.Application;
import android.content.SharedPreferences;

import com.inin.daggerproducertest.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(App app);

    SessionProductionComponent plus(SessionProductionComponent sessionProductionComponent);

    SessionProvisionComponent plus(SessionProvisionComponent component);

    SharedPreferences sharedPreferences();

    Application application();
}
