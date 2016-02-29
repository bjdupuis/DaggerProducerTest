package com.inin.daggerproducertest;

import android.app.Application;
import android.content.SharedPreferences;

import com.inin.daggerproducertest.di.ApplicationComponent;
import com.inin.daggerproducertest.di.ApplicationModule;
import com.inin.daggerproducertest.di.DaggerApplicationComponent;
import com.inin.daggerproducertest.di.DaggerSessionComponent;
import com.inin.daggerproducertest.di.SessionComponent;
import com.inin.daggerproducertest.di.SessionModule;

import java.util.concurrent.Executors;

import javax.inject.Inject;

public class App extends Application {
    @Inject
    SharedPreferences sharedPreferences;
    private ApplicationComponent component;
    private SessionComponent sessionComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public SessionComponent getSessionComponent() {
        return sessionComponent;
    }

    public SessionComponent createSessionComponent(SessionModule sessionModule) {
        sessionComponent = component.plus(DaggerSessionComponent.builder()
                .applicationComponent(getComponent())
                .sessionModule(sessionModule)
                .executor(Executors.newSingleThreadExecutor())
                .build());
        return sessionComponent;
    }

    public void releaseSessionComponent() {
        sessionComponent = null;
    }

    private void buildComponentAndInject() {
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        component.inject(this);
    }
}
