package com.tempos21.cieguitos.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.tempos21.cieguitos.R;

public class WearMainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, WatchViewStub.OnLayoutInflatedListener, GoogleApiClient.OnConnectionFailedListener, DataApi.DataListener, View.OnClickListener, ResultCallback<DataApi.DataItemResult>, MessageApi.MessageListener, GridViewPager.OnPageChangeListener {

	private static final String COUNT_KEY = "COUNT_KEY";
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
			GridPagerAdapter adapter = new MuseumGridPagerAdapter(getFragmentManager());
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
	public void onConnectionSuspended(int i) {

	}

	@Override
	public void onConnected(Bundle connectionHint) {
		Wearable.DataApi.addListener(mGoogleApiClient, this);
		Wearable.MessageApi.addListener(mGoogleApiClient, this);
		Toast.makeText(this, "Hello!", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onStop() {
		if (null != mGoogleApiClient && mGoogleApiClient.isConnected()) {
			Wearable.DataApi.removeListener(mGoogleApiClient, this);
			mGoogleApiClient.disconnect();
		}
		super.onStop();
	}

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {

	}

	@Override
	public void onDataChanged(DataEventBuffer dataEvents) {
		for (final DataEvent event : dataEvents) {
			if (event.getType() == DataEvent.TYPE_DELETED) {

			} else if (event.getType() == DataEvent.TYPE_CHANGED) {

			}
		}
	}

	@Override
	public void onClick(View v) {

	}

	private void sendData() {
		PutDataMapRequest dataMap = PutDataMapRequest.create("/patata");
		dataMap.getDataMap().putInt(COUNT_KEY, 100);
		PutDataRequest request = dataMap.asPutDataRequest();
		PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi
				.putDataItem(mGoogleApiClient, request);
		pendingResult.setResultCallback(this);
	}

	@Override
	public void onResult(DataApi.DataItemResult dataItemResult) {
		if (dataItemResult.getStatus().isSuccess()) {
			Toast.makeText(WearMainActivity.this, "Data item set: " + dataItemResult.getDataItem().getUri(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onMessageReceived(MessageEvent messageEvent) {

	}

	@Override
	public void onPageScrolled(int i, int i2, float v, float v2, int i3, int i4) {

	}

	@Override
	public void onPageSelected(int i, int i2) {

	}

	@Override
	public void onPageScrollStateChanged(int i) {

	}
}
