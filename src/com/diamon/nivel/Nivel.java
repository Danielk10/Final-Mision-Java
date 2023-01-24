package com.diamon.nivel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.diamon.actor.AntiAereo;
import com.diamon.actor.Caja;
import com.diamon.actor.Fondo;
import com.diamon.actor.Jugador;
import com.diamon.actor.LanzaMisil;
import com.diamon.actor.MaquinaFinal;
import com.diamon.actor.MaquinaPared;
import com.diamon.actor.Robot;
import com.diamon.actor.Saltador;
import com.diamon.actor.Vida;
import com.diamon.actor.Volador;
import com.diamon.dato.InformacionDeNiveles;
import com.diamon.nucleo.Mundo;
import com.diamon.nucleo.Pantalla;
import com.diamon.utilidad.Vector2D;

public class Nivel extends Mundo {

	private String numeroNivel;

	private Fondo[] fondo;

	private Vida[] vidas;

	private MaquinaFinal maquina;

	public Nivel(Pantalla pantalla, Jugador jugador) {
		super(pantalla, jugador);

		camara.setX(0);

		fondo = new Fondo[21];

		iniciarFondos();

		vidas = new Vida[3];

		int pocicionVida = 32;

		for (int i = 0; i < vidas.length; i++) {

			vidas[i] = new Vida(pantalla);

			vidas[i].setTamano(16, 32);

			vidas[i].setPosicion(pocicionVida, 32);

			vidas[i].setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("vida1.png") });

			pocicionVida += 32;

			actores.add(vidas[i]);

		}

		numeroNivel = "Nivel " + datosNiveles.getNumeroNivel();

		for (Vector2D coodenadas : datosNiveles.getPosicionActores(InformacionDeNiveles.FONDO, numeroNivel)) {

			Fondo fondo = new Fondo(pantalla);

			fondo.setTamano(640, 480);

			fondo.setPosicion(coodenadas.getX(), coodenadas.getY());

			fondo.setVelocidad(1);

			fondo.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("fondo" + 1 + ".png") });

			fondo.setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			actores.add(fondo);

		}

		for (Vector2D coodenadas : datosNiveles.getPosicionActores(InformacionDeNiveles.VOLADOR, numeroNivel)) {

			Volador volador = new Volador(pantalla);

			volador.setTamano(32, 32);

			volador.setCuadros(7);

			volador.setPosicion(coodenadas.getX(), coodenadas.getY());

			volador.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("voladorI1.png"),
					juego.getRecurso().getImagen("voladorI2.png"), juego.getRecurso().getImagen("voladorI3.png") });

			volador.setVelocidadY((int) (Math.random() * 7 - 5));

			actores.add(volador);
		}

		for (Vector2D coodenadas : datosNiveles.getPosicionActores(InformacionDeNiveles.SALTADOR, numeroNivel)) {

			Saltador saltador = new Saltador(pantalla);

			saltador.setTamano(48, 64);

			saltador.setPosicion(coodenadas.getX(), coodenadas.getY());

			saltador.setCuadros(20);

			saltador.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("saltador2.png") });

			actores.add(saltador);

		}

		for (Vector2D coodenadas : datosNiveles.getPosicionActores(InformacionDeNiveles.ROBOT, numeroNivel)) {

			Robot robot1 = new Robot(pantalla);

			robot1.setTamano(64, 64);

			robot1.setPosicion(coodenadas.getX(), coodenadas.getY());

			robot1.setLado(Robot.LADO_IZQUIERDO);

			robot1.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("robotI.png") });

			actores.add(robot1);

		}

		for (Vector2D coodenadas : datosNiveles.getPosicionActores(InformacionDeNiveles.MAQUINA_PARED, numeroNivel)) {

			MaquinaPared maquinaPared = new MaquinaPared(pantalla);

			maquinaPared.setTamano(32, 32);

			maquinaPared.setPosicion(coodenadas.getX(), coodenadas.getY());

			maquinaPared.setVelocidadY(1);

			maquinaPared.setMover(MaquinaPared.MOVER_ABAJO);

			maquinaPared.setLado(MaquinaPared.LADO_IZQUIERDO);

			maquinaPared.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("maquinaParedI1.png") });

			maquinaPared.setDuracionDisparo(1f);

			actores.add(maquinaPared);

		}

		for (Vector2D coodenadas : datosNiveles.getPosicionActores(InformacionDeNiveles.ANTI_AEREO, numeroNivel)) {

			AntiAereo antiAreo = new AntiAereo(pantalla);

			antiAreo.setTamano(32, 32);

			antiAreo.setPosicion(coodenadas.getX(), coodenadas.getY());

			antiAreo.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("antiAreoH1.png") });

			actores.add(antiAreo);

		}

		for (Vector2D coodenadas : datosNiveles.getPosicionActores(InformacionDeNiveles.CAJA, numeroNivel)) {

			Caja caja = new Caja(pantalla);

			caja.setTamano(32, 32);

			caja.setPosicion(coodenadas.getX(), coodenadas.getY());

			caja.setCuadros(10);

			caja.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("cajaPoder1.png"),
					juego.getRecurso().getImagen("cajaPoder2.png"), juego.getRecurso().getImagen("cajaPoder3.png"),
					juego.getRecurso().getImagen("cajaPoder4.png") });

			caja.setPoderBala(Caja.PODER_B);

			actores.add(caja);

		}

		for (Vector2D coodenadas : datosNiveles.getPosicionActores(InformacionDeNiveles.LANZA_MISIL, numeroNivel)) {

			LanzaMisil lanzaMisil = new LanzaMisil(pantalla);

			lanzaMisil.setTamano(48, 32);

			lanzaMisil.setPosicion(coodenadas.getX(), coodenadas.getY());

			lanzaMisil.setCuadros(20);

			lanzaMisil.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("lanzaMisilI1.png"),
					juego.getRecurso().getImagen("lanzaMisilI2.png"),
					juego.getRecurso().getImagen("lanzaMisilI3.png") });

			actores.add(lanzaMisil);

		}

		jugador.agregarSatelites();

		actores.add(jugador);
	}

	public MaquinaFinal getMaquina() {
		return maquina;
	}

	private void iniciarFondos() {

		fondo[20] = new Fondo(pantalla);

		fondo[20].setTamano(640, 480);

		fondo[20].setPosicion(640, 0);

		fondo[20].setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("fondo21MGF.png") });

		fondo[20].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

		fondo[20].setVelocidad(0);

		actores.add(fondo[20]);

		int contador = 1;

		int posicion = 0;

		int velocidad = 2560;

		for (int i = 0; i < fondo.length - 1; i++) {

			fondo[i] = new Fondo(pantalla);

			fondo[i].setTamano(640, 480);

			fondo[i].setPosicion(posicion, 0);

			fondo[i].setVelocidad(1);

			if (posicion > velocidad) {
				fondo[i].setVelocidad(0);

			}

			fondo[i].setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("fondo" + contador + ".png") });

			fondo[i].setDireccion(Fondo.HORIZONTAL_IZQUIERDA);

			contador += 1;

			posicion += 640;

			actores.add(fondo[i]);

		}

		maquina = new MaquinaFinal(pantalla);

		maquina.setTamano(64, 64);

		maquina.setPosicion(0, 480);

		maquina.setImagenes(new BufferedImage[] { juego.getRecurso().getImagen("maquinaFinal.png") });

		maquina.setCuadros(20);

		actores.add(maquina);

	}

	private void moverFondo() {

		if (fondo[4].getX() <= 0 && fondo[4].getY() == 0) {

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

		if (fondo[7].getX() == 0 && fondo[7].getY() >= 0) {

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

		if (fondo[10].getX() <= 0 && fondo[10].getY() == 0) {

			fondo[10].setPosicion(0, 0);

			fondo[10].setVelocidad(1);

			fondo[10].setDireccion(Fondo.VERTICAL_ARRIBA);

			fondo[11].setPosicion(0, 480);

			fondo[11].setVelocidad(1);

			fondo[11].setDireccion(Fondo.VERTICAL_ARRIBA);

		}

		if (fondo[11].getX() == 0 && fondo[11].getY() <= 0) {

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

		if (fondo[13].getX() <= 0 && fondo[13].getY() == 0) {

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

		if (fondo[15].getX() == 0 && fondo[15].getY() <= 0) {

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

			if (fondo[20].getX() <= 0 && fondo[20].getY() == 0) {

				fondo[20].setParar(true);

				maquina.setPosicion(512, 176);

				maquina.setDisparar(true);

			}

		}

	}

	public Vida[] getVidas() {
		return vidas;
	}

	@Override
	public void actualizar(float delta) {

		for (int i = 0; i < actores.size(); i++) {

			actores.get(i).actualizar(delta);

		}

		moverFondo();

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		for (int i = 0; i < actores.size(); i++) {

			actores.get(i).dibujar(pincel, delta);

		}

	}

	@Override
	public void guardarDatos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void liberarRecursos() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void iniciar() {

		actores.clear();

	}

}
