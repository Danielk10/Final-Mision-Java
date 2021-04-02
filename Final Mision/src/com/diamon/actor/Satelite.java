package com.diamon.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Pantalla;

public class Satelite extends Actor {

	private int cicloDisparo;

	private boolean disparar;

	private int lado;

	public final static int LADO_DERECHO = 1;

	public final static int LADO_IZQUIERDO = 2;

	private boolean activar;

	public Satelite(Pantalla pantalla) {
		super(pantalla);

		cicloDisparo = 0;

		disparar = false;

		activar = false;

		lado = 0;
	}

	public boolean isActivar() {
		return activar;
	}

	public void setActivar(boolean activar) {
		this.activar = activar;
	}

	@Override
	public void actualizar(float delta) {
		// TODO Auto-generated method stub
		super.actualizar(delta);

		cicloDisparo++;

		if (disparar) {

			if (cicloDisparo % 20 == 0) {

				disparar();

				cicloDisparo = 0;

			}

		}

		if (lado == Satelite.LADO_DERECHO) {

		}

		if (lado == Satelite.LADO_IZQUIERDO) {

		}

	}

	public int getLado() {
		return lado;
	}

	public void setLado(int lado) {
		this.lado = lado;
	}

	public boolean isDisparar() {
		return disparar;
	}

	public void setDisparar(boolean disparar) {
		this.disparar = disparar;
	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		if (!activar) {

			this.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("sateliteHD1.png") });

		}

		if (activar) {

			this.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("sateliteHD2.png") });

		}
		super.dibujar(pincel, delta);
	}

	public void disparar() {

		Bala bala = new Bala(pantalla);

		bala.setTamano(8, 8);

		bala.setPosicion(x, y + 4);

		bala.setLado(true);

		bala.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("balaSatelite.png") });

		pantalla.getActores().add(bala);

	}

	@Override
	public void colision(Actor actor) {
		// TODO Auto-generated method stub

	}

}
