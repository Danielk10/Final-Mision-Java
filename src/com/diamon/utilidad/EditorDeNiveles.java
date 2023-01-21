package com.diamon.utilidad;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.diamon.actor.Volador;
import com.diamon.dato.InformacionDeNiveles;
import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Camara2D;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class EditorDeNiveles {

	private Pantalla pantalla;

	private Juego juego;

	private ArrayList<Actor> actores;

	private Camara2D camara;

	private int xCamara, yCamara;

	private InformacionDeNiveles datosNiveles;

	public EditorDeNiveles(Pantalla pantalla) {

		this.pantalla = pantalla;

		this.actores = pantalla.getActores();

		this.juego = pantalla.getJuego();

		this.camara = pantalla.getCamara();

		datosNiveles = juego.getDatosNiveles();

	}

	public void actualizar(float delta) {

		xCamara = camara.getX();

		yCamara = camara.getY();

		camara.setX(xCamara);

		camara.setY(yCamara);

	}

	public void dibujar(Graphics2D pincel, float delta) {

	}

	public void colisiones() {

	}

	public void teclaPresionada(KeyEvent ev) {

		switch (ev.getKeyCode()) {

		case KeyEvent.VK_LEFT:

			xCamara -= 5;

			camara.setX(xCamara);

			if (actores.size() > 0) {

				for (int i = 0; i < actores.size(); i++) {

					int x = actores.get(i).getX() + 5;

					actores.get(i).setX(x);

				}

			}

			break;

		case KeyEvent.VK_RIGHT:

			xCamara += 5;

			camara.setX(xCamara);

			if (actores.size() > 0) {

				for (int i = 0; i < actores.size(); i++) {

					int x = actores.get(i).getX() - 5;

					actores.get(i).setX(x);

				}
			}

			break;

		case KeyEvent.VK_UP:

			yCamara += 5;

			camara.setY(yCamara);

			if (actores.size() > 0) {

				for (int i = 0; i < actores.size(); i++) {

					int y = actores.get(i).getY() + 5;

					actores.get(i).setY(y);

				}
			}

			break;

		case KeyEvent.VK_DOWN:

			yCamara -= 5;

			camara.setY(yCamara);

			if (actores.size() > 0) {

				for (int i = 0; i < actores.size(); i++) {

					int y = actores.get(i).getY() - 5;

					actores.get(i).setY(y);

				}
			}

			break;

		case KeyEvent.VK_G:

			datosNiveles.guardarConfiguraciones();

		//	actores.clear();

			break;

		default:

			break;

		}

	}

	public void teclaLevantada(KeyEvent ev) {

	}

	public void teclaTipo(KeyEvent ev) {

	}

	public void ratonDeslizando(MouseEvent ev) {

		if (actores.size() > 0) {

			if (actores.get(actores.size() - 1) instanceof Volador) {

				actores.get(actores.size() - 1).setX(ev.getX());

				actores.get(actores.size() - 1).setY(ev.getY());
			}

		}

	}

	private void agregarActor(ArrayList<Actor> actores) {

		String nivel = "Nivel " + 1;

		this.datosNiveles.gurdarActores(actores, "com.diamon.actor.Volador", nivel);

	}

	private void agregarActorTemporal(int x, int y) {

		Volador volador = new Volador(pantalla);

		volador.setTamano(32, 32);

		volador.setCuadros(7);

		volador.setPosicion(x, y);

		volador.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("voladorI1.png"),
				juego.getRecurso().getImagen("voladorI2.png"), juego.getRecurso().getImagen("voladorI3.png") });

		volador.setVelocidadY((int) (Math.random() * 7 - 5));

		actores.add(volador);

	}

	private void agregarActor(int x, int y) {

		ArrayList<Actor> actores = new ArrayList<Actor>();

		Volador volador = new Volador(pantalla);

		volador.setTamano(32, 32);

		volador.setCuadros(7);

		volador.setPosicion(camara.getX() + x, camara.getY() + y);

		volador.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("voladorI1.png"),
				juego.getRecurso().getImagen("voladorI2.png"), juego.getRecurso().getImagen("voladorI3.png") });

		volador.setVelocidadY((int) (Math.random() * 7 - 5));

		actores.add(volador);

		agregarActor(actores); ///

	}

	public void ratonMoviendo(MouseEvent ev) {

	}

	public void ratonClick(MouseEvent ev) {

	}

	public void ratonPresionado(MouseEvent ev) {

		agregarActorTemporal(ev.getX(), ev.getY());

	}

	public void ratonLevantado(MouseEvent ev) {

		agregarActor(ev.getX(), ev.getY());

	}

}
