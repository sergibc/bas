package com.tempos21.cieguitos.ui.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.ui.fragment.ListaExposFragment;

/**
 * Created by Bernat on 25/10/2014.
 */
public class ListaExposActivity  extends ActionBarActivity {

	public static final String PLANTA = "PLANTA";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);


		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		Fragment f = new ListaExposFragment();
		f.setArguments(getIntent().getExtras());

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.content, f);
		ft.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		return super.onOptionsItemSelected(item);
	}
}
