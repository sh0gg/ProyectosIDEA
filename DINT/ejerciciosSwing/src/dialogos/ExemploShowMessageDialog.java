package dialogos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExemploShowMessageDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExemploShowMessageDialog frame = new ExemploShowMessageDialog();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExemploShowMessageDialog() {
		setTitle("Exemplo showMessageDialog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 80);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 3, 5, 5));
		
		JButton btnMetodo1 = new JButton("Método 1");
		contentPane.add(btnMetodo1);
		
		JButton btnMetodo2 = new JButton("Método 2");
		contentPane.add(btnMetodo2);
		
		JButton btnMetodo3 = new JButton("Método 3");
		contentPane.add(btnMetodo3);
		
		// Método 1: parent + mensaje
		btnMetodo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						ExemploShowMessageDialog.this,
						"O botón foi premido");
			}
		});
		
		// Método 2: parent + mensaje + título + tipo
		btnMetodo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(
						ExemploShowMessageDialog.this,
						"O botón foi premido",
						"Metodo 2",
						JOptionPane.WARNING_MESSAGE);
			}
		});
		
		// Método 3: parent + mensaje + título + tipo + icono persoalizado
		btnMetodo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// A ruta /imaxes/icona.png debe existir nos teus recursos
				ImageIcon icon = new ImageIcon(
						ExemploShowMessageDialog.this.getClass()
								.getResource("/imaxes/icona.png"));
				
				JOptionPane.showMessageDialog(
						ExemploShowMessageDialog.this,
						"O botón foi premido",
						"Metodo 3",
						JOptionPane.PLAIN_MESSAGE,
						icon);
			}
		});
	}

}
