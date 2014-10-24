package com.tempos21.cieguitos.ui.activity;

import android.app.Activity;
import android.os.Bundle;
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

public class WearMainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, WatchViewStub.OnLayoutInflatedListener, GoogleApiClient.OnConnectionFailedListener, DataApi.DataListener, View.OnClickListener, ResultCallback<DataApi.DataItemResult>, MessageApi.MessageListener {

	private static final String COUNT_KEY = "COUNT_KEY";
	private TextView mTextView;
	private GoogleApiClient mGoogleApiClient;
	private boolean mResolvingError;
	private View wearButton;

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
		findViewsFromStub(watchViewStub);
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (!mResolvingError) {
			mGoogleApiClient.connect();
		}
	}

	private void findViewsFromStub(WatchViewStub stub) {
		mTextView = (TextView) stub.findViewById(R.id.text);
		wearButton = findViewById(R.id.wearButton);
		wearButton.setOnClickListener(this);
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
				mTextView.setText(event.getDataItem().getUri().toString());
			}
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.wearButton:
				sendData();
				break;
		}
	}

	private void sendData() {
		wearButton.setEnabled(false);
		PutDataMapRequest dataMap = PutDataMapRequest.create("/patata");
		dataMap.getDataMap().putInt(COUNT_KEY, 100);
		PutDataRequest request = dataMap.asPutDataRequest();
		PendingResult<DataApi.DataItemResult> pendingResult = Wearable.DataApi
				.putDataItem(mGoogleApiClient, request);
		pendingResult.setResultCallback(this);
	}

	@Override
	public void onResult(DataApi.DataItemResult dataItemResult) {
		wearButton.setEnabled(true);
		if (dataItemResult.getStatus().isSuccess()) {
			Toast.makeText(WearMainActivity.this, "Data item set: " + dataItemResult.getDataItem().getUri(), Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void onMessageReceived(MessageEvent messageEvent) {

	}
}
