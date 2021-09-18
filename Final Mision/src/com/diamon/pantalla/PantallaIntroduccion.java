package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaIntroduccion extends Pantalla {

	private int ciclo;

	private BufferedImage fondo1;

	private BufferedImage fondo2;

	private int x;

	public PantallaIntroduccion(Juego juego) {
		super(juego);

		fondo1 = recurso.getImagen("fondoIntroduccion3.png");

		fondo2 = recurso.getImagen("fondoIntroduccion3.png");

		ciclo = 0;

		recurso.playSonido("introduccion.wav");

		x = 0;
	}

	@Override
	public void pausa() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(float delta) {

		ciclo++;

		if (ciclo % 6330 == 0) {

			juego.setPantalla(new PantallaMenu(juego));

			ciclo = 0;
		}

		x--;

		if (x <= -Juego.ANCHO_PANTALLA) {

			x = 0;
		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		pincel.drawImage(fondo1, x, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

		pincel.drawImage(fondo2, x + Juego.ANCHO_PANTALLA, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

	}

	@Override
	public void colisiones() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ocultar() {

		recurso.pararSonido(recurso.getSonido("introduccion.wav"));

	}

	@Override
	public void mostrar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reajustarPantalla(int ancho, int alto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void teclaPresionada(KeyEvent ev) {

		switch (ev.getKeyCode()) {

		case KeyEvent.VK_ENTER:

			juego.setPantalla(new PantallaMenu(juego));

			break;

		default:

			break;

		}

	}

	@Override
	public void teclaLevantada(KeyEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void teclaTipo(KeyEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonDeslizando(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonMoviendo(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonClick(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonPresionado(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonLevantado(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void guardarDatos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void liberarRecursos() {
		// TODO Auto-generated method stub
		
	}

}
