package com.diamon.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class Saltador extends Actor {

	private int cicloExplosion;

	private int cicloDisparo;

	private boolean preparado;

	private boolean salta;

	private boolean suelo;

	public final static int VELOCIDAD_MAQUINA = 5;

	private int velocidadY;

	private int xJugador;

	public Saltador(Pantalla pantalla) {
		super(pantalla);

		cicloExplosion = 0;

		cicloDisparo = 0;

		xJugador = 0;

		preparado = false;

		suelo = false;

		salta = false;

		velocidadY = VELOCIDAD_MAQUINA;
	}

	@Override
	public void actualizar(float delta) {
		// TODO Auto-generated method stub
		super.actualizar(delta);

		cicloExplosion++;

		cicloDisparo++;

		x--;

		for (int i = 0; i < actores.size(); i++) {

			if (actores.get(i) instanceof Jugador) {
				Jugador j = (Jugador) actores.get(i);

				xJugador = j.getX();

			}

		}

		if (cicloExplosion % 30 == 0) {

			for (int i = 0; i < actores.size(); i++) {

				if (actores.get(i) instanceof Explosion) {
					Explosion e = (Explosion) actores.get(i);

					e.remover();

				}
			}

			cicloExplosion = 0;

		}

		if (cicloDisparo % 40 == 0) {

			disparar();

			cicloDisparo = 0;

		}

		if (y == 352) {

			suelo = true;

		} else {
			suelo = false;

		}

		if (suelo) {

			if (xJugador == x) {

				salta = true;

			}
			if (xJugador != x) {

				salta = false;

			}

		}

		if (salta) {

			y += velocidadY;

		}

		if (y <= 0 || y >= Juego.ALTO_PANTALLA - tamano.height - 63) {

			velocidadY = -velocidadY;

		}

		if (x <= -tamano.width) {

			remover = true;
		}

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

	public void disparar() {

		BalaEnemigoDestruible bala = new BalaEnemigoDestruible(pantalla);

		bala.setTamano(32, 32);

		bala.setPosicion(x, y + 12);

		bala.setImagenes(new BufferedImage[] { recurso.getImagen("balaSaltador1.png"),
				recurso.getImagen("balaSaltador2.png") });

		bala.setCuadros(5);

		if (bala.getX() <= 640) {

			actores.add(bala);
		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		super.dibujar(pincel, delta);

		if (suelo) {

			setCuadros(20);

			setImagenes(new BufferedImage[] { recurso.getImagen("saltador2.png") });

			if (preparado) {
				setCuadros(20);
				setImagenes(new BufferedImage[] { recurso.getImagen("saltador2.png"),
						recurso.getImagen("saltador1.png"),
						recurso.getImagen("saltador3.png") });

			}

		}

		if (salta) {

			setCuadros(1);

			setImagenes(new BufferedImage[] { recurso.getImagen("saltador4.png") });

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
