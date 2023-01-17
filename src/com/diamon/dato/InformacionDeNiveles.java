package com.diamon.dato;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.diamon.actor.Volador;
import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.utilidad.Vector2D;

public class InformacionDeNiveles {

	private DatosDeNiveles datos;

	public static final int INTERNO = 0;

	public static final int LOCAL = 1;

	private int tipo;

	private boolean leerDatosInternos;

	private ArrayList<Vector2D>[] posicionActores;

	private ArrayList<String>[] tipoActores;

	private ArrayList<String> niveles;

	private ArrayList<Actor> actoresInternos;

	private final static short NUMERO_NIVELES = 5;

	private Juego juego;

	private int numeroActores;

	public InformacionDeNiveles(int tipo, final Juego juego) {

		datos = new DatosDeNiveles();

		this.juego = juego;

		this.tipo = tipo;

		numeroActores = 0;

		leerDatosInternos = true;

		niveles = new ArrayList<String>();

		actoresInternos = new ArrayList<Actor>();

		posicionActores = new ArrayList[InformacionDeNiveles.NUMERO_NIVELES];

		tipoActores = new ArrayList[InformacionDeNiveles.NUMERO_NIVELES];

		for (int i = 0; i < posicionActores.length; i++) {

			posicionActores[i] = new ArrayList<Vector2D>();

		}

		for (int i = 0; i < tipoActores.length; i++) {

			tipoActores[i] = new ArrayList<String>();

		}
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

			numeroActores = Integer.parseInt(buferarchivoLeer.readLine());

			String paquete = "";
			int x = 0;
			int y = 0;
			String nivel = "";

			for (int i = 0; i < numeroActores; i++) {

				for (int j = 0; j < 4; j++) {

					if (j == 0) {

						paquete = buferarchivoLeer.readLine();
					}

					if (j == 1) {

						x = Integer.parseInt(buferarchivoLeer.readLine());

					}

					if (j == 2) {

						y = Integer.parseInt(buferarchivoLeer.readLine());
					}

					if (j == 3) {

						nivel = buferarchivoLeer.readLine();

						Volador actor = new Volador(juego.getPantalla());

						actor.setX(x);

						actor.setY(y);

						ArrayList<Actor> actores = new ArrayList<Actor>();

						actores.add(actor);

						gurdarActores(actores, paquete, nivel);

					}

				}

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

		numeroActores = actoresInternos.size();

		BufferedWriter buferarchivoEscribir = null;

		try {

			buferarchivoEscribir = new BufferedWriter(new OutputStreamWriter(datos.escribirDato(DatosDeNiveles.DATOS)));

			buferarchivoEscribir.write(Boolean.toString(leerDatosInternos));

			buferarchivoEscribir.newLine();

			buferarchivoEscribir.write(Integer.toString(numeroActores));

			buferarchivoEscribir.newLine();

			String ni = "";

			int n = 1;

			for (int i = 0; i < posicionActores.length; i++) {

				ni = "Nivel " + n;
				for (int k = 0; k < posicionActores.length; k++) {
					if (niveles.get(k).equals(ni)) {

						for (int j = 0; j < actoresInternos.size(); j++) {

							if (actoresInternos.get(j).getClass().getName().toString()
									.equals(actoresInternos.get(j).getClass().getName().toString())) {

								posicionActores[i].add(
										new Vector2D(actoresInternos.get(j).getX(), actoresInternos.get(j).getY()));

								buferarchivoEscribir.write(actoresInternos.get(j).getClass().getName().toString());

								buferarchivoEscribir.newLine();

								buferarchivoEscribir.write(Integer.toString(actoresInternos.get(j).getX()));

								buferarchivoEscribir.newLine();

								buferarchivoEscribir.write(Integer.toString(actoresInternos.get(j).getY()));

								buferarchivoEscribir.newLine();

								buferarchivoEscribir.write(niveles.get(k));

								buferarchivoEscribir.newLine();

							}

						}

					}
				}
				n++;
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

	public void gurdarActores(ArrayList<Actor> actores, String tipoActor, String nivel) {

		String ni = "";

		int n = 1;

		for (int i = 0; i < posicionActores.length; i++) {

			ni = "Nivel " + n;

			if (nivel.equals(ni)) {

				for (int j = 0; j < actores.size(); j++) {

					if (tipoActor.equals(actores.get(j).getClass().getName().toString())) {

						posicionActores[i].add(new Vector2D(actores.get(j).getX(), actores.get(j).getY()));

						tipoActores[i].add(actores.get(j).getClass().getName().toString());

						actoresInternos.add(actores.get(j));////////

						niveles.add(nivel);////
					}

				}

			}

			n++;
		}

	}

	public ArrayList<Vector2D> getPosicionActores(String tipoActor, String nivel) {

		ArrayList<Vector2D> posicion = new ArrayList<Vector2D>();

		String nombreNivel = "";

		int numeroNivel = 1;

		for (int i = 0; i < posicionActores.length; i++) {

			nombreNivel = "Nivel " + numeroNivel;

			if (nivel.equals(nombreNivel)) {

				for (int j = 0; j < tipoActores[i].size(); j++) {

					if (tipoActor.equals(tipoActores[i].get(j))) {

						posicion.add(posicionActores[i].get(j));

					}

				}

			}

			numeroNivel++;

		}

		return posicion;

	}

	public ArrayList<Vector2D> getTamanoArray(String nivel) {

		ArrayList<Vector2D> posicion = new ArrayList<Vector2D>();

		String nombreNivel = "";

		int numeroNivel = 1;

		for (int i = 0; i < posicionActores.length; i++) {

			nombreNivel = "Nivel " + numeroNivel;

			if (nivel.equals(nombreNivel)) {

				for (int j = 0; j < posicionActores[i].size(); j++) {

					posicion.add(posicionActores[i].get(j));

				}

			}

			numeroNivel++;
		}

		return posicion;

	}

	public void eliminarActores(String nivel) {

		String nombreNivel = "";

		int numeroNivel = 1;

		for (int i = 0; i < posicionActores.length; i++) {

			nombreNivel = "Nivel " + numeroNivel;

			if (nivel.equals(nombreNivel)) {

				posicionActores[i].clear();

				tipoActores[i].clear();

			}

			numeroNivel++;

		}

	}

	public void eliminarActor(String nivel, String tipoActor, int indice) {

		String nombreNivel = "";

		int numeroNivel = 1;

		for (int i = 0; i < posicionActores.length; i++) {

			nombreNivel = "Nivel " + numeroNivel;

			if (nivel.equals(nombreNivel)) {

				for (int j = 0; j < tipoActores[i].size(); j++) {

					if (tipoActor.equals(tipoActores[i].get(j))) {

						if (indice == j) {

							posicionActores[i].remove(j);

							tipoActores[i].remove(j);

						}

					}

				}

			}

			numeroNivel++;

		}

	}

}
