package com.diamon.pantalla;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.diamon.juego.FinalMision;
import com.diamon.nucleo.Juego;
import com.diamon.nucleo.Pantalla;

public class PantallaCarga extends Pantalla {

	private BufferedImage fondo;

	private int ciclo;

	private int x;

	private BufferedImage barra;

	public PantallaCarga(FinalMision juego) {
		super(juego);

		fondo = juego.getRecurso().cargarImagen("introduccion.png");

		barra = juego.getRecurso().cargarImagen("barraProgreso1.png");

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

			juego.getRecurso().cargarImagen("fondo1.png");

			juego.getRecurso().cargarImagen("fondo2.png");

			juego.getRecurso().cargarImagen("fondo3.png");

			juego.getRecurso().cargarImagen("fondo4.png");

			juego.getRecurso().cargarImagen("fondo5.png");

			juego.getRecurso().cargarImagen("fondo6.png");

			juego.getRecurso().cargarImagen("fondo7.png");

			juego.getRecurso().cargarImagen("fondo8.png");

			juego.getRecurso().cargarImagen("fondo9.png");

			juego.getRecurso().cargarImagen("fondo10.png");

			juego.getRecurso().cargarImagen("fondo11.png");

			juego.getRecurso().cargarImagen("fondo12.png");

			juego.getRecurso().cargarImagen("fondo13.png");

			juego.getRecurso().cargarImagen("fondo14.png");

			juego.getRecurso().cargarImagen("fondo15.png");

			juego.getRecurso().cargarImagen("fondo16.png");

			juego.getRecurso().cargarImagen("fondo17.png");

			juego.getRecurso().cargarImagen("fondo18.png");

			juego.getRecurso().cargarImagen("fondo19.png");

			juego.getRecurso().cargarImagen("fondo20.png");

			juego.getRecurso().cargarImagen("fondo21MGF.png");

			juego.getRecurso().cargarImagen("maquinaFinal.png");

			juego.getRecurso().cargarImagen("fondoIntroduccion1.png");

			juego.getRecurso().cargarImagen("fondoIntroduccion2.png");

			juego.getRecurso().cargarImagen("fondoIntroduccion3.png");

			juego.getRecurso().cargarImagen("menu1.png");

			juego.getRecurso().cargarImagen("menu2.png");

			juego.getRecurso().cargarImagen("menuFinal.png");

			juego.getRecurso().cargarImagen("creditos.png");

			juego.getRecurso().cargarImagen("continuar.png");

			juego.getRecurso().cargarImagen("nivel1.png");

			juego.getRecurso().cargarImagen("finJuego.png");

			juego.getRecurso().cargarImagen("finNivel.png");

			juego.getRecurso().cargarImagen("jugador1D1.png");

			juego.getRecurso().cargarImagen("jugador1D2.png");

			juego.getRecurso().cargarImagen("jugador1D3.png");

			juego.getRecurso().cargarImagen("jugador1D4.png");

			juego.getRecurso().cargarImagen("jugador1D5.png");

			juego.getRecurso().cargarImagen("jugador1D6.png");

			juego.getRecurso().cargarImagen("jugador1I1.png");

			juego.getRecurso().cargarImagen("jugador1I2.png");

			juego.getRecurso().cargarImagen("jugador1I3.png");

			juego.getRecurso().cargarImagen("jugador2D1.png");

			juego.getRecurso().cargarImagen("jugador2D2.png");

			juego.getRecurso().cargarImagen("jugador2D3.png");

			juego.getRecurso().cargarImagen("jugador2D4.png");

			juego.getRecurso().cargarImagen("jugador2I1.png");

			juego.getRecurso().cargarImagen("humoMisil1.png");

			juego.getRecurso().cargarImagen("humoMisil2.png");

			juego.getRecurso().cargarImagen("humoMisil3.png");

			juego.getRecurso().cargarImagen("lanzaMisilI1.png");

			juego.getRecurso().cargarImagen("lanzaMisilI2.png");

			juego.getRecurso().cargarImagen("lanzaMisilI3.png");

			juego.getRecurso().cargarImagen("lanzaMisilD1.png");

			juego.getRecurso().cargarImagen("lanzaMisilD2.png");

			juego.getRecurso().cargarImagen("lanzaMisilD3.png");

			juego.getRecurso().cargarImagen("maquinaParedI1.png");

			juego.getRecurso().cargarImagen("maquinaParedI2.png");

			juego.getRecurso().cargarImagen("maquinaParedD1.png");

			juego.getRecurso().cargarImagen("maquinaParedD2.png");

			juego.getRecurso().cargarImagen("poderB.png");

			juego.getRecurso().cargarImagen("poderL.png");

			juego.getRecurso().cargarImagen("poderS.png");

			juego.getRecurso().cargarImagen("poderW.png");

			juego.getRecurso().cargarImagen("robotD.png");

			juego.getRecurso().cargarImagen("robotI.png");

			juego.getRecurso().cargarImagen("saltador1.png");

			juego.getRecurso().cargarImagen("saltador2.png");

			juego.getRecurso().cargarImagen("saltador3.png");

			juego.getRecurso().cargarImagen("saltador4.png");

			juego.getRecurso().cargarImagen("sateliteDD1.png");

			juego.getRecurso().cargarImagen("sateliteDD2.png");

			juego.getRecurso().cargarImagen("sateliteDI1.png");

			juego.getRecurso().cargarImagen("sateliteDI2.png");

			juego.getRecurso().cargarImagen("sateliteHD1.png");

			juego.getRecurso().cargarImagen("sateliteHD2.png");

			juego.getRecurso().cargarImagen("sateliteHI1.png");

			juego.getRecurso().cargarImagen("sateliteHI2.png");

			juego.getRecurso().cargarImagen("sateliteVA1.png");

			juego.getRecurso().cargarImagen("sateliteVA2.png");

			juego.getRecurso().cargarImagen("sateliteVB1.png");

			juego.getRecurso().cargarImagen("sateliteVB2.png");

			juego.getRecurso().cargarImagen("selector1.png");

			juego.getRecurso().cargarImagen("selector2.png");

			juego.getRecurso().cargarImagen("vida1.png");

			juego.getRecurso().cargarImagen("vida2.png");

			juego.getRecurso().cargarImagen("voladorI1.png");

			juego.getRecurso().cargarImagen("voladorI2.png");

			juego.getRecurso().cargarImagen("voladorI3.png");

			juego.getRecurso().cargarImagen("voladorD1.png");

			juego.getRecurso().cargarImagen("voladorD2.png");

			juego.getRecurso().cargarImagen("voladorD3.png");

			juego.getRecurso().cargarImagen("antiAreoD1.png");

			juego.getRecurso().cargarImagen("antiAreoH1.png");

			juego.getRecurso().cargarImagen("antiAreoV1.png");

			juego.getRecurso().cargarImagen("antiAreoD2.png");

			juego.getRecurso().cargarImagen("antiAreoH2.png");

			juego.getRecurso().cargarImagen("antiAreoV2.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialD.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialH.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV1.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV2.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV3.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV4.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV5.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV6.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV7.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV8.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV9.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV10.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV11.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV12.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV13.png");

			juego.getRecurso().cargarImagen("antiAreoEspecialV14.png");

			juego.getRecurso().cargarImagen("misilD1.png");

			juego.getRecurso().cargarImagen("misilH1.png");

			juego.getRecurso().cargarImagen("misilV1.png");

			juego.getRecurso().cargarImagen("misilD2.png");

			juego.getRecurso().cargarImagen("misilH2.png");

			juego.getRecurso().cargarImagen("misilV2.png");

			juego.getRecurso().cargarImagen("balaBD.png");

			juego.getRecurso().cargarImagen("balaBI.png");

			juego.getRecurso().cargarImagen("balaE1.png");

			juego.getRecurso().cargarImagen("balaE2.png");

			juego.getRecurso().cargarImagen("balaE3.png");

			juego.getRecurso().cargarImagen("balaE4.png");

			juego.getRecurso().cargarImagen("balaHD.png");

			juego.getRecurso().cargarImagen("balaHI.png");

			juego.getRecurso().cargarImagen("balaLD1.png");

			juego.getRecurso().cargarImagen("balaLD2.png");

			juego.getRecurso().cargarImagen("balaLD3.png");

			juego.getRecurso().cargarImagen("balaLI1.png");

			juego.getRecurso().cargarImagen("balaLI2.png");

			juego.getRecurso().cargarImagen("balaLI3.png");

			juego.getRecurso().cargarImagen("balaParedI.png");

			juego.getRecurso().cargarImagen("balaParedD.png");

			juego.getRecurso().cargarImagen("balaSaltador1.png");

			juego.getRecurso().cargarImagen("balaSaltador2.png");

			juego.getRecurso().cargarImagen("balaSatelite.png");

			juego.getRecurso().cargarImagen("balaWD1.png");

			juego.getRecurso().cargarImagen("balaWD2.png");

			juego.getRecurso().cargarImagen("balaWD3.png");

			juego.getRecurso().cargarImagen("balaWI1.png");

			juego.getRecurso().cargarImagen("balaWI2.png");

			juego.getRecurso().cargarImagen("balaWI3.png");

			juego.getRecurso().cargarImagen("bola1.png");

			juego.getRecurso().cargarImagen("bola2.png");

			juego.getRecurso().cargarImagen("bola3.png");

			juego.getRecurso().cargarImagen("bola4.png");

			juego.getRecurso().cargarImagen("bola5.png");

			juego.getRecurso().cargarImagen("bola6.png");

			juego.getRecurso().cargarImagen("bola7.png");

			juego.getRecurso().cargarImagen("bola8.png");

			juego.getRecurso().cargarImagen("bola9.png");

			juego.getRecurso().cargarImagen("bola10.png");

			juego.getRecurso().cargarImagen("bola11.png");

			juego.getRecurso().cargarImagen("bola12.png");

			juego.getRecurso().cargarImagen("bola13.png");

			juego.getRecurso().cargarImagen("bola14.png");

			juego.getRecurso().cargarImagen("bola15.png");

			juego.getRecurso().cargarImagen("bola16.png");

			juego.getRecurso().cargarImagen("cajaPoder1.png");

			juego.getRecurso().cargarImagen("cajaPoder2.png");

			juego.getRecurso().cargarImagen("cajaPoder3.png");

			juego.getRecurso().cargarImagen("cajaPoder4.png");

			juego.getRecurso().cargarImagen("explosion1.png");

			juego.getRecurso().cargarImagen("explosion2.png");

			juego.getRecurso().cargarImagen("explosion3.png");

			juego.getRecurso().cargarImagen("explosion4.png");

			juego.getRecurso().cargarImagen("explosionB1.png");

			juego.getRecurso().cargarImagen("explosionB2.png");

			juego.getRecurso().cargarImagen("explosionB3.png");

			juego.getRecurso().cargarImagen("explosionB4.png");

			juego.getRecurso().cargarImagen("explosionB5.png");

			juego.getRecurso().cargarImagen("explosionMisil1.png");

			juego.getRecurso().cargarImagen("explosionMisil2.png");

			juego.getRecurso().cargarImagen("explosionMisil3.png");

			juego.getRecurso().cargarImagen("explosionMisil4.png");

			juego.getRecurso().cargarImagen("logo.png");

			juego.getRecurso().cargarSonido("musica.wav");

			juego.getRecurso().cargarSonido("menu.wav");

			juego.getRecurso().cargarSonido("precentacion1.wav");

			juego.getRecurso().cargarSonido("comienzo1.wav");

			juego.getRecurso().cargarSonido("disparo.wav");

			juego.getRecurso().cargarSonido("disparoL.wav");

			juego.getRecurso().cargarSonido("poder.wav");

			juego.getRecurso().cargarSonido("introduccion.wav");

			juego.getRecurso().cargarSonido("finDeJuego.wav");

			juego.getRecurso().cargarSonido("muriendo.wav");

			juego.getRecurso().cargarSonido("explosion.wav");

			juego.getRecurso().cargarSonido("pausa.wav");

			juego.setPantalla(new PantallaMenu(juego));

			// juego.setPantalla(new PantallaExtra(juego));

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

}
