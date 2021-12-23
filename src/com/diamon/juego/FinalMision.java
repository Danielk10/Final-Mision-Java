package com.diamon.juego;

import com.diamon.nucleo.Juego;
import com.diamon.pantalla.PantallaPrecentacion;

public class FinalMision extends Juego {

	private static final long serialVersionUID = 1L;

	public FinalMision() {
		super();

		setPantalla(new PantallaPrecentacion(this)); 

	}

}
