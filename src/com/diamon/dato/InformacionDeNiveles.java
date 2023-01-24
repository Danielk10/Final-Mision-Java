package com.diamon.dato;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import com.diamon.actor.AntiAereo;
import com.diamon.actor.Bala;
import com.diamon.actor.BalaEnemigo;
import com.diamon.actor.BalaEnemigoDestruible;
import com.diamon.actor.BalaEspecial;
import com.diamon.actor.Caja;
import com.diamon.actor.Explocion;
import com.diamon.actor.ExplocionB;
import com.diamon.actor.Fondo;
import com.diamon.actor.Humo;
import com.diamon.actor.Jugador;
import com.diamon.actor.JugadorMuriendo;
import com.diamon.actor.LanzaMisil;
import com.diamon.actor.MaquinaFinal;
import com.diamon.actor.MaquinaPared;
import com.diamon.actor.Misil;
import com.diamon.actor.Piso;
import com.diamon.actor.Poder;
import com.diamon.actor.Robot;
import com.diamon.actor.Saltador;
import com.diamon.actor.Satelite;
import com.diamon.actor.Vida;
import com.diamon.actor.Volador;
import com.diamon.nucleo.Actor;
import com.diamon.nucleo.Juego;
import com.diamon.utilidad.Vector2D;

public class InformacionDeNiveles {

	public final static String BALA = "com.diamon.actor.Bala";
	public final static String ANTI_AEREO = "com.diamon.actor.AntiAereo";
	public final static String BALA_ENEMIGO = "com.diamon.actor.BalaEnemigo";
	public final static String BALA_ENEMIGO_DESTRUIBLE = "com.diamon.actor.BalaEnemigoDestruible";
	public final static String BALA_EPECIAL = "com.diamon.actor.BalaEspecial";
	public final static String CAJA = "com.diamon.actor.Caja";
	public final static String EXPLOCION = "com.diamon.actor.Explocion";
	public final static String EXPLOCION_B = "com.diamon.actor.ExplocionB";
	public final static String FONDO = "com.diamon.actor.Fondo";
	public final static String HUMO = "com.diamon.actor.Humo";
	public final static String JUGADOR = "com.diamon.actor.Jugador";
	public final static String JUGADOR_MURIENDO = "com.diamon.actor.JuagadorMueriendo";
	public final static String LANZA_MISIL = "com.diamon.actor.LanzaMisil";
	public final static String MISIL = "com.diamon.actor.Misil";
	public final static String MAQUINA_FINAL = "com.diamon.actor.MaquinaFinal";
	public final static String MAQUINA_PARED = "com.diamon.actor.MaquinaPared";
	public final static String PISO = "com.diamon.actor.Piso";
	public final static String PODER = "com.diamon.actor.Poder";
	public final static String ROBOT = "com.diamon.actor.Robot";
	public final static String SALTADOR = "com.diamon.actor.Saltador";
	public final static String SATELITE = "com.diamon.actor.Satelite";
	public final static String VIDA = "com.diamon.actor.Vida";
	public final static String VOLADOR = "com.diamon.actor.Volador";

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

	private int numeroNivel;

	@SuppressWarnings("unchecked")
	public InformacionDeNiveles(int tipo, final Juego juego) {

		datos = new DatosDeNiveles();

		this.juego = juego;

		this.tipo = tipo;

		numeroActores = 0;

		numeroNivel = 1;

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

	public int getNumeroNivel() {
		return numeroNivel;
	}

	public void setNumeroNivel(int numeroNivel) {
		this.numeroNivel = numeroNivel;
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

			numeroNivel = Integer.parseInt(buferarchivoLeer.readLine());

			String tioActor = "";

			int x = 0;

			int y = 0;

			String nivel = "";

			for (int i = 0; i < numeroActores; i++) {

				for (int j = 0; j < 4; j++) {

					if (j == 0) {

						tioActor = buferarchivoLeer.readLine();
					}

					if (j == 1) {

						x = Integer.parseInt(buferarchivoLeer.readLine());

					}

					if (j == 2) {

						y = Integer.parseInt(buferarchivoLeer.readLine());
					}

					if (j == 3) {

						nivel = buferarchivoLeer.readLine();

						Actor actor = getTipoDeActor(tioActor);

						actor.setX(x);

						actor.setY(y);

						ArrayList<Actor> actores = new ArrayList<Actor>();

						actores.add(actor);

						gurdarActores(actores, tioActor, nivel);

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

	private Actor getTipoDeActor(String tipo) {

		Actor actor = null;

		if (tipo.equals(InformacionDeNiveles.VOLADOR)) {

			actor = new Volador(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.FONDO)) {

			actor = new Fondo(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.BALA)) {

			actor = new Bala(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.ANTI_AEREO)) {

			actor = new AntiAereo(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.BALA_ENEMIGO)) {

			actor = new BalaEnemigo(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.BALA_ENEMIGO_DESTRUIBLE)) {

			actor = new BalaEnemigoDestruible(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.BALA_EPECIAL)) {

			actor = new BalaEspecial(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.CAJA)) {

			actor = new Caja(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.EXPLOCION)) {

			actor = new Explocion(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.EXPLOCION_B)) {

			actor = new ExplocionB(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.HUMO)) {

			actor = new Humo(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.JUGADOR)) {

			actor = new Jugador(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.JUGADOR_MURIENDO)) {

			actor = new JugadorMuriendo(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.LANZA_MISIL)) {

			actor = new LanzaMisil(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.MAQUINA_FINAL)) {

			actor = new MaquinaFinal(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.MAQUINA_PARED)) {

			actor = new MaquinaPared(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.MISIL)) {

			actor = new Misil(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.PISO)) {

			actor = new Piso(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.PODER)) {

			actor = new Poder(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.ROBOT)) {

			actor = new Robot(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.SALTADOR)) {

			actor = new Saltador(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.SATELITE)) {

			actor = new Satelite(juego.getPantalla());

		}

		if (tipo.equals(InformacionDeNiveles.VIDA)) {

			actor = new Vida(juego.getPantalla());

		}

		return actor;
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

			buferarchivoEscribir.write(Integer.toString(numeroNivel));

			buferarchivoEscribir.newLine();

			String ni = "";

			int n = 1;

			for (int i = 0; i < posicionActores.length; i++) {

				ni = "Nivel " + n;

				if (niveles.size() > 0) {

					if (niveles.get(i).equals(ni)) {

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

								buferarchivoEscribir.write(niveles.get(i));

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

						actoresInternos.add(actores.get(j));

						niveles.add(nivel);

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

				actoresInternos.clear();//

				niveles.clear();//

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

							actoresInternos.remove(j);///

							niveles.remove(j); ///

						}

					}

				}

			}

			numeroNivel++;

		}

	}

}
