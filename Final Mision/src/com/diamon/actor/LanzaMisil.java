package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Pantalla;

public class LanzaMisil extends Actor {

	public final static int VELOCIDAD_MAQUINA = 2;

	private int cicloExplosion;

	private int cicloDisparo;

	public LanzaMisil(Pantalla pantalla) {

		super(pantalla);

		cicloExplosion = 0;

		cicloDisparo = 0;

	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		x -= LanzaMisil.VELOCIDAD_MAQUINA;

		if (x <= -tamano.width) {

			remover = true;

		}

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

		m.setImagenes(new BufferedImage[] { recurso.getImagen("misilH1.png") });

		actores.add(m);

	}

	public void explosion() {

		Explosion explosion = new Explosion(pantalla);

		explosion.setTamano(64, 64);

		explosion.setPosicion(x - 32, y - 32);

		explosion.setImagenes(new BufferedImage[] { recurso.getImagen("explosion1.png"),
				recurso.getImagen("explosion2.png"),
				recurso.getImagen("explosion3.png"),
				recurso.getImagen("explosion4.png") });

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
