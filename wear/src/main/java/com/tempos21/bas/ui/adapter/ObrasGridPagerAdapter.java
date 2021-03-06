package com.tempos21.bas.ui.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.ImageReference;

import com.tempos21.bas.sdk.sdk.data.MuseumData;
import com.tempos21.bas.sdk.sdk.data.Obra;
import com.tempos21.bas.BuildConfig;
import com.tempos21.bas.R;
import com.tempos21.bas.ui.fragment.BASCardFragment;

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
        return BASCardFragment.newInstance(obra.getTitle(), null);
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
