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
				pSeleccion.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EmptyBorder(0, 0, 0, 0)), "Indique o m\u00E1ximo grao acadado:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				GridBagConstraints gbc_pSeleccion = new GridBagConstraints();
				gbc_pSeleccion.insets = new Insets(20, 20, 20, 10);
				gbc_pSeleccion.fill = GridBagConstraints.BOTH;
				gbc_pSeleccion.gridx = 0;
				gbc_pSeleccion.gridy = 0;
				pDatos.add(pSeleccion, gbc_pSeleccion);
			}
		}
		{
			JPanel pBoton = new JPanel();
			getContentPane().add(pBoton, BorderLayout.EAST);
		}
	}

}
