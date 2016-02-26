package com.inin.daggerproducertest.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.inin.daggerproducertest.R;
import com.inin.daggerproducertest.data.CompositeSessionInfo;
import com.inin.daggerproducertest.di.AsyncDependencyCreatorModule;
import com.inin.daggerproducertest.di.DaggerSessionComponent;
import com.inin.daggerproducertest.di.SessionComponent;
import com.inin.daggerproducertest.di.SessionModule;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast toast = Toast.makeText(this, "Fetching session info...", Toast.LENGTH_SHORT);
        toast.show();

        SessionComponent sessionComponent = DaggerSessionComponent.builder()
                .sessionModule(new SessionModule())
                .asyncDependencyCreatorComponent(new AsyncDependencyCreatorModule())
                .executor(Executors.newSingleThreadExecutor())
                .build();

        Futures.addCallback(sessionComponent.getCompositeSessionInfoFuture(),
                new FutureCallback<CompositeSessionInfo>() {
                    @Override
                    public void onSuccess(CompositeSessionInfo result) {
                        handler.post(() -> {
                            Toast toast = Toast.makeText(MainActivity.this, "Got composite session info", Toast.LENGTH_LONG);
                            toast.show();
                        });
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });

    }
}
