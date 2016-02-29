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
import com.inin.daggerproducertest.data.CompositeSessionInfo;
import com.inin.daggerproducertest.di.SessionComponent;
import com.inin.daggerproducertest.di.SessionModule;
import com.inin.daggerproducertest.service.AnotherAsyncDependency;
import com.inin.daggerproducertest.service.SomeAsyncDependency;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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
    private Handler handler = new Handler();
    private SessionComponent sessionComponent;

    @OnClick(R.id.reset_button)
    public void onResetButtonClicked(View v) {
        App app = (App) getApplication();
        app.releaseSessionComponent();
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

        App app = (App) getApplication();
        sessionComponent = app.getSessionComponent();
        if (app.getSessionComponent() == null) {
            sessionComponent = app.createSessionComponent(new SessionModule());
        }
        sessionComponent.inject(this);

        Futures.addCallback(sessionComponent.getSomeAsyncDependencyFuture(), new FutureCallback<SomeAsyncDependency>() {
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

        Futures.addCallback(sessionComponent.getAnotherAsyncDependencyFuture(), new FutureCallback<AnotherAsyncDependency>() {
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

        Futures.addCallback(sessionComponent.getCompositeSessionInfoFuture(),
                new FutureCallback<CompositeSessionInfo>() {
                    @Override
                    public void onSuccess(CompositeSessionInfo result) {
                        handler.post(() -> {
                            compositeStatus.setText("Acquired");
                            compositeStatus.setTextColor(Color.GREEN);
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
    }

}
