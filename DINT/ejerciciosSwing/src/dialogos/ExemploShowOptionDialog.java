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

public class ExemploShowOptionDialog extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExemploShowOptionDialog frame = new ExemploShowOptionDialog();
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
	public ExemploShowOptionDialog() {
		setTitle("Exemplo showOptionDialog");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 120);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 5, 5, 5));
		
		JButton btnSiNon = new JButton("Si / Non");
		contentPane.add(btnSiNon);
		
		JButton btnSiNonCancelarPredef = new JButton("Si / Non / Cancelar");
		contentPane.add(btnSiNonCancelarPredef);
		
		JButton btnSiNonCancelarIconaPers = new JButton("Si/Non/Cancel + Icona");
		contentPane.add(btnSiNonCancelarIconaPers);
		
		JButton btnBotonsPersoalizados = new JButton("Botóns persoalizados");
		contentPane.add(btnBotonsPersoalizados);
		
		JButton btnBotonsPersoalizadosPorDefecto = new JButton("Persoalizados + preselección");
		contentPane.add(btnBotonsPersoalizadosPorDefecto);
		
		// -------- LISTENERS --------
		
		// 1) YES / NO
		btnSiNon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(
						ExemploShowOptionDialog.this,
						"Selecciona unha opción",
						"Exemplo 1",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.PLAIN_MESSAGE,
						null,
						null,
						null);
				
				String mensaxe = "Xanela pechada polo usuario";
				if (seleccion == JOptionPane.YES_OPTION) {
					mensaxe = "Pulsado o botón Sí";
				} else if (seleccion == JOptionPane.NO_OPTION) {
					mensaxe = "Pulsado o botón Non";
				}
				
				JOptionPane.showMessageDialog(
						ExemploShowOptionDialog.this,
						mensaxe,
						"Resultado",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// 2) YES / NO / CANCEL + icona predefinida
		btnSiNonCancelarPredef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int seleccion = JOptionPane.showOptionDialog(
						ExemploShowOptionDialog.this,
						"Selecciona unha opción",
						"Exemplo 2",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						null,
						null);
				
				String mensaxe = "Xanela pechada polo usuario";
				if (seleccion == JOptionPane.YES_OPTION) {
					mensaxe = "Pulsado o botón Sí";
				} else if (seleccion == JOptionPane.NO_OPTION) {
					mensaxe = "Pulsado o botón Non";
				} else if (seleccion == JOptionPane.CANCEL_OPTION) {
					mensaxe = "Pulsado o botón Cancelar";
				}
				
				JOptionPane.showMessageDialog(
						ExemploShowOptionDialog.this,
						mensaxe,
						"Resultado",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// 3) YES / NO / CANCEL + icona personalizada
		btnSiNonCancelarIconaPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImageIcon icon = new ImageIcon(
						ExemploShowOptionDialog.this.getClass()
								.getResource("/imaxes/icona.png"));
				
				int seleccion = JOptionPane.showOptionDialog(
						ExemploShowOptionDialog.this,
						"Selecciona unha opción",
						"Exemplo 3",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						icon,
						null,
						null);
				
				String mensaxe = "Xanela pechada polo usuario";
				if (seleccion == JOptionPane.YES_OPTION) {
					mensaxe = "Pulsado o botón Sí";
				} else if (seleccion == JOptionPane.NO_OPTION) {
					mensaxe = "Pulsado o botón Non";
				} else if (seleccion == JOptionPane.CANCEL_OPTION) {
					mensaxe = "Pulsado o botón Cancelar";
				}
				
				JOptionPane.showMessageDialog(
						ExemploShowOptionDialog.this,
						mensaxe,
						"Resultado",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// 4) Botóns persoalizados
		btnBotonsPersoalizados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] botons = {"Chove", "Non chove", "Poidera ser"};
				
				int seleccion = JOptionPane.showOptionDialog(
						ExemploShowOptionDialog.this,
						"¿Choverá pola tarde?",
						"Exemplo 4",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						botons,
						null);
				
				String mensaxe = "Xanela pechada polo usuario";
				switch (seleccion) {
					case 0:
						mensaxe = "Vai chover";
						break;
					case 1:
						mensaxe = "Non vai chover";
						break;
					case 2:
						mensaxe = "Poidera ser que chova. Xa veremos";
						break;
					default:
						break;
				}
				
				JOptionPane.showMessageDialog(
						ExemploShowOptionDialog.this,
						mensaxe,
						"Resultado",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// 5) Botóns persoalizados + valor preseleccionado
		btnBotonsPersoalizadosPorDefecto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] botons = {"Chove", "Non chove", "Poidera ser"};
				
				int seleccion = JOptionPane.showOptionDialog(
						ExemploShowOptionDialog.this,
						"¿Choverá pola tarde?",
						"Exemplo 5",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						botons,
						botons[2]); // preseleccionado "Poidera ser"
				
				String mensaxe = "Xanela pechada polo usuario";
				switch (seleccion) {
					case 0:
						mensaxe = "Vai chover";
						break;
					case 1:
						mensaxe = "Non vai chover";
						break;
					case 2:
						mensaxe = "Poidera ser que chova. Xa veremos";
						break;
					default:
						break;
				}
				
				JOptionPane.showMessageDialog(
						ExemploShowOptionDialog.this,
						mensaxe,
						"Resultado",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

}
