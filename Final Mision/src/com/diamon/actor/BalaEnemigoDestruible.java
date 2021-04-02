package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class BalaEnemigoDestruible extends Actor {

	public final static int VELOCIDAD_BALA = 3;

	private int velocidad;

	private int cicloExplosion;

	public BalaEnemigoDestruible(Pantalla pantalla) {
		super(pantalla);

		velocidad = VELOCIDAD_BALA;

		cicloExplosion = 0;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		cicloExplosion++;

		x -= velocidad;

		if (x <= -tamano.width) {

			remover = true;

		}

		if (y <= -tamano.height) {

			remover = true;
		}

		if (x >= Juego.ANCHO_PANTALLA) {

			remover = true;
		}

		if (y >= Juego.ALTO_PANTALLA) {

			remover = true;
		}

		if (cicloExplosion % 30 == 0) {

			for (int i = 0; i < pantalla.getActores().size(); i++) {

				if (pantalla.getActores().get(i) instanceof Explosion) {
					Explosion e = (Explosion) pantalla.getActores().get(i);

					e.remover();

				}
			}

			cicloExplosion = 0;

		}
	}

	public void explosion() {

		Explosion explosion = new Explosion(pantalla);

		explosion.setTamano(64, 64);

		explosion.setPosicion(x - 32, y - 32);

		explosion.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("explosion1.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion2.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion3.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion4.png") });

		explosion.setCuadros(4);

		if (explosion.getX() <= 640) {

			pantalla.getActores().add(explosion);

		}

	}

	@Override
	public void colision(Actor actor) {

		if (actor instanceof Jugador || actor instanceof Bala || actor instanceof BalaEspecial) {

			pantalla.getJuego().getRecurso().playSonido("explosion.wav");

			explosion();

			remover = true;

		}

	}

}
