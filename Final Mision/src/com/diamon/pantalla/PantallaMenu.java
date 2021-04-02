package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.juego.FinalMision;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaMenu extends Pantalla {

	private BufferedImage fondo;

	private BufferedImage selector;

	private int posicionY;

	private int ciclo;

	public PantallaMenu(FinalMision juego) {
		super(juego);

		fondo = juego.getRecurso().getImagen("menu2.png");

		selector = juego.getRecurso().getImagen("selector1.png");

		posicionY = 320;

		juego.getRecurso().playSonido("menu.wav");

		ciclo = 0;

	}

	@Override
	public void pausa() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void actualizar(float delta) {

		ciclo++;

		if (ciclo % 300 == 0) {

			juego.setPantalla(new PantallaIntroduccion(juego));

			ciclo = 0;
		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		pincel.drawImage(fondo, 0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

		pincel.drawImage(selector, 186, posicionY, 16, 16, this);

	}

	@Override
	public void colisiones() {

	}

	@Override
	public void ocultar() {

	}

	@Override
	public void mostrar() {

	}

	@Override
	public void reajustarPantalla(int ancho, int alto) {

	}

	@Override
	public void teclaPresionada(KeyEvent ev) {

		switch (ev.getKeyCode()) {

		case KeyEvent.VK_ENTER:

			if (posicionY == 320) {
				juego.setPantalla(new PantallaNivel(juego));
			}

			if (posicionY == 354) {
				juego.setPantalla(new PantallaMenu(juego));
			}

			break;

		case KeyEvent.VK_UP:

			posicionY = 320;

			break;
		case KeyEvent.VK_DOWN:

			posicionY = 354;

			break;

		case KeyEvent.VK_A:

			juego.setPantalla(new PantallaAyuda(juego));

			break;

		default:

			break;

		}

	}

	@Override
	public void teclaLevantada(KeyEvent ev) {

	}

	@Override
	public void teclaTipo(KeyEvent ev) {

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
