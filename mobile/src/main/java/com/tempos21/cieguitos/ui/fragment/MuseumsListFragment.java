package com.tempos21.cieguitos.ui.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.sergibc.sdk.data.MuseumData;
import com.tempos21.cieguitos.ui.adapter.MuseumsAdapter;

/**
 * Created by Bernat on 25/10/2014.
 */
public class MuseumsListFragment extends ListFragment {

	private MuseumsAdapter museumsAdapter;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getListView().setDivider(null);

		museumsAdapter = new MuseumsAdapter(getActivity(), MuseumData.getInstance().getMuseos());
		setListAdapter(museumsAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		museumsAdapter.setSelectedItem(position);
	}
}
