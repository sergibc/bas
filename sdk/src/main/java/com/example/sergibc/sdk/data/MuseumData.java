package com.example.sergibc.sdk.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 24/10/2014.
 */
public class MuseumData {
	private List<Museo> museos;

	public List<Planta> getPlantas() {
		return plantas;
	}

	public void setPlantas(List<Planta> plantas) {
		this.plantas = plantas;
	}

	public List<Planta> plantas;

	public static MuseumData getInstance() {
		return new MuseumData();
	}

	private MuseumData() {
		plantas = new ArrayList<Planta>();
		plantas.add(new Planta());
		plantas.add(new Planta());
		plantas.add(new Planta());

		museos = new ArrayList<Museo>();

		Museo museo1 = new Museo();

		museo1.setName("Casa arbol del terror");
		museo1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum ut maximus dui. Morbi dolor leo, dignissim sit amet dapibus ac, sollicitudin nec libero. Vivamus enim augue, pellentesque eu erat at, feugiat ullamcorper urna. Nunc pellentesque pellentesque leo. Pellentesque euismod feugiat quam, id aliquet massa ultricies eget. Pellentesque viverra et ex ac fringilla. Suspendisse erat nisi, egestas porta venenatis sollicitudin, lobortis ac ex. Proin ut sapien eget elit sollicitudin fermentum at sit amet ipsum. Pellentesque vel enim vel velit eleifend porttitor. Phasellus pulvinar ac quam nec euismod.");
		museo1.setImage("http://www.decorablog.com/wp-content/2008/10/casa_arbol.jpg");

		museos.add(museo1);
		museos.add(museo1);
		museos.add(museo1);
		museos.add(museo1);
		museos.add(museo1);
	}

	public List<Museo> getMuseos() {
		return museos;
	}
}
