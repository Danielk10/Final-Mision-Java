package com.diamon.actor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Animacion;
import com.diamon.nucleo.Pantalla;

public class Animar extends Actor {
	
	private Animacion ani;
	
	private float tiempo;

	public Animar(Pantalla pantalla) {
		super(pantalla);
		
		tiempo =0;
		
		
		
	}
	
	
	
	

	@Override
	public void actualizar(float delta) {
		
		//super.actualizar(delta);
		if (delta > 0.1f) {
			delta = 0.1f;
		}
		tiempo += delta;

		if (tiempo >= 1000000000) {
			tiempo = 0;
		}
		
	}





	@Override
	public void dibujar(Graphics2D pincel, float delta) {
		
		//super.dibujar(pincel, delta);
		
		pincel.drawImage(ani.getKeyFrame(tiempo, Animacion.REPETIR), x, y, tamano.width, tamano.height, this);
		
		
		
	}





	@Override
	public void setImagenes(BufferedImage... imagenes) {
		ani = new Animacion(0.1f,imagenes);
		super.setImagenes(imagenes);
	}





	@Override
	public void colision(Actor actor) {
		// TODO Auto-generated method stub

	}

}
