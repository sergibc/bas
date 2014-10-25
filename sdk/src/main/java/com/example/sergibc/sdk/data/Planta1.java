package com.example.sergibc.sdk.data;

/**
 * Created by Bernat on 25/10/2014.
 */
public class Planta1 extends Planta {

	public Planta1() {
		Expo genesis = new Expo();
		genesis.setDescription("Génesis");
		getExpos().add(genesis);

		Expo color = new Expo();
		color.setDescription("Espacio del color");
		getExpos().add(color);

		Expo narrativas = new Expo();
		narrativas.setDescription("Tres narrativas");
		getExpos().add(narrativas);

		Expo espacio = new Expo();
		espacio.setDescription("Espacio modernista");
		getExpos().add(espacio);
	}

}
