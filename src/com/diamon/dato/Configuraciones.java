package com.diamon.dato;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Configuraciones {

	private boolean sonido;

	private int puntuaciones[] = new int[20];

	private boolean leerDatosInternos;

	private DatosJuego datos;

	public static final int INTERNO = 0;

	public static final int LOCAL = 1;

	private int tipo;

	public Configuraciones(int tipo) {

		datos = new DatosJuego();

		this.tipo = tipo;

		sonido = true;

		leerDatosInternos = true;

	}

	public boolean isSonido() {
		return sonido;
	}

	public boolean isLeerDatosInternos() {
		return leerDatosInternos;
	}

	public void setLeerDatosInternos(boolean leerDatosInternos) {
		this.leerDatosInternos = leerDatosInternos;
	}

	public void setSonido(boolean sonido) {
		this.sonido = sonido;
	}

	public int[] getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(int[] puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	public Configuraciones cargarConfiguraciones() {

		BufferedReader buferarchivoLeer = null;

		try {

			if (tipo == Configuraciones.INTERNO) {

				buferarchivoLeer = new BufferedReader(new InputStreamReader(
						datos.leerDatoInterno(getClass().getClassLoader().getResource("res/datos.txt"))));

			}

			if (tipo == Configuraciones.LOCAL) {

				buferarchivoLeer = new BufferedReader(new InputStreamReader(datos.leerDato(DatosJuego.DATOS)));

			}

			sonido = Boolean.parseBoolean(buferarchivoLeer.readLine());

			leerDatosInternos = Boolean.parseBoolean(buferarchivoLeer.readLine());

			for (int i = 0; i < puntuaciones.length; i++) {

				puntuaciones[i] = Integer.parseInt(buferarchivoLeer.readLine());

			}

		} catch (IOException e) {

		} finally {
			try {
				if (buferarchivoLeer != null) {

					buferarchivoLeer.close();
				}

			} catch (IOException e) {

			}

		}

		return this;

	}

	public void guardarConfiguraciones() {

		BufferedWriter buferarchivoEscribir = null;

		try {

			buferarchivoEscribir = new BufferedWriter(new OutputStreamWriter(datos.escribirDato(DatosJuego.DATOS)));

			buferarchivoEscribir.write(Boolean.toString(sonido));

			buferarchivoEscribir.newLine();

			buferarchivoEscribir.write(Boolean.toString(leerDatosInternos));

			buferarchivoEscribir.newLine();

			for (int i = 0; i < puntuaciones.length; i++) {

				buferarchivoEscribir.write(Integer.toString(puntuaciones[i]));

				buferarchivoEscribir.newLine();
			}
		} catch (IOException e) {

		} finally {
			if (buferarchivoEscribir != null) {
				try {

					buferarchivoEscribir.close();

				} catch (IOException e) {

				}

			}

		}

	}

	public void anadirPuntuaciones(int puntuacion) {

		for (int i = 0; i < puntuaciones.length; i++) {
			if (puntuaciones[i] < puntuacion) {
				for (int j = (puntuaciones.length - 1); j > i; j--) {
					puntuaciones[j] = puntuaciones[j - 1];
				}
				puntuaciones[i] = puntuacion;
				break;
			}

		}

	}

}
