package com.inin.daggerproducertest;

import android.app.Application;
import android.content.SharedPreferences;

import com.inin.daggerproducertest.data.SessionProvisionModule;
import com.inin.daggerproducertest.di.ApplicationComponent;
import com.inin.daggerproducertest.di.ApplicationModule;
import com.inin.daggerproducertest.di.DaggerApplicationComponent;
import com.inin.daggerproducertest.di.DaggerSessionProductionComponent;
import com.inin.daggerproducertest.di.DaggerSessionProvisionComponent;
import com.inin.daggerproducertest.di.SessionProductionComponent;
import com.inin.daggerproducertest.di.SessionProductionModule;
import com.inin.daggerproducertest.di.SessionProvisionComponent;

import java.util.concurrent.Executors;

import javax.inject.Inject;

public class App extends Application {
    @Inject
    SharedPreferences sharedPreferences;
    private ApplicationComponent component;
    private SessionProductionComponent sessionProductionComponent = null;
    private SessionProvisionComponent sessionProvisionComponent = null;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
    }

    public ApplicationComponent getComponent() {
        return component;
    }

    public SessionProvisionComponent createSessionProvisionComponent(SessionProvisionModule module) {
        sessionProvisionComponent = component.plus(DaggerSessionProvisionComponent.builder()
                .applicationComponent(getComponent())
                .sessionProvisionModule(module)
                .build());
        return sessionProvisionComponent;
    }

    public SessionProvisionComponent getSessionProvisionComponent() {
        return sessionProvisionComponent;
    }

    public SessionProductionComponent getSessionProductionComponent() {
        return sessionProductionComponent;
    }

    public SessionProductionComponent createSessionProductionComponent(SessionProductionModule sessionProductionModule) {
        sessionProductionComponent = component.plus(DaggerSessionProductionComponent.builder()
                .applicationComponent(getComponent())
                .sessionProductionModule(sessionProductionModule)
                .executor(Executors.newSingleThreadExecutor())
                .build());
        return sessionProductionComponent;
    }

    public void releaseSessionComponents() {
        sessionProductionComponent = null;
        sessionProvisionComponent = null;
    }

    private void buildComponentAndInject() {
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        component.inject(this);
    }
}
