package com.tempos21.cieguitos.ui.fragment;

/**
 * Created by Bernat on 25/10/2014.
 */

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.example.sergibc.sdk.data.Expo;
import com.example.sergibc.sdk.data.MuseumData;
import com.example.sergibc.sdk.data.Obra;
import com.tempos21.cieguitos.ui.activity.ListaExposActivity;
import com.tempos21.cieguitos.ui.activity.ListaObrasActivity;
import com.tempos21.cieguitos.ui.adapter.ExposSimpleRectangularAdapter;
import com.tempos21.cieguitos.ui.adapter.ObrasPlayAdapter;

import java.util.ArrayList;
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

