package com.tempos21.cieguitos.ui.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.ImageReference;
import android.view.View;
import android.view.ViewGroup;

import com.tempos21.cieguitos.R;
import com.tempos21.cieguitos.data.MuseumData;

/**
 * Created by Bernat on 24/10/2014.
 */
public class MuseumGridPagerAdapter extends FragmentGridPagerAdapter {

	public MuseumGridPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getFragment(int row, int column) {
		CardFragment fragment = null;
		if (column == 0) {
			fragment = CardFragment.create("Planta: " + row, "Bla bla bl nskfwe wefmwpfmwpe noefmpowemfwe wejfpwemfwrfo`wa BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackgroundBackgroundController.getBackgroundBackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground BackgroundController.getBackground");
		} else {
			fragment = CardFragment.create("Expo: " + column, "AAh aah ahh");
		}
		return fragment;
	}

	@Override
	public int getRowCount() {
		return MuseumData.getInstance().getPlantas().size();
	}

	@Override
	public int getColumnCount(int i) {
		return MuseumData.getInstance().getPlantas().get(i).getExpos().size();
	}

	@Override
	public int getCurrentColumnForRow(int row, int currentColumn) {
		return 0;
	}

	@Override
	public ImageReference getBackground(int row, int column) {
		ImageReference ir = ImageReference.forDrawable(R.drawable.ic_launcher);
		return ir;
	}
}
