package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Pantalla;

public class LanzaMisil extends Actor {

	public final static int VELOCIDAD_MAQUINA = 2;

	private int cicloDisparo;

	public LanzaMisil(Pantalla pantalla) {

		super(pantalla);

		cicloDisparo = 0;

	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		x -= LanzaMisil.VELOCIDAD_MAQUINA;

		if (x <= -tamano.width) {

			remover = true;

		}

		cicloDisparo++;

		if (cicloDisparo % 150 == 0) {

			disparar();

			cicloDisparo = 0;

		}

	}

	private void disparar() {

		Misil m = new Misil(pantalla);

		m.setTamano(16, 8);

		m.setPosicion(x + tamano.width, y + 12);

		m.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("misilH1.png") });

		pantalla.getActores().add(m);

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
