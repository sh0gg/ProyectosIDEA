package supabase;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DialogoProvincias extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	JComboBox comboBox;
	InterfazTrabajadores parent;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			DialogoProvincias dialog = new DialogoProvincias();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * Create the dialog.
	 */
	public DialogoProvincias(InterfazTrabajadores parent) {
		super(parent);
		this.parent = parent;
		 // Provincias españolas
		   final String[] provincias = {
		       "Álava", "Albacete", "Alicante", "Almería", "Asturias", "Ávila",
		       "Badajoz", "Barcelona", "Burgos", "Cáceres", "Cádiz", "Cantabria",
		       "Castellón", "Ciudad Real", "Córdoba", "Cuenca", "Girona", "Granada",
		       "Guadalajara", "Guipúzcoa", "Huelva", "Huesca", "Islas Baleares", "Jaén",
		       "La Coruña", "La Rioja", "Las Palmas", "León", "Lleida", "Lugo",
		       "Madrid", "Málaga", "Murcia", "Navarra", "Ourense", "Palencia",
		       "Pontevedra", "Salamanca", "Santa Cruz de Tenerife", "Segovia",
		       "Sevilla", "Soria", "Tarragona", "Teruel", "Toledo", "Valencia",
		       "Valladolid", "Vizcaya", "Zamora", "Zaragoza", "Ceuta", "Melilla"
		   };

		setBounds(100, 100, 407, 109);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "Seleccione una provincia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			comboBox = new JComboBox();
			for (int i = 0; i < provincias.length; i++) {
				comboBox.addItem(provincias[i]);
			}
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String provincia = comboBox.getSelectedItem().toString();
						parent.establecerProvincia(provincia);
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
