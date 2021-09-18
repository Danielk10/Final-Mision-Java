package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Pantalla;

public class AntiAereo extends Actor {

	private int cicloDisparo;

	private int cicloExplosion;

	public AntiAereo(Pantalla pantalla) {
		super(pantalla);

		cicloDisparo = 0;

		cicloExplosion = 0;

	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (x <= -tamano.width) {

			remover = true;

		}

		x--;

		cicloDisparo++;

		cicloExplosion++;

		if (cicloExplosion % 30 == 0) {

			for (int i = 0; i < actores.size(); i++) {

				if (actores.get(i) instanceof Explosion) {
					Explosion e = (Explosion) actores.get(i);

					e.remover();

				}
			}

			cicloExplosion = 0;

		}
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

		bala.setImagenes(new BufferedImage[] { recurso.getImagen("balaE1.png"), recurso.getImagen("balaE2.png"),
				recurso.getImagen("balaE3.png"), recurso.getImagen("balaE4.png") });

		bala.setCuadros(3);

		bala.setLado(BalaEnemigo.LADO_IZQUIERDO);

		if (bala.getX() <= 640) {

			actores.add(bala);
		}

	}

	public void explosion() {

		Explosion explosion = new Explosion(pantalla);

		explosion.setTamano(64, 64);

		explosion.setPosicion(x - 32, y - 32);

		explosion.setImagenes(
				new BufferedImage[] { recurso.getImagen("explosion1.png"), recurso.getImagen("explosion2.png"),
						recurso.getImagen("explosion3.png"), recurso.getImagen("explosion4.png") });

		explosion.setCuadros(4);

		if (explosion.getX() <= 640) {

			actores.add(explosion);

		}

	}

	@Override
	public void colision(Actor actor) {
		if (actor instanceof Bala || actor instanceof Jugador || actor instanceof BalaEspecial
				|| actor instanceof ExplosionB) {

			recurso.playSonido("explosion.wav");
			explosion();

			remover = true;

		}

	}

}
