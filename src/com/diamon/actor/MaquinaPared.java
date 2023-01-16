package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Animacion;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class MaquinaPared extends Actor {

	private float tiempoCuadro;

	private float duracionDisparo;

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

	private Animacion animacion1;

	private Animacion animacion2;

	public MaquinaPared(Pantalla pantalla) {
		super(pantalla);

		duracionDisparo = 0;

		mover = 0;

		cicloChoque = 0;

		velocidadY = 0;

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

		cicloChoque++;

		if (jugador != null) {

			if (lado == MaquinaPared.LADO_DERECHO) {

				if (jugador.getY() <= y + tamano.height && jugador.getY() + jugador.getTamano().height >= y
						&& jugador.getX() >= x + tamano.width)

				{

					if (animacion1 != null) {

						animacion = animacion1;
					}

					if (tiempoCuadro == 0)

					{

						disparar();
					}

					tiempoCuadro += delta;

					if (tiempoCuadro / duracionDisparo >= 1) {

						disparar();

						tiempoCuadro = 0;
					}

				} else {

					if (animacion2 != null) {

						animacion = animacion2;
					}

				}

			}

			if (lado == MaquinaPared.LADO_IZQUIERDO) {

				if (jugador.getY() <= y + tamano.height && jugador.getY() + jugador.getTamano().height >= y
						&& jugador.getX() <= x)

				{

					if (animacion1 != null) {

						animacion = animacion1;
					}

					if (tiempoCuadro == 0)

					{

						disparar();
					}

					tiempoCuadro += delta;

					if (tiempoCuadro / duracionDisparo >= 1) {

						disparar();

						tiempoCuadro = 0;
					}

				} else {

					if (animacion2 != null) {

						animacion = animacion2;
					}

				}

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

	public void setDuracionDisparo(float duracionDisparo) {
		this.duracionDisparo = duracionDisparo;
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

		if (lado == MaquinaPared.LADO_DERECHO) {

			setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("maquinaParedD2.png"),
					pantalla.getJuego().getRecurso().getImagen("maquinaParedD1.png") });

			animacion1 = new Animacion(cuadros / Juego.FPS, imagenes[0]);

			animacion1.setModo(Animacion.NORMAL);

			animacion = animacion1;

			animacion2 = new Animacion(cuadros / Juego.FPS, imagenes[1]);

			animacion2.setModo(Animacion.NORMAL);

			animacion = animacion2;

		}

		if (lado == MaquinaPared.LADO_IZQUIERDO) {

			setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("maquinaParedI2.png"),
					pantalla.getJuego().getRecurso().getImagen("maquinaParedI1.png") });

			animacion1 = new Animacion(cuadros / Juego.FPS, imagenes[0]);

			animacion1.setModo(Animacion.NORMAL);

			animacion = animacion1;

			animacion2 = new Animacion(cuadros / Juego.FPS, imagenes[1]);

			animacion2.setModo(Animacion.NORMAL);

			animacion = animacion2;

		}

	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
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
