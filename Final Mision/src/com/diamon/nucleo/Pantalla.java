package com.diamon.nucleo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import com.diamon.dato.Configuraciones;
import com.diamon.utilidad.Recurso;

public abstract class Pantalla implements ImageObserver {

	protected final Juego juego;

	protected ArrayList<Actor> actores;

	protected Recurso recurso;

	protected Configuraciones configuracion;

	protected Camara2D camara;

	public Pantalla(Juego juego) {

		this.juego = juego;

		this.recurso = juego.recurso;

		this.configuracion = juego.configuracion;

		camara = juego.camara;

		actores = new ArrayList<Actor>();

	}

	public abstract void pausa();

	public abstract void resume();

	public abstract void colisiones();

	public abstract void actualizar(float delta);

	public abstract void dibujar(Graphics2D pincel, float delta);

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		return (infoflags & (ALLBITS | ABORT)) == 0;
	}

	public abstract void ocultar();

	public abstract void mostrar();

	public abstract void reajustarPantalla(int ancho, int alto);

	public abstract void teclaPresionada(KeyEvent ev);

	public abstract void teclaLevantada(KeyEvent ev);

	public abstract void teclaTipo(KeyEvent ev);

	public abstract void ratonDeslizando(MouseEvent ev);

	public abstract void ratonMoviendo(MouseEvent ev);

	public abstract void ratonClick(MouseEvent ev);

	public abstract void ratonPresionado(MouseEvent ev);

	public abstract void ratonLevantado(MouseEvent ev);

	public abstract void guardarDatos();

	public abstract void liberarRecursos();

}
