package com.diamon.nucleo;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public abstract class Actor implements ImageObserver {

	protected int x;

	protected int y;

	protected Dimension tamano;

	protected boolean remover;

	protected float cuadros;

	private float tiempo;

	private int frames;

	protected BufferedImage[] imagenes;

	protected Pantalla pantalla;

	protected Animacion animacion;

	private boolean animar;

	public Actor(Pantalla pantalla) {

		this.pantalla = pantalla;

		x = 0;

		y = 0;

		tiempo = 0;

		frames = 0;

		cuadros = 1;

		imagenes = null;

		tamano = new Dimension(0, 0);

		remover = false;

		animar = false;

		animacion = null;

	}

	public void setPosicion(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void setCuadros(float cuadros) {

		this.cuadros = cuadros;

	}

	public void setImagenes(BufferedImage... imagenes) {

		this.imagenes = imagenes;

		if (imagenes.length > 0) {

			animar = true;

		}

		if (animar) {

			animacion = new Animacion(cuadros / Juego.FPS, imagenes);

			animacion.setModo(Animacion.REPETIR);

		}

	}

	public boolean isRemover() {
		return remover;
	}

	public void actualizar(float delta) {

		if (animar) {

			if (delta == 0) {

				return;

			}

			if (delta > 0.1f) {

				delta = 0.1f;
			}

			tiempo += delta;

		}

	}

	public void dibujar(Graphics2D pincel, float delta) {

		if (animar) {

			if (animacion != null) {

				pincel.drawImage(animacion.getKeyFrame(tiempo), x, y, tamano.width, tamano.height, this);

			}

		} else {

			pincel.drawImage(imagenes[frames], x, y, tamano.width, tamano.height, this);

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

	public Rectangle getRectangulo() {
		return new Rectangle(x, y, tamano.width, tamano.height);

	}

	public void setTamano(int ancho, int alto) {

		tamano.setSize(ancho, alto);

	}

	public Dimension getTamano() {

		return tamano;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		return (infoflags & (ALLBITS | ABORT)) == 0;
	}

	public abstract void colision(Actor actor);

	public void remover() {
		remover = true;
	}

}
