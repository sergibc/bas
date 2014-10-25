package com.tempos21.cieguitos.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;

import com.example.sergibc.sdk.data.Museo;
import com.example.sergibc.sdk.data.MuseumData;
import com.example.sergibc.sdk.data.Planta;
import com.example.sergibc.sdk.data.Planta0;
import com.example.sergibc.sdk.data.Planta1;
import com.example.sergibc.sdk.data.Planta2;
import com.tempos21.cieguitos.R;

import java.util.ArrayList;
import java.util.List;

public class WearLaunchActivity extends Activity implements WatchViewStub.OnLayoutInflatedListener, View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
		stub.setOnLayoutInflatedListener(this);
	}

	private void createData() {

		List<Planta> plantas = new ArrayList<Planta>();
		plantas.add(new Planta0());
		plantas.add(new Planta1());
		plantas.add(new Planta2());

		MuseumData.getInstance().setPlantas(plantas);
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
