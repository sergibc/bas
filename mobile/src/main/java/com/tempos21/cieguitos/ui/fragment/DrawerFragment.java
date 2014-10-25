package com.tempos21.cieguitos.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.ui.activity.ListaTicketsActivity;
import com.tempos21.cieguitos.ui.activity.ProfileActivity;

/**
 * Created by Bernat on 25/10/2014.
 */
public class DrawerFragment extends Fragment implements View.OnClickListener {

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.drawer_layout, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		view.findViewById(R.id.miPerfil).setOnClickListener(this);
		view.findViewById(R.id.tickets).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tickets:
				Intent ticketsIntent = new Intent(getActivity(), ListaTicketsActivity.class);
				startActivity(ticketsIntent);
				break;
			case R.id.miPerfil:
				Intent intent = new Intent(getActivity(), ProfileActivity.class);
				startActivity(intent);
				break;
		}
	}
}
