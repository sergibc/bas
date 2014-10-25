package com.example.sergibc.sdk.data;

/**
 * Created by Bernat on 25/10/2014.
 */
public class Obra implements ItemSelectable{
	private String title;
	private String description;
	private Status status;
	private int drawable;

	public Obra(int drawable) {
		this.drawable = drawable;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public int getImage() {
		return drawable;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		PLAYING,
		PAUSE,
		STOP
	}
}
