package com.diamon.nucleo;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import com.diamon.dato.Configuraciones;
import com.diamon.utilidad.Recurso;

public abstract class Actor implements ImageObserver {

	protected int x;

	protected int y;

	protected Dimension tamano;

	protected boolean remover;

	private int cuadros;

	private int timpo;

	private int frames;

	protected BufferedImage[] imagenes;

	protected Pantalla pantalla;

	protected Recurso recurso;

	protected Configuraciones configuracion;

	protected ArrayList<Actor> actores;
	
	public Camara2D camara;

	private boolean animar;

	public Actor(Pantalla pantalla) {

		this.pantalla = pantalla;

		this.recurso = pantalla.recurso;

		this.configuracion = pantalla.configuracion;

		actores = pantalla.actores;
		
		this.camara = pantalla.camara;

		x = 0;

		y = 0;

		timpo = 0;

		frames = 0;

		cuadros = 1;

		imagenes = null;

		tamano = new Dimension(0, 0);

		remover = false;

		animar = false;

	}

	public void setPosicion(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public void setCuadros(int cuadros) {
		this.cuadros = cuadros;

		animar = true;
	}

	public void setImagenes(BufferedImage... imagenes) {

		this.imagenes = imagenes;

	}

	public boolean isRemover() {
		return remover;
	}

	public void actualizar(float delta) {

		if (animar) {
			timpo++;

			if (timpo % cuadros == 0) {
				timpo = 0;
				frames = (frames + 1) % imagenes.length;

			}

		}

	}

	public void dibujar(Graphics2D pincel, float delta) {

		pincel.drawImage(imagenes[frames], x, y, tamano.width, tamano.height, this);

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
