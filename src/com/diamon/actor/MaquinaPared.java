package com.diamon.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class MaquinaPared extends Actor {

	private int cicloExplosion;

	private int cicloDisparo;

	private boolean disparar;

	private int velocidadY;

	private int mover;

	public final static int MOVER_ARRIBA = 1;

	public final static int MOVER_ABAJO = 2;

	public final static int LADO_IZQUIERDO = 3;

	public final static int LADO_DERECHO = 4;

	private int vida;

	private boolean choque;

	private int cicloChoque;

	private int lado;

	private Jugador jugador;

	public MaquinaPared(Pantalla pantalla) {
		super(pantalla);

		cicloExplosion = 0;

		cicloDisparo = 0;

		mover = 0;

		cicloChoque = 0;

		velocidadY = 0;

		disparar = false;

		choque = false;

		vida = 4;

		obtenerJugador();

	}

	private void obtenerJugador() {

		for (int i = 0; i < pantalla.getActores().size(); i++) {

			if (pantalla.getActores().get(i) instanceof Jugador) {

				jugador = (Jugador) pantalla.getActores().get(i);

			}

		}

	}

	@Override
	public void actualizar(float delta) {
		// TODO Auto-generated method stub
		super.actualizar(delta);

		cicloExplosion++;

		cicloDisparo++;

		cicloChoque++;

		if (cicloExplosion % 30 == 0) {

			for (int i = 0; i < pantalla.getActores().size(); i++) {

				if (pantalla.getActores().get(i) instanceof Explosion) {
					Explosion e = (Explosion) pantalla.getActores().get(i);

					e.remover();

				}
			}

			cicloExplosion = 0;

		}

		if (jugador != null) {

			if (jugador.getY() <= y + tamano.height && jugador.getY() >= y && jugador.getX() <= x)

			{

				disparar = true;

			} else {

				disparar = false;

			}

		}

		if (disparar) {

			cicloDisparo++;

			if (cicloDisparo % 10 == 0) {

				disparar();

				cicloDisparo = 0;
			}
		}

		if (mover == MaquinaPared.MOVER_ABAJO) {

			y += velocidadY;

			if (y >= Juego.ALTO_PANTALLA) {

				remover = true;
			}

		}

		if (mover == MaquinaPared.MOVER_ARRIBA) {

			y -= velocidadY;

			if (y <= -tamano.height) {

				remover = true;
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

	public int getMover() {
		return mover;
	}

	public void setMover(int mover) {
		this.mover = mover;
	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		if (lado == MaquinaPared.LADO_IZQUIERDO) {

			if (disparar)

			{

				setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("maquinaParedI2.png") });

			} else {

				setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("maquinaParedI1.png") });

			}

		}

		if (lado == MaquinaPared.LADO_DERECHO) {

			if (disparar)

			{

				setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("maquinaParedD2.png") });

			} else {

				setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("maquinaParedD1.png") });

			}

		}

		super.dibujar(pincel, delta);
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

		if (lado == MaquinaPared.LADO_IZQUIERDO) {

			BalaEnemigo bala = new BalaEnemigo(pantalla);

			bala.setTamano(32, 12);

			bala.setPosicion(x, y + 12);

			bala.setLado(BalaEnemigo.LADO_IZQUIERDO);

			bala.setMover(BalaEnemigo.MOVER_ABAJO);

			bala.setVelocidadY(1);

			bala.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaParedI.png") });

			if (bala.getX() <= 640) {

				pantalla.getActores().add(bala);
			}

		}

		if (lado == MaquinaPared.LADO_DERECHO) {

			BalaEnemigo bala = new BalaEnemigo(pantalla);

			bala.setTamano(32, 12);

			bala.setPosicion(x + 32, y + 12);

			bala.setLado(BalaEnemigo.LADO_DERECHO);

			bala.setMover(BalaEnemigo.MOVER_ARRIBA);

			bala.setVelocidadY(1);

			bala.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaParedD.png") });

			if (bala.getX() <= 640) {

				pantalla.getActores().add(bala);
			}

		}

	}

	@Override
	public void colision(Actor actor) {

		if (actor instanceof Bala || actor instanceof Jugador || actor instanceof BalaEspecial
				|| actor instanceof ExplosionB) {

			pantalla.getJuego().getRecurso().playSonido("explosion.wav");

			choque = true;

			if (vida == 0) {

				vida = 0;

				explosion();

				remover = true;

			}

		}

	}

}
