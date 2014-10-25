package com.tempos21.bas.sdk.sdk.data;

/**
 * Created by Bernat on 25/10/2014.
 */
public class Museo implements ItemSelectable{
	private int image;
	private String name;
	private String description;
	private boolean comprada = false;

	@Override
	public String getTitle() {
		return name;
	}

	@Override
	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isComprada() {
		return comprada;
	}

	public void setComprada(boolean comprada) {
		this.comprada = comprada;
	}
}
