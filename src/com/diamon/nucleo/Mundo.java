package com.diamon.nucleo;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.diamon.actor.Jugador;
import com.diamon.dato.InformacionDeNiveles;
import com.diamon.utilidad.Recurso;

public abstract class Mundo {

	protected Pantalla pantalla;

	protected Jugador jugador;

	protected Juego juego;

	protected ArrayList<Actor> actores;

	protected Recurso recurso;

	protected boolean moverCamara;

	protected InformacionDeNiveles datosNiveles;

	protected Camara2D camara;

	public Mundo(Pantalla pantalla, Jugador jugador) {

		this.pantalla = pantalla;

		this.juego = pantalla.juego;

		this.actores = pantalla.actores;

		this.jugador = jugador;

		this.camara = pantalla.camara;

		this.recurso = pantalla.juego.getRecurso();

		this.datosNiveles = juego.getDatosNiveles();

		iniciar();
	}

	protected abstract void iniciar();

	public abstract void actualizar(float delta);

	public abstract void dibujar(Graphics2D pincel, float delta);

	public abstract void guardarDatos();

	public abstract void liberarRecursos();

	public boolean isMoverCamara() {
		return moverCamara;
	}

	public void setMoverCamara(boolean moverCamara) {
		this.moverCamara = moverCamara;
	}

}
