/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.diamon.nucleo;

import java.awt.image.BufferedImage;

public class Animacion {

	public static final int REPETIR = 0;

	public static final int NORMAL = 1;

	private final BufferedImage[] imagenes;

	private final float duracionCuadros;

	private int modo;

	public Animacion(float duracionCuadros, BufferedImage... imagenes) {

		this.duracionCuadros = duracionCuadros;

		this.imagenes = imagenes;

		modo = NORMAL;
	}

	public int getModo() {
		return modo;
	}

	public void setModo(int modo) {
		this.modo = modo;
	}

	public BufferedImage getKeyFrame(float tiempo) {

		int numeroCuadros = (int) (tiempo / duracionCuadros);

		if (modo == NORMAL) {

			numeroCuadros = Math.min(imagenes.length - 1, numeroCuadros);
		}

		if (modo == REPETIR) {

			numeroCuadros = numeroCuadros % imagenes.length;
		}
		return imagenes[numeroCuadros];
	}
}
