package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.juego.FinalMision;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaFinal extends Pantalla {

	private BufferedImage fondo;

	private BufferedImage creditos;

	private BufferedImage menuFinal;

	private BufferedImage selector;

	private int posicionY;

	private boolean cambio;

	private boolean cambio2;

	private int ciclo;

	public PantallaFinal(FinalMision juego) {
		super(juego);

		fondo = juego.getRecurso().getImagen("finNivel.png");

		creditos = juego.getRecurso().getImagen("creditos.png");

		menuFinal = juego.getRecurso().getImagen("menuFinal.png");

		selector = juego.getRecurso().getImagen("selector2.png");

		posicionY = 288;

		cambio = false;

		cambio2 = false;

		ciclo = 0;
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

		if (ciclo % 300 == 0) {

			cambio = true;

		}

		if (ciclo % 2000 == 0) {

			cambio2 = true;

			ciclo = 0;

		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		if (cambio) {
			pincel.drawImage(creditos, 0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

		} else {

			pincel.drawImage(fondo, 0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

		}

		if (cambio2) {
			pincel.drawImage(menuFinal, 0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

			pincel.drawImage(selector, 202, posicionY, 16, 16, this);

		}

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

			if (cambio2) {
				if (posicionY == 288) {

					juego.setPantalla(new PantallaMenu(juego));
				}

				if (posicionY == 322) {

					juego.setPantalla(new PantallaExtra(juego));
				}

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
