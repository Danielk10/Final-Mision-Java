package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import com.diamon.juego.FinalMision;
import com.diamon.nucleo.Pantalla;
import com.diamon.utilidad.EditorDeNiveles;

public class PantallaExtra extends Pantalla {

	private EditorDeNiveles editor;

	private boolean pausa;

	public PantallaExtra(FinalMision juego) {
		super(juego);

		editor = new EditorDeNiveles(this);

		pausa = false;
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

		if (pausa) {

			editor.actualizar(delta);

		} else

		{

			for (int i = 0; i < actores.size(); i++) {

				actores.get(i).actualizar(delta);

			}

		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		if (pausa) {

			editor.dibujar(pincel, delta);

		} else

		{
			for (int i = 0; i < actores.size(); i++) {

				actores.get(i).dibujar(pincel, delta);

			}

		}

	}

	@Override
	public void colisiones() {

		editor.colisiones();

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

	}

	@Override
	public void teclaPresionada(KeyEvent ev) {

		editor.teclaPresionada(ev);

		switch (ev.getKeyCode()) {

		case KeyEvent.VK_E:

			pausa = true;

			break;

		case KeyEvent.VK_R:

			pausa = false;

			break;

		default:

			break;

		}

	}

	@Override
	public void teclaLevantada(KeyEvent ev) {
		editor.teclaLevantada(ev);

	}

	@Override
	public void teclaTipo(KeyEvent ev) {
		editor.teclaTipo(ev);

	}

	@Override
	public void ratonDeslizando(MouseEvent ev) {
		editor.ratonDeslizando(ev);

	}

	@Override
	public void ratonMoviendo(MouseEvent ev) {

		editor.ratonMoviendo(ev);

	}

	@Override
	public void ratonClick(MouseEvent ev) {
		editor.ratonClick(ev);

	}

	@Override
	public void ratonPresionado(MouseEvent ev) {

		editor.ratonPresionado(ev);

	}

	@Override
	public void ratonLevantado(MouseEvent ev) {
		editor.ratonLevantado(ev);

	}

}
