package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.juego.FinalMision;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaFinDeJuego extends Pantalla {

	private int ciclo;

	private BufferedImage fondo;

	public PantallaFinDeJuego(FinalMision juego) {
		super(juego);

		ciclo = 0;

		fondo = juego.getRecurso().getImagen("finJuego.png");

		juego.getRecurso().playSonido("finDeJuego.wav");
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

		if (ciclo % 340 == 0) {

			juego.getRecurso().pararSonido(juego.getRecurso().getSonido("musica.wav"));

			juego.setPantalla(new PantallaContinuar(juego));

			ciclo = 0;

		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		pincel.drawImage(fondo, 0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

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
		// TODO Auto-generated method stub

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
