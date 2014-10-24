package com.tempos21.cieguitos.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.estimote.sdk.Region;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.bean.PlaceInfo;

import java.util.HashMap;
import java.util.Map;


public class PhoneMainActivity extends LocationBeaconsActivity implements GoogleApiClient.ConnectionCallbacks, ResultCallback<DataApi.DataItemResult>, View.OnClickListener, DataApi.DataListener {

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

	private void sendData() {
		button.setEnabled(false);
		PutDataMapRequest dataMap = PutDataMapRequest.create("/count");
		dataMap.getDataMap().putInt(COUNT_KEY, 300);
		PutDataRequest request = dataMap.asPutDataRequest();
		PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi
				.putDataItem(mGoogleApiClient, request);
		pendingResult.setResultCallback(this);
	}

	private void configPlayServices() {
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addApi(Wearable.API)
				.build();
		mGoogleApiClient.connect();
	}

	@Override
	public void onConnected(Bundle bundle) {
		Wearable.DataApi.addListener(mGoogleApiClient, this);
		sendData();
	}

	@Override
	public void onConnectionSuspended(int i) {

	}

	@Override
	public void onResult(DataApi.DataItemResult dataItemResult) {
		if (dataItemResult.getStatus().isSuccess()) {
			Toast.makeText(PhoneMainActivity.this, "Data item set: " + dataItemResult.getDataItem().getUri(), Toast.LENGTH_SHORT).show();
		}
		button.setEnabled(true);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.sendButton:
				sendData();
				break;
		}
	}

	@Override
	public void onDataChanged(DataEventBuffer dataEvents) {
		for (DataEvent event : dataEvents) {
			if (event.getType() == DataEvent.TYPE_DELETED) {

			} else if (event.getType() == DataEvent.TYPE_CHANGED) {
				statusWear.setText(event.getDataItem().getUri().toString());
			}
		}
	}
}
