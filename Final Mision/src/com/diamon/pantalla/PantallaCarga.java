package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaCarga extends Pantalla {

	private BufferedImage fondo;

	private int ciclo;

	private int x;

	private BufferedImage barra;

	public PantallaCarga(Juego juego) {
		super(juego);

		fondo = recurso.cargarImagen("introduccion.png");

		barra = recurso.cargarImagen("barraProgreso1.png");

		ciclo = 0;

		x = 0;
	}

	@Override
	public void pausa() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actualizar(float delta) {

		ciclo++;

		if (ciclo % 60 == 0) {

			recurso.cargarImagen("fondo1.png");

			recurso.cargarImagen("fondo2.png");

			recurso.cargarImagen("fondo3.png");

			recurso.cargarImagen("fondo4.png");

			recurso.cargarImagen("fondo5.png");

			recurso.cargarImagen("fondo6.png");

			recurso.cargarImagen("fondo7.png");

			recurso.cargarImagen("fondo8.png");

			recurso.cargarImagen("fondo9.png");

			recurso.cargarImagen("fondo10.png");

			recurso.cargarImagen("fondo11.png");

			recurso.cargarImagen("fondo12.png");

			recurso.cargarImagen("fondo13.png");

			recurso.cargarImagen("fondo14.png");

			recurso.cargarImagen("fondo15.png");

			recurso.cargarImagen("fondo16.png");

			recurso.cargarImagen("fondo17.png");

			recurso.cargarImagen("fondo18.png");

			recurso.cargarImagen("fondo19.png");

			recurso.cargarImagen("fondo20.png");

			recurso.cargarImagen("fondo21MGF.png");

			recurso.cargarImagen("maquinaFinal.png");

			recurso.cargarImagen("fondoIntroduccion1.png");

			recurso.cargarImagen("fondoIntroduccion2.png");

			recurso.cargarImagen("fondoIntroduccion3.png");

			recurso.cargarImagen("menu1.png");

			recurso.cargarImagen("menu2.png");

			recurso.cargarImagen("menuFinal.png");

			recurso.cargarImagen("creditos.png");

			recurso.cargarImagen("continuar.png");

			recurso.cargarImagen("nivel1.png");

			recurso.cargarImagen("finJuego.png");

			recurso.cargarImagen("finNivel.png");

			recurso.cargarImagen("jugador1D1.png");

			recurso.cargarImagen("jugador1D2.png");

			recurso.cargarImagen("jugador1D3.png");

			recurso.cargarImagen("jugador1D4.png");

			recurso.cargarImagen("jugador1D5.png");

			recurso.cargarImagen("jugador1D6.png");

			recurso.cargarImagen("jugador1I1.png");

			recurso.cargarImagen("jugador1I2.png");

			recurso.cargarImagen("jugador1I3.png");

			recurso.cargarImagen("jugador2D1.png");

			recurso.cargarImagen("jugador2D2.png");

			recurso.cargarImagen("jugador2D3.png");

			recurso.cargarImagen("jugador2D4.png");

			recurso.cargarImagen("jugador2I1.png");

			recurso.cargarImagen("humoMisil1.png");

			recurso.cargarImagen("humoMisil2.png");

			recurso.cargarImagen("humoMisil3.png");

			recurso.cargarImagen("lanzaMisilI1.png");

			recurso.cargarImagen("lanzaMisilI2.png");

			recurso.cargarImagen("lanzaMisilI3.png");

			recurso.cargarImagen("lanzaMisilD1.png");

			recurso.cargarImagen("lanzaMisilD2.png");

			recurso.cargarImagen("lanzaMisilD3.png");

			recurso.cargarImagen("maquinaParedI1.png");

			recurso.cargarImagen("maquinaParedI2.png");

			recurso.cargarImagen("maquinaParedD1.png");

			recurso.cargarImagen("maquinaParedD2.png");

			recurso.cargarImagen("poderB.png");

			recurso.cargarImagen("poderL.png");

			recurso.cargarImagen("poderS.png");

			recurso.cargarImagen("poderW.png");

			recurso.cargarImagen("robotD.png");

			recurso.cargarImagen("robotI.png");

			recurso.cargarImagen("saltador1.png");

			recurso.cargarImagen("saltador2.png");

			recurso.cargarImagen("saltador3.png");

			recurso.cargarImagen("saltador4.png");

			recurso.cargarImagen("sateliteDD1.png");

			recurso.cargarImagen("sateliteDD2.png");

			recurso.cargarImagen("sateliteDI1.png");

			recurso.cargarImagen("sateliteDI2.png");

			recurso.cargarImagen("sateliteHD1.png");

			recurso.cargarImagen("sateliteHD2.png");

			recurso.cargarImagen("sateliteHI1.png");

			recurso.cargarImagen("sateliteHI2.png");

			recurso.cargarImagen("sateliteVA1.png");

			recurso.cargarImagen("sateliteVA2.png");

			recurso.cargarImagen("sateliteVB1.png");

			recurso.cargarImagen("sateliteVB2.png");

			recurso.cargarImagen("selector1.png");

			recurso.cargarImagen("selector2.png");

			recurso.cargarImagen("vida1.png");

			recurso.cargarImagen("vida2.png");

			recurso.cargarImagen("voladorI1.png");

			recurso.cargarImagen("voladorI2.png");

			recurso.cargarImagen("voladorI3.png");

			recurso.cargarImagen("voladorD1.png");

			recurso.cargarImagen("voladorD2.png");

			recurso.cargarImagen("voladorD3.png");

			recurso.cargarImagen("antiAreoD1.png");

			recurso.cargarImagen("antiAreoH1.png");

			recurso.cargarImagen("antiAreoV1.png");

			recurso.cargarImagen("antiAreoD2.png");

			recurso.cargarImagen("antiAreoH2.png");

			recurso.cargarImagen("antiAreoV2.png");

			recurso.cargarImagen("antiAreoEspecialD.png");

			recurso.cargarImagen("antiAreoEspecialH.png");

			recurso.cargarImagen("antiAreoEspecialV1.png");

			recurso.cargarImagen("antiAreoEspecialV2.png");

			recurso.cargarImagen("antiAreoEspecialV3.png");

			recurso.cargarImagen("antiAreoEspecialV4.png");

			recurso.cargarImagen("antiAreoEspecialV5.png");

			recurso.cargarImagen("antiAreoEspecialV6.png");

			recurso.cargarImagen("antiAreoEspecialV7.png");

			recurso.cargarImagen("antiAreoEspecialV8.png");

			recurso.cargarImagen("antiAreoEspecialV9.png");

			recurso.cargarImagen("antiAreoEspecialV10.png");

			recurso.cargarImagen("antiAreoEspecialV11.png");

			recurso.cargarImagen("antiAreoEspecialV12.png");

			recurso.cargarImagen("antiAreoEspecialV13.png");

			recurso.cargarImagen("antiAreoEspecialV14.png");

			recurso.cargarImagen("misilD1.png");

			recurso.cargarImagen("misilH1.png");

			recurso.cargarImagen("misilV1.png");

			recurso.cargarImagen("misilD2.png");

			recurso.cargarImagen("misilH2.png");

			recurso.cargarImagen("misilV2.png");

			recurso.cargarImagen("balaBD.png");

			recurso.cargarImagen("balaBI.png");

			recurso.cargarImagen("balaE1.png");

			recurso.cargarImagen("balaE2.png");

			recurso.cargarImagen("balaE3.png");

			recurso.cargarImagen("balaE4.png");

			recurso.cargarImagen("balaHD.png");

			recurso.cargarImagen("balaHI.png");

			recurso.cargarImagen("balaLD1.png");

			recurso.cargarImagen("balaLD2.png");

			recurso.cargarImagen("balaLD3.png");

			recurso.cargarImagen("balaLI1.png");

			recurso.cargarImagen("balaLI2.png");

			recurso.cargarImagen("balaLI3.png");

			recurso.cargarImagen("balaParedI.png");

			recurso.cargarImagen("balaParedD.png");

			recurso.cargarImagen("balaSaltador1.png");

			recurso.cargarImagen("balaSaltador2.png");

			recurso.cargarImagen("balaSatelite.png");

			recurso.cargarImagen("balaWD1.png");

			recurso.cargarImagen("balaWD2.png");

			recurso.cargarImagen("balaWD3.png");

			recurso.cargarImagen("balaWI1.png");

			recurso.cargarImagen("balaWI2.png");

			recurso.cargarImagen("balaWI3.png");

			recurso.cargarImagen("bola1.png");

			recurso.cargarImagen("bola2.png");

			recurso.cargarImagen("bola3.png");

			recurso.cargarImagen("bola4.png");

			recurso.cargarImagen("bola5.png");

			recurso.cargarImagen("bola6.png");

			recurso.cargarImagen("bola7.png");

			recurso.cargarImagen("bola8.png");

			recurso.cargarImagen("bola9.png");

			recurso.cargarImagen("bola10.png");

			recurso.cargarImagen("bola11.png");

			recurso.cargarImagen("bola12.png");

			recurso.cargarImagen("bola13.png");

			recurso.cargarImagen("bola14.png");

			recurso.cargarImagen("bola15.png");

			recurso.cargarImagen("bola16.png");

			recurso.cargarImagen("cajaPoder1.png");

			recurso.cargarImagen("cajaPoder2.png");

			recurso.cargarImagen("cajaPoder3.png");

			recurso.cargarImagen("cajaPoder4.png");

			recurso.cargarImagen("explosion1.png");

			recurso.cargarImagen("explosion2.png");

			recurso.cargarImagen("explosion3.png");

			recurso.cargarImagen("explosion4.png");

			recurso.cargarImagen("explosionB1.png");

			recurso.cargarImagen("explosionB2.png");

			recurso.cargarImagen("explosionB3.png");

			recurso.cargarImagen("explosionB4.png");

			recurso.cargarImagen("explosionB5.png");

			recurso.cargarImagen("explosionMisil1.png");

			recurso.cargarImagen("explosionMisil2.png");

			recurso.cargarImagen("explosionMisil3.png");

			recurso.cargarImagen("explosionMisil4.png");

			recurso.cargarImagen("logo.png");

			recurso.cargarSonido("musica.wav");

			recurso.cargarSonido("menu.wav");

			recurso.cargarSonido("precentacion1.wav");

			recurso.cargarSonido("comienzo1.wav");

			recurso.cargarSonido("disparo.wav");

			recurso.cargarSonido("disparoL.wav");

			recurso.cargarSonido("poder.wav");

			recurso.cargarSonido("introduccion.wav");

			recurso.cargarSonido("finDeJuego.wav");

			recurso.cargarSonido("muriendo.wav");

			recurso.cargarSonido("explosion.wav");

			recurso.cargarSonido("pausa.wav");

			juego.setPantalla(new PantallaMenu(juego));

			ciclo = 0;
		}

	}

	@Override
	public void dibujar(Graphics2D pincel, float delta) {

		x += 12;

		pincel.drawImage(fondo, 0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA, this);

		pincel.drawImage(barra, x, 160, Juego.ANCHO_PANTALLA, 64, this);

	}

	@Override
	public void colisiones() {
		// TODO Auto-generated method stub

	}

	@Override
	public void ocultar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mostrar() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reajustarPantalla(int ancho, int alto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void teclaPresionada(KeyEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void teclaLevantada(KeyEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void teclaTipo(KeyEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonDeslizando(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonMoviendo(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonClick(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonPresionado(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ratonLevantado(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void guardarDatos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void liberarRecursos() {
		// TODO Auto-generated method stub
		
	}

}
