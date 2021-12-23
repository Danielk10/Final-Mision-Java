package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Pantalla;

public class MaquinaFinal extends Actor {

	private int cicloExplosion;

	private int cicloDisparo;

	private int cicloDisparoDestruible;

	private int cicloChoque;

	private int vida;

	private boolean disparar;

	private boolean choque;

	public MaquinaFinal(Pantalla pantalla) {
		super(pantalla);

		cicloExplosion = 0;

		cicloDisparo = 0;

		cicloChoque = 0;

		cicloDisparoDestruible = 0;

		vida = 50;

		disparar = false;

		choque = false;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean isDisparar() {
		return disparar;
	}

	public void setDisparar(boolean disparar) {
		this.disparar = disparar;
	}

	@Override
	public void actualizar(float delta) {
		// TODO Auto-generated method stub
		super.actualizar(delta);

		cicloDisparo++;

		cicloChoque++;

		cicloDisparoDestruible++;

		if (vida == 0) {

			vida = 0;

			cicloExplosion++;

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

		if (disparar) {

			if (cicloDisparo % 120 == 0) {

				disparar();

				cicloDisparo = 0;

			}

			if (cicloDisparoDestruible % 60 == 0) {

				dispararBalaDestruible();

				cicloDisparoDestruible = 0;

			}

		}

		if (choque) {

			if (cicloChoque % 20 == 0) {

				vida--;

				cicloChoque = 0;

				choque = false;

			}

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

	public void disparar() {

		BalaEnemigo bala = new BalaEnemigo(pantalla);

		bala.setTamano(32, 12);

		bala.setPosicion(x, y + 26);

		bala.setLado(BalaEnemigo.LADO_IZQUIERDO);

		bala.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaParedI.png") });

		if (bala.getX() <= 640) {

			pantalla.getActores().add(bala);
		}

	}

	public void dispararBalaDestruible() {

		BalaEnemigoDestruible bala1 = new BalaEnemigoDestruible(pantalla);

		bala1.setTamano(32, 32);

		bala1.setPosicion(x, y - 128);

		bala1.setCuadros(20);

		bala1.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaSaltador1.png"),
				pantalla.getJuego().getRecurso().getImagen("balaSaltador2.png") });

		if (bala1.getX() <= 640) {

			pantalla.getActores().add(bala1);
		}

		BalaEnemigoDestruible bala2 = new BalaEnemigoDestruible(pantalla);

		bala2.setTamano(32, 32);

		bala2.setPosicion(x, y + 128);

		bala2.setCuadros(20);

		bala2.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaSaltador1.png"),
				pantalla.getJuego().getRecurso().getImagen("balaSaltador2.png") });

		if (bala2.getX() <= 640) {

			pantalla.getActores().add(bala2);
		}

	}

	@Override
	public void colision(Actor actor) {

		if (actor instanceof Bala || actor instanceof Jugador || actor instanceof BalaEspecial
				|| actor instanceof ExplosionB) {

			pantalla.getJuego().getRecurso().playSonido("explosion.wav");
			if (vida == 0) {

				explosion();
				remover = true;

			}

			choque = true;

		}

	}
}
