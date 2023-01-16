package com.diamon.dato;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import com.diamon.nucleo.Datos;

public class DatosDeNiveles implements Datos {

	public final static String DATOS = "datosNiveles.txt";

	@Override
	public InputStream leerDatoInterno(URL nombre) throws IOException {
		// TODO Auto-generated method stub
		return nombre.openStream();
	}

	@Override
	public InputStream leerDato(String nombre) throws IOException {
		// TODO Auto-generated method stub
		return new FileInputStream(new File(nombre));
	}

	@Override
	public OutputStream escribirDato(String nombre) throws IOException {
		// TODO Auto-generated method stub
		return new FileOutputStream(new File(nombre));
	}

}
