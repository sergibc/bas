package com.example.sergibc.sdk.data;

import com.example.sergibc.sdk.R;

/**
 * Created by Bernat on 25/10/2014.
 */
public class Planta0 extends Planta{

	public Planta0() {
		super(0);
		Expo info = new Expo(R.drawable.exposiciones_jpg_expo1);
		info.setDescription("Información");
		getExpos().add(info);
	}

	@Override
	public int getImage() {
		return R.drawable.exposiciones_jpg_planta0;
	}
}
