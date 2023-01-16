package com.diamon.actor;

import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Pantalla;

public class Explosion extends Actor {

	private float duracionExplosion;

	private float tiemoExplosion;

	public Explosion(Pantalla pantalla) {
		super(pantalla);

		duracionExplosion = 0;

		tiemoExplosion = 0;
	}

	@Override
	public void actualizar(float delta) {

		super.actualizar(delta);

		tiemoExplosion += delta;

		if (tiemoExplosion / duracionExplosion >= 1) {

			remover = true;

			tiemoExplosion = 0;
		}

	}

	public void setDuracionExplosion(float duracionExplosion) {
		this.duracionExplosion = duracionExplosion;
	}

	@Override
	public void colision(Actor actor) {

	}

}
