package com.tempos21.cieguitos.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;

import com.estimote.sdk.Region;
import com.tempos21.cieguitos.BuildConfig;
import com.tempos21.cieguitos.bean.PlaceInfo;
import com.tempos21.cieguitos.service.LocationBeaconsService;

import java.util.Map;

/**
 * Created by Bernat on 24/10/2014.
 */
public abstract class LocationBeaconsActivity extends ActionBarActivity {

    private Map<Region, PlaceInfo> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        map = createRegions();

        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getExtras() != null && intent.getExtras().containsKey(LocationBeaconsService.REGION_FOUND)) {
                    Region region = intent.getParcelableExtra(LocationBeaconsService.REGION_FOUND);
                    if (region != null && map != null) {
                        PlaceInfo placeInfo = map.get(region);
                        onRegionEntered(placeInfo);
                    }
                }
            }
        }, new IntentFilter(BuildConfig.APPLICATION_ID + ".BEACON_DISCOVERED"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        startService(new Intent(this, LocationBeaconsService.class));
    }

    protected abstract Map<Region, PlaceInfo> createRegions();

    protected abstract void onRegionEntered(PlaceInfo placeInfo);

    @Override
    protected void onStop() {
        stopService(new Intent(this, LocationBeaconsService.class));
        super.onStop();
    }
}
