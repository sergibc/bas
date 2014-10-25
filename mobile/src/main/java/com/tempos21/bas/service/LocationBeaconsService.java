package com.tempos21.bas.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.content.LocalBroadcastManager;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;
import com.tempos21.bas.BuildConfig;

import java.util.List;

/**
 * Created by Bernat on 24/10/2014.
 */
public class LocationBeaconsService extends Service implements BeaconManager.RangingListener, BeaconManager.ServiceReadyCallback {

	private static final Region REGION = new Region("", null, null, null);
	public static final String REGION_FOUND = "REGION_FOUND";

	private BeaconManager beaconManager;
	private String lastUUID;
	private int lastMajor;
	private int lastMinor;


	@Override
	public IBinder onBind(Intent intent) {
		start();
		return new Binder();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		start();
		return START_NOT_STICKY;
	}

	private void start() {
		beaconManager = new BeaconManager(this);
		if (beaconManager.checkPermissionsAndService()) {
			beaconManager.setBackgroundScanPeriod(10, 10000);
			beaconManager.setRangingListener(LocationBeaconsService.this);
			beaconManager.connect(LocationBeaconsService.this);
		}
	}

	private Beacon getNearestBeacon(List<Beacon> beacons) {
		Beacon beacon = null;

		for (Beacon item : beacons) {
			if (Utils.computeProximity(item) == Utils.Proximity.IMMEDIATE) {
				beacon = item;
			}
		}

		return beacon;
	}

	@Override
	public void onServiceReady() {
		try {
			beaconManager.startRanging(REGION);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
		if (beacons != null && beacons.size() > 0) {
			Beacon beacon = getNearestBeacon(beacons);
			if (beacon != null) {
				Intent intent = new Intent(BuildConfig.APPLICATION_ID + ".BEACON_DISCOVERED");
				lastUUID = beacon.getProximityUUID();
				lastMajor = beacon.getMajor();
				lastMinor = beacon.getMinor();
				// Toast.makeText(this, lastUUID + "\n" + lastMajor + "\n" + lastMinor + "\n" + Utils.computeProximity(beacon), Toast.LENGTH_SHORT).show();
				intent.putExtra(REGION_FOUND, new Region("", beacon.getProximityUUID(), beacon.getMajor(), beacon.getMinor()));
				LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
			}
		}
	}

	@Override
	public void onDestroy() {
		// Should be invoked in #onStop.
		try {
			beaconManager.stopRanging(REGION);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		beaconManager.disconnect();
		super.onDestroy();
	}
}
