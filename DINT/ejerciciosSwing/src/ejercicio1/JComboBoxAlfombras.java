package ejercicio1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.PlainDocument;
import javax.swing.text.BadLocationException;

public class JComboBoxAlfombras extends JFrame {

    public static class Alfombra {
        private String modelo;
        private String cor;
        private int alto;
        private int ancho;

        @Override
        public String toString() {
            return "MODELO " + modelo + " - COR " + cor;
        }

        public Alfombra(String modelo, String cor, int alto, int ancho) {
            this.modelo = modelo;
            this.cor = cor;
            this.alto = alto;
            this.ancho = ancho;
        }

        public String getModelo() { return modelo; }
        public String getCor() { return cor; }
        public int getAlto() { return alto; }
        public int getAncho() { return ancho; }
    }

    public static class LimiteLonxitudeJTextField extends PlainDocument {
        private final int max;
        public LimiteLonxitudeJTextField(int max) { this.max = Math.max(0, max); }
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws BadLocationException {
            if (str == null) return;
            int dispo = max - getLength();
            if (dispo <= 0) return;
            String ins = (str.length() <= dispo) ? str : str.substring(0, dispo);
            super.insertString(offs, ins, a);
        }
    }

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfModelo;
    private JTextField tfAlto;
    private JTextField tfCor;
    private JTextField tfAncho;

    private DefaultComboBoxModel<Alfombra> modeloAlfombras;
    private JComboBox<Alfombra> cmbAlfombras;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JComboBoxAlfombras frame = new JComboBoxAlfombras();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JComboBoxAlfombras() {
        setTitle("Exemplo control JComboBox");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 560, 260);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JPanel panelGeneral = new JPanel();
        contentPane.add(panelGeneral, BorderLayout.CENTER);
        panelGeneral.setLayout(new BorderLayout(10, 10));

        JPanel pNovaAlfombra = new JPanel();
        pNovaAlfombra.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)), "Nova alfombra", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
        panelGeneral.add(pNovaAlfombra, BorderLayout.NORTH);
        GridBagLayout gbl_pNovaAlfombra = new GridBagLayout();
        gbl_pNovaAlfombra.columnWidths = new int[]{420, 120, 0};
        gbl_pNovaAlfombra.rowHeights = new int[]{40, 0};
        gbl_pNovaAlfombra.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
        gbl_pNovaAlfombra.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        pNovaAlfombra.setLayout(gbl_pNovaAlfombra);

        JPanel pDatos = new JPanel();
        GridBagConstraints gbc_pDatos = new GridBagConstraints();
        gbc_pDatos.fill = GridBagConstraints.BOTH;
        gbc_pDatos.insets = new Insets(0, 0, 0, 5);
        gbc_pDatos.gridx = 0;
        gbc_pDatos.gridy = 0;
        pNovaAlfombra.add(pDatos, gbc_pDatos);
        GridBagLayout gbl_pDatos = new GridBagLayout();
        gbl_pDatos.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_pDatos.rowHeights = new int[]{0, 0, 0};
        gbl_pDatos.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gbl_pDatos.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        pDatos.setLayout(gbl_pDatos);

        JLabel lblModelo = new JLabel("Modelo");
        GridBagConstraints gbc_lblModelo = new GridBagConstraints();
        gbc_lblModelo.insets = new Insets(10, 10, 5, 5);
        gbc_lblModelo.anchor = GridBagConstraints.WEST;
        gbc_lblModelo.gridx = 0;
        gbc_lblModelo.gridy = 0;
        pDatos.add(lblModelo, gbc_lblModelo);

        tfModelo = new JTextField();
        GridBagConstraints gbc_tfModelo = new GridBagConstraints();
        gbc_tfModelo.gridwidth = 9;
        gbc_tfModelo.insets = new Insets(10, 0, 5, 10);
        gbc_tfModelo.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfModelo.gridx = 1;
        gbc_tfModelo.gridy = 0;
        pDatos.add(tfModelo, gbc_tfModelo);
        tfModelo.setColumns(10);

        JLabel lblCor = new JLabel("Cor");
        GridBagConstraints gbc_lblCor = new GridBagConstraints();
        gbc_lblCor.anchor = GridBagConstraints.WEST;
        gbc_lblCor.insets = new Insets(0, 10, 10, 5);
        gbc_lblCor.gridx = 0;
        gbc_lblCor.gridy = 1;
        pDatos.add(lblCor, gbc_lblCor);

        tfCor = new JTextField();
        GridBagConstraints gbc_tfCor = new GridBagConstraints();
        gbc_tfCor.insets = new Insets(0, 0, 10, 10);
        gbc_tfCor.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfCor.gridx = 1;
        gbc_tfCor.gridy = 1;
        pDatos.add(tfCor, gbc_tfCor);
        tfCor.setColumns(10);

        JLabel lblAncho = new JLabel("Ancho");
        GridBagConstraints gbc_lblAncho = new GridBagConstraints();
        gbc_lblAncho.insets = new Insets(0, 0, 10, 5);
        gbc_lblAncho.anchor = GridBagConstraints.WEST;
        gbc_lblAncho.gridx = 2;
        gbc_lblAncho.gridy = 1;
        pDatos.add(lblAncho, gbc_lblAncho);

        tfAncho = new JTextField();
        GridBagConstraints gbc_tfAncho = new GridBagConstraints();
        gbc_tfAncho.insets = new Insets(0, 0, 10, 5);
        gbc_tfAncho.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfAncho.gridx = 3;
        gbc_tfAncho.gridy = 1;
        pDatos.add(tfAncho, gbc_tfAncho);
        tfAncho.setColumns(6);

        JLabel lblCm1 = new JLabel("(cm)");
        GridBagConstraints gbc_lblCm1 = new GridBagConstraints();
        gbc_lblCm1.anchor = GridBagConstraints.WEST;
        gbc_lblCm1.insets = new Insets(0, 0, 10, 15);
        gbc_lblCm1.gridx = 4;
        gbc_lblCm1.gridy = 1;
        pDatos.add(lblCm1, gbc_lblCm1);

        JLabel lblAlto = new JLabel("Alto");
        GridBagConstraints gbc_lblAlto = new GridBagConstraints();
        gbc_lblAlto.insets = new Insets(0, 0, 10, 5);
        gbc_lblAlto.anchor = GridBagConstraints.WEST;
        gbc_lblAlto.gridx = 5;
        gbc_lblAlto.gridy = 1;
        pDatos.add(lblAlto, gbc_lblAlto);

        tfAlto = new JTextField();
        GridBagConstraints gbc_tfAlto = new GridBagConstraints();
        gbc_tfAlto.insets = new Insets(0, 0, 10, 5);
        gbc_tfAlto.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfAlto.gridx = 6;
        gbc_tfAlto.gridy = 1;
        pDatos.add(tfAlto, gbc_tfAlto);
        tfAlto.setColumns(6);

        JLabel lblCm2 = new JLabel("(cm)");
        GridBagConstraints gbc_lblCm2 = new GridBagConstraints();
        gbc_lblCm2.anchor = GridBagConstraints.WEST;
        gbc_lblCm2.insets = new Insets(0, 0, 10, 0);
        gbc_lblCm2.gridx = 7;
        gbc_lblCm2.gridy = 1;
        pDatos.add(lblCm2, gbc_lblCm2);

        JPanel pBoton = new JPanel();
        GridBagConstraints gbc_pBoton = new GridBagConstraints();
        gbc_pBoton.fill = GridBagConstraints.BOTH;
        gbc_pBoton.gridx = 1;
        gbc_pBoton.gridy = 0;
        pNovaAlfombra.add(pBoton, gbc_pBoton);
        GridBagLayout gbl_pBoton = new GridBagLayout();
        gbl_pBoton.columnWidths = new int[]{100, 0};
        gbl_pBoton.rowHeights = new int[]{23, 0};
        gbl_pBoton.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_pBoton.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        pBoton.setLayout(gbl_pBoton);

        JButton bEngadir = new JButton("Engadir");
        GridBagConstraints gbc_bEngadir = new GridBagConstraints();
        gbc_bEngadir.insets = new Insets(10, 0, 0, 0);
        gbc_bEngadir.fill = GridBagConstraints.HORIZONTAL;
        gbc_bEngadir.anchor = GridBagConstraints.NORTH;
        gbc_bEngadir.gridx = 0;
        gbc_bEngadir.gridy = 0;
        pBoton.add(bEngadir, gbc_bEngadir);

        JPanel pDisponibles = new JPanel();
        pDisponibles.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), new EtchedBorder(EtchedBorder.LOWERED, null, null)), "Alfombras dispoñibles", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
        panelGeneral.add(pDisponibles, BorderLayout.CENTER);
        GridBagLayout gbl_pDisponibles = new GridBagLayout();
        gbl_pDisponibles.columnWidths = new int[]{0, 0, 0, 0};
        gbl_pDisponibles.rowHeights = new int[]{0, 0, 0};
        gbl_pDisponibles.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0};
        gbl_pDisponibles.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        pDisponibles.setLayout(gbl_pDisponibles);

        cmbAlfombras = new JComboBox<>();
        GridBagConstraints gbc_cmbAlfombras = new GridBagConstraints();
        gbc_cmbAlfombras.insets = new Insets(10, 10, 5, 10);
        gbc_cmbAlfombras.fill = GridBagConstraints.HORIZONTAL;
        gbc_cmbAlfombras.gridwidth = 4;
        gbc_cmbAlfombras.gridx = 0;
        gbc_cmbAlfombras.gridy = 0;
        pDisponibles.add(cmbAlfombras, gbc_cmbAlfombras);

        JButton bInfo = new JButton("Información da alfombra");
        GridBagConstraints gbc_bInfo = new GridBagConstraints();
        gbc_bInfo.insets = new Insets(5, 10, 10, 5);
        gbc_bInfo.fill = GridBagConstraints.HORIZONTAL;
        gbc_bInfo.gridx = 0;
        gbc_bInfo.gridy = 1;
        pDisponibles.add(bInfo, gbc_bInfo);

        JButton bEliminarSel = new JButton("Eliminar alfombra");
        GridBagConstraints gbc_bEliminarSel = new GridBagConstraints();
        gbc_bEliminarSel.insets = new Insets(5, 5, 10, 5);
        gbc_bEliminarSel.fill = GridBagConstraints.HORIZONTAL;
        gbc_bEliminarSel.gridx = 1;
        gbc_bEliminarSel.gridy = 1;
        pDisponibles.add(bEliminarSel, gbc_bEliminarSel);

        JButton bEliminarTodas = new JButton("Eliminar todas");
        GridBagConstraints gbc_bEliminarTodas = new GridBagConstraints();
        gbc_bEliminarTodas.insets = new Insets(5, 5, 10, 10);
        gbc_bEliminarTodas.fill = GridBagConstraints.HORIZONTAL;
        gbc_bEliminarTodas.gridx = 2;
        gbc_bEliminarTodas.gridy = 1;
        pDisponibles.add(bEliminarTodas, gbc_bEliminarTodas);

        modeloAlfombras = new DefaultComboBoxModel<>();
        cmbAlfombras.setModel(modeloAlfombras);

        tfAncho.setDocument(new LimiteLonxitudeJTextField(5));
        tfAlto.setDocument(new LimiteLonxitudeJTextField(5));

        bEngadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String modelo = tfModelo.getText().trim();
                String cor = tfCor.getText().trim();
                String sAncho = tfAncho.getText().trim();
                String sAlto  = tfAlto.getText().trim();

                if (modelo.isEmpty() || cor.isEmpty() || sAncho.isEmpty() || sAlto.isEmpty()) {
                    JOptionPane.showMessageDialog(JComboBoxAlfombras.this, "Todos los campos son obrigatorios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (!sAncho.matches("\\d+") || !sAlto.matches("\\d+")) {
                    JOptionPane.showMessageDialog(JComboBoxAlfombras.this, "Ancho e Alto deben ser numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int ancho = Integer.parseInt(sAncho);
                int alto  = Integer.parseInt(sAlto);

                Alfombra alf = new Alfombra(modelo, cor, alto, ancho);
                modeloAlfombras.addElement(alf);

                tfModelo.setText("");
                tfCor.setText("");
                tfAncho.setText("");
                tfAlto.setText("");
                tfModelo.requestFocus();
            }
        });

        bInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modeloAlfombras.getSize() == 0) {
                    JOptionPane.showMessageDialog(JComboBoxAlfombras.this, "Non hai alfombras dispoñibles");
                    return;
                }
                int idx = cmbAlfombras.getSelectedIndex();
                if (idx == -1) {
                    JOptionPane.showMessageDialog(JComboBoxAlfombras.this, "Debe seleccionar unha alfombra");
                    return;
                }
                Alfombra a = modeloAlfombras.getElementAt(idx);
                JOptionPane.showMessageDialog(JComboBoxAlfombras.this,
                        "MODELO: " + a.getModelo() +
                        "\nCOR: " + a.getCor() +
                        "\nANCHO: " + a.getAncho() + " cm" +
                        "\nALTO: " + a.getAlto() + " cm");
            }
        });

        bEliminarSel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modeloAlfombras.getSize() == 0) {
                    JOptionPane.showMessageDialog(JComboBoxAlfombras.this, "Non hai alfombras dispoñibles");
                    return;
                }
                int idx = cmbAlfombras.getSelectedIndex();
                if (idx == -1) {
                    JOptionPane.showMessageDialog(JComboBoxAlfombras.this, "Debe seleccionar unha alfombra");
                    return;
                }
                modeloAlfombras.removeElementAt(idx);
            }
        });

        bEliminarTodas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (modeloAlfombras.getSize() == 0) {
                    JOptionPane.showMessageDialog(JComboBoxAlfombras.this, "Non hai alfombras dispoñibles");
                    return;
                }
                modeloAlfombras.removeAllElements();
            }
        });
    }
}
