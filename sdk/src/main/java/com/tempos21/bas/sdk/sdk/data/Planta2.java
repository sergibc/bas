package com.tempos21.bas.sdk.sdk.data;

import com.example.sergibc.sdk.R;

/**
 * Created by Bernat on 25/10/2014.
 */
public class Planta2 extends Planta {

	public Planta2() {
		super(2);
		Expo belleza = new Expo(R.drawable.exposiciones_jpg_planta1);
		belleza.setDescription("La belleza cautiva");
		getExpos().add(belleza);

		Expo retratos = new Expo(R.drawable.exposiciones_jpg_planta0);
		retratos.setDescription("Retratos de una huida");
		getExpos().add(retratos);

		Expo caixalab = new Expo(R.drawable.exposiciones_jpg_planta2);
		caixalab.setDescription("Caixalab experience");
		getExpos().add(caixalab);
	}

	@Override
	public int getImage() {
		return R.drawable.exposiciones_jpg_planta2;
	}

}
