package com.tempos21.bas.ui.adapter;

import android.content.Context;

import com.tempos21.bas.sdk.sdk.data.Planta;

import java.util.List;

/**
 * Created by Bernat on 25/10/2014.
 */
public class PlantasSimpleRectangularAdapter extends SimpleRectangularAdapter<Planta>{
	public PlantasSimpleRectangularAdapter(Context context, List<Planta> objects) {
		super(context, objects);
	}
}
