package com.diamon.actor;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Pantalla;

public class Explosion extends Actor {
	
	private int cicloExplosion;

	public Explosion(Pantalla pantalla) {
		super(pantalla);
		cicloExplosion = 0;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		cicloExplosion++;

		if (cicloExplosion % 10 == 0) {

			remover = true;

			cicloExplosion = 0;

		}
	}

	@Override
	public void colision(Actor actor) {

	}

}
