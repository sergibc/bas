package com.example.sergibc.sdk.data;

import com.example.sergibc.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 24/10/2014.
 */
public class MuseumData {
	private static MuseumData museumData;
	private List<Museo> museos;

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	public List<Planta> plantas;

	public static MuseumData getInstance() {
		if (museumData == null) {
			museumData = new MuseumData();
		}
		return museumData;
	}

	private MuseumData() {
		plantas = new ArrayList<Planta>();
		plantas.add(new Planta0());
		plantas.add(new Planta1());
		plantas.add(new Planta2());

		museos = new ArrayList<Museo>();
	}

	public List<Museo> getMuseos() {
		return museos;
	}

	public void setMuseos(List<Museo> museos) {
		this.museos = museos;
	}
}
