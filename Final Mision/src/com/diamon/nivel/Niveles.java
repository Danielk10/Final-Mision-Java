package com.diamon.nivel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.diamon.actor.AntiAereo;
import com.diamon.actor.Caja;
import com.diamon.actor.Fondo;
import com.diamon.actor.Jugador;
import com.diamon.actor.JugadorMuriendo;
import com.diamon.actor.LanzaMisil;
import com.diamon.actor.MaquinaFinal;
import com.diamon.actor.MaquinaPared;
import com.diamon.actor.Robot;
import com.diamon.actor.Saltador;
import com.diamon.actor.Vida;
import com.diamon.actor.Volador;
import com.diamon.dato.Configuraciones;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Nivel;
import com.diamon.nucleo.Pantalla;
import com.diamon.pantalla.PantallaFinal;
import com.diamon.pantalla.PantallaJuego;
import com.diamon.utilidad.Vector2D;

public class Niveles extends Nivel {

	private int ciclo;

	private Fondo[] fondo;

	private MaquinaFinal maquina;

	private boolean musicaMuriendo1;

	private boolean musicaMuriendo2;

	private int cicloMuriendo;

	private int cicloParaEnemigos;

	public Niveles(Pantalla pantalla, Jugador jugador) {
		super(pantalla, jugador);
		// TODO Auto-generated constructor stub

	}

	@Override
	public void iniciar() {

		String numeroNivel = "Nivel " + configuracion.getNumeroNivel();

		fondo = new Fondo[21];

		ciclo = 0;

		cicloParaEnemigos = 0;

		musicaMuriendo1 = false;

		musicaMuriendo2 = true;

		cicloMuriendo = 0;

		int contador = 1;

		int posicion = 0;

		int velocidad = 2560;

		for (int i = 0; i < fondo.length - 1; i++) {

			fondo[i] = new Fondo(pantalla);

			fondo[i].setTamano(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

			fondo[i].setPosicion(posicion, 0);

			fondo[i].setVelocidad(1);

			if (posicion > velocidad) {
				fondo[i].setVelocidad(0);

			}

			fondo[i].setImagenes(new BufferedImage[] { recurso.getImagen("fondo" + contador + ".png") });

			fondo[i].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			contador += 1;

			posicion += 640;

			actores.add(fondo[i]);

		}

		fondo[20] = new Fondo(pantalla);

		fondo[20].setTamano(640, 480);

		fondo[20].setPosicion(640, 0);

		fondo[20].setImagenes(new BufferedImage[] { recurso.getImagen("fondo21MGF.png") });

		fondo[20].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

		fondo[20].setVelocidad(0);

		actores.add(fondo[20]);

		for (Vector2D posiciones : configuracion.getPosicionActores(Configuraciones.VOLADOR, numeroNivel))

		{
			Volador voladores = new Volador(pantalla);

			voladores.setTamano(32, 32);

			voladores.setCuadros(7);

			voladores.setPosicion(posiciones.x, posiciones.y);

			voladores.setImagenes(new BufferedImage[] { recurso.getImagen("voladorI1.png"),
					recurso.getImagen("voladorI2.png"), recurso.getImagen("voladorI3.png") });

			voladores.setVelocidadY((int) (Math.random() * 7 - 5));

			actores.add(voladores);

		}

		int posicionCaja = 64;

		Caja[] cajas = new Caja[4];

		for (int i = 0; i < cajas.length; i++) {

			cajas[i] = new Caja(pantalla);

			cajas[i].setTamano(32, 32);

			cajas[i].setPosicion(1920, posicionCaja);

			cajas[i].setCuadros(10);

			cajas[i].setImagenes(
					new BufferedImage[] { recurso.getImagen("cajaPoder1.png"), recurso.getImagen("cajaPoder2.png"),
							recurso.getImagen("cajaPoder3.png"), recurso.getImagen("cajaPoder4.png") });

			posicionCaja += 96;

		}

		cajas[0].setAgilidad(Caja.AGILIDAD_S);
		cajas[1].setPoderBala(Caja.PODER_B);
		cajas[2].setPoderBala(Caja.PODER_W);
		cajas[3].setPoderBala(Caja.PODER_L);

		actores.add(cajas[0]);
		actores.add(cajas[1]);
		actores.add(cajas[2]);
		actores.add(cajas[3]);

		maquina = new MaquinaFinal(pantalla);

		maquina.setTamano(64, 64);

		maquina.setPosicion(0, 480);

		maquina.setImagenes(new BufferedImage[] { recurso.getImagen("maquinaFinal.png") });

		maquina.setCuadros(20);

		actores.add(maquina);

		recurso.playSonido("comienzo1.wav");

		int pocicionVida = 32;

		for (int i = 0; i < ((PantallaJuego) pantalla).vidas.length; i++) {

			((PantallaJuego) pantalla).vidas[i] = new Vida(pantalla);

			((PantallaJuego) pantalla).vidas[i].setTamano(16, 32);

			((PantallaJuego) pantalla).vidas[i].setPosicion(pocicionVida, 32);

			((PantallaJuego) pantalla).vidas[i].setImagenes(new BufferedImage[] { recurso.getImagen("vida1.png") });

			pocicionVida += 32;

			actores.add(((PantallaJuego) pantalla).vidas[i]);

		}

		for (Vector2D posiciones : configuracion.getPosicionActores(Configuraciones.ANTI_AEREO, numeroNivel))

		{
			AntiAereo antiAreo = new AntiAereo(pantalla);

			antiAreo.setTamano(32, 32);

			antiAreo.setPosicion(posiciones.x, posiciones.y);

			antiAreo.setImagenes(new BufferedImage[] { recurso.getImagen("antiAreoH1.png") });

			actores.add(antiAreo);
		}

		jugador.setPosicion(0, (Juego.ALTO_PANTALLA / 3) + jugador.getTamano().width);

		jugador.anadirSatelite();

		actores.add(jugador);

	}

	private void moverFondo() {

		if (fondo[4].getX() == 0 && fondo[4].getY() == 0) {

			fondo[4].setPosicion(0, 0);

			fondo[4].setVelocidad(1);

			fondo[4].setDireccion(Fondo.VERTICAL_ABAJO);

			fondo[5].setPosicion(0, -480);

			fondo[5].setVelocidad(1);

			fondo[5].setDireccion(Fondo.VERTICAL_ABAJO);

			fondo[6].setPosicion(0, -960);

			fondo[6].setVelocidad(1);

			fondo[6].setDireccion(Fondo.VERTICAL_ABAJO);

			fondo[7].setPosicion(0, -1380);

			fondo[7].setVelocidad(1);

			fondo[7].setDireccion(Fondo.VERTICAL_ABAJO);

		}

		if (fondo[7].getX() == 0 && fondo[7].getY() == 0) {

			fondo[7].setPosicion(0, 0);

			fondo[7].setVelocidad(1);

			fondo[7].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[8].setPosicion(640, 0);

			fondo[8].setVelocidad(1);

			fondo[8].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[9].setPosicion(1280, 0);

			fondo[9].setVelocidad(1);

			fondo[9].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[10].setPosicion(1920, 0);

			fondo[10].setVelocidad(1);

			fondo[10].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

		}

		if (fondo[10].getX() == 0 && fondo[10].getY() == 0) {

			fondo[10].setPosicion(0, 0);

			fondo[10].setVelocidad(1);

			fondo[10].setDireccion(Fondo.VERTICAL_ARRIBA);

			fondo[11].setPosicion(0, 480);

			fondo[11].setVelocidad(1);

			fondo[11].setDireccion(Fondo.VERTICAL_ARRIBA);

		}

		if (fondo[11].getX() == 0 && fondo[11].getY() == 0) {

			fondo[11].setPosicion(0, 0);

			fondo[11].setVelocidad(1);

			fondo[11].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[12].setPosicion(640, 0);

			fondo[12].setVelocidad(1);

			fondo[12].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[13].setPosicion(1280, 0);

			fondo[13].setVelocidad(1);

			fondo[13].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

		}

		if (fondo[13].getX() == 0 && fondo[13].getY() == 0) {

			fondo[13].setPosicion(0, 0);

			fondo[13].setVelocidad(1);

			fondo[13].setDireccion(Fondo.VERTICAL_ARRIBA);

			fondo[14].setPosicion(0, 480);

			fondo[14].setVelocidad(1);

			fondo[14].setDireccion(Fondo.VERTICAL_ARRIBA);

			fondo[15].setPosicion(0, 960);

			fondo[15].setVelocidad(1);

			fondo[15].setDireccion(Fondo.VERTICAL_ARRIBA);

		}

		if (fondo[15].getX() == 0 && fondo[15].getY() == 0) {

			fondo[15].setPosicion(0, 0);

			fondo[15].setVelocidad(1);

			fondo[15].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[16].setPosicion(640, 0);

			fondo[16].setVelocidad(1);

			fondo[16].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[17].setPosicion(1280, 0);

			fondo[17].setVelocidad(1);

			fondo[17].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[18].setPosicion(1920, 0);

			fondo[18].setVelocidad(1);

			fondo[18].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[19].setPosicion(2560, 0);

			fondo[19].setVelocidad(1);

			fondo[19].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			fondo[20].setPosicion(3200, 0);

			fondo[20].setVelocidad(1);

			fondo[20].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

		}

		if (!fondo[20].isParar()) {

			if (fondo[20].getX() == 0 && fondo[20].getY() == 0) {

				fondo[20].setParar(true);

				maquina.setPosicion(512, 176);

				maquina.setDisparar(true);

			}

		}

	}

	public void jugadorMuriendo() {

		JugadorMuriendo j = new JugadorMuriendo(pantalla);

		j.setTamano(64, 64);

		j.setPosicion(jugador.getX(), jugador.getY());

		j.setCuadros(15);

		j.setImagenes(new BufferedImage[] { recurso.getImagen("jugador1D4.png"), recurso.getImagen("jugador1D5.png"),
				recurso.getImagen("jugador1D6.png") });

		actores.add(j);

	}

	@Override
	public void actualizar(float delta) {

		for (int i = 0; i < actores.size(); i++) {

			actores.get(i).actualizar(delta);

		}

		moverFondo();

		if (cicloParaEnemigos <= 10100) {

			cicloParaEnemigos++;

		}

		colocarEnemigos();

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

				recurso.pararSonido(recurso.getSonido("musica.wav"));

				recurso.playSonido("muriendo.wav");

				jugadorMuriendo();

				musicaMuriendo2 = false;
			}

		}

		if (maquina.getVida() == 0) {

			ciclo++;

			if (ciclo % 100 == 0) {

				recurso.pararSonido(recurso.getSonido("musica.wav"));

				if (ciclo % 100 == 0) {

					jugador.setNivelTerminado(true);

				}

				if (configuracion.getNumeroNivel() == 5) {

					juego.setPantalla(new PantallaFinal(juego));

				}

				ciclo = 0;

			}

		}

	}

	private void colocarEnemigos() {

		if (cicloParaEnemigos % 600 == 0) {

			if (600 == cicloParaEnemigos) {

				agregarMaquinaAntiAreo();

				agregarVolador();
			}

		}

		if (cicloParaEnemigos % 800 == 0) {

			if (800 == cicloParaEnemigos) {

				agregarSaltador();

			}

		}

		if (cicloParaEnemigos % 2500 == 0) {

			agregarRobot();

			agregarLanzaMisil();

		}
		if (cicloParaEnemigos % 2750 == 0) {

			if (2750 == cicloParaEnemigos) {

				agregarMaquinaPared();
			}

		}

		if (cicloParaEnemigos % 3000 == 0) {

			if (3000 == cicloParaEnemigos) {

				agregarMaquinaPared();
			}

		}

		if (cicloParaEnemigos % 3200 == 0) {

			if (3200 == cicloParaEnemigos) {

				agregarMaquinaPared();
			}

		}

		if (cicloParaEnemigos % 7000 == 0) {

			if (7000 == cicloParaEnemigos) {

				agregarPoder();
			}

		}

		if (cicloParaEnemigos % 10000 == 0) {

			cicloParaEnemigos = 10100;

		}

	}

	private void agregarLanzaMisil() {

		LanzaMisil lanzaMisil = new LanzaMisil(pantalla);

		lanzaMisil.setTamano(48, 32);

		lanzaMisil.setPosicion(640, 100);

		lanzaMisil.setCuadros(20);

		lanzaMisil.setImagenes(new BufferedImage[] { recurso.getImagen("lanzaMisilI1.png"),
				recurso.getImagen("lanzaMisilI2.png"), recurso.getImagen("lanzaMisilI3.png") });

		actores.add(lanzaMisil);
	}

	private void agregarVolador() {

		Random r = new Random();

		Volador volador = new Volador(pantalla);

		volador.setTamano(32, 32);

		volador.setCuadros(7);

		volador.setPosicion(r.nextInt(640) + 640, r.nextInt(480) - 32);

		volador.setImagenes(new BufferedImage[] { recurso.getImagen("voladorI1.png"),
				recurso.getImagen("voladorI2.png"), recurso.getImagen("voladorI3.png") });

		volador.setVelocidadY((int) (Math.random() * 7 - 5));

		actores.add(volador);

	}

	private void agregarSaltador() {

		Saltador saltador = new Saltador(pantalla);

		saltador.setTamano(48, 64);

		saltador.setPosicion(640, 320);

		saltador.setCuadros(20);

		saltador.setImagenes(new BufferedImage[] { recurso.getImagen("saltador2.png") });

		actores.add(saltador);

	}

	private void agregarRobot() {

		Robot robot1 = new Robot(pantalla);

		robot1.setTamano(64, 64);

		robot1.setPosicion(639, 0);

		robot1.setLado(Robot.LADO_IZQUIERDO);

		robot1.setImagenes(new BufferedImage[] { recurso.getImagen("robotI.png") });

		actores.add(robot1);

		Robot robot2 = new Robot(pantalla);

		robot2.setTamano(64, 64);

		robot2.setPosicion(0, 0);

		robot2.setLado(Robot.LADO_DERECHO);

		robot2.setImagenes(new BufferedImage[] { recurso.getImagen("robotD.png") });

		actores.add(robot2);
	}

	private void agregarMaquinaPared() {

		MaquinaPared maquinaPared = new MaquinaPared(pantalla);

		maquinaPared.setTamano(32, 32);

		maquinaPared.setPosicion(532, 0);

		maquinaPared.setVelocidadY(1);

		maquinaPared.setMover(MaquinaPared.MOVER_ABAJO);

		maquinaPared.setLado(MaquinaPared.LADO_IZQUIERDO);

		maquinaPared.setImagenes(new BufferedImage[] { recurso.getImagen("maquinaParedD1.png") });

		actores.add(maquinaPared);

	}

	private void agregarMaquinaAntiAreo() {

		AntiAereo antiAreo = new AntiAereo(pantalla);

		antiAreo.setTamano(32, 32);

		antiAreo.setPosicion(640, 352);

		antiAreo.setImagenes(new BufferedImage[] { recurso.getImagen("antiAreoH1.png") });

		actores.add(antiAreo);

	}

	private void agregarPoder() {
		int posicionCaja = 64;

		Caja[] cajas = new Caja[4];

		for (int i = 0; i < cajas.length; i++) {
			cajas[i] = new Caja(pantalla);

			cajas[i].setTamano(32, 32);

			cajas[i].setPosicion(1920, posicionCaja);

			cajas[i].setCuadros(10);

			cajas[i].setImagenes(
					new BufferedImage[] { recurso.getImagen("cajaPoder1.png"), recurso.getImagen("cajaPoder2.png"),
							recurso.getImagen("cajaPoder3.png"), recurso.getImagen("cajaPoder4.png") });

			posicionCaja += 96;

		}

		cajas[0].setAgilidad(Caja.AGILIDAD_S);
		cajas[1].setPoderBala(Caja.PODER_B);
		cajas[2].setPoderBala(Caja.PODER_W);
		cajas[3].setPoderBala(Caja.PODER_L);

		actores.add(cajas[0]);
		actores.add(cajas[1]);
		actores.add(cajas[2]);
		actores.add(cajas[3]);
	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		for (int i = 0; i < actores.size(); i++) {

			actores.get(i).dibujar(pincel, delta);

		}

	}

	@Override
	public void guardarDatos() {

		configuracion.guardarConfiguraciones();

	}

	@Override
	public void liberarRecursos() {

		actores.clear();

	}

}
