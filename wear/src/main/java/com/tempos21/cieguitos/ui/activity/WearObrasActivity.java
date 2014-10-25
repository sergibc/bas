package com.tempos21.cieguitos.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Toast;

import com.example.sergibc.sdk.constants.Constants;
import com.example.sergibc.sdk.data.MuseumDataTransfer;
import com.example.sergibc.sdk.task.SendMessageThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;
import com.google.gson.Gson;
import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.ui.adapter.ObrasGridPagerAdapter;

/**
 * Created by Bernat on 25/10/2014.
 */
public class WearObrasActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        WatchViewStub.OnLayoutInflatedListener,
        GoogleApiClient.OnConnectionFailedListener,
        MessageApi.MessageListener,
        GridViewPager.OnPageChangeListener {

    private static final String PLANTA = "PLANTA";
    private static final String EXPO = "EXPO";

    private GoogleApiClient mGoogleApiClient;
    private boolean mResolvingError;
    private WatchViewStub watchViewStub;
    private GridViewPager grid;
    private int planta;
    private int expo;

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
        setData();
    }

    private void findViewsFromStub(WatchViewStub stub) {
        grid = (GridViewPager) stub.findViewById(R.id.grid);
        grid.setOnPageChangeListener(this);
    }

    private void setData() {
        if (getIntent() != null) {
            if (grid != null) {

                planta = getIntent().getIntExtra(PLANTA, 0);
                expo = getIntent().getIntExtra(EXPO, 0);

                GridPagerAdapter adapter = new ObrasGridPagerAdapter(this, planta, expo, getFragmentManager());
                grid.setAdapter(adapter);
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!mResolvingError) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Wearable.MessageApi.addListener(mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Toast.makeText(this, "onConnectionSuspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {

//        if (Constants.BAS_PHONE_PATH.equals(messageEvent.getPath())) {
//            final String message = new String(messageEvent.getData());
//            runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(WearObrasActivity.this, message, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }

        if (Constants.BAS_PHONE_FLOOR_PATH.equals(messageEvent.getPath())) {
            String eBaconInfo = new String(messageEvent.getData());
            Intent intent = new Intent(this, WearMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(Constants.EBACON_PARAM, eBaconInfo);
            startActivity(intent);
            finish();
        } else if (Constants.BAS_PHONE_COLLECTION_PATH.equals(messageEvent.getPath())) {
            String eBaconInfo = new String(messageEvent.getData());
            Intent intent = new Intent(this, WearMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(Constants.EBACON_PARAM, eBaconInfo);
            startActivity(intent);
            finish();
        }
    }

    private void sendCurrentScreenMessage(String message) {
//        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_WEAR_PATH, message);
        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_WEAR_OBRA_PATH, message);
        thread.start();
    }

    private void sendActionPlayerMessage(String message) {
        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_ACTION_PLAYER_PATH, message);
        thread.start();
    }

    @Override
    public void onPageScrolled(int i, int i2, float v, float v2, int i3, int i4) {
        Gson gson = new Gson();
        MuseumDataTransfer dataTransfer = new MuseumDataTransfer();
        dataTransfer.setPlanta(planta);
        dataTransfer.setExpo(expo);
        dataTransfer.setObra(i);
        sendCurrentScreenMessage(gson.toJson(dataTransfer));
    }

    @Override
    public void onPageSelected(int i, int i2) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void onScreenClicked(final View view) {
        sendActionPlayerMessage(Constants.FILE_ERMITA); // TODO send correct file name
    }

}