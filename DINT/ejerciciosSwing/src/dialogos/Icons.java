package g;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class Icons {
	
	// Método de uso:
	// Busca el archivo "image.png" dentro de la carpeta "images" situada en la raíz del classpath.
	// Ejemplo:
	// ImageIcon icono = Icons.convertImage("/images/image.png", 64, 64);

	public static ImageIcon convertImage(String path, int width, int height) {
	    ImageIcon icon = new ImageIcon(Icons.class.getResource(path));
	    Image img = icon.getImage();
	    Image scaled = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
	    
	    return new ImageIcon(scaled);
	}
}
