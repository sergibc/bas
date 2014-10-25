package com.tempos21.cieguitos.ui.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.ui.fragment.ListaObrasFragment;
import com.tempos21.cieguitos.ui.fragment.MyTicketsListFragment;

/**
 * Created by Bernat on 25/10/2014.
 */
public class ListaObrasActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple);


		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.content, new ListaObrasFragment());
		ft.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		finish();
		return super.onOptionsItemSelected(item);
	}
}