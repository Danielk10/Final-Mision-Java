package com.diamon.nucleo;

public class Camara2D {

	private int x;

	private int y;

	private int ancho;

	private int alto;

	private int velocidad;

	private int orientacion;

	private int lado;

	public final static int HORIZONTAL = 0;

	public final static int VERTICAL = 1;

	public final static int ARRIBA = 2;

	public final static int ABAJO = 3;

	public final static int IZQUIERDA = 4;

	public final static int DERECHA = 5;

	public Camara2D(int x, int y, int ancho, int alto) {

		this.x = x;

		this.y = y;

		this.ancho = ancho;

		this.alto = alto;
	}

	public int getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(int orientacion, int lado) {

		this.orientacion = orientacion;

		this.lado = lado;
	}

	public void actualizar(float delta) {

		if (orientacion == Camara2D.HORIZONTAL) {

			if (lado == Camara2D.DERECHA) {

				x += velocidad;
			}

			if (lado == Camara2D.IZQUIERDA) {
				x -= velocidad;

			}

		}

		if (orientacion == Camara2D.VERTICAL) {

			if (lado == Camara2D.ARRIBA) {

				y -= velocidad;
			}

			if (lado == Camara2D.ABAJO) {
				y += velocidad;

			}
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

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {

		this.velocidad = velocidad;

	}

}
