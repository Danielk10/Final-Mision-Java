package com.diamon.dato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import com.diamon.nucleo.Datos;

public class DatosJuego implements Datos {

	public final static String DATOS = "datos.txt";

	@Override
	public InputStream leerDato(String nombre) throws IOException {
		return new FileInputStream(new File(nombre));
	}

	@Override
	public OutputStream escribirDato(String nombre) throws IOException {

		return new FileOutputStream(new File(nombre));
	}

	@Override
	public InputStream leerDatoInterno(URL nombre) throws IOException {

		return nombre.openStream();
	}

}
