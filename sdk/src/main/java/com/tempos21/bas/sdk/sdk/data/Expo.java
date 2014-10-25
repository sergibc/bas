package com.tempos21.bas.sdk.sdk.data;

import com.example.sergibc.sdk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bernat on 24/10/2014.
 */
public class Expo implements ItemSelectable{
	private String description;
	private List<Obra> obras = new ArrayList<Obra>();
	private int drawable;

	public Expo(int drawable) {
		this.drawable = drawable;
		Obra obra1 = new Obra(R.drawable.obra_0);
		obra1.setTitle("La ermita de San Isidro el día de la fiesta");
		obra1.setDescription("Dos de las composiciones más célebres de Goya, La gallina ciega (P804 y P2781) y La pradera de San Isidro (P750), pertenecen a la misma serie que este boceto. La escena representada transcurre el día de la fiesta de San Isidro en los alrededores de su ermita madrileña, donde se congregaban numerosas personas para beber el agua de la fuente milagrosa que el santo había hecho brotar allí. En primer término, un majo trae el agua a un grupo de majas sentadas en el suelo, mientras que, al fondo, la muchedumbre hace cola para acceder a la fuente, distinguiéndose a la derecha a dos guardias de corps, lo que podría significar la presencia de algún miembro de la familia real entre los asistentes. De expresiva pincelada, es magistral en su estudio de las luces, además de revelar la capacidad de observación del artista (Texto extractado de La Belleza Cautiva. Pequeños tesoros del Museo del Prado, Museo Nacional del Prado, Obra Social la Caixa, 2014, p. 189).");
		obra1.setStatus(Obra.Status.PAUSE);
		obras.add(obra1);

		Obra obra2 = new Obra(R.drawable.obra_1);
		obra2.setTitle("La Virgen con el Niño y ángeles");
		obra2.setDescription("Friedländer atribuyó esta tabla del Prado a Gérard David, pintor de origen holandés nacido en Oudewater, que obtuvo la maestría en Brujas en 1484, donde tuvo abierto su taller hasta 1523. Aunque en las primeras obras manifiesta tendencias arcaizantes, utilizando para sus composiciones modelos inspirados en Jan van Eyck y en Roger van del Weyden, después se acentúan en su obra elementos novedosos -entre ellos el modo de representar el paisaje- y se crean en su taller algunos temas que se repetirían durante mucho tiempo e la escuela de Brujas como La Virgen de la sopa o La huida a Egipto. Para Friedländer esta tabla del Prado pertenece a la última etapa de la actividad del pintor, en torno a 1520, a juzgar por el modelado mucho más blando que el de sus obras anteriores. A pesar de lo tardío de su supuesta fecha de ejecución, coincide con otras pinturas de devoción de pequeño formato salidas del taller de David, en las que se sustituyó el marco arquitectónico o el paisaje por un fondo de oro. Inspirada posiblemente en el grabado de La Virgen del mono de Durero, conocido quizás antes de la estancia del pintor en Nuremberg en los Países Bajos en 1521, aunque no sea necesario. En esta tabla, el autor representó a María, de medio cuerpo como reina de los cielos, coronada por dos ángeles. Su rostro pensativo, mirando a su Hijo al que sostiene en sus brazos, y el modo en que Jesús se aparta de ella para jugar con los cardos, aluden de nuevo a los dolores de la pasión, que están presentes en los días de la infancia, como lo reitera la literatura religiosa de la época (Texto extractado de Silva Maroto, P.: Pintura flamenca de los siglos XV y XVI. Guía, Museo Nacional del Prado, 2001, p. 102).");
		obra2.setStatus(Obra.Status.PLAYING);
		obras.add(obra2);

		Obra obra3 = new Obra(R.drawable.obra_2);
		obra3.setTitle("El Olimpo, o Triunfo de Venus");
		obra3.setDescription("Esta obra es el único boceto superviviente de la serie proyectada para el canciller del imperio ruso, conde Michail Voronzov, que comisionó tres grandes techos para su palacio en San Petersburgo. Los otros temas proyectados representan La Gloria de los héroes, y un tercero, de Giandomenico, con el tema El triunfo de Hércules. Lorenzo Tiepolo realizó un grabado correspondiente a esta pintura que se conserva en la Biblioteca Nacional (inv. número 41218). De la comparación entre ambos se deduce que Giambattista debió introducir numerosas variantes en su lienzo definitivo y que fue precisamente éste el que fue reproducido en el grabado.Constituye una obra importante dentro de la producción de Giambattista que muy posiblemente trajo consigo en su viaje a España, puesto que es conocida su vocación de reutilizar elementos sueltos de sus obras anteriores en otras posteriores. Su composición es muy simple y está dominada por una línea zigzagueante situada en el mismo plano del espectador, casi como si de una obra de caballete se tratara, utilizando un procedimiento que posteriormente imitaría en el techo de la Sala de Alabarderos del Palacio Real, que representa La Apoteosis de Eneas.Tras una limpieza efectuada sobre la obra, el boceto ha recuperado su colorido original dominado por los tonos crema y blanco lechosos de las figuras desnudas. Tiepolo consigue formidables vibraciones de color gracias a toques sueltos de tonos muy intensos, como los rojos y azules del guacamayo rojo situado en la parte baja o el sombrero de Mercurio en la alta y, en general, en pinceladas sueltas de rojos y dorados en las telas que cubren los personajes (Texto extractado de Úbeda de los Cobos, A.: Lorenzo Tiepolo, Museo Nacional del Prado, 1999, p. 98).");
		obra3.setStatus(Obra.Status.STOP);
		obras.add(obra3);

		Obra obra4 = new Obra(R.drawable.obra_3);
		obra4.setTitle("Diana y sus ninfas cazando");
		obra4.setDescription("Diana, reconocible por la diadema en forma de luna que orna su cabeza, acomete la caza de varios ciervos con lanzas y perros, acompañada por varias ninfas. Se aprecia a simple vista el cambio que hizo Rubens en la posición del brazo derecho de la diosa. (Texto extractado de Vergara, A.; Pérez Preciado, J. J.: Rubens, Guía de Exposición, Museo Nacional del Prado, 2010, p.40).La decoración de la Torre de la Parada, en cuyo proyecto también participaron otros autores como Velázquez, fue el mayor encargo que Rubens recibió de Felipe IV. A partir de 1636 se enviaron desde Amberes a Madrid más de sesenta obras para esta casa de recreo situada en los montes del Pardo. La mayor parte de las escenas narraban las pasiones de los dioses, según fueron descritas en las Metamorfosis del poeta romano Ovidio y otras fuentes clásicas. Para llevar a cabo un proyecto tan amplio, Rubens realizó pequeños bocetos sobre tabla, donde capta la esencia moral de las historias y las actitudes de los personajes. Estos bocetos sirvieron de base para la elaboración de los lienzos definitivos. \n" +
				"\n" +
				"El Museo del Prado conserva diez de los bocetos de Rubens, nueve de ellos donados en 1889 por la duquesa de Pastrana, y uno adquirido en el año 2000. El Prado también conserva la mayor parte de los cuadros ejecutados a partir de los bocetos, entre ellos los realizados por el propio Rubens o por su taller (muchos de los cuadros fueron pintados por otros artistas). (Texto extractado de Vergara, A.; y Pérez Preciado J.J.: Rubens, Guías de la Colección. Museo Nacional del Prado, 2010, p. 52).");
		obra4.setStatus(Obra.Status.PAUSE);
		obras.add(obra4);
	}

	public List<Obra> getObras() {
		return obras;
	}

	public void setObras(List<Obra> obras) {
		this.obras = obras;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getTitle() {
		return description;
	}

	@Override
	public int getImage() {
		return drawable;
	}
}
