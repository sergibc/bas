package com.tempos21.bas.ui.adapter;

import android.content.Context;

import com.tempos21.bas.sdk.sdk.data.Museo;

import java.util.List;

/**
 * Created by Bernat on 25/10/2014.
 */
public class MuseumsSimpleRectangularAdapter extends SimpleRectangularAdapter<Museo> {

	public MuseumsSimpleRectangularAdapter(Context context, List<Museo> objects) {
		super(context, objects);
	}
}
