package com.tempos21.cieguitos.ui.fragment;

/**
 * Created by Bernat on 25/10/2014.
 */

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.sergibc.sdk.data.Expo;
import com.example.sergibc.sdk.data.MuseumData;
import com.tempos21.cieguitos.ui.activity.ListaExposActivity;
import com.tempos21.cieguitos.ui.adapter.ExposSimpleRectangularAdapter;

import java.util.List;


/**
 * Created by Bernat on 25/10/2014.
 */
public class ListaExposFragment extends ListFragment {

	private ExposSimpleRectangularAdapter adapter;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		int planta = getArguments().getInt(ListaExposActivity.PLANTA, 0);
		getListView().setDivider(null);
		List<Expo> items = MuseumData.getInstance().getPlantas().get(planta).getExpos();
		adapter = new ExposSimpleRectangularAdapter(getActivity(), items);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		adapter.setItemSelected(position);

		Intent intent = new Intent(getActivity(), ListaExposActivity.class);
		intent.putExtra(ListaExposActivity.PLANTA, position);
	}
}

