package com.tempos21.cieguitos.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.tempos21.cieguitos.R;

public class WelcomeActivity extends Activity implements WatchViewStub.OnLayoutInflatedListener, View.OnClickListener {

	int step = 0;
	private ImageView image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
		stub.setOnLayoutInflatedListener(this);
	}

	@Override
	public void onLayoutInflated(WatchViewStub watchViewStub) {
		image = (ImageView) watchViewStub.findViewById(R.id.image);
		image.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (step++) {
			case 0:
				image.setImageResource(R.drawable.welcome_pause);
				break;
			case 1:
				image.setImageResource(R.drawable.welcome_stop);
				break;
			case 2:
				Intent intent = new Intent(this, WearMainActivity.class);
				startActivity(intent);
				finish();
				break;
		}
	}
}
