package com.tempos21.cieguitos.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;

import com.tempos21.cieguitos.R;

public class WearLaunchActivity extends Activity implements WatchViewStub.OnLayoutInflatedListener, View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
		stub.setOnLayoutInflatedListener(this);
	}

	@Override
	public void onLayoutInflated(WatchViewStub watchViewStub) {
		watchViewStub.findViewById(R.id.qr).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, WearWelcomeActivity.class);
		startActivity(intent);
		finish();
	}
}
