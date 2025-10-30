package newpack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class EjemploControlJTable extends JFrame {

	public static class Moble {
		private String nome;
		private String cor;
		private String material;
		private int ancho;
		private int alto;
		private int fondo;

		public Moble(String nome, String cor, String material, int ancho, int alto, int fondo) {
			this.nome = nome;
			this.cor = cor;
			this.material = material;
			this.ancho = ancho;
			this.alto = alto;
			this.fondo = fondo;
		}

		@Override
		public String toString() {
			return nome;
		}

		public String getCor() {
			return cor;
		}

		public String getMaterial() {
			return material;
		}

		public int getAncho() {
			return ancho;
		}

		public int getAlto() {
			return alto;
		}

		public int getFondo() {
			return fondo;
		}

		public String getNome() {
			return nome;
		}
	}

	private JTextField tfNome;
	private JTextField tfCor;
	private JTextField tfAncho;
	private JTextField tfAlto;
	private JTextField tfFondo;
	private JTable tabla;
	private DefaultTableModel modeloMobles;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static ArrayList<Moble> mobles = new ArrayList<Moble>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjemploControlJTable frame = new EjemploControlJTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void insertarMoble(Moble m) {
		if (modeloMobles == null)
			return;
		modeloMobles.setRowCount(modeloMobles.getRowCount() + 1);
		int r = modeloMobles.getRowCount() - 1;
		modeloMobles.setValueAt(m, r, 0);
		modeloMobles.setValueAt(m.getCor(), r, 1);
		modeloMobles.setValueAt(m.getMaterial(), r, 2);
		String medidas = m.getAncho() + "x" + m.getAlto() + "x" + m.getFondo();
		modeloMobles.setValueAt(medidas, r, 3);
	}

	public void mostrarInforme(int[] selectedRows) {
		if (modeloMobles.getRowCount() == 0) {
			JOptionPane.showMessageDialog(EjemploControlJTable.this, "Non hai mobles dispoñibles");
			return;
		}

		if (selectedRows.length == 0) {
			JOptionPane.showMessageDialog(EjemploControlJTable.this, "Debe seleccionar ao menos un moble");
			return;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < selectedRows.length; i++) {
			Moble m = (Moble) modeloMobles.getValueAt(selectedRows[i], 0);
			sb.append("Nome: ").append(m.getNome()).append(" \nCor: ").append(m.getCor()).append(" \nMaterial: ")
					.append(m.getMaterial()).append(" \nAncho: ").append(m.getAncho()).append(" Alto: ")
					.append(m.getAlto()).append(" Fondo: ").append(m.getFondo()).append("\n");
		}

		JOptionPane.showMessageDialog(EjemploControlJTable.this, sb.toString());
	}

	public EjemploControlJTable() {
		setTitle("Ejercicio JTable David Besada");

		JPanel pPrincipal = new JPanel();
		getContentPane().add(pPrincipal, BorderLayout.CENTER);
		GridBagLayout gbl_pPrincipal = new GridBagLayout();
		gbl_pPrincipal.columnWidths = new int[] { 0, 0 };
		gbl_pPrincipal.rowHeights = new int[] { 121, 212, 0 };
		gbl_pPrincipal.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_pPrincipal.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		pPrincipal.setLayout(gbl_pPrincipal);

		JPanel pNovoMoble = new JPanel();
		pNovoMoble.setBorder(new TitledBorder(null, "Novo moble", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pNovoMoble = new GridBagConstraints();
		gbc_pNovoMoble.insets = new Insets(10, 10, 10, 10);
		gbc_pNovoMoble.fill = GridBagConstraints.BOTH;
		gbc_pNovoMoble.gridx = 0;
		gbc_pNovoMoble.gridy = 0;
		pPrincipal.add(pNovoMoble, gbc_pNovoMoble);
		GridBagLayout gbl_pNovoMoble = new GridBagLayout();
		gbl_pNovoMoble.columnWidths = new int[] { 310, 129, 0 };
		gbl_pNovoMoble.rowHeights = new int[] { 110, 0 };
		gbl_pNovoMoble.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_pNovoMoble.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		pNovoMoble.setLayout(gbl_pNovoMoble);

		JPanel pInputDatos = new JPanel();
		GridBagConstraints gbc_pInputDatos = new GridBagConstraints();
		gbc_pInputDatos.insets = new Insets(10, 10, 0, 10);
		gbc_pInputDatos.fill = GridBagConstraints.BOTH;
		gbc_pInputDatos.gridx = 0;
		gbc_pInputDatos.gridy = 0;
		pNovoMoble.add(pInputDatos, gbc_pInputDatos);
		GridBagLayout gbl_pInputDatos = new GridBagLayout();
		gbl_pInputDatos.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pInputDatos.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_pInputDatos.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_pInputDatos.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pInputDatos.setLayout(gbl_pInputDatos);

		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.EAST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		pInputDatos.add(lblNome, gbc_lblNome);

		tfNome = new JTextField();
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.gridwidth = 8;
		gbc_tfNome.insets = new Insets(0, 0, 5, 0);
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.gridx = 1;
		gbc_tfNome.gridy = 0;
		pInputDatos.add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);

		JLabel lblCor = new JLabel("Cor");
		GridBagConstraints gbc_lblCor = new GridBagConstraints();
		gbc_lblCor.anchor = GridBagConstraints.EAST;
		gbc_lblCor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCor.gridx = 0;
		gbc_lblCor.gridy = 1;
		pInputDatos.add(lblCor, gbc_lblCor);

		tfCor = new JTextField();
		GridBagConstraints gbc_tfCor = new GridBagConstraints();
		gbc_tfCor.insets = new Insets(0, 0, 5, 5);
		gbc_tfCor.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfCor.gridx = 1;
		gbc_tfCor.gridy = 1;
		pInputDatos.add(tfCor, gbc_tfCor);
		tfCor.setColumns(10);

		JLabel lblMaterial = new JLabel("Material");
		GridBagConstraints gbc_lblMaterial = new GridBagConstraints();
		gbc_lblMaterial.anchor = GridBagConstraints.EAST;
		gbc_lblMaterial.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaterial.gridx = 2;
		gbc_lblMaterial.gridy = 1;
		pInputDatos.add(lblMaterial, gbc_lblMaterial);

		JComboBox<String> cbMaterial = new JComboBox<>();
		cbMaterial.addItem("Carballo");
		cbMaterial.addItem("Cerdeira");
		cbMaterial.addItem("Faia");
		cbMaterial.addItem("Ferro");
		cbMaterial.addItem("Piñeiro");
		cbMaterial.addItem("Plástico");
		cbMaterial.setSelectedIndex(-1);
		GridBagConstraints gbc_cbMaterial = new GridBagConstraints();
		gbc_cbMaterial.gridwidth = 6;
		gbc_cbMaterial.insets = new Insets(0, 0, 5, 0);
		gbc_cbMaterial.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbMaterial.gridx = 3;
		gbc_cbMaterial.gridy = 1;
		pInputDatos.add(cbMaterial, gbc_cbMaterial);

		JLabel lblAncho = new JLabel("Ancho");
		GridBagConstraints gbc_lblAncho = new GridBagConstraints();
		gbc_lblAncho.anchor = GridBagConstraints.EAST;
		gbc_lblAncho.insets = new Insets(0, 0, 0, 5);
		gbc_lblAncho.gridx = 0;
		gbc_lblAncho.gridy = 2;
		pInputDatos.add(lblAncho, gbc_lblAncho);

		tfAncho = new JTextField();
		GridBagConstraints gbc_tfAncho = new GridBagConstraints();
		gbc_tfAncho.insets = new Insets(0, 0, 0, 5);
		gbc_tfAncho.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAncho.gridx = 1;
		gbc_tfAncho.gridy = 2;
		pInputDatos.add(tfAncho, gbc_tfAncho);
		tfAncho.setColumns(10);

		JLabel lblAnchoMm = new JLabel("mm");
		GridBagConstraints gbc_lblAnchoMm = new GridBagConstraints();
		gbc_lblAnchoMm.anchor = GridBagConstraints.WEST;
		gbc_lblAnchoMm.insets = new Insets(0, 0, 0, 5);
		gbc_lblAnchoMm.gridx = 2;
		gbc_lblAnchoMm.gridy = 2;
		pInputDatos.add(lblAnchoMm, gbc_lblAnchoMm);

		JLabel lblAlto = new JLabel("Alto");
		GridBagConstraints gbc_lblAlto = new GridBagConstraints();
		gbc_lblAlto.anchor = GridBagConstraints.EAST;
		gbc_lblAlto.insets = new Insets(0, 0, 0, 5);
		gbc_lblAlto.gridx = 3;
		gbc_lblAlto.gridy = 2;
		pInputDatos.add(lblAlto, gbc_lblAlto);

		tfAlto = new JTextField();
		GridBagConstraints gbc_tfAlto = new GridBagConstraints();
		gbc_tfAlto.insets = new Insets(0, 0, 0, 5);
		gbc_tfAlto.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfAlto.gridx = 4;
		gbc_tfAlto.gridy = 2;
		pInputDatos.add(tfAlto, gbc_tfAlto);
		tfAlto.setColumns(10);

		JLabel lblAltoMm = new JLabel("mm");
		GridBagConstraints gbc_lblAltoMm = new GridBagConstraints();
		gbc_lblAltoMm.anchor = GridBagConstraints.WEST;
		gbc_lblAltoMm.insets = new Insets(0, 0, 0, 5);
		gbc_lblAltoMm.gridx = 5;
		gbc_lblAltoMm.gridy = 2;
		pInputDatos.add(lblAltoMm, gbc_lblAltoMm);

		JLabel lblFondo = new JLabel("Fondo");
		GridBagConstraints gbc_lblFondo = new GridBagConstraints();
		gbc_lblFondo.anchor = GridBagConstraints.WEST;
		gbc_lblFondo.insets = new Insets(0, 0, 0, 5);
		gbc_lblFondo.gridx = 6;
		gbc_lblFondo.gridy = 2;
		pInputDatos.add(lblFondo, gbc_lblFondo);

		tfFondo = new JTextField();
		GridBagConstraints gbc_tfFondo = new GridBagConstraints();
		gbc_tfFondo.insets = new Insets(0, 0, 0, 5);
		gbc_tfFondo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfFondo.gridx = 7;
		gbc_tfFondo.gridy = 2;
		pInputDatos.add(tfFondo, gbc_tfFondo);
		tfFondo.setColumns(10);

		JLabel lblFmm = new JLabel("mm");
		GridBagConstraints gbc_lblFmm = new GridBagConstraints();
		gbc_lblFmm.anchor = GridBagConstraints.EAST;
		gbc_lblFmm.gridx = 8;
		gbc_lblFmm.gridy = 2;
		pInputDatos.add(lblFmm, gbc_lblFmm);

		JPanel pBotonInput = new JPanel();
		GridBagConstraints gbc_pBotonInput = new GridBagConstraints();
		gbc_pBotonInput.insets = new Insets(10, 0, 0, 10);
		gbc_pBotonInput.fill = GridBagConstraints.BOTH;
		gbc_pBotonInput.gridx = 1;
		gbc_pBotonInput.gridy = 0;
		pNovoMoble.add(pBotonInput, gbc_pBotonInput);
		pBotonInput.setLayout(new BorderLayout(0, 0));

		JButton bEngadir = new JButton("Engadir");
		pBotonInput.add(bEngadir, BorderLayout.NORTH);

		JPanel pMoblesDispoñibles = new JPanel();
		pMoblesDispoñibles.setBorder(
				new TitledBorder(null, "Mobles dispo\u00F1ibles", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pMoblesDispoñibles = new GridBagConstraints();
		gbc_pMoblesDispoñibles.insets = new Insets(0, 10, 10, 10);
		gbc_pMoblesDispoñibles.fill = GridBagConstraints.BOTH;
		gbc_pMoblesDispoñibles.gridx = 0;
		gbc_pMoblesDispoñibles.gridy = 1;
		pPrincipal.add(pMoblesDispoñibles, gbc_pMoblesDispoñibles);
		GridBagLayout gbl_pMoblesDispoñibles = new GridBagLayout();
		gbl_pMoblesDispoñibles.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_pMoblesDispoñibles.rowHeights = new int[] { 255, 0, 0 };
		gbl_pMoblesDispoñibles.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_pMoblesDispoñibles.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		pMoblesDispoñibles.setLayout(gbl_pMoblesDispoñibles);

		tabla = new JTable();
		tabla.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nome", "Cor", "Material", "Medidas An x Al x Fo" }) {
			@Override
			public boolean isCellEditable(int r, int c) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return Object.class;
			}
		});
		modeloMobles = (DefaultTableModel) tabla.getModel();
		tabla.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = tabla.rowAtPoint(e.getPoint());

					if (row >= 0) {
						mostrarInforme(tabla.getSelectedRows());
					}
				}
			}
		});

		// Mobles base

		String[] nomes = { "Mesa camilla", "Mesilla", "Cama" };
		String[] cores = { "verde", "caoba", "madeira" };
		String[] mates = { "Ferro", "Plástico", "Piñeiro" };
		int[] ancho = { 75, 50, 150 };
		int[] alto = { 100, 70, 50 };
		int[] fondo = { 75, 40, 200 };
		for (int i = 0; i < nomes.length; i++) {
			Moble moble = new Moble(nomes[i], cores[i], mates[i], ancho[i], alto[i], fondo[i]);
			mobles.add(moble);
		}

		// Recorres la lista
		for (Moble m : mobles) {
			insertarMoble(m);
		}

		JScrollPane scroller = new JScrollPane(tabla);
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.weighty = 1.0;
		gbc_table.weightx = 1.0;
		gbc_table.gridwidth = 3;
		gbc_table.insets = new Insets(10, 10, 15, 10);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 0;
		gbc_table.gridy = 0;
		pMoblesDispoñibles.add(scroller, gbc_table);

		JLabel lblEmpty = new JLabel("Non hai mobles dispoñibles");
		GridBagConstraints gbc_lblEmpty = new GridBagConstraints();
		gbc_lblEmpty.gridwidth = 3;
		gbc_lblEmpty.insets = new Insets(10, 10, 15, 10);
		gbc_lblEmpty.fill = GridBagConstraints.BOTH;
		gbc_lblEmpty.gridx = 0;
		gbc_lblEmpty.gridy = 0;
		pMoblesDispoñibles.add(lblEmpty, gbc_lblEmpty);

		JButton bInforme = new JButton("Informe dos mobles");
		GridBagConstraints gbc_bInforme = new GridBagConstraints();
		gbc_bInforme.fill = GridBagConstraints.HORIZONTAL;
		gbc_bInforme.insets = new Insets(0, 10, 10, 10);
		gbc_bInforme.gridx = 0;
		gbc_bInforme.gridy = 1;
		pMoblesDispoñibles.add(bInforme, gbc_bInforme);

		JButton bEliminarSel = new JButton("Eliminar selección");
		GridBagConstraints gbc_bEliminarSel = new GridBagConstraints();
		gbc_bEliminarSel.fill = GridBagConstraints.HORIZONTAL;
		gbc_bEliminarSel.insets = new Insets(0, 10, 10, 10);
		gbc_bEliminarSel.gridx = 1;
		gbc_bEliminarSel.gridy = 1;
		pMoblesDispoñibles.add(bEliminarSel, gbc_bEliminarSel);

		JButton bEliminarTodos = new JButton("Eliminar todos");
		GridBagConstraints gbc_bEliminarTodos = new GridBagConstraints();
		gbc_bEliminarTodos.insets = new Insets(0, 10, 10, 10);
		gbc_bEliminarTodos.fill = GridBagConstraints.HORIZONTAL;
		gbc_bEliminarTodos.gridx = 2;
		gbc_bEliminarTodos.gridy = 1;
		pMoblesDispoñibles.add(bEliminarTodos, gbc_bEliminarTodos);

		lblEmpty.setVisible(false);
		scroller.setVisible(false);
		bInforme.setEnabled(false);
		bEliminarSel.setEnabled(false);
		bEliminarTodos.setEnabled(false);

		DocumentFilter numeric5 = new DocumentFilter() {
			@Override
			public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
					throws BadLocationException {
				if (string == null)
					return;
				String ns = string.replaceAll("\\D", "");
				if (fb.getDocument().getLength() + ns.length() <= 5)
					super.insertString(fb, offset, ns, attr);
			}

			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
					throws BadLocationException {
				if (text == null)
					return;
				String ns = text.replaceAll("\\D", "");
				if (fb.getDocument().getLength() - length + ns.length() <= 5)
					super.replace(fb, offset, length, ns, attrs);
			}
		};
		((AbstractDocument) tfAncho.getDocument()).setDocumentFilter(numeric5);
		((AbstractDocument) tfAlto.getDocument()).setDocumentFilter(numeric5);
		((AbstractDocument) tfFondo.getDocument()).setDocumentFilter(numeric5);

		Runnable toggleEmptyView = () -> {
			boolean empty = modeloMobles.getRowCount() == 0;
			scroller.setVisible(!empty);
			lblEmpty.setVisible(empty);
			bInforme.setEnabled(!empty);
			bEliminarSel.setEnabled(!empty);
			bEliminarTodos.setEnabled(!empty);
			pMoblesDispoñibles.revalidate();
			pMoblesDispoñibles.repaint();
		};

		toggleEmptyView.run();

		bEngadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = tfNome.getText().trim();
				String cor = tfCor.getText().trim();
				Object mat = cbMaterial.getSelectedItem();
				String sAn = tfAncho.getText().trim();
				String sAl = tfAlto.getText().trim();
				String sFo = tfFondo.getText().trim();

				if (nome.isEmpty() || cor.isEmpty() || mat == null || sAn.isEmpty() || sAl.isEmpty() || sFo.isEmpty()) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Debe cubrir todos os campos");
					return;
				}
				int an, al, fo;
				try {
					an = Integer.parseInt(sAn);
					al = Integer.parseInt(sAl);
					fo = Integer.parseInt(sFo);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Ancho, Alto e Fondo deben ser numéricos");
					return;
				}
				Moble moble = new Moble(nome, cor, String.valueOf(mat), an, al, fo);
				insertarMoble(moble);
				toggleEmptyView.run();
				tfNome.setText("");
				tfCor.setText("");
				cbMaterial.setSelectedIndex(-1);
				tfAncho.setText("");
				tfAlto.setText("");
				tfFondo.setText("");
			}
		});

		bInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = tabla.getSelectedRows();

				if (selectedRows.length > 0) {
					mostrarInforme(selectedRows);
				} else {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Debe seleccionar al menos un mueble");
				}
			}
		});

		bEliminarSel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modeloMobles.getRowCount() == 0) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Non hai mobles dispoñibles");
					return;
				}
				int[] pos = tabla.getSelectedRows();
				if (pos.length == 0) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Debe seleccionar ao menos un moble");
					return;
				}
				for (int i = pos.length - 1; i >= 0; i--) {
					modeloMobles.removeRow(pos[i]);
				}
				toggleEmptyView.run();
			}
		});

		bEliminarTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (modeloMobles.getRowCount() == 0) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Non hai mobles dispoñibles");
					return;
				}
				modeloMobles.setRowCount(0);
				toggleEmptyView.run();
			}
		});

		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
