package dialogos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
// añadidos:
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DatosAcademicos extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatosAcademicos dialog = new DatosAcademicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatosAcademicos() {
		setTitle("Datos académicos");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel pDatos = new JPanel();
			getContentPane().add(pDatos, BorderLayout.CENTER);
			GridBagLayout gbl_pDatos = new GridBagLayout();
			gbl_pDatos.columnWidths = new int[]{424, 0};
			gbl_pDatos.rowHeights = new int[]{261, 0};
			gbl_pDatos.columnWeights = new double[]{0.0, Double.MIN_VALUE};
			gbl_pDatos.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			pDatos.setLayout(gbl_pDatos);
			{
				JPanel pSeleccion = new JPanel();
				pSeleccion.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(0, 0, 0, 0)), "Indique o máximo grao acadado:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_pSeleccion = new GridBagConstraints();
				gbc_pSeleccion.insets = new Insets(20, 20, 20, 10);
				gbc_pSeleccion.fill = GridBagConstraints.BOTH;
				gbc_pSeleccion.gridx = 0;
				gbc_pSeleccion.gridy = 0;
				pDatos.add(pSeleccion, gbc_pSeleccion);
				
				// === OPCIÓNS DE GRAO ACADADO ===
				pSeleccion.setLayout(new GridLayout(4, 1, 5, 5));
				
				final JRadioButton rbtESO = new JRadioButton("ESO");
				final JRadioButton rbtBacharelato = new JRadioButton("Bacharelato");
				final JRadioButton rbtFP = new JRadioButton("FP");
				final JRadioButton rbtUniversidade = new JRadioButton("Universidade");
				
				ButtonGroup grupoGraos = new ButtonGroup();
				grupoGraos.add(rbtESO);
				grupoGraos.add(rbtBacharelato);
				grupoGraos.add(rbtFP);
				grupoGraos.add(rbtUniversidade);
				
				pSeleccion.add(rbtESO);
				pSeleccion.add(rbtBacharelato);
				pSeleccion.add(rbtFP);
				pSeleccion.add(rbtUniversidade);
			}
		}
		{
			JPanel pBoton = new JPanel();
			getContentPane().add(pBoton, BorderLayout.EAST);
			pBoton.setLayout(new GridLayout(2, 1, 5, 5));
			
			JButton bAceptar = new JButton("Aceptar");
			JButton bCancelar = new JButton("Cancelar");
			pBoton.add(bAceptar);
			pBoton.add(bCancelar);
			
			// Listener botón Aceptar
			bAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String mensaxe = null;

					if (((JRadioButton)((JPanel)((BorderLayout)getContentPane().getLayout()).getLayoutComponent(BorderLayout.CENTER))
							.getComponent(0)).isShowing()) {
						// Nada, só para evitar advertencias do compilador en algúns IDEs
					}

					// Identificamos cal está seleccionado
					if (rbtESO.isSelected()) {
						mensaxe = "O máximo grao que acadou vostede é ESO";
					} else if (rbtBacharelato.isSelected()) {
						mensaxe = "O máximo grao que acadou vostede é Bacharelato";
					} else if (rbtFP.isSelected()) {
						mensaxe = "O máximo grao que acadou vostede é FP";
					} else if (rbtUniversidade.isSelected()) {
						mensaxe = "O máximo grao que acadou vostede é Universidade";
					}
					
					if (mensaxe == null) {
						JOptionPane.showMessageDialog(
								DatosAcademicos.this,
								"Debe seleccionar algún grao acadado",
								"Erro",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					JOptionPane.showMessageDialog(
							DatosAcademicos.this,
							mensaxe,
							"Información",
							JOptionPane.INFORMATION_MESSAGE);
					
					dispose();
				}
			});
			
			// Listener botón Cancelar
			bCancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
	}

}
