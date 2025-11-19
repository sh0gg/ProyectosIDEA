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

public class ExemploShowInputDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExemploShowInputDialog frame = new ExemploShowInputDialog();
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
	public ExemploShowInputDialog() {
		setTitle("Exemplo showInputDialog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 3, 5, 5));
		
		JButton btnMetodo1 = new JButton("Método 1");
		contentPane.add(btnMetodo1);
		
		JButton btnMetodo2 = new JButton("Método 2");
		contentPane.add(btnMetodo2);
		
		JButton btnMetodo3 = new JButton("Método 3");
		contentPane.add(btnMetodo3);
		
		JButton btnMetodo4 = new JButton("Método 4");
		contentPane.add(btnMetodo4);
		
		JButton btnMetodo5 = new JButton("Método 5");
		contentPane.add(btnMetodo5);
		
		JButton btnMetodo6 = new JButton("Método 6");
		contentPane.add(btnMetodo6);
		
		// ------------- LISTENERS ----------------
		
		// Método 1: só mensaxe (centro da pantalla)
		btnMetodo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog("Indique o seu domicilio");
				tratarResultadoDomicilio(domicilio);
			}
		});
		
		// Método 2: mensaxe + valor inicial (centro pantalla)
		btnMetodo2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog(
						"Indique o seu domicilio", 
						"Rúa Descoñecida s/n, 15001, A Coruña");
				tratarResultadoDomicilio(domicilio);
			}
		});
		
		// Método 3: parent + mensaxe (centrado na xanela)
		btnMetodo3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog(
						ExemploShowInputDialog.this,
						"Indique o seu domicilio");
				tratarResultadoDomicilio(domicilio);
			}
		});
		
		// Método 4: parent + mensaxe + valor inicial
		btnMetodo4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog(
						ExemploShowInputDialog.this,
						"Indique o seu domicilio",
						"Rúa Descoñecida s/n, 15001, A Coruña");
				tratarResultadoDomicilio(domicilio);
			}
		});
		
		// Método 5: parent + mensaxe + título + tipo icona
		btnMetodo5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String domicilio = JOptionPane.showInputDialog(
						ExemploShowInputDialog.this,
						"Indique o seu domicilio",
						"Domicilio",
						JOptionPane.WARNING_MESSAGE);
				tratarResultadoDomicilio(domicilio);
			}
		});
		
		// Método 6: parent + mensaxe + título + tipo + icona + combo de valores
		btnMetodo6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon(
						ExemploShowInputDialog.this.getClass()
								.getResource("/imaxes/icona.png"));
				
				String[] provincias = {"A Coruña", "Lugo", "Ourense", "Pontevedra"};
				
				Object seleccion = JOptionPane.showInputDialog(
						ExemploShowInputDialog.this,
						"Indique a súa provincia de nacemento",
						"Nacemento",
						JOptionPane.QUESTION_MESSAGE,
						icon,
						provincias,
						"Lugo");
				
				if (seleccion == null) {
					JOptionPane.showMessageDialog(
							ExemploShowInputDialog.this,
							"Acción anulada polo usuario",
							"Atención",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(
							ExemploShowInputDialog.this,
							"A provincia seleccionada é " + seleccion,
							"Atención",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
	// --------- MÉTODO AUXILIAR COMPARTIDO ---------
	
	private void tratarResultadoDomicilio(String domicilio) {
		if (domicilio == null) {
			JOptionPane.showMessageDialog(
					this,
					"Acción anulada polo usuario",
					"Atención",
					JOptionPane.ERROR_MESSAGE);
		} else {
			if (domicilio.trim().isEmpty()) {
				JOptionPane.showMessageDialog(
						this,
						"A caixa de texto está baleira",
						"Atención",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(
						this,
						"O seu domicilio é " + domicilio,
						"Atención",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		// Solo para ver en consola o que devolve
		System.out.println(domicilio);
	}
}
