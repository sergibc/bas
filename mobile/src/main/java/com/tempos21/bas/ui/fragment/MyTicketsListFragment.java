package com.tempos21.bas.ui.fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.tempos21.bas.sdk.sdk.data.MuseumData;

/**
 * Created by Bernat on 25/10/2014.
 */
public class MyTicketsListFragment extends ListFragment {

	private com.tempos21.bas.ui.adapter.MiTicketsAdapter miTicketsAdapter;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getListView().setDivider(null);

		miTicketsAdapter = new com.tempos21.bas.ui.adapter.MiTicketsAdapter(getActivity(), MuseumData.getInstance().getMuseos());
		setListAdapter(miTicketsAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		miTicketsAdapter.setSelectedItem(position);
	}
}
