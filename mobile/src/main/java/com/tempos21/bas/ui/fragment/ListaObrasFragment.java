package com.tempos21.bas.ui.fragment;

/**
 * Created by Bernat on 25/10/2014.
 */

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;

import com.example.sergibc.sdk.data.MuseumData;
import com.example.sergibc.sdk.data.Obra;
import com.tempos21.bas.ui.adapter.ObrasPlayAdapter;

import java.util.List;


/**
 * Created by Bernat on 25/10/2014.
 */
public class ListaObrasFragment extends ListFragment {


	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		getListView().setDivider(null);

		List<Obra> obras = MuseumData.getInstance().getPlantas().get(0).getExpos().get(0).getObras();

		ObrasPlayAdapter adapter = new ObrasPlayAdapter(getActivity(), obras);
		setListAdapter(adapter);
	}

}

