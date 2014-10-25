package com.example.sergibc.sdk.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 24/10/2014.
 */
public class Expo {
	private List<Obra> obras = new ArrayList<Obra>();

	public Expo() {
		obras.add(new Obra());
		obras.add(new Obra());
		obras.add(new Obra());
		obras.add(new Obra());
	}

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}
}
