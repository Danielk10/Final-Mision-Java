package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class BalaEnemigoDestruible extends Actor {

	public final static int VELOCIDAD_BALA = 3;

	private int velocidad;

	public BalaEnemigoDestruible(Pantalla pantalla) {
		super(pantalla);

		velocidad = VELOCIDAD_BALA;

	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

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

		if (actor instanceof Jugador || actor instanceof Bala || actor instanceof BalaEspecial) {

			pantalla.getJuego().getRecurso().playSonido("explosion.wav");

			explosion();

			remover = true;

		}

	}

}
