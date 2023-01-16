package com.diamon.dato;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InformacionDeNiveles {

	private DatosDeNiveles datos;

	public static final int INTERNO = 0;

	public static final int LOCAL = 1;

	private int tipo;

	private boolean leerDatosInternos;

	public InformacionDeNiveles(int tipo) {

		datos = new DatosDeNiveles();

		this.tipo = tipo;

		leerDatosInternos = true;
	}

	public boolean isLeerDatosInternos() {
		return leerDatosInternos;
	}

	public void setLeerDatosInternos(boolean leerDatosInternos) {
		this.leerDatosInternos = leerDatosInternos;
	}

	public InformacionDeNiveles cargarConfiguraciones() {

		BufferedReader buferarchivoLeer = null;

		try {

			if (tipo == Configuraciones.INTERNO) {

				buferarchivoLeer = new BufferedReader(new InputStreamReader(
						datos.leerDatoInterno(getClass().getClassLoader().getResource("res/datosNiveles.txt"))));

			}

			if (tipo == Configuraciones.LOCAL) {

				buferarchivoLeer = new BufferedReader(new InputStreamReader(datos.leerDato(DatosDeNiveles.DATOS)));

			}

			leerDatosInternos = Boolean.parseBoolean(buferarchivoLeer.readLine());

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

			buferarchivoEscribir = new BufferedWriter(new OutputStreamWriter(datos.escribirDato(DatosDeNiveles.DATOS)));

			buferarchivoEscribir.write(Boolean.toString(leerDatosInternos));

			buferarchivoEscribir.newLine();

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

}
