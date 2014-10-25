package com.tempos21.cieguitos.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
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
import com.tempos21.cieguitos.ui.adapter.MuseumGridPagerAdapter;

public class WearMainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        WatchViewStub.OnLayoutInflatedListener,
        GoogleApiClient.OnConnectionFailedListener,
        MessageApi.MessageListener,
        GridViewPager.OnPageChangeListener {

    private static final String TAG = WearMainActivity.class.getSimpleName();

    private GoogleApiClient mGoogleApiClient;
    private boolean mResolvingError;
    private WatchViewStub watchViewStub;
    private GridViewPager grid;

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
        if (grid != null) {
            GridPagerAdapter adapter = new MuseumGridPagerAdapter(this, getFragmentManager());
            grid.setAdapter(adapter);
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

        if (Constants.BAS_PHONE_PATH.equals(messageEvent.getPath())) {
            final String message = new String(messageEvent.getData());
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(WearMainActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void sendMessage(String message) {
        SendMessageThread thread = new SendMessageThread(mGoogleApiClient, Constants.BAS_WEAR_PATH, message);
        thread.start();
    }

    @Override
    public void onPageScrolled(int i, int i2, float v, float v2, int i3, int i4) {
        Gson gson = new Gson();
        MuseumDataTransfer dataTransfer = new MuseumDataTransfer();
        dataTransfer.setPlanta(i);
        dataTransfer.setExpo(i);
        sendMessage(gson.toJson(dataTransfer));
    }

    @Override
    public void onPageSelected(int i, int i2) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void onScreenClicked(final View view) {
        Log.i(TAG, "onScreenClicked");
        Intent intent = new Intent(this, WearObrasActivity.class);
        startActivity(intent);

    }
}
