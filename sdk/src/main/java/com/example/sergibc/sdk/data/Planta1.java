package com.example.sergibc.sdk.data;

import com.example.sergibc.sdk.R;

/**
 * Created by Bernat on 25/10/2014.
 */
public class Planta1 extends Planta {

	public Planta1() {
		super(1);
		Expo genesis = new Expo(R.drawable.exposiciones_jpg_planta1);
		genesis.setDescription("Génesis");
		getExpos().add(genesis);

		Expo color = new Expo(R.drawable.exposiciones_jpg_planta2);
		color.setDescription("Espacio del color");
		getExpos().add(color);

		Expo narrativas = new Expo(R.drawable.exposiciones_jpg_planta0);
		narrativas.setDescription("Tres narrativas");
		getExpos().add(narrativas);

		Expo espacio = new Expo(R.drawable.exposiciones_jpg_planta1);
		espacio.setDescription("Espacio modernista");
		getExpos().add(espacio);
	}

	@Override
	public int getImage() {
		return R.drawable.exposiciones_jpg_planta1;
	}
}
