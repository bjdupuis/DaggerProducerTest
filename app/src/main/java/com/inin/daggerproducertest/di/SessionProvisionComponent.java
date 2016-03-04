package com.inin.daggerproducertest.di;


import com.inin.daggerproducertest.data.SessionProvisionModule;
import com.inin.daggerproducertest.ui.MainActivity;

import dagger.Component;

@ForSession
@Component(modules = SessionProvisionModule.class, dependencies = ApplicationComponent.class)
public interface SessionProvisionComponent {
    void inject(MainActivity mainActivity);
}
