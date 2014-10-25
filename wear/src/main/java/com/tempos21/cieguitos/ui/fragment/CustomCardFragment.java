package com.tempos21.cieguitos.ui.fragment;

import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Bernat on 25/10/2014.
 */
public class CustomCardFragment extends CardFragment implements View.OnClickListener {

	public static final String ROW = "ROW";
	public static final String COLUMN = "COLUMN";
	private OnCardSelectedListener onCardSelectedListener;
	private int row;
	private int column;

	public static CustomCardFragment newInstance(int row, int column, OnCardSelectedListener onCardSelectedListener) {
		Bundle args = new Bundle();
		args.putInt(CustomCardFragment.ROW, row);
		args.putInt(CustomCardFragment.COLUMN, column);
		CustomCardFragment f = new CustomCardFragment();
		f.setOnCardSelectedListener(onCardSelectedListener);
		f.setArguments(args);
		return f;
	}

	@Override
	public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (container != null) {
			container.setOnClickListener(this);
			row = getArguments().getInt(ROW, 0);
			column = getArguments().getInt(COLUMN, 0);
		}
		return super.onCreateContentView(inflater, container, savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		if (onCardSelectedListener != null) {
			onCardSelectedListener.onCardSelected(row, column);
		}
	}

	public void setOnCardSelectedListener(OnCardSelectedListener onCardSelectedListener) {
		this.onCardSelectedListener = onCardSelectedListener;
	}

	public interface OnCardSelectedListener {
		void onCardSelected(int row, int column);
	}
}
