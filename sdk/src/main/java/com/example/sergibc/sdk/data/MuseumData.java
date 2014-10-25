package com.example.sergibc.sdk.data;

import com.example.sergibc.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 24/10/2014.
 */
public class MuseumData {
	private static MuseumData museumData;
	private ArrayList<Museo> museos;

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
		museos = new ArrayList<Museo>();
	}

	public ArrayList<Museo> getMuseos() {
		return museos;
	}

	public void setMuseos(ArrayList<Museo> museos) {
		this.museos = museos;
	}
}
