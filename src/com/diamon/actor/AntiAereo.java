package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Animacion;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class AntiAereo extends Actor {

	private float tiempoCuadro;

	private float duracionDisparo;

	private Jugador jugador;

	private Animacion animacion1;

	private Animacion animacion2;

	private Animacion animacion3;

	private Animacion animacion4;

	private Animacion animacion5;

	public AntiAereo(Pantalla pantalla) {
		super(pantalla);

		duracionDisparo = 0;

		setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("antiAreoH1.png") });

		animacion1 = new Animacion(cuadros / Juego.FPS, imagenes[0]);

		animacion1.setModo(Animacion.NORMAL);

		animacion = animacion1;

		animacion2 = new Animacion(cuadros / Juego.FPS, imagenes[0]);

		animacion2.setModo(Animacion.NORMAL);

		animacion = animacion2;

		obtenerJugador();

	}

	private void obtenerJugador() {

		for (int i = 0; i < pantalla.getActores().size(); i++) {

			if (pantalla.getActores().get(i) instanceof Jugador) {

				jugador = (Jugador) pantalla.getActores().get(i);

			}

		}

	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (x <= -tamano.width) {

			remover = true;

		}

		x--;

		if (jugador != null) {

			if (jugador.getY() <= y + tamano.height && jugador.getY() + jugador.getTamano().height >= y
					&& jugador.getX() <= x)

			{

				if (tiempoCuadro == 0)

				{

					disparar();
				}

				tiempoCuadro += delta;

				if (tiempoCuadro / duracionDisparo >= 1) {

					disparar();

					tiempoCuadro = 0;
				}

			}

		}

		if (x <= -tamano.width) {

			remover = true;
		}

	}

	public void setDuracionDisparo(float duracionDisparo) {
		this.duracionDisparo = duracionDisparo;
	}

	public void disparar() {

		BalaEnemigo bala = new BalaEnemigo(pantalla);

		bala.setTamano(12, 12);

		bala.setPosicion(x, y + 12);

		bala.setCuadros(3);

		bala.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaE1.png"),
				pantalla.getJuego().getRecurso().getImagen("balaE2.png"),
				pantalla.getJuego().getRecurso().getImagen("balaE3.png"),
				pantalla.getJuego().getRecurso().getImagen("balaE4.png") });

		bala.setLado(BalaEnemigo.LADO_IZQUIERDO);

		if (bala.getX() <= 640) {

			pantalla.getActores().add(bala);
		}

	}

	public void explosion() {

		Explocion explosion = new Explocion(pantalla);

		explosion.setTamano(64, 64);

		explosion.setPosicion(x - 32, y - 32);

		explosion.setCuadros(4);

		explosion.setDuracionExplosion(0.2f);

		explosion.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("explosion1.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion2.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion3.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion4.png") });

		if (explosion.getX() <= 640) {

			pantalla.getActores().add(explosion);

		}

	}

	@Override
	public void colision(Actor actor) {
		if (actor instanceof Bala || actor instanceof Jugador || actor instanceof BalaEspecial
				|| actor instanceof ExplocionB) {

			pantalla.getJuego().getRecurso().playSonido("explosion.wav");
			explosion();

			remover = true;

		}

	}

}
