package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class Misil extends Actor {

	private int cicloExplosion;

	private int cicloDisparoHumo;

	private int cicloHumo;

	public final static int VELOCIDAD_MAQUINA = 2;

	public Misil(Pantalla pantalla) {

		super(pantalla);

		cicloExplosion = 0;

		cicloDisparoHumo = 0;

		cicloHumo = 0;

	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		cicloExplosion++;

		cicloDisparoHumo++;

		cicloHumo++;

		if (cicloExplosion % 30 == 0) {

			for (int i = 0; i < actores.size(); i++) {

				if (actores.get(i) instanceof Explosion) {
					Explosion e = (Explosion) actores.get(i);

					e.remover();

				}
			}

			cicloExplosion = 0;

		}

		x += Misil.VELOCIDAD_MAQUINA;

		if (x >= Juego.ANCHO_PANTALLA) {

			remover = true;

		}

		if (cicloDisparoHumo % 15 == 0) {

			humo();

			cicloDisparoHumo = 0;

		}

		if (cicloHumo % 30 == 0) {

			for (int i = 0; i < actores.size(); i++) {

				if (actores.get(i) instanceof Humo) {
					Humo e = (Humo) actores.get(i);

					e.remover();

				}
			}

			cicloHumo = 0;

		}

	}

	public void explosion() {

		Explosion explosion = new Explosion(pantalla);

		explosion.setTamano(32, 32);

		explosion.setPosicion(x, y);

		explosion.setImagenes(new BufferedImage[] { recurso.getImagen("explosion1.png"),
				recurso.getImagen("explosion2.png"),
				recurso.getImagen("explosion3.png"),
				recurso.getImagen("explosion4.png") });

		explosion.setCuadros(4);

		if (explosion.getX() <= 640) {

			actores.add(explosion);

		}

	}

	public void humo() {

		Humo humo = new Humo(pantalla);

		humo.setTamano(16, 16);

		humo.setPosicion(x, y - 4);

		humo.setCuadros(5);

		humo.setImagenes(new BufferedImage[] { recurso.getImagen("humoMisil1.png"),
				recurso.getImagen("humoMisil2.png"),
				recurso.getImagen("humoMisil3.png") });

		if (humo.getX() <= 640) {

			actores.add(humo);
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
