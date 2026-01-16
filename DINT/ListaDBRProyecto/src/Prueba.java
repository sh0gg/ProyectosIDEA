import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Color;

public class Prueba extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prueba frame = new Prueba();
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
	public Prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		ListaDBRv2 listaDBRv2 = new ListaDBRv2();
		listaDBRv2.setColorSeleccionado3(new Color(255, 0, 0));
		listaDBRv2.setColorSeleccionado2(new Color(128, 0, 64));
		listaDBRv2.setColorSeleccionado1(new Color(128, 128, 128));
		listaDBRv2.setItems(new String[] {"1", "2", "3", "43", "4", "5"});
		contentPane.add(listaDBRv2);

	}

}
