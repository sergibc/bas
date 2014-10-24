package com.tempos21.cieguitos.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sergibc.sdk.constants.Constants;
import com.example.sergibc.sdk.task.SendMessageThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.tempos21.cieguitos.R;

public class WearMainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        WatchViewStub.OnLayoutInflatedListener,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,
        MessageApi.MessageListener {

    private static final String COUNT_KEY = "COUNT_KEY";
    private TextView mTextView;
    private GoogleApiClient mGoogleApiClient;
    private boolean mResolvingError;
    private View wearButton;
    private WatchViewStub watchViewStub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
        stub.setOnLayoutInflatedListener(this);
    }

    @Override
    public void onLayoutInflated(WatchViewStub watchViewStub) {
        this.watchViewStub = watchViewStub;
        findViewsFromStub(watchViewStub);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mResolvingError) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }


    private void findViewsFromStub(WatchViewStub stub) {
        mTextView = (TextView) stub.findViewById(R.id.text);
        wearButton = findViewById(R.id.wearButton);
        wearButton.setOnClickListener(this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        Wearable.MessageApi.addListener(mGoogleApiClient, this);
        Toast.makeText(this, "Connected!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wearButton:
                sendMessage();
                break;
        }
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        if (Constants.BAS_PHONE_PATH.equals(messageEvent.getPath())) {
            final String message = new String(messageEvent.getData());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                Toast.makeText(getApplicationContext(), "onMessageReceived", Toast.LENGTH_SHORT).show();
                    mTextView.setText(message);
                }
            });
        }
    }

    private void sendMessage() {
//        new SendMessageTask().execute();
        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_WEAR_PATH, "fromWear");
        thread.start();
    }

}
