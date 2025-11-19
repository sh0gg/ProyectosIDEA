package simulacro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

import simulacro.InterfazPrincipal.Alumno;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class DlgActualizar extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfDNI;
	private JTextField tfNombre;
	private JTextField tfApellidos;
	private JTextField tfCambio;
	private JButton bAplicarCambios;
	private JButton bDescartarCambios;

	
	/**
	 * Create the dialog.
	 */
	public DlgActualizar(Alumno alumno) {
		setTitle("Actualizar datos - David Besada Ramilo - Simulacro 19/11");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel pDatosAlumno = new JPanel();
		pDatosAlumno.setBorder(new TitledBorder(null, "Datos alumno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(pDatosAlumno);
		GridBagLayout gbl_pDatosAlumno = new GridBagLayout();
		gbl_pDatosAlumno.columnWidths = new int[]{0, 0, 0};
		gbl_pDatosAlumno.rowHeights = new int[]{20, 0, 0, 0};
		gbl_pDatosAlumno.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pDatosAlumno.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		pDatosAlumno.setLayout(gbl_pDatosAlumno);
		
		JLabel lblDNI = new JLabel("DNI");
		GridBagConstraints gbc_lblDNI = new GridBagConstraints();
		gbc_lblDNI.anchor = GridBagConstraints.WEST;
		gbc_lblDNI.insets = new Insets(20, 20, 5, 5);
		gbc_lblDNI.gridx = 0;
		gbc_lblDNI.gridy = 0;
		pDatosAlumno.add(lblDNI, gbc_lblDNI);
		
		tfDNI = new JTextField(alumno.getDni());
		GridBagConstraints gbc_tfDNI = new GridBagConstraints();
		gbc_tfDNI.insets = new Insets(20, 0, 5, 20);
		gbc_tfDNI.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfDNI.gridx = 1;
		gbc_tfDNI.gridy = 0;
		pDatosAlumno.add(tfDNI, gbc_tfDNI);
		tfDNI.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 20, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 1;
		pDatosAlumno.add(lblNombre, gbc_lblNombre);
		
		tfNombre = new JTextField(alumno.getNombre());
		GridBagConstraints gbc_tfNombre = new GridBagConstraints();
		gbc_tfNombre.insets = new Insets(0, 0, 5, 20);
		gbc_tfNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNombre.gridx = 1;
		gbc_tfNombre.gridy = 1;
		pDatosAlumno.add(tfNombre, gbc_tfNombre);
		tfNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		GridBagConstraints gbc_lblApellidos = new GridBagConstraints();
		gbc_lblApellidos.anchor = GridBagConstraints.WEST;
		gbc_lblApellidos.insets = new Insets(0, 20, 20, 5);
		gbc_lblApellidos.gridx = 0;
		gbc_lblApellidos.gridy = 2;
		pDatosAlumno.add(lblApellidos, gbc_lblApellidos);
		
		tfApellidos = new JTextField(alumno.getApellidos());
		GridBagConstraints gbc_tfApellidos = new GridBagConstraints();
		gbc_tfApellidos.insets = new Insets(0, 0, 20, 20);
		gbc_tfApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfApellidos.gridx = 1;
		gbc_tfApellidos.gridy = 2;
		pDatosAlumno.add(tfApellidos, gbc_tfApellidos);
		tfApellidos.setColumns(10);
		
		JPanel pActualizar = new JPanel();
		pActualizar.setBorder(new TitledBorder(null, "Datos alumno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(pActualizar);
		GridBagLayout gbl_pActualizar = new GridBagLayout();
		gbl_pActualizar.columnWidths = new int[]{0, 0};
		gbl_pActualizar.rowHeights = new int[]{20, 0, 0, 0, 0};
		gbl_pActualizar.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pActualizar.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pActualizar.setLayout(gbl_pActualizar);
		
		JComboBox cbCambio = new JComboBox();
		cbCambio.setModel(new DefaultComboBoxModel(new String[] {"Nombre", "Apellidos"}));
		GridBagConstraints gbc_cbCambio = new GridBagConstraints();
		gbc_cbCambio.insets = new Insets(20, 20, 5, 20);
		gbc_cbCambio.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbCambio.gridx = 0;
		gbc_cbCambio.gridy = 0;
		pActualizar.add(cbCambio, gbc_cbCambio);
		
		tfCambio = new JTextField();
		GridBagConstraints gbc_tfCambio = new GridBagConstraints();
		gbc_tfCambio.insets = new Insets(0, 20, 5, 20);
		gbc_tfCambio.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCambio.gridx = 0;
		gbc_tfCambio.gridy = 1;
		pActualizar.add(tfCambio, gbc_tfCambio);
		tfCambio.setColumns(10);
		
		tfCambio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                actualizarEstadoBotonesCambio();
            }
        });
		
		bAplicarCambios = new JButton("Aplicar cambio");
		bAplicarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbCambio.getSelectedIndex() == 0) {
					// Si elige nombre
					alumno.setNombre(tfCambio.getText());
				} else if (cbCambio.getSelectedIndex() == 1) {
					// Si elige apellidos
					alumno.setApellidos(tfCambio.getText());
				} else {
					// seleccion erronea??? no se si es posible, pero porsiacaso
					JOptionPane.showMessageDialog(DlgActualizar.this, "Debes seleccionar opcion en el CB!!!");
					return;
				}
				dispose();
			}
		});
		GridBagConstraints gbc_bAplicarCambios = new GridBagConstraints();
		gbc_bAplicarCambios.fill = GridBagConstraints.HORIZONTAL;
		gbc_bAplicarCambios.insets = new Insets(0, 20, 5, 20);
		gbc_bAplicarCambios.gridx = 0;
		gbc_bAplicarCambios.gridy = 2;
		pActualizar.add(bAplicarCambios, gbc_bAplicarCambios);
		
		bDescartarCambios = new JButton("Descartar cambio");
		bDescartarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GridBagConstraints gbc_bDescartarCambios = new GridBagConstraints();
		gbc_bDescartarCambios.fill = GridBagConstraints.HORIZONTAL;
		gbc_bDescartarCambios.insets = new Insets(0, 20, 20, 20);
		gbc_bDescartarCambios.gridx = 0;
		gbc_bDescartarCambios.gridy = 3;
		pActualizar.add(bDescartarCambios, gbc_bDescartarCambios);
		
		tfDNI.setEditable(false);
		tfNombre.setEditable(false);
		tfApellidos.setEditable(false);
		
		bAplicarCambios.setEnabled(false);
		bDescartarCambios.setEnabled(false);
	}


	private void actualizarEstadoBotonesCambio() {
		boolean campoCambio = !tfCambio.getText().trim().isEmpty();
		
		bAplicarCambios.setEnabled(campoCambio);
		bDescartarCambios.setEnabled(campoCambio);
	}

}
