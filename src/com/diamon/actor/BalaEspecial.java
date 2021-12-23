package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class BalaEspecial extends Actor {

	public final static byte PODER_S = 1;

	public final static byte BALA_W = 2;

	public final static byte BALA_L = 3;

	public final static byte BALA_B = 4;

	public final static int VELOCIDAD_BALA = 10;

	private boolean lado;

	private byte bala;

	private int cicloExplosion;

	private int velocidad;

	public BalaEspecial(Pantalla pantalla) {
		super(pantalla);

		lado = true;

		bala = 0;

		cicloExplosion = 0;

		velocidad = VELOCIDAD_BALA;
	}

	public byte getBala() {
		return bala;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void setBala(byte bala) {
		this.bala = bala;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (lado) {
			x += velocidad;

			if (x >= Juego.ANCHO_PANTALLA) {

				remover = true;
			}

		} else {

			x -= velocidad;

			if (x <= -tamano.width) {

				remover = true;
			}

		}

		cicloExplosion++;

		if (cicloExplosion % 5 == 0) {

			for (int i = 0; i < pantalla.getActores().size(); i++) {

				if (pantalla.getActores().get(i) instanceof ExplosionB) {
					ExplosionB e = (ExplosionB) pantalla.getActores().get(i);

					e.remover();

				}
			}

			cicloExplosion = 0;

		}

	}

	public void explosion() {

		ExplosionB explosion = new ExplosionB(pantalla);

		explosion.setTamano(64, 64);

		explosion.setPosicion(x - 32, y - 32);

		explosion.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("explosionB2.png"),
				pantalla.getJuego().getRecurso().getImagen("explosionB3.png"),
				pantalla.getJuego().getRecurso().getImagen("explosionB4.png"),
				pantalla.getJuego().getRecurso().getImagen("explosionB5.png") });

		explosion.setCuadros(2);

		if (explosion.getX() <= 640) {

			pantalla.getActores().add(explosion);

		}

	}

	public void setLado(boolean lado) {
		this.lado = lado;
	}

	@Override
	public void colision(Actor actor) {

		if (actor instanceof Volador || actor instanceof LanzaMisil || actor instanceof Caja
				|| actor instanceof MaquinaFinal || actor instanceof MaquinaPared || actor instanceof Robot
				|| actor instanceof Saltador || actor instanceof Misil || actor instanceof AntiAereo || actor instanceof BalaEnemigoDestruible) {
			if (bala == BalaEspecial.BALA_B) {
				explosion();
			}
			remover = true;

		}

	}

}
