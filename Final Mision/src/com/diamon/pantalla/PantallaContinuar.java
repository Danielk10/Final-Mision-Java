package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.juego.FinalMision;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaContinuar extends Pantalla {

	private BufferedImage fondo;

	private BufferedImage selector;

	private int posicionY;

	public PantallaContinuar(FinalMision juego) {
		super(juego);

		fondo = juego.getRecurso().getImagen("continuar.png");

		selector = juego.getRecurso().getImagen("selector2.png");

		posicionY = 288;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		pincel.drawImage(fondo, 0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

		pincel.drawImage(selector, 202, posicionY, 16, 16, this);

	}

	@Override
	public void colisiones() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ocultar() {
		// TODO Auto-generated method stub

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

			if (posicionY == 288) {

				juego.setPantalla(new PantallaNivel(juego));
			}

			if (posicionY == 322) {

				juego.setPantalla(new PantallaMenu(juego));
			}

			break;

		case KeyEvent.VK_UP:

			posicionY = 288;

			break;
		case KeyEvent.VK_DOWN:

			posicionY = 322;

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

}
