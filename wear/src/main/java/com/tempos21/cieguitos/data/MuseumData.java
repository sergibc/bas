package com.tempos21.cieguitos.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 24/10/2014.
 */
public class MuseumData {
	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	public List<Planta> plantas = new ArrayList<Planta>();

	public static MuseumData getInstance() {
		return new MuseumData();
	}

	private MuseumData() {
		plantas = new ArrayList<Planta>();
		plantas.add(new Planta());
		plantas.add(new Planta());
		plantas.add(new Planta());
	}
}
