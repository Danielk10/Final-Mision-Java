package com.diamon.utilidad;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Recurso implements ImageObserver {

	private HashMap<String, BufferedImage> imagenes;

	private HashMap<String, AudioClip> sonidos;

	public Recurso() {

		sonidos = new HashMap<String, AudioClip>();

		imagenes = new HashMap<String, BufferedImage>();

	}

	public BufferedImage cargarImagen(String nombre) {

		if (imagenes.keySet().size() <= 0) {

			try {

				final URL rutaImagen = getClass().getClassLoader().getResource("res/" + nombre);

				final BufferedImage imagen = ImageIO.read(rutaImagen);

				final BufferedImage compatible = createCompatible(imagen.getWidth(), imagen.getHeight(),
						Transparency.BITMASK);

				final Graphics2D g = (Graphics2D) compatible.getGraphics();

				g.drawImage(imagen, 0, 0, this);

				imagenes.put(nombre, compatible);

				return imagenes.get(nombre);

			} catch (IOException e) {

				return null;
			}

		}

		for (String nombreGuardado : imagenes.keySet()) {

			if (nombre.equals(nombreGuardado)) {

				return null;

			} else {
				try {

					final URL rutaImagen = getClass().getClassLoader().getResource("res/" + nombre);

					final BufferedImage imagen = ImageIO.read(rutaImagen);

					final BufferedImage compatible = createCompatible(imagen.getWidth(), imagen.getHeight(),
							Transparency.BITMASK);

					final Graphics2D g = (Graphics2D) compatible.getGraphics();

					g.drawImage(imagen, 0, 0, this);

					imagenes.put(nombre, compatible);

					return imagenes.get(nombre);

				} catch (IOException e) {

					return null;
				}

			}

		}
		return null;

	}

	public AudioClip cargarSonido(String nombre) {

		try {

			final URL rutaSonido = getClass().getClassLoader().getResource("res/" + nombre);

			final AudioClip sonido = Applet.newAudioClip(rutaSonido);

			sonidos.put(nombre, sonido);

			return sonidos.get(nombre);

		} catch (Exception e) {

			return null;
		}

	}

	public AudioClip getSonido(String nombre) {

		AudioClip sonido = (AudioClip) sonidos.get(nombre);

		if (sonido == null) {

			sonido = cargarSonido(nombre);

			sonidos.put(nombre, sonido);

		}

		return sonido;

	}

	public void playSonido(final String nombre) {
		new Thread(new Runnable() {
			public void run() {
				getSonido(nombre).play();
			}
		}).start();
	}

	public void repetirSonido(final String nombre) {
		new Thread(new Runnable() {
			public void run() {
				getSonido(nombre).loop();
			}
		}).start();
	}

	public void pararSonido(AudioClip sonido) {

		sonido.stop();

	}

	public BufferedImage getImagen(String nombre) {

		BufferedImage imagen = (BufferedImage) imagenes.get(nombre);

		if (imagen == null) {

			imagen = cargarImagen(nombre);

			imagenes.put(nombre, imagen);

		}

		return imagen;

	}

	public BufferedImage createCompatible(int width, int height, int transparency) {
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
				.getDefaultConfiguration();
		BufferedImage compatible = gc.createCompatibleImage(width, height, transparency);
		return compatible;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		return (infoflags & (ALLBITS | ABORT)) == 0;
	}

	public BufferedImage[] getImagenes(BufferedImage img, int ancho, int alto) {

		BufferedImage cell[] = new BufferedImage[(img.getWidth(null) / ancho) / 2 + (img.getHeight(null) / alto) / 2];

		int iw, ih;

		int tw, th;

		int fila, columna;

		fila = ((img.getWidth(null) / ancho) / 2) / 2;

		columna = ((img.getHeight(null) / alto) / 2) / 2;

		iw = img.getWidth(null);

		ih = img.getHeight(null);

		tw = (iw / ancho) * ((img.getWidth(null) / ancho) / 2);

		th = (ih / alto) * ((img.getHeight(null) / alto) / 2);

		CropImageFilter f;

		FilteredImageSource fis;

		for (int y = 0; y < fila; y++) {

			for (int x = 0; x < columna; x++) {

				f = new CropImageFilter(tw * x, th * y, tw, th);

				fis = new FilteredImageSource(img.getSource(), f);

				int i = y * columna + x;

				BufferedImage fg = createCompatible(tw, th, Transparency.BITMASK);

				fg.getGraphics().drawImage(new Applet().createImage(fis), 0, 0, null);

				cell[i] = fg;

			}
		}

		return cell;
	}

}
