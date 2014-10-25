package com.tempos21.bas.ui.fragment;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.sergibc.sdk.data.MuseumData;
import com.example.sergibc.sdk.data.Planta;
import com.tempos21.bas.ui.activity.ListaExposActivity;
import com.tempos21.bas.ui.adapter.PlantasSimpleRectangularAdapter;

import java.util.List;

/**
 * Created by Bernat on 25/10/2014.
 */
public class ListaPlantasFragment extends ListFragment {

	private PlantasSimpleRectangularAdapter adapter;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		getListView().setDivider(null);
		List<Planta> items = MuseumData.getInstance().getPlantas();
		adapter = new PlantasSimpleRectangularAdapter(getActivity(), items);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		adapter.setItemSelected(position);

		Intent intent = new Intent(getActivity(), ListaExposActivity.class);
		intent.putExtra(ListaExposActivity.PLANTA, position);
		startActivity(intent);
	}
}
