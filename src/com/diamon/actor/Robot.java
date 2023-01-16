package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class Robot extends Actor {

	private int cicloDisparo;

	public final static int LADO_IZQUIERDO = 1;

	public final static int LADO_DERECHO = 2;

	private int velocidad;

	private int lado;

	private boolean disparar;

	public Robot(Pantalla pantalla) {
		super(pantalla);

		cicloDisparo = 0;

		velocidad = 0;

		disparar = false;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (lado == Robot.LADO_DERECHO) {
			x += velocidad;

			if (!disparar) {

				if (x <= 100) {

					x += 2;
					y += 2;

				}

			}

			if (disparar)

			{
				x -= 2;
				y -= 2;

			}

			if (x >= Juego.ANCHO_PANTALLA) {

				remover = true;
			}

		}

		if (lado == Robot.LADO_IZQUIERDO) {

			x -= velocidad;

			if (!disparar) {
				if (x >= 500) {

					x -= 2;

					y += 2;

				}
			}

			if (disparar)

			{
				x += 2;
				y -= 2;

			}
			if (x <= -tamano.width) {

				remover = true;
			}

		}

		cicloDisparo++;

		if (cicloDisparo % 40 == 0) {

			if (Math.random() < 0.08f) {
				disparar();

				disparar = true;

			}

			cicloDisparo = 0;

		}

	}

	private void disparar() {

		if (lado == Robot.LADO_DERECHO) {

			BalaEnemigo bala = new BalaEnemigo(pantalla);

			bala.setTamano(12, 12);

			bala.setPosicion(x + tamano.width, y + 16);

			bala.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaE1.png"),
					pantalla.getJuego().getRecurso().getImagen("balaE2.png"),
					pantalla.getJuego().getRecurso().getImagen("balaE3.png"),
					pantalla.getJuego().getRecurso().getImagen("balaE4.png") });

			bala.setCuadros(3);

			bala.setLado(BalaEnemigo.LADO_DERECHO);

			if (bala.getX() <= 640) {

				pantalla.getActores().add(bala);
			}

		}

		if (lado == Robot.LADO_IZQUIERDO) {

			BalaEnemigo bala = new BalaEnemigo(pantalla);

			bala.setTamano(12, 12);

			bala.setPosicion(x, y + 16);

			bala.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaE1.png"),
					pantalla.getJuego().getRecurso().getImagen("balaE2.png"),
					pantalla.getJuego().getRecurso().getImagen("balaE3.png"),
					pantalla.getJuego().getRecurso().getImagen("balaE4.png") });

			bala.setCuadros(3);

			bala.setLado(BalaEnemigo.LADO_IZQUIERDO);

			if (bala.getX() <= 640) {

				pantalla.getActores().add(bala);
			}

		}

	}

	private void explosion() {

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

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
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
