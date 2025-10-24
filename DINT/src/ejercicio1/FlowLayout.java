package ejercicio1;

import javax.swing.JPanel;
import javax.swing.JButton;

public class FlowLayout extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public FlowLayout() {
		setLayout(new java.awt.BorderLayout(0, 0));
		
		JButton btnNorte = new JButton("Norte");
		add(btnNorte, java.awt.BorderLayout.NORTH);
		
		JButton btnSur = new JButton("Sur");
		add(btnSur, java.awt.BorderLayout.SOUTH);
		
		JButton btnEste = new JButton("Este");
		add(btnEste, java.awt.BorderLayout.EAST);
		
		JButton btnOeste = new JButton("Oeste");
		add(btnOeste, java.awt.BorderLayout.WEST);
		
		JButton btnCentro = new JButton("Centro");
		add(btnCentro);

	}

}
