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
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private DefaultTableModel modeloMobles;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		ArrayList<Moble> mobles = new ArrayList<Moble>();
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
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EjemploControlJTable frame = new EjemploControlJTable();
					for (Moble m : mobles) {
						frame.insertarMoble(m, frame.table);
					}
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void insertarMoble(Moble m, JTable j) {
		if (modeloMobles == null) return;
		modeloMobles.setRowCount(modeloMobles.getRowCount()+1);
		int r = modeloMobles.getRowCount()-1;
		modeloMobles.setValueAt(m, r, 0);
		modeloMobles.setValueAt(m.getCor(), r, 1);
		modeloMobles.setValueAt(m.getMaterial(), r, 2);
		String medidas = m.getAncho()+"x"+m.getAlto()+"x"+m.getFondo();
		modeloMobles.setValueAt(medidas, r, 3);
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

		JLabel lblNewLabel_1 = new JLabel("Nome");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		pInputDatos.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 8;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		pInputDatos.add(textField, gbc_textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Cor");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		pInputDatos.add(lblNewLabel_2, gbc_lblNewLabel_2);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 1;
		pInputDatos.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Material");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 1;
		pInputDatos.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addItem("Carballo");
		comboBox.addItem("Cerdeira");
		comboBox.addItem("Faia");
		comboBox.addItem("Ferro");
		comboBox.addItem("Piñeiro");
		comboBox.addItem("Plástico");
		comboBox.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 6;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 1;
		pInputDatos.add(comboBox, gbc_comboBox);

		JLabel lblNewLabel_3 = new JLabel("Ancho");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 2;
		pInputDatos.add(lblNewLabel_3, gbc_lblNewLabel_3);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 0, 5);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 2;
		pInputDatos.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("mm");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_5.gridx = 2;
		gbc_lblNewLabel_5.gridy = 2;
		pInputDatos.add(lblNewLabel_5, gbc_lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Alto");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 2;
		pInputDatos.add(lblNewLabel_6, gbc_lblNewLabel_6);

		textField_3 = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.insets = new Insets(0, 0, 0, 5);
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.gridx = 4;
		gbc_textField_3.gridy = 2;
		pInputDatos.add(textField_3, gbc_textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("mm");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 5;
		gbc_lblNewLabel_7.gridy = 2;
		pInputDatos.add(lblNewLabel_7, gbc_lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Fondo");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_8.gridx = 6;
		gbc_lblNewLabel_8.gridy = 2;
		pInputDatos.add(lblNewLabel_8, gbc_lblNewLabel_8);

		textField_4 = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 0, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 7;
		gbc_textField_4.gridy = 2;
		pInputDatos.add(textField_4, gbc_textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel = new JLabel("mm");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 8;
		gbc_lblNewLabel.gridy = 2;
		pInputDatos.add(lblNewLabel, gbc_lblNewLabel);

		JPanel pBotonInput = new JPanel();
		GridBagConstraints gbc_pBotonInput = new GridBagConstraints();
		gbc_pBotonInput.insets = new Insets(10, 0, 0, 10);
		gbc_pBotonInput.fill = GridBagConstraints.BOTH;
		gbc_pBotonInput.gridx = 1;
		gbc_pBotonInput.gridy = 0;
		pNovoMoble.add(pBotonInput, gbc_pBotonInput);
		pBotonInput.setLayout(new BorderLayout(0, 0));

		JButton btnNewButton = new JButton("Engadir");
		pBotonInput.add(btnNewButton, BorderLayout.NORTH);

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
		gbl_pMoblesDispoñibles.rowHeights = new int[] { 0, 0, 0 };
		gbl_pMoblesDispoñibles.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_pMoblesDispoñibles.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		pMoblesDispoñibles.setLayout(gbl_pMoblesDispoñibles);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] { },
				new String[] { "Nome", "Cor", "Material", "Medidas An x Al x Fo" }) {
			@Override public boolean isCellEditable(int r, int c) { return false; }
			@Override public Class<?> getColumnClass(int columnIndex) { return Object.class; }
		});
		modeloMobles = (DefaultTableModel) table.getModel();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				if (e.getClickCount()==2 && table.getSelectedRow()>=0) {
					Moble moble=(Moble)modeloMobles.getValueAt(table.getSelectedRow(), 0);
					String resultado="Nome: "+moble.getNome()+" Cor: "+moble.getCor()
							+" Material: "+moble.getMaterial()+" Ancho: "+moble.getAncho()
							+" Alto: "+moble.getAlto()+" Fondo: "+moble.getFondo();
					JOptionPane.showMessageDialog(EjemploControlJTable.this, resultado);
				}
			}
		});

		JScrollPane scroller = new JScrollPane(table);
		GridBagConstraints gbc_table = new GridBagConstraints();
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

		JButton btnNewButton_1 = new JButton("Informe dos mobles");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 10, 10, 10);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 1;
		pMoblesDispoñibles.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Eliminar selección");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 10, 10, 10);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 1;
		pMoblesDispoñibles.add(btnNewButton_2, gbc_btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Eliminar todos");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 10, 10, 10);
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 1;
		pMoblesDispoñibles.add(btnNewButton_3, gbc_btnNewButton_3);

		lblEmpty.setVisible(true);
		scroller.setVisible(false);
		btnNewButton_1.setEnabled(false);
		btnNewButton_2.setEnabled(false);
		btnNewButton_3.setEnabled(false);

		DocumentFilter numeric5 = new DocumentFilter() {
			@Override public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
				if (string == null) return;
				String ns = string.replaceAll("\\D", "");
				if (fb.getDocument().getLength() + ns.length() <= 5) super.insertString(fb, offset, ns, attr);
			}
			@Override public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				if (text == null) return;
				String ns = text.replaceAll("\\D", "");
				if (fb.getDocument().getLength() - length + ns.length() <= 5) super.replace(fb, offset, length, ns, attrs);
			}
		};
		((AbstractDocument)textField_2.getDocument()).setDocumentFilter(numeric5);
		((AbstractDocument)textField_3.getDocument()).setDocumentFilter(numeric5);
		((AbstractDocument)textField_4.getDocument()).setDocumentFilter(numeric5);

		Runnable toggleEmptyView = () -> {
			boolean empty = modeloMobles.getRowCount()==0;
			scroller.setVisible(!empty);
			lblEmpty.setVisible(empty);
			btnNewButton_1.setEnabled(!empty);
			btnNewButton_2.setEnabled(!empty);
			btnNewButton_3.setEnabled(!empty);
			pMoblesDispoñibles.revalidate();
			pMoblesDispoñibles.repaint();
		};

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = textField.getText().trim();
				String cor = textField_1.getText().trim();
				Object mat = comboBox.getSelectedItem();
				String sAn = textField_2.getText().trim();
				String sAl = textField_3.getText().trim();
				String sFo = textField_4.getText().trim();

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
				insertarMoble(moble, table);
				toggleEmptyView.run();
				textField.setText("");
				textField_1.setText("");
				comboBox.setSelectedIndex(-1);
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});

		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modeloMobles.getRowCount()==0) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Non hai mobles dispoñibles");
					return;
				}
				if(table.getSelectedRowCount()==0) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Debe seleccionar ao menos un moble");
					return;
				}
				int[] pos = table.getSelectedRows();
				StringBuilder sb = new StringBuilder();
				for (int i=0;i<pos.length;i++) {
					Moble m = (Moble) modeloMobles.getValueAt(pos[i], 0);
					sb.append("Nome: ").append(m.getNome()).append(" Cor: ").append(m.getCor())
					  .append(" Material: ").append(m.getMaterial()).append(" Ancho: ").append(m.getAncho())
					  .append(" Alto: ").append(m.getAlto()).append(" Fondo: ").append(m.getFondo()).append("\n");
				}
				JOptionPane.showMessageDialog(EjemploControlJTable.this, sb.toString());
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modeloMobles.getRowCount()==0) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Non hai mobles dispoñibles");
					return;
				}
				int[] pos = table.getSelectedRows();
				if (pos.length==0) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Debe seleccionar ao menos un moble");
					return;
				}
				for (int i=pos.length-1;i>=0;i--) {
					modeloMobles.removeRow(pos[i]);
				}
				toggleEmptyView.run();
			}
		});

		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(modeloMobles.getRowCount()==0) {
					JOptionPane.showMessageDialog(EjemploControlJTable.this, "Non hai mobles dispoñibles");
					return;
				}
				modeloMobles.setRowCount(0);
				toggleEmptyView.run();
			}
		});
	}
}
