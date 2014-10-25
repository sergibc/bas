package com.example.sergibc.sdk.data;

/**
 * Created by Bernat on 25/10/2014.
 */
public class Planta2 extends Planta {

	public Planta2() {
		Expo belleza = new Expo();
		belleza.setDescription("La belleza cautiva");
		getExpos().add(belleza);

		Expo retratos = new Expo();
		retratos.setDescription("Retratos de una huida");
		getExpos().add(retratos);

		Expo caixalab = new Expo();
		caixalab.setDescription("Caixalab experience");
		getExpos().add(caixalab);
	}

}
