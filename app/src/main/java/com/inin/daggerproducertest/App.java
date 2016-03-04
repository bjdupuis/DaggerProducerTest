package com.inin.daggerproducertest;

import android.app.Application;
import android.content.SharedPreferences;

import com.inin.daggerproducertest.data.SessionProvisionModule;
import com.inin.daggerproducertest.di.ApplicationComponent;
import com.inin.daggerproducertest.di.ApplicationModule;
import com.inin.daggerproducertest.di.DaggerApplicationComponent;
import com.inin.daggerproducertest.di.DaggerSessionAcquisitionComponent;
import com.inin.daggerproducertest.di.DaggerSessionProvisionComponent;
import com.inin.daggerproducertest.di.SessionAcquisitionComponent;
import com.inin.daggerproducertest.di.SessionAcquisitionModule;
import com.inin.daggerproducertest.di.SessionProvisionComponent;

import java.util.concurrent.Executors;

import javax.inject.Inject;

public class App extends Application {
    @Inject
    SharedPreferences sharedPreferences;
    private ApplicationComponent component;
    private SessionAcquisitionComponent sessionAcquisitionComponent = null;
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

    public SessionAcquisitionComponent getSessionAcquisitionComponent() {
        return sessionAcquisitionComponent;
    }

    public SessionAcquisitionComponent createSessionAcquisitionComponent(SessionAcquisitionModule sessionAcquisitionModule) {
        sessionAcquisitionComponent = component.plus(DaggerSessionAcquisitionComponent.builder()
                .applicationComponent(getComponent())
                .sessionAcquisitionModule(sessionAcquisitionModule)
                .executor(Executors.newSingleThreadExecutor())
                .build());
        return sessionAcquisitionComponent;
    }

    public void releaseSessionComponent() {
        sessionAcquisitionComponent = null;
        sessionProvisionComponent = null;
    }

    private void buildComponentAndInject() {
        component = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        component.inject(this);
    }
}
