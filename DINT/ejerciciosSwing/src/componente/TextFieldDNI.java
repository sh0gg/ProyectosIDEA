package componente;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TextFieldDNI extends JTextField implements KeyListener {
	private static final long serialVersionUID = 1L;
	private Color emptyColor = Color.LIGHT_GRAY;
	private Color invalidColor = Color.RED;
	private Color validColor = Color.GREEN;

	public TextFieldDNI() {
		super();
		setBackground(emptyColor);
		addKeyListener(this);
	}

// --- Métodos del KeyListener ---
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		validarYActualizar();
	}

// --- Lógica de validación y pintado ---
	private void validarYActualizar() {
		String text = getText().trim();
		if (text.isEmpty()) {
			setBackground(emptyColor);
		} else if (isValidDNI(text)) {
			setBackground(validColor);
		} else {
			setBackground(invalidColor);
		}
	}

	private boolean isValidDNI(String dni) {
		if (!dni.matches("^\\d{8}[A-Za-z]$"))
			return false;
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
		int numero = Integer.parseInt(dni.substring(0, 8));
		char letra = Character.toUpperCase(dni.charAt(8));
		return letra == letras.charAt(numero % 23);
	}

// --- Getters y setters ---
	public Color getEmptyColor() {
		return emptyColor;
	}

	public void setEmptyColor(Color emptyColor) {
		this.emptyColor = emptyColor;
		validarYActualizar();
	}

	public Color getInvalidColor() {
		return invalidColor;
	}

	public void setInvalidColor(Color invalidColor) {
		this.invalidColor = invalidColor;
		validarYActualizar();
	}

	public Color getValidColor() {
		return validColor;
	}

	public void setValidColor(Color validColor) {
		this.validColor = validColor;
		validarYActualizar();
	}
}
