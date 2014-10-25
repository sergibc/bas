package com.tempos21.bas.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by Bernat on 25/10/2014.
 */
public class PlantaFragment extends Fragment {

	private static final String ARG_ROW = "ARG_ROW";

	public static PlantaFragment newInstance(int row) {
		PlantaFragment f = new PlantaFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_ROW, row);

		f.setArguments(args);

		return f;
	}

//	@Nullable
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		super.onCreateView(inflater, container, savedInstanceState);
//		return inflater.inflate(R.layout.row_planta, container, false);
//	}
//
//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//
//		if (getArguments() != null) {
//			TextView text = (TextView) view.findViewById(R.id.text);
//			int row = getArguments().getInt(ARG_ROW, 0);
//			text.setText("P" + row);
//		}
//	}
}
