package com.example.sergibc.sdk.data;

/**
 * Created by Bernat on 25/10/2014.
 */
public class Obra {
	private String title;
	private String description;
	private ActionObras action;

	public String getTitle() {
		return title;
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

	public ActionObras getAction() {
		return action;
	}

	public void setAction(ActionObras action) {
		this.action = action;
	}
}