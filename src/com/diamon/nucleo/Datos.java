package com.diamon.nucleo;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.net.URL;

public interface Datos {
	
	public InputStream leerDatoInterno(URL nombre) throws IOException;

	public InputStream leerDato(String nombre) throws IOException;

	public OutputStream escribirDato(String nombre) throws IOException;

}
