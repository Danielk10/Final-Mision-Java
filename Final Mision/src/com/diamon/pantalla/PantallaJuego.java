package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.diamon.actor.AntiAereo;
import com.diamon.actor.Jugador;
import com.diamon.actor.Vida;
import com.diamon.actor.Volador;
import com.diamon.dato.Configuraciones;
import com.diamon.nivel.Niveles;
import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Nivel;
import com.diamon.nucleo.Pantalla;

public class PantallaJuego extends Pantalla {

	private boolean pausa;

	public Vida[] vidas;

	private int ciclo;

	private int cicloIntro;

	private Jugador jugador;

	private boolean musicaIntro1;

	private boolean musicaIntro2;

	private Nivel[] niveles;

	private Nivel nivel;

	private int numeroNivel;

	private String tipo = Configuraciones.ANTI_AEREO;

	public PantallaJuego(Juego juego) {
		super(juego);

	}

	private void iniciar() {

		numeroNivel = configuracion.getNumeroNivel();

		niveles = new Nivel[5];

		jugador = new Jugador(this);

		jugador.setTamano(64, 64);

		jugador.setImagenes(new BufferedImage[] { recurso.getImagen("jugador1D1.png") });

		jugador.setNivelTerminado(true);

		recurso.playSonido("comienzo1.wav");

		niveles[0] = new Niveles(this, jugador);

		nivel = niveles[0];

		camara.posicion.x = 0;

	}

	@Override
	public void pausa() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void actualizar(float delta) {

		if (pausa) {

			nivel.actualizar(delta);

			camara.posicion.x++;

		}

		if (jugador.isNivelTerminado()) {

			for (int i = 0; i < niveles.length; i++) {

				if (numeroNivel == i + 1) {

					configuracion.setNumeroNivel(numeroNivel);

					nivel.liberarRecursos();

					niveles[i] = new Niveles(this, jugador);

					nivel = niveles[i];

				}

			}

			numeroNivel++;

			jugador.setNivelTerminado(false);

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

			recurso.repetirSonido("musica.wav");

			musicaIntro1 = false;
		}

		if (jugador.getVida() <= 0) {

			ciclo++;

			if (ciclo % 200 == 0) {

				juego.setPantalla(new PantallaFinDeJuego(juego));

				ciclo = 0;
			}

		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		nivel.dibujar(pincel, delta);

	}

	@Override
	public void colisiones() {

		for (int i = 0; i < actores.size(); i++) {

			Actor actor1 = (Actor) actores.get(i);

			Rectangle rectangulo1 = actor1.getRectangulo();

			for (int j = i + 1; j < actores.size(); j++) {

				Actor actor2 = (Actor) actores.get(j);

				Rectangle rectangulo2 = actor2.getRectangulo();

				if (rectangulo1.intersects(rectangulo2)) {

					actor1.colision(actor2);

					actor2.colision(actor1);

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

		if (jugador.getVida() == 2) {

			vidas[jugador.getVida()].remover();
		}
		if (jugador.getVida() == 1) {

			vidas[jugador.getVida()].remover();
		}

		if (jugador.getVida() == 0) {

			vidas[jugador.getVida()].remover();
		}

	}

	@Override
	public void ocultar() {

	}

	@Override
	public void mostrar() {

		pausa = true;

		vidas = new Vida[3];

		ciclo = 0;

		cicloIntro = 0;

		musicaIntro1 = false;

		musicaIntro2 = true;

		iniciar();

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

					recurso.pararSonido(recurso.getSonido("musica.wav"));

					recurso.playSonido("pausa.wav");

				} else {

					recurso.repetirSonido("musica.wav");
				}

				pausa = !pausa;

				jugador.setPausado(pausa);

			}

			break;

		case KeyEvent.VK_T:

			tipo = Configuraciones.VOLADOR;

			break;

		case KeyEvent.VK_Y:

			tipo = Configuraciones.ANTI_AEREO;

			break;

		default:

			break;

		}

	}

	@Override
	public void teclaLevantada(KeyEvent ev) {

		jugador.teclaLevantada(ev);

	}

	@Override
	public void teclaTipo(KeyEvent ev) {

	}

	@Override
	public void ratonDeslizando(MouseEvent ev) {

		if (!pausa) {

			actores.get(actores.size() - 1).setPosicion(ev.getX(), ev.getY());

		}

		jugador.ratonDeslizando(ev);
	}

	@Override
	public void ratonMoviendo(MouseEvent ev) {

		jugador.ratonMoviendo(ev);

	}

	@Override
	public void ratonClick(MouseEvent ev) {

		jugador.ratonClick(ev);

	}

	@Override
	public void ratonPresionado(MouseEvent ev) {

		if (!pausa) {

			agregarActorTemporal(ev.getX(), ev.getY());

		}

		jugador.ratonPresionado(ev);

	}

	@Override
	public void ratonLevantado(MouseEvent ev) {

		if (!pausa) {

			agregarActor(ev.getX(), ev.getY());

			configuracion.guardarConfiguraciones();

		}

		jugador.ratonLevantado(ev);

	}

	@Override
	public void guardarDatos() {

		nivel.guardarDatos();

	}

	@Override
	public void liberarRecursos() {

		nivel.liberarRecursos();

	}

	private void agregarActorTemporal(int x, int y) {

	

		if (tipo.contains(Configuraciones.ANTI_AEREO)) {

			AntiAereo antiAreo = new AntiAereo(this);

			antiAreo.setTamano(32, 32);

			antiAreo.setPosicion(camara.posicion.x + x, y);

			antiAreo.setImagenes(new BufferedImage[] { recurso.getImagen("antiAreoH1.png") });

			actores.add(antiAreo);
		}
		
		
		if (tipo.contains(Configuraciones.VOLADOR)) {

			Volador volador = new Volador(this);

			volador.setTamano(32, 32);

			volador.setPosicion(camara.posicion.x + x, y);

			volador.setImagenes(new BufferedImage[] { recurso.getImagen("voladorI1.png"),
					recurso.getImagen("voladorI2.png"), recurso.getImagen("voladorI3.png") });

			actores.add(volador);
		}

	}

	private void agregarActor(int x, int y) {

		ArrayList<Actor> personaje = new ArrayList<Actor>();

		

		if (tipo.contains(Configuraciones.ANTI_AEREO)) {

			AntiAereo antiAreo = new AntiAereo(this);

			antiAreo.setTamano(32, 32);

			antiAreo.setPosicion(camara.posicion.x + x, y);

			antiAreo.setImagenes(new BufferedImage[] { recurso.getImagen("antiAreoH1.png") });

			personaje.add(antiAreo);
		}
		
		if (tipo.contains(Configuraciones.VOLADOR)) {

			Volador volador = new Volador(this);

			volador.setTamano(32, 32);

			volador.setPosicion(camara.posicion.x + x, y);

			volador.setImagenes(new BufferedImage[] { recurso.getImagen("voladorI1.png"),
					recurso.getImagen("voladorI2.png"), recurso.getImagen("voladorI3.png") });

			personaje.add(volador);
		}

		agregarActor(personaje);

	}

	public void agregarActor(ArrayList<Actor> personajes) {

		String numeroNivel = "Nivel " + configuracion.getNumeroNivel();

		configuracion.gurdarActores(personajes, tipo, numeroNivel);

	}

}
