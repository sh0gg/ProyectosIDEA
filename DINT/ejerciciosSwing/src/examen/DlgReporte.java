package examen;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import examen.InterfazPrincipal.Usuario;

public class DlgReporte extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel modeloReporte;
	private String[] items;
	private double[] valores;

	public void mostrarDato(int[] selectedRows) {

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < selectedRows.length; i++) {
			sb.append(modeloReporte.getValueAt(selectedRows[i], 0).toString() + " : "
					+ modeloReporte.getValueAt(selectedRows[i], 1).toString());
		}

		JOptionPane.showMessageDialog(DlgReporte.this, sb.toString());
	}

	public void insertarTabla(String item, double valor) {
		if (modeloReporte == null)
			return;
		modeloReporte.setRowCount(modeloReporte.getRowCount() + 1);
		int r = modeloReporte.getRowCount() - 1;
		modeloReporte.setValueAt(item, r, 0);
		modeloReporte.setValueAt(valor, r, 1);
	}

	/**
	 * Create the dialog.
	 */
	public DlgReporte(ArrayList<Usuario> usuarios, int movimientosHistorial) {

		setTitle("David Besada - Examen");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(
				new TitledBorder(null, "Tabla de reportes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Item", "Valor" }));
			modeloReporte = (DefaultTableModel) table.getModel();
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (e.getClickCount() == 2) {
						int row = table.rowAtPoint(e.getPoint());

						if (row >= 0) {
							mostrarDato(table.getSelectedRows());
						}
					}
				}
			});
		}

		JScrollPane scroller = new JScrollPane(table);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.weighty = 1.0;
		gbc_table.weightx = 1.0;
		gbc_table.gridwidth = 3;
		gbc_table.insets = new Insets(10, 10, 15, 10);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		contentPanel.add(scroller, gbc_table);

		int usuariosDentro = 0;
		int edadDentro = 0;
		int edad = 0;

		for (Usuario u : usuarios) {
			if (u.isDentro()) {
				usuariosDentro++;
				edadDentro = edadDentro + u.getEdad();
			}
			edad = edad + u.getEdad();
		}

		int totalUsuarios = usuarios.size();

		double mediaEdadDentro = 0;
		if (usuariosDentro != 0) {
			mediaEdadDentro = edadDentro / usuariosDentro;
		}
		double mediaEdad = edad / totalUsuarios;

		String[] items = { "Nº de usuario registrados", "Nº de usuarios en el gimnasio",
				"Nº de movimientos en el historial", "Edad media usuarios registrados",
				"Edad media usuario en el gimnasio" };
		double[] valores = { totalUsuarios, usuariosDentro, movimientosHistorial, mediaEdad, mediaEdadDentro };

		for (int i = 0; i < items.length; i++) {
			insertarTabla(items[i], valores[i]);
		}

		contentPanel.revalidate();
		contentPanel.repaint();
	}

}
