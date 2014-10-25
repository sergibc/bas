package com.tempos21.bas.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tempos21.bas.R;
import com.tempos21.bas.ui.fragment.ListaPlantasFragment;

/**
 * Created by Bernat on 25/10/2014.
 */
public class ListaPlantasActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);


		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.content, new ListaPlantasFragment());
		ft.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		return super.onOptionsItemSelected(item);
	}
}
