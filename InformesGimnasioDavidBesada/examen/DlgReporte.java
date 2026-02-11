package examen;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import examen.InterfazPrincipal.Usuario;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

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
	
	public void verInformeResumen() {
	    try {
	        Collection<Map<String, ?>> data = new ArrayList<>();
	        
	        for (int i = 0; i < modeloReporte.getRowCount(); i++) {
	            Map<String, Object> fila = new HashMap<>();
	            fila.put("item", modeloReporte.getValueAt(i, 0).toString());
	            fila.put("valor", modeloReporte.getValueAt(i, 1).toString());
	            data.add(fila);
	        }
	        

	        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
	        JasperPrint print = JasperFillManager.fillReport("D:/dbesarami/IDEAProjects/DINT/ejerciciosSwing/src/reportes/INFORME1Tarea3DavidBesada.jasper", null, dataSource);
	        JasperViewer.viewReport(print, false);

	    } catch (JRException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al generar informe: " + e.getMessage());
	    }
	}
	
	
	public void descargarInformeResumen() {
	    try {
	        Collection<Map<String, ?>> data = new ArrayList<>();
	        for (int i = 0; i < modeloReporte.getRowCount(); i++) {
	            Map<String, Object> fila = new HashMap<>();
	            fila.put("item", modeloReporte.getValueAt(i, 0).toString());
	            fila.put("valor", modeloReporte.getValueAt(i, 1).toString());
	            data.add(fila);
	        }

	        JRMapCollectionDataSource dataSource = new JRMapCollectionDataSource(data);
	        JasperPrint print = JasperFillManager.fillReport("D:/dbesarami/IDEAProjects/DINT/ejerciciosSwing/src/reportes/INFORME1Tarea3DavidBesada.jasper", null, dataSource);

	        // Esto guarda el PDF directamente en la carpeta del proyecto
	        JasperExportManager.exportReportToPdfFile(print, "D:/dbesarami/IDEAProjects/DINT/ejerciciosSwing/src/reportes/Reporte_Gimnasio.pdf");
	        
	        JOptionPane.showMessageDialog(this, "Informe guardado como 'Reporte_Gimnasio.pdf'");
	    } catch (JRException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * Create the dialog.
	 */
	public DlgReporte(java.awt.Frame parent, boolean modal, ArrayList<Usuario> usuarios, int movimientosHistorial) {
	    super(parent, modal);
		setTitle("David Besada - Examen");
		setBounds(100, 100, 500, 400); // Un poco más alto para los botones nuevos
	    getContentPane().setLayout(new BorderLayout());
	    
	    contentPanel.setBorder(new TitledBorder(null, "Tabla de reportes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	    getContentPane().add(contentPanel, BorderLayout.CENTER);
	    
	    // Cambiamos el Layout a GridBagLayout para que el JScrollPane se vea bien
	    contentPanel.setLayout(new GridBagLayout());

	    table = new JTable();
	    // Hacer que las celdas NO sean editables (requisito examen)
	    modeloReporte = new DefaultTableModel(new Object[][] {}, new String[] { "Item", "Valor" }) {
	        @Override
	        public boolean isCellEditable(int row, int column) {
	            return false;
	        }
	    };
	    table.setModel(modeloReporte);
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

	    JScrollPane scroller = new JScrollPane(table);
	    GridBagConstraints gbc_table = new GridBagConstraints();
	    gbc_table.weighty = 1.0;
	    gbc_table.weightx = 1.0;
	    gbc_table.fill = GridBagConstraints.BOTH;
	    gbc_table.insets = new Insets(10, 10, 10, 10);
	    gbc_table.gridx = 0;
	    gbc_table.gridy = 0;
	    contentPanel.add(scroller, gbc_table);

	    // --- LÓGICA DE CÁLCULOS ---
	    int totalUsuarios = usuarios.size();
	    int usuariosDentro = 0;
	    int sumaEdadTotal = 0;
	    int sumaEdadDentro = 0;

	    for (Usuario u : usuarios) {
	        sumaEdadTotal += u.getEdad();
	        if (u.isDentro()) {
	            usuariosDentro++;
	            sumaEdadDentro += u.getEdad();
	        }
	    }

	    // Usamos (double) para no perder decimales
	    double mediaEdadTotal = totalUsuarios > 0 ? (double) sumaEdadTotal / totalUsuarios : 0;
	    double mediaEdadDentro = usuariosDentro > 0 ? (double) sumaEdadDentro / usuariosDentro : 0;

	    // Insertar filas
	    modeloReporte.addRow(new Object[]{"Nº de usuario registrados", totalUsuarios});
	    modeloReporte.addRow(new Object[]{"Nº de usuarios en el gimnasio", usuariosDentro});
	    modeloReporte.addRow(new Object[]{"Nº de movimientos en el historial", movimientosHistorial});
	    modeloReporte.addRow(new Object[]{"Edad media usuarios registrados", mediaEdadTotal});
	    modeloReporte.addRow(new Object[]{"Edad media usuario en el gimnasio", mediaEdadDentro});

	    // --- PANEL PARA BOTONES DE JASPER ---
	    JPanel pBotonesJasper = new JPanel();
	    getContentPane().add(pBotonesJasper, BorderLayout.SOUTH);
	    
	    JButton btnVerInforme = new JButton("Ver informe");
	    btnVerInforme.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            verInformeResumen(); // Llamamos al método que creamos antes
	        }
	    });
	    pBotonesJasper.add(btnVerInforme);

	    JButton btnDescargarInforme = new JButton("Descargar informe");
	    btnDescargarInforme.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            descargarInformeResumen(); // Este sería para guardar en PDF (opcional ahora)
	        }
	    });
	    pBotonesJasper.add(btnDescargarInforme);
	}

}
