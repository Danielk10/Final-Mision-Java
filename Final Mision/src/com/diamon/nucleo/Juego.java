package com.diamon.nucleo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.diamon.dato.Configuraciones;
import com.diamon.dato.DatosJuego;
import com.diamon.utilidad.Recurso;

public abstract class Juego extends Canvas implements Runnable, KeyListener, MouseMotionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	public final static int ANCHO_PANTALLA = 640;

	public final static int ALTO_PANTALLA = 480;

	private Thread hilo;

	public static final String TITULO_JUEGO = "Final Mision";

	private volatile boolean iniciar;

	private final static int UNIDAD_TIEMPO = 1000000000;

	private double delta = 0;

	private final static byte CICLOS = 60;

	private final static double LIMITE_CICLOS = UNIDAD_TIEMPO / CICLOS;

	private BufferStrategy bufer;

	private JFrame ventana;

	private Pantalla pantalla;

	private Recurso recurso;

	private DatosJuego datos;

	private Configuraciones configuracioin;

	private GraphicsDevice dispositivo;

	public Juego() {

		super();

		setBounds(0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

		setBackground(Color.BLACK);

		recurso = new Recurso();

		configuracioin = new Configuraciones();

		datos = new DatosJuego();

		BufferedImage cursor = recurso.createCompatible(10, 10, Transparency.BITMASK);

     //  cursor.getGraphics().drawImage(recurso.cargarImagen("vida1.png"), 0, 0, 5, 5,this);

		Toolkit t = Toolkit.getDefaultToolkit();

		Cursor c = t.createCustomCursor(cursor, new Point(5, 5), "null");

		setCursor(c);

		requestFocus();

		setFocusable(true);

		setIgnoreRepaint(true);

		addKeyListener(this);

		addMouseMotionListener(this);

		addMouseListener(this);

		hilo = new Thread(this);

		ventana = new JFrame(Juego.TITULO_JUEGO);

		ventana.setBounds(0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = (JPanel) ventana.getContentPane();

		panel.setPreferredSize(new Dimension(Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA));

		panel.setLayout(null);

		panel.add(this);

		ventana.setUndecorated(true);

		ventana.setResizable(false);

		ventana.setIconImage(recurso.cargarImagen("logo.png"));

		ventana.setVisible(true);

		ventana.setLocationRelativeTo(null);

		GraphicsEnvironment graficosLocales = GraphicsEnvironment.getLocalGraphicsEnvironment();

		dispositivo = graficosLocales.getDefaultScreenDevice();

		DisplayMode[] dis = dispositivo.getDisplayModes();

		dispositivo.setFullScreenWindow(ventana);

		for (int i = 0; i < dis.length; i++) {

			if (dis != null && dispositivo.isDisplayChangeSupported()) {

				try {

					if ((dis[i].getWidth() == 640) && (dis[i].getHeight() == 480) && (dis[i].getBitDepth() == 32)
							&& (dis[i].getRefreshRate() == 60)) {

						dispositivo.setDisplayMode(dis[i]);

					}

				} catch (Exception e) {

				}

			}

		}

		createBufferStrategy(2);

		bufer = getBufferStrategy();

		iniciar = false;

		pantalla = null;

	}

	@Override
	public void run() {

		final Graphics2D pincel = (Graphics2D) bufer.getDrawGraphics();

		double referencia = System.nanoTime();

		while (iniciar) {

			final double tiempoInicial = System.nanoTime();

			delta = (float) (tiempoInicial - referencia) / UNIDAD_TIEMPO;

			// Aquí actualización y dibújo

			pincel.setColor(Color.BLUE);

			pincel.fillRect(0, 0, Juego.ANCHO_PANTALLA, Juego.ALTO_PANTALLA);

			colisiones();

			actualizar((float) delta);

			renderizar(pincel, (float) delta);

			bufer.show(); // Hasta aquí

			referencia = tiempoInicial;

			// Limite de cuadros

			do {

				Thread.yield();

			} while (System.nanoTime() - tiempoInicial < LIMITE_CICLOS);

		}

	}

	public void iniciar() {
		hilo.start();
		iniciar = true;

	}

	public void renderizar(Graphics2D pincel, float delta) {
		if (pantalla != null) {
			pantalla.dibujar(pincel, delta);

		}

		pincel.setColor(Color.GREEN);

		pincel.drawString(getFPS() + " FPS", 0, 10);
	}

	public void actualizar(float delta) {
		if (pantalla != null) {
			pantalla.actualizar(delta);

		}
	}

	public void colisiones() {
		if (pantalla != null) {
			pantalla.colisiones();

		}

	}

	public void reajustarPantalla(int ancho, int alto) {
		if (pantalla != null) {
			pantalla.reajustarPantalla(ancho, ancho);
		}
	}

	public void resumen() {
		if (pantalla != null) {

			iniciar = true;
			hilo = new Thread(this);
			hilo.start();
			pantalla.resume();
		}
	}

	public void pausa() {
		if (pantalla != null) {

			pantalla.pausa();
			iniciar = false;
			while (true) {
				try {
					hilo.join();

					return;

				} catch (InterruptedException e) {

				}

			}

		}
	}

	public void liberarRecursos() {
		if (pantalla != null) {
			pantalla.ocultar();
		}
	}

	public void setPantalla(Pantalla pantalla) {
		if (this.pantalla != null) {
			this.pantalla.ocultar();
		}

		this.pantalla = pantalla;

		if (this.pantalla != null) {
			this.pantalla.mostrar();
			this.pantalla.reajustarPantalla(getWidth(), getHeight());

		}
	}

	public Pantalla getPantalla() {
		return pantalla;

	}

	@Override
	public void keyPressed(KeyEvent ev) {
		if (pantalla != null) {
			pantalla.teclaPresionada(ev);

		}

		switch (ev.getKeyCode()) {

		case KeyEvent.VK_Q:

			dispositivo.setFullScreenWindow(null);

			break;

		case KeyEvent.VK_W:

			setPantallaCompleta();

			break;

		case KeyEvent.VK_ESCAPE:

			System.exit(0);

			break;

		default:

			break;

		}

	}

	@Override
	public void keyReleased(KeyEvent ev) {
		if (pantalla != null) {
			pantalla.teclaLevantada(ev);

		}

	}

	@Override
	public void mouseDragged(MouseEvent ev) {

		if (pantalla != null) {
			pantalla.ratonDeslizando(ev);

		}

	}

	@Override
	public void mouseMoved(MouseEvent ev) {
		if (pantalla != null) {
			pantalla.ratonMoviendo(ev);

		}

	}

	@Override
	public void mouseClicked(MouseEvent ev) {
		if (pantalla != null) {
			pantalla.ratonClick(ev);

		}

	}

	@Override
	public void mouseEntered(MouseEvent ev) {

	}

	@Override
	public void mouseExited(MouseEvent ev) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent ev) {
		if (pantalla != null) {
			pantalla.ratonPresionado(ev);

		}

	}

	@Override
	public void mouseReleased(MouseEvent ev) {
		if (pantalla != null) {
			pantalla.ratonLevantado(ev);

		}

	}

	@Override
	public void keyTyped(KeyEvent ev) {
		if (pantalla != null) {
			pantalla.teclaTipo(ev);

		}

	}

	public void setPantallaCompleta() {

		DisplayMode[] dis = dispositivo.getDisplayModes();

		dispositivo.setFullScreenWindow(ventana);

		for (int i = 0; i < dis.length; i++) {

			if (dis != null && dispositivo.isDisplayChangeSupported()) {

				try {

					if ((dis[i].getWidth() == 640) && (dis[i].getHeight() == 480) && (dis[i].getBitDepth() == 32)
							&& (dis[i].getRefreshRate() == 60)) {

						dispositivo.setDisplayMode(dis[i]);

					}

				} catch (Exception e) {

				}

			}

		}

	}

	public int getFPS() {

		return (int) (1 / delta);

	}

	public DatosJuego getDatos() {
		return datos;
	}

	public Recurso getRecurso() {
		return recurso;
	}

	public Configuraciones getConfiguracioin() {
		return configuracioin;
	}

	public void parar() {
		iniciar = false;
	}

}
