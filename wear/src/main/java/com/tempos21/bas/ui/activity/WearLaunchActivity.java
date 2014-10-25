package com.tempos21.bas.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;

import com.tempos21.bas.sdk.sdk.data.MuseumData;
import com.tempos21.bas.sdk.sdk.data.Planta;
import com.tempos21.bas.sdk.sdk.data.Planta0;
import com.tempos21.bas.sdk.sdk.data.Planta1;
import com.tempos21.bas.sdk.sdk.data.Planta2;
import com.tempos21.bas.R;

import java.util.ArrayList;
import java.util.List;

public class WearLaunchActivity extends Activity implements WatchViewStub.OnLayoutInflatedListener, View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

		createData();

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
