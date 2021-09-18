package com.diamon.actor;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class Jugador extends Actor {

	private int deltaXTactil, deltaYTactil;

	private int x1 = 0;

	private int y1 = 0;
	
	private int xDelta = 0;
	
	private int xDeltaReferencia = 0;

	private int velocidadX;

	private int velocidadY;

	private final static int VELOCIDAD_JUGADOR = 5;

	private boolean arriba, abajo, izquierda, derecha, disparar;

	private int cicloDisparo;

	private int cicloDisparoB;

	private int cicloParpadeo;

	private int cuadros = 7;

	private int timpo = 0;;

	protected int frames = 0;

	private BufferedImage[] imagenes1 = new BufferedImage[2];

	private BufferedImage[] imagenes2 = new BufferedImage[2];

	private boolean dezplazamientoInicial;

	private boolean parpadeo;

	private boolean lado;

	private boolean pausado;

	private int vida;

	private boolean inmune;

	private boolean poder;
	
	private boolean nivelTerminado;

	private int tipoPoder;

	private int velocidad;

	private Satelite satelite1;

	private Satelite satelite2;

	public Jugador(Pantalla pantalla) {
		super(pantalla);

		velocidadX = 0;

		velocidadY = 0;
		
		
		nivelTerminado = false;

		dezplazamientoInicial = true;

		arriba = abajo = izquierda = derecha = disparar = false;

		cicloParpadeo = 0;

		cicloDisparoB = 0;

		cicloDisparo = 0;

		parpadeo = false;

		velocidad = VELOCIDAD_JUGADOR;

		lado = false;

		poder = false;

		pausado = true;

		vida = 3;

		inmune = false;

		tipoPoder = 0;

		int contador1 = 2;

		for (int i = 0; i < imagenes1.length; i++) {

			imagenes1[i] = recurso.getImagen("jugador1D" + contador1 + ".png");

			contador1++;

		}
		int contador2 = 2;

		for (int i = 0; i < imagenes2.length; i++) {

			imagenes2[i] = recurso.getImagen("jugador1I" + contador2 + ".png");

			contador2++;

		}

		satelite1 = new Satelite(pantalla);

		satelite2 = new Satelite(pantalla);

		satelite1.setTamano(16, 16);

		satelite1.setPosicion(0, 0);

		satelite1.setCuadros(1);

		satelite1.setImagenes(new BufferedImage[] { recurso.getImagen("sateliteHD1.png") });

		satelite2.setTamano(16, 16);

		satelite2.setPosicion(0, 0);

		satelite2.setCuadros(1);

		satelite2.setImagenes(new BufferedImage[] { recurso.getImagen("sateliteHD1.png") });

		satelite1.setDisparar(false);

		satelite2.setDisparar(false);

		

	}
	
	
	public boolean isNivelTerminado() {
		return nivelTerminado;
	}


	public void setNivelTerminado(boolean nivelTerminado) {
		this.nivelTerminado = nivelTerminado;
	}


	public void anadirSatelite()
	{
		
		actores.add(satelite1);

		actores.add(satelite2);
		
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public void setPausado(boolean pausado) {
		this.pausado = pausado;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		if (!lado) {

			satelite1.setPosicion((x + 46), (y - 16));

			satelite2.setPosicion(x + 46, y + 36);

		} else {
			satelite1.setPosicion(x + 64, y - 16);

			satelite2.setPosicion(x + 64, y + 36);

		}

		x += velocidadX;

		y += velocidadY;

		timpo++;

		if (!lado) {
			if (timpo % cuadros == 0) {
				timpo = 0;
				frames = (frames + 1) % imagenes1.length;

			}

		}

		else {

			if (timpo % cuadros == 0) {
				timpo = 0;
				frames = (frames + 1) % imagenes2.length;

			}

		}

		if (dezplazamientoInicial) {

			if (x <= (Juego.ANCHO_PANTALLA / 2) - tamano.width) {

				x += 3;

			} else {

				dezplazamientoInicial = false;
			}

		}

		if (x >= (Juego.ANCHO_PANTALLA - 32) - tamano.width) {

			x = (Juego.ANCHO_PANTALLA - 32) - tamano.width;

		}

		if (x <= 32) {

			x = 32;

		}

		if (y >= (Juego.ALTO_PANTALLA - 32) - tamano.height) {

			y = (Juego.ALTO_PANTALLA - 32) - tamano.height;

		}

		if (y <= 32) {

			y = 32;

		}

		cicloDisparoB++;

		cicloDisparo++;

		if (disparar) {

			if (!poder) {

				disparar();
			}

			if (poder) {
				disparoEspecial();

			}

		}

		if (cicloParpadeo % 2 == 0) {

			parpadeo = true;

		} else {

			parpadeo = false;
		}

		if (cicloParpadeo <= 0) {
			cicloParpadeo = 0;

			inmune = true;
		} else {
			inmune = false;
		}
		cicloParpadeo--;

		if (vida == 0) {

			vida = 0;

			satelite1.remover();
			satelite2.remover();

			remover = true;
		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		if (!parpadeo) {

			if (disparar) {

				if (!lado) {

					pincel.drawImage(imagenes1[frames], x, y, tamano.width, tamano.height, this);

				} else {

					pincel.drawImage(imagenes2[frames], x, y, tamano.width, tamano.height, this);

				}

			} else {

				if (!lado) {
					imagenes[0] = recurso.getImagen("jugador1D1.png");

					pincel.drawImage(imagenes[0], x, y, tamano.width, tamano.height, this);

				} else {

					imagenes[0] = recurso.getImagen("jugador1I1.png");

					pincel.drawImage(imagenes[0], x, y, tamano.width, tamano.height, this);
				}

			}

		}

	}

	@Override
	public void colision(Actor actor) {

		if (inmune) {

			if (actor instanceof BalaEnemigo || actor instanceof Volador || actor instanceof LanzaMisil
					|| actor instanceof Caja || actor instanceof BalaEnemigoDestruible || actor instanceof MaquinaFinal
					|| actor instanceof Misil || actor instanceof MaquinaPared || actor instanceof Saltador
					|| actor instanceof AntiAereo || actor instanceof Robot) {

				poder = false;

				satelite1.setActivar(false);

				satelite2.setActivar(false);

				velocidad = Jugador.VELOCIDAD_JUGADOR;

				cicloParpadeo = 100;

				vida--;

			}

		}

		if (actor instanceof Poder) {

			Poder p = (Poder) actor;

			recurso.playSonido("poder.wav");

			if (p.getPoder() != Caja.AGILIDAD_S) {

				tipoPoder = p.getPoder();

				poder = true;
			}

			if (p.getPoder() == Caja.AGILIDAD_S) {

				velocidad += 1;

			}

		}

	}

	public int getVida() {
		return vida;
	}

	public void teclaPresionada(KeyEvent ev) {
		switch (ev.getKeyCode()) {

		case KeyEvent.VK_LEFT:

			izquierda = true;
			dezplazamientoInicial = false;

			if (pausado) {

				lado = true;
			}

			break;

		case KeyEvent.VK_RIGHT:

			derecha = true;
			dezplazamientoInicial = false;

			if (pausado) {
				lado = false;
			}

			break;
		case KeyEvent.VK_UP:

			arriba = true;
			dezplazamientoInicial = false;
			break;
		case KeyEvent.VK_DOWN:

			abajo = true;
			dezplazamientoInicial = false;
			break;

		case KeyEvent.VK_Z:

			disparar = true;

			satelite1.setDisparar(true);

			satelite2.setDisparar(true);

			break;

		case KeyEvent.VK_X:

			satelite1.setActivar(!satelite1.isActivar());

			satelite2.setActivar(!satelite2.isActivar());
			break;

		default:

			break;

		}

		actualizarVelocidad();

	}

	public void teclaLevantada(KeyEvent ev) {
		switch (ev.getKeyCode()) {

		case KeyEvent.VK_LEFT:

			izquierda = false;
			dezplazamientoInicial = false;

			break;

		case KeyEvent.VK_RIGHT:

			derecha = false;
			dezplazamientoInicial = false;

			break;
		case KeyEvent.VK_UP:

			arriba = false;
			dezplazamientoInicial = false;
			break;
		case KeyEvent.VK_DOWN:

			abajo = false;
			dezplazamientoInicial = false;
			break;

		case KeyEvent.VK_Z:

			disparar = false;

			satelite1.setDisparar(false);

			satelite2.setDisparar(false);

			break;

		default:

			break;

		}

		actualizarVelocidad();

	}

	public void teclaTipo(KeyEvent ev) {

	}

	protected void actualizarVelocidad() {

		velocidadX = 0;

		velocidadY = 0;

		if (abajo) {
			velocidadY = velocidad;
		}

		if (arriba) {

			velocidadY = -velocidad;
		}

		if (izquierda) {
			velocidadX = -velocidad;

		}

		if (derecha) {
			velocidadX = velocidad;

		}

	}

	public void disparar() {

		if (cicloDisparo % 20 == 0) {

			if (!lado) {
				Bala bala = new Bala(pantalla);

				bala.setTamano(16, 4);

				bala.setPosicion(x + 56, y + 16);

				bala.setLado(true);

				bala.setImagenes(new BufferedImage[] { recurso.getImagen("balaHD.png") });

				actores.add(bala);

			} else {
				Bala bala = new Bala(pantalla);

				bala.setTamano(16, 4);

				bala.setPosicion(x, y + 16);

				bala.setLado(false);

				bala.setImagenes(new BufferedImage[] { recurso.getImagen("balaHI.png") });

				actores.add(bala);

			}

			cicloDisparo = 0;

			recurso.playSonido("disparo.wav");

		}

	}

	public void disparoEspecial() {

		if (!lado) {

			if (cicloDisparo % 20 == 0) {

				if (tipoPoder == BalaEspecial.BALA_W) {

					BalaEspecial bala = new BalaEspecial(pantalla);

					bala.setLado(true);

					bala.setCuadros(5);

					bala.setBala(BalaEspecial.BALA_W);
					bala.setPosicion(x + 56, y - 16);

					bala.setTamano(16, 64);

					bala.setImagenes(new BufferedImage[] {

							recurso.getImagen("balaWD1.png"),
							recurso.getImagen("balaWD2.png"),
							recurso.getImagen("balaWD3.png")

					});

					actores.add(bala);

				}

				if (tipoPoder == BalaEspecial.BALA_L) {

					BalaEspecial bala = new BalaEspecial(pantalla);

					bala.setLado(true);

					bala.setCuadros(5);

					bala.setBala(BalaEspecial.BALA_L);

					bala.setTamano(256, 4);

					bala.setPosicion(x - 48, y + 16);

					bala.setImagenes(new BufferedImage[] {

							recurso.getImagen("balaLD1.png"),
							recurso.getImagen("balaLD2.png"),
							recurso.getImagen("balaLD3.png")

					});

					recurso.playSonido("disparoL.wav");

					actores.add(bala);

				}

				cicloDisparo = 0;

			}

			if (cicloDisparoB % 30 == 0) {

				if (tipoPoder == BalaEspecial.BALA_B) {

					BalaEspecial bala = new BalaEspecial(pantalla);

					bala.setLado(true);

					bala.setCuadros(5);

					bala.setBala(BalaEspecial.BALA_B);

					bala.setTamano(12, 12);

					bala.setPosicion(x + 56, y + 12);

					bala.setImagenes(new BufferedImage[] {

							recurso.getImagen("balaBD.png")

					});

					recurso.playSonido("disparo.wav");

					actores.add(bala);

				}

				cicloDisparoB = 0;

			}

		} else {

			if (cicloDisparo % 20 == 0) {

				if (tipoPoder == BalaEspecial.BALA_W) {

					BalaEspecial bala = new BalaEspecial(pantalla);

					bala.setLado(false);

					bala.setCuadros(5);

					bala.setBala(BalaEspecial.BALA_W);

					bala.setTamano(16, 64);

					bala.setPosicion(x, y - 16);

					bala.setImagenes(new BufferedImage[] {

							recurso.getImagen("balaWI1.png"),
							recurso.getImagen("balaWI2.png"),
							recurso.getImagen("balaWI3.png")

					});

					actores.add(bala);

				}

				if (tipoPoder == BalaEspecial.BALA_L) {

					BalaEspecial bala = new BalaEspecial(pantalla);

					bala.setLado(false);

					bala.setCuadros(5);

					bala.setBala(BalaEspecial.BALA_L);

					bala.setTamano(256, 4);

					bala.setPosicion(x - 144, y + 16);

					bala.setImagenes(new BufferedImage[] {

							recurso.getImagen("balaLI1.png"),
							recurso.getImagen("balaLI2.png"),
							recurso.getImagen("balaLI3.png")

					});

					recurso.playSonido("disparoL.wav");

					actores.add(bala);

				}

				cicloDisparo = 0;

			}

			if (cicloDisparoB % 30 == 0) {

				if (tipoPoder == BalaEspecial.BALA_B) {

					BalaEspecial bala = new BalaEspecial(pantalla);

					bala.setLado(false);

					bala.setCuadros(5);

					bala.setBala(BalaEspecial.BALA_B);

					bala.setTamano(12, 12);

					bala.setPosicion(x, y + 12);

					bala.setImagenes(new BufferedImage[] {

							recurso.getImagen("balaBI.png")

					});

					recurso.playSonido("disparo.wav");

					actores.add(bala);
				}

				cicloDisparoB = 0;

			}

		}

	}

	public void ratonDeslizando(MouseEvent ev) {
		
		
		
		if(pausado)
		{
		
	     
		
		xDelta  = ev.getX();
		
		
		if(xDelta >=xDeltaReferencia)
		{
			
			//Derecho
		
		lado = false;
			
		}else
		{
			
			//Izquierdo
		
		lado = true;
			
		}
		

		x1 = (int) ev.getX() - deltaXTactil;
		y1 = (int) ev.getY() - deltaYTactil;

		if (x1 <= 32) {

			x1 = 32;
		}
		if (y1 >= (Juego.ALTO_PANTALLA-32) - tamano.height) {
			y1 = (Juego.ALTO_PANTALLA-32) - tamano.height;
		}

		if (x1 >= (Juego.ANCHO_PANTALLA-32) - tamano.width) {
			x1 = (Juego.ANCHO_PANTALLA-32) - tamano.width;
		}
		if (y1 <= 32) {

			y1 = 32;
		}

		x = x1;

		y = y1;

		dezplazamientoInicial = false;
		
		}

	}

	public void ratonMoviendo(MouseEvent ev) {

	}

	public void ratonClick(MouseEvent ev) {
		
		

	}

	public void ratonPresionado(MouseEvent ev) {

		
		if(pausado)
		{
		xDeltaReferencia = x;
		
		disparar = true;

		satelite1.setDisparar(true);

		satelite2.setDisparar(true);

		x1 = x;

		y1 = y;

		x = x1;

		y = y1;

		deltaXTactil = (int) ev.getX() - x1;

		deltaYTactil = (int) ev.getY() - y1;

		if (x1 <= 32) {
			x1 = 32;

		}

		if (y1 >= ((Juego.ALTO_PANTALLA-32) - tamano.height)) {

			y1 = (Juego.ALTO_PANTALLA-32) - tamano.height;

		}
		if (x1 >= (Juego.ANCHO_PANTALLA-32) - tamano.width) {
			x1 = (Juego.ANCHO_PANTALLA-32) - tamano.width;
		}

		if (y1 <= 32) {
			y1 = 32;

		}
		
		}

	}

	public void ratonLevantado(MouseEvent ev) {
		disparar = false;

		satelite1.setDisparar(false);

		satelite2.setDisparar(false);

	}

}
