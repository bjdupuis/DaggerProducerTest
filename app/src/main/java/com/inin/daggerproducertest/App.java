package com.inin.daggerproducertest;

import android.app.Application;
import android.content.SharedPreferences;

import com.inin.daggerproducertest.di.ApplicationComponent;
import com.inin.daggerproducertest.di.ApplicationModule;
import com.inin.daggerproducertest.di.DaggerApplicationComponent;

import javax.inject.Inject;

public class App extends Application {
    @Inject
    SharedPreferences sharedPreferences;
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    private void buildComponentAndInject() {
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        component.inject(this);
    }
}
