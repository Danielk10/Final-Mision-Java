package com.diamon.nucleo;

public class Camara2D {

	private int x;
	private int y;
	private int ancho;
	private int alto;
	private int volocidad;
	private boolean mover;

	public Camara2D(int x, int y, int ancho, int alto) {

		this.x = x;

		this.y = y;

		this.ancho = ancho;

		this.alto = alto;
	}

	public void actualizar(float delta) {

		if (mover) {

			x += volocidad;
		}

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}

	public int getVolocidad() {
		return volocidad;
	}

	public void setVolocidad(int volocidad) {

		this.volocidad = volocidad;

		if (this.volocidad == 0) {

			mover = false;
		} else {
			mover = true;
		}
	}

}
