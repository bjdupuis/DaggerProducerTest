package com.inin.daggerproducertest.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.inin.daggerproducertest.App;
import com.inin.daggerproducertest.R;
import com.inin.daggerproducertest.data.SessionProvisionModule;
import com.inin.daggerproducertest.di.ForSession;
import com.inin.daggerproducertest.di.SessionProductionComponent;
import com.inin.daggerproducertest.di.SessionProductionModule;
import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.CommonPrecursorAsyncDependency;
import com.inin.daggerproducertest.service.SomeAsyncDependency;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.precursor_status)
    TextView precursorStatus;
    @Bind(R.id.some_status)
    TextView someStatus;
    @Bind(R.id.another_status)
    TextView anotherStatus;
    @Bind(R.id.composite_status)
    TextView compositeStatus;
    // primarily doing this just to prove we can still inject from the @Singleton scope
    @Singleton
    @Inject
    SharedPreferences sharedPreferences;

    @ForSession
    @Inject
    SomeAsyncDependency someAsyncDependency;
    @ForSession
    @Inject
    AnotherAsyncDependency anotherAsyncDependency;

    private Handler handler = new Handler();
    private SessionProductionComponent sessionProductionComponent;

    @OnClick(R.id.reset_button)
    public void onResetButtonClicked(View v) {
        App app = (App) getApplication();
        app.releaseSessionComponents();
        acquireDependencies();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        acquireDependencies();
    }

    private void acquireDependencies() {
        someStatus.setText("Awaiting...");
        someStatus.setTextColor(Color.GRAY);
        anotherStatus.setText("Awaiting...");
        anotherStatus.setTextColor(Color.GRAY);
        compositeStatus.setText("Awaiting...");
        compositeStatus.setTextColor(Color.GRAY);
        precursorStatus.setText("Awaiting...");
        precursorStatus.setTextColor(Color.GRAY);

        App app = (App) getApplication();
        sessionProductionComponent = app.getSessionProductionComponent();
        if (app.getSessionProductionComponent() == null) {
            sessionProductionComponent = app.createSessionProductionComponent(new SessionProductionModule());
        }

        Futures.addCallback(sessionProductionComponent.getCommonPrecursorAsyncDependencyFuture(), new FutureCallback<CommonPrecursorAsyncDependency>() {
            @Override
            public void onSuccess(CommonPrecursorAsyncDependency result) {
                handler.post(() -> {
                    precursorStatus.setText("Acquired");
                    precursorStatus.setTextColor(Color.GREEN);
                });
            }

            @Override
            public void onFailure(Throwable t) {
                // empty
            }
        });

        Futures.addCallback(sessionProductionComponent.getSomeAsyncDependencyFuture(), new FutureCallback<SomeAsyncDependency>() {
            @Override
            public void onSuccess(SomeAsyncDependency result) {
                handler.post(() -> {
                    someStatus.setText("Acquired");
                    someStatus.setTextColor(Color.GREEN);
                });
            }

            @Override
            public void onFailure(Throwable t) {
                // empty
            }
        });

        Futures.addCallback(sessionProductionComponent.getAnotherAsyncDependencyFuture(), new FutureCallback<AnotherAsyncDependency>() {
            @Override
            public void onSuccess(AnotherAsyncDependency result) {
                handler.post(() -> {
                    anotherStatus.setText("Acquired");
                    anotherStatus.setTextColor(Color.GREEN);
                });
            }

            @Override
            public void onFailure(Throwable t) {
                // empty
            }
        });

        Futures.addCallback(sessionProductionComponent.getSessionProvisionModuleFuture(),
                new FutureCallback<SessionProvisionModule>() {
                    @Override
                    public void onSuccess(SessionProvisionModule result) {
                        handler.post(() -> {
                            compositeStatus.setText("Acquired");
                            compositeStatus.setTextColor(Color.GREEN);

                            app.getSessionProvisionComponent().inject(MainActivity.this);
                            if (someAsyncDependency == null || anotherAsyncDependency == null) {
                                compositeStatus.setText("No dependencies injected");
                                compositeStatus.setTextColor(Color.RED);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

}
