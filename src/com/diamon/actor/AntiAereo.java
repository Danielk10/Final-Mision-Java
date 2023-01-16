package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Pantalla;

public class AntiAereo extends Actor {

	private int cicloDisparo;

	public AntiAereo(Pantalla pantalla) {
		super(pantalla);

		cicloDisparo = 0;

	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (x <= -tamano.width) {

			remover = true;

		}

		x--;

		cicloDisparo++;

		if (cicloDisparo % 10 == 0) {

			if (Math.random() < 0.08f) {
				disparar();

			}

			cicloDisparo = 0;

		}

		if (x <= -tamano.width) {

			remover = true;
		}

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

		Explosion explosion = new Explosion(pantalla);

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
				|| actor instanceof ExplosionB) {

			pantalla.getJuego().getRecurso().playSonido("explosion.wav");
			explosion();

			remover = true;

		}

	}

}
