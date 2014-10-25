package com.tempos21.cieguitos.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.ImageReference;
import android.view.View;
import android.view.ViewGroup;

import com.example.sergibc.sdk.data.MuseumData;
import com.example.sergibc.sdk.data.Obra;
import com.tempos21.cieguitos.BuildConfig;
import com.tempos21.cieguitos.R;

/**
 * Created by Bernat on 25/10/2014.
 */
public class ObrasGridPagerAdapter extends FragmentGridPagerAdapter {

	private Context context;
	private int planta;
	private int expo;

	public ObrasGridPagerAdapter(Context context, int planta, int expo, FragmentManager fragmentManager) {
		super(fragmentManager);
		this.context = context;
		this.planta = planta;
		this.expo = expo;
	}

	@Override
	public Fragment getFragment(int row, int column) {
		Obra obra = MuseumData.getInstance().getPlantas().get(planta).getExpos().get(expo).getObras().get(row);
		return CardFragment.create(obra.getTitle(), obra.getDescription());
	}

	@Override
	public int getRowCount() {
		return MuseumData.getInstance().getPlantas().get(planta).getExpos().get(expo).getObras().size();
	}

	@Override
	public int getColumnCount(int i) {
		return 1;
	}

	@Override
	public ImageReference getBackground(int row, int column) {
		String name;
		int id = R.drawable.expo_01;
		name = "obra_" + row;

		int resId = context.getResources().getIdentifier(name, "drawable", BuildConfig.APPLICATION_ID);
		if (resId != 0) {
			id = resId;
		}
		return ImageReference.forDrawable(id);
	}
}
