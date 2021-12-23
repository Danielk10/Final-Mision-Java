package com.diamon.actor;

import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class Volador extends Actor {

	public final static int VELOCIDAD_MAQUINA = 2;

	private int cicloDisparo;

	private int velocidadY;

	public Volador(Pantalla pantalla) {

		super(pantalla);

		cicloDisparo = 0;
;

		velocidadY = 0;

	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		x -= Volador.VELOCIDAD_MAQUINA;

		if (x <= -tamano.width) {

			remover = true;

		}

		cicloDisparo++;

	
		if (cicloDisparo % 40 == 0) {

			
			
			
			if (Math.random() < 0.08f) {
				disparar();

			}

			cicloDisparo = 0;

		}

		y += velocidadY;

		if (y <= 0 || y >= Juego.ALTO_PANTALLA - tamano.height) {

			velocidadY = -velocidadY;

		}

	}

	public void disparar() {

		BalaEnemigo bala = new BalaEnemigo(pantalla);

		bala.setTamano(12, 12);

		bala.setPosicion(x - 16, y);

		bala.setLado(BalaEnemigo.LADO_IZQUIERDO);

		bala.setImagenes(pantalla.getJuego().getRecurso().getImagen("balaE1.png"),
				pantalla.getJuego().getRecurso().getImagen("balaE2.png"),
				pantalla.getJuego().getRecurso().getImagen("balaE3.png"),
				pantalla.getJuego().getRecurso().getImagen("balaE4.png") );

		bala.setCuadros(3);

		if (bala.getX() <= 640) {
			
			

			pantalla.getActores().add(bala);
		}

	}

	public void explosion() {

		Explosion explosion = new Explosion(pantalla);

		explosion.setTamano(64, 64);

		explosion.setPosicion(x - 32, y - 32);
	
		explosion.setCuadros(4);
	
		explosion.setImagenes(new BufferedImage[] { pantalla.getJuego().getRecurso().getImagen("explosion1.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion2.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion3.png"),
				pantalla.getJuego().getRecurso().getImagen("explosion4.png") });

             		

		if (explosion.getX() <= 640) {

			pantalla.getActores().add(explosion);

		}

	}

	@Override
	public void colision(Actor actor) {

		if (actor instanceof Bala || actor instanceof Jugador || actor instanceof BalaEspecial
				|| actor instanceof ExplosionB) {

			pantalla.getJuego().getRecurso().playSonido("explosion.wav");

			explosion();
			remover = true;
		}

	}

}
