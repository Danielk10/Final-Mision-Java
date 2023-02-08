package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.actor.Jugador;
import com.diamon.actor.JugadorMuriendo;
import com.diamon.juego.FinalMision;
import com.diamon.nivel.Nivel;
import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Camara2D;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;
import com.diamon.utilidad.EditorDeNiveles;

public class PantallaJuego extends Pantalla {

	private boolean pausa;

	private int ciclo;

	private int cicloIntro;

	private Jugador jugador;

	private boolean musicaMuriendo1;

	private boolean musicaMuriendo2;

	private int cicloMuriendo;

	private boolean musicaIntro1;

	private boolean musicaIntro2;

	private EditorDeNiveles editor;

	private Nivel mundo;

	public PantallaJuego(FinalMision juego) {
		super(juego);

		camara.setOrientacion(Camara2D.HORIZONTAL, Camara2D.DERECHA);

		pausa = true;

		ciclo = 0;

		cicloIntro = 0;

		musicaMuriendo1 = false;

		musicaMuriendo2 = true;

		musicaIntro1 = false;
		musicaIntro2 = true;

		cicloMuriendo = 0;

		editor = new EditorDeNiveles(this);

		iniciar();

		mundo = new Nivel(this, jugador);

	}

	private void moverCamara() {

		if (camara.getX() <= 2559) {

		} else {

			camara.setOrientacion(Camara2D.VERTICAL, Camara2D.ARRIBA);
		}

	}

	private void iniciar() {

		jugador = new Jugador(this);

		jugador.setTamano(64, 64);

		jugador.setPosicion(0, (Juego.ALTO_PANTALLA / 3) + jugador.getTamano().width);

		jugador.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("jugador1D1.png") });

		actores.add(jugador);

		if (juego.getConfiguracion().isSonido()) {

			juego.getRecurso().playSonido("comienzo1.wav");

		}

	}

	@Override
	public void pausa() {

	}

	@Override
	public void resume() {

	}

	public void jugadorMuriendo() {

		JugadorMuriendo j = new JugadorMuriendo(this);

		j.setTamano(64, 64);

		j.setPosicion(jugador.getX(), jugador.getY());

		j.setCuadros(15);

		j.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("jugador1D4.png"),
				juego.getRecurso().getImagen("jugador1D5.png"), juego.getRecurso().getImagen("jugador1D6.png") });

		actores.add(j);

	}

	@Override
	public void actualizar(float delta) {

		if (pausa) {

			if (mundo != null) {

				camara.actualizar(delta);

				moverCamara();

				mundo.actualizar(delta);

			}

		} else {
			editor.actualizar(delta);
		}

		if (musicaIntro2) {

			cicloIntro++;

			if (cicloIntro % 220 == 0) {

				musicaIntro2 = false;

				musicaIntro1 = true;

				cicloIntro = 0;

			}

		}

		if (musicaIntro1) {

			juego.getRecurso().repetirSonido("musica.wav");

			musicaIntro1 = false;
		}

		if (jugador.getVida() <= 0) {

			ciclo++;

			if (ciclo % 200 == 0) {

				juego.setPantalla(new PantallaFinDeJuego(juego));

				ciclo = 0;
			}

		}

		if (mundo.getMaquina().getVida() == 0) {

			ciclo++;

			if (ciclo % 100 == 0) {

				juego.getRecurso().pararSonido(juego.getRecurso().getSonido("musica.wav"));

				juego.setPantalla(new PantallaFinal(juego));

				ciclo = 0;

			}

		}

		cicloMuriendo++;

		if (jugador.getVida() <= 0) {

			musicaMuriendo1 = true;

			if (cicloMuriendo % 50 == 0) {

				for (int i = 0; i < actores.size(); i++) {

					if (actores.get(i) instanceof JugadorMuriendo) {
						JugadorMuriendo j = (JugadorMuriendo) actores.get(i);

						j.remover();

					}
				}

				cicloMuriendo = 0;

			}

		}

		if (musicaMuriendo1) {

			if (musicaMuriendo2) {

				juego.getRecurso().pararSonido(juego.getRecurso().getSonido("musica.wav"));

				juego.getRecurso().playSonido("muriendo.wav");

				jugadorMuriendo();

				musicaMuriendo2 = false;
			}

		}

	}

	int x, y;

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

		removerVida();

	}

	private void removerVida() {

		if (jugador.getVida() == 0) {

			mundo.getVidas()[jugador.getVida()].remover();
		}

		if (jugador.getVida() == 1) {

			mundo.getVidas()[jugador.getVida()].remover();
		}

		if (jugador.getVida() == 2) {

			mundo.getVidas()[jugador.getVida()].remover();
		}

	}

	@Override
	public void ocultar() {

		juego.getConfiguracion().guardarConfiguraciones();
	}

	@Override
	public void mostrar() {

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
