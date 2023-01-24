package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.actor.Jugador;
import com.diamon.juego.FinalMision;
import com.diamon.nivel.Nivel;
import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.utilidad.EditorDeNiveles;

public class PantallaExtra extends Pantalla {

	private Nivel mundo;

	private Jugador jugador;

	private EditorDeNiveles editor;

	private boolean pausa;

	private int xCamara;

	public PantallaExtra(FinalMision juego) {
		super(juego);

		pausa = true;

		xCamara = 0;
	}

	@Override
	public void pausa() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {

	}

	@Override
	public void actualizar(float delta) {

		if (pausa) {

			if (mundo != null) {

				xCamara++;

				camara.setX(xCamara);

				mundo.actualizar(delta);

			}

		} else {
			editor.actualizar(delta);
		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		if (mundo != null) {

			mundo.dibujar(pincel, delta);

		}

	}

	@Override
	public void colisiones() {

		for (int i = 0; i < actores.size(); i++) {

			Actor a1 = (Actor) actores.get(i);

			Rectangle r1 = a1.getRectangulo();

			for (int j = i + 1; j < actores.size(); j++) {
				Actor a2 = (Actor) actores.get(j);
				Rectangle r2 = a2.getRectangulo();
				if (r1.intersects(r2)) {

					a1.colision(a2);
					a2.colision(a1);

				}
			}

			Actor actor = (Actor) actores.get(i);

			if (actor.isRemover()) {

				actores.remove(i);

			}

		}

	}

	@Override
	public void ocultar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrar() {

		jugador = new Jugador(this);

		jugador.setTamano(64, 64);

		jugador.setPosicion(0, (Juego.ALTO_PANTALLA / 3) + jugador.getTamano().width);

		jugador.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("jugador1D1.png") });

		actores.add(jugador);

		editor = new EditorDeNiveles(this);

		mundo = new Nivel(this, jugador);

	}

	@Override
	public void reajustarPantalla(int ancho, int alto) {

	}

	@Override
	public void teclaPresionada(KeyEvent ev) {

		jugador.teclaPresionada(ev);

		switch (ev.getKeyCode()) {

		case KeyEvent.VK_ENTER:

			if (jugador.getVida() != 0) {
				if (pausa) {
					juego.getRecurso().pararSonido(juego.getRecurso().getSonido("musica.wav"));
					juego.getRecurso().playSonido("pausa.wav");
				} else {

					juego.getRecurso().repetirSonido("musica.wav");
				}

				pausa = !pausa;

				jugador.setPausado(pausa);

			}

			break;

		case KeyEvent.VK_J:

			break;

		default:

			break;

		}

		if (!pausa) {

			editor.teclaPresionada(ev);
		}

	}

	@Override
	public void teclaLevantada(KeyEvent ev) {

		jugador.teclaLevantada(ev);

		if (!pausa) {
			editor.teclaLevantada(ev);
		}

	}

	@Override
	public void teclaTipo(KeyEvent ev) {

		if (!pausa) {
			editor.teclaTipo(ev);
		}

	}

	@Override
	public void ratonDeslizando(MouseEvent ev) {

		jugador.ratonDeslizando(ev);

		if (!pausa) {
			editor.ratonDeslizando(ev);
		}

	}

	@Override
	public void ratonMoviendo(MouseEvent ev) {

		jugador.ratonMoviendo(ev);

		if (!pausa) {

			editor.ratonMoviendo(ev);
		}

	}

	@Override
	public void ratonClick(MouseEvent ev) {
		jugador.ratonClick(ev);

		if (!pausa) {
			editor.ratonClick(ev);
		}

	}

	@Override
	public void ratonPresionado(MouseEvent ev) {
		jugador.ratonPresionado(ev);

		if (!pausa) {
			editor.ratonPresionado(ev);
		}

	}

	@Override
	public void ratonLevantado(MouseEvent ev) {

		jugador.ratonLevantado(ev);

		if (!pausa) {
			editor.ratonLevantado(ev);
		}

	}

}
