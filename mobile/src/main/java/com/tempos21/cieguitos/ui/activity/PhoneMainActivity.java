package com.tempos21.cieguitos.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Region;
import com.example.sergibc.sdk.constants.Constants;
import com.example.sergibc.sdk.task.SendMessageThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.bean.PlaceInfo;

import java.util.HashMap;
import java.util.Map;

public class PhoneMainActivity extends LocationBeaconsActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener,
        MessageApi.MessageListener {

    private static final String COUNT_KEY = "COUNT_KEY";
    private GoogleApiClient mGoogleApiClient;
    private TextView statusWear;
    private View button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        configPlayServices();
    }

    @Override
    protected Map<Region, PlaceInfo> createRegions() {
        Map<Region, PlaceInfo> map = new HashMap<Region, PlaceInfo>();
        map.put(new Region("BLUE", "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 1), new PlaceInfo("BLUE"));
        map.put(new Region("PINK", "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 2), new PlaceInfo("PINK"));
        map.put(new Region("GREEN", "B9407F30-F5F8-466E-AFF9-25556B57FE6D", 1000, 3), new PlaceInfo("GREEN"));
        return map;
    }

    @Override
    protected void onRegionEntered(PlaceInfo placeInfo) {
        Toast.makeText(this, placeInfo.getText(), Toast.LENGTH_SHORT).show();
    }

    private void findViews() {
        statusWear = (TextView) findViewById(R.id.statusWear);
        button = findViewById(R.id.sendButton);
        button.setOnClickListener(this);
    }

    private void configPlayServices() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Wearable.MessageApi.addListener(mGoogleApiClient, this);
        Toast.makeText(this, "Connected!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendButton:
                sendMessage();
                break;
        }
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

        if (Constants.BAS_WEAR_PATH.equals(messageEvent.getPath())) {
            final String message = new String(messageEvent.getData());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                Toast.makeText(getApplicationContext(), "onMessageReceived", Toast.LENGTH_SHORT).show();
                    statusWear.setText(message);
                }

            });
        }
    }

    private void sendMessage() {
//        new SendMessageTask().execute();
        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_PHONE_PATH, "fromPhone");
        thread.start();
    }
}
