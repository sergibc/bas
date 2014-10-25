package com.tempos21.bas.sdk.sdk.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 24/10/2014.
 */
public class Planta implements ItemSelectable{
	private List<Expo> expos;
	private int numPlanta;

	public Planta(int numPlanta) {
		this.numPlanta = numPlanta;
		expos = new ArrayList<Expo>();
		expos.add(new Expo(0));
	}

	public List<Expo> getExpos() {
		return expos;
	}

	public void setExpos(List<Expo> expos) {
		this.expos = expos;
	}

	@Override
	public String getTitle() {
		return "Planta " + numPlanta;
	}

	@Override
	public int getImage() {
		return 0;
	}
}
