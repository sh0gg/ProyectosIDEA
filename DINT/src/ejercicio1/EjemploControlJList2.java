package ejercicio1;

import java.awt.EventQueue;

import javax.swing.*;

import javax.swing.border.*;

import java.awt.*;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

public class EjemploControlJList2 extends JFrame {

    public static class Alfombra {

        private final String modelo;

        private final String cor;

        private final int alto;

        private final int ancho;

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

    private static final long serialVersionUID = 1L;

    // === Estado/UI compartido ===

    private JPanel contentPane;

    private JTextField tfModelo;

    private JTextField tfAlto;

    private JTextField tfCor;

    private JTextField tfAncho;

    private DefaultListModel<Alfombra> listModel; // <---- EL MODELO

    private JList<Alfombra> listAlfombras;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {

                EjemploControlJList frame = new EjemploControlJList();

                frame.setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();

            }

        });

    }

    public EjemploControlJList2() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setBounds(100, 100, 650, 380);

        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelGeneral = new JPanel();

        contentPane.add(panelGeneral);

        panelGeneral.setLayout(new BorderLayout(10, 10));

        JPanel pNovaAlfombra = new JPanel();

        pNovaAlfombra.setBorder(new TitledBorder(

                new CompoundBorder(new BevelBorder(BevelBorder.LOWERED), new EtchedBorder(EtchedBorder.LOWERED)),

                "Nova alfombra", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.BLACK));

        panelGeneral.add(pNovaAlfombra, BorderLayout.NORTH);

        GridBagLayout gbl_pNovaAlfombra = new GridBagLayout();

        gbl_pNovaAlfombra.columnWidths = new int[]{483, 124, 0};

        gbl_pNovaAlfombra.rowHeights = new int[]{45, 0};

        gbl_pNovaAlfombra.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};

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

        gbc_tfModelo.insets = new Insets(10, 0, 5, 0);

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

        gbc_tfCor.insets = new Insets(0, 0, 10, 5);

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

        tfAncho.setColumns(10);

        JLabel lblCm1 = new JLabel("(cm)");

        GridBagConstraints gbc_lblCm1 = new GridBagConstraints();

        gbc_lblCm1.anchor = GridBagConstraints.WEST;

        gbc_lblCm1.insets = new Insets(0, 0, 10, 5);

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

        tfAlto.setColumns(10);

        JLabel lblCm2 = new JLabel("(cm)");

        GridBagConstraints gbc_lblCm2 = new GridBagConstraints();

        gbc_lblCm2.anchor = GridBagConstraints.WEST;

        gbc_lblCm2.insets = new Insets(0, 0, 10, 5);

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

        gbl_pBoton.columnWidths = new int[]{69, 0};

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

        pDisponibles.setBorder(new TitledBorder(

                new CompoundBorder(new BevelBorder(BevelBorder.LOWERED), new EtchedBorder(EtchedBorder.LOWERED)),

                "Alfombras disponibles", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.BLACK));

        panelGeneral.add(pDisponibles, BorderLayout.CENTER);

        GridBagLayout gbl_pDisponibles = new GridBagLayout();

        gbl_pDisponibles.columnWidths = new int[]{306, 158, 0};

        gbl_pDisponibles.rowHeights = new int[]{120, 0};

        gbl_pDisponibles.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};

        gbl_pDisponibles.rowWeights = new double[]{1.0, Double.MIN_VALUE};

        pDisponibles.setLayout(gbl_pDisponibles);

        JPanel pLista = new JPanel();

        GridBagConstraints gbc_pLista = new GridBagConstraints();

        gbc_pLista.fill = GridBagConstraints.BOTH;

        gbc_pLista.insets = new Insets(0, 0, 0, 5);

        gbc_pLista.gridx = 0;

        gbc_pLista.gridy = 0;

        pDisponibles.add(pLista, gbc_pLista);

        GridBagLayout gbl_pLista = new GridBagLayout();

        gbl_pLista.columnWidths = new int[]{0, 0};

        gbl_pLista.rowHeights = new int[]{0, 0};

        gbl_pLista.columnWeights = new double[]{1.0, Double.MIN_VALUE};

        gbl_pLista.rowWeights = new double[]{1.0, Double.MIN_VALUE};

        pLista.setLayout(gbl_pLista);

        // === MODELO + LISTA ===

        listModel = new DefaultListModel<>();

        listAlfombras = new JList<>(listModel);

        listAlfombras.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JScrollPane sp = new JScrollPane(listAlfombras);

        GridBagConstraints gbc_listAlfombras = new GridBagConstraints();

        gbc_listAlfombras.insets = new Insets(10, 10, 10, 10);

        gbc_listAlfombras.fill = GridBagConstraints.BOTH;

        gbc_listAlfombras.gridx = 0;

        gbc_listAlfombras.gridy = 0;

        pLista.add(sp, gbc_listAlfombras);

        JPanel pBotonera = new JPanel();

        GridBagConstraints gbc_pBotonera = new GridBagConstraints();

        gbc_pBotonera.fill = GridBagConstraints.BOTH;

        gbc_pBotonera.gridx = 1;

        gbc_pBotonera.gridy = 0;

        pDisponibles.add(pBotonera, gbc_pBotonera);

        GridBagLayout gbl_pBotonera = new GridBagLayout();

        gbl_pBotonera.columnWidths = new int[]{303, 0};

        gbl_pBotonera.rowHeights = new int[]{0, 0, 0, 0, 0};

        gbl_pBotonera.columnWeights = new double[]{1.0, Double.MIN_VALUE};

        gbl_pBotonera.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};

        pBotonera.setLayout(gbl_pBotonera);

        JButton bInfo = new JButton("Información das alfombras");

        bInfo.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc_bInfo = new GridBagConstraints();

        gbc_bInfo.fill = GridBagConstraints.BOTH;

        gbc_bInfo.insets = new Insets(10, 0, 5, 0);

        gbc_bInfo.gridx = 0;

        gbc_bInfo.gridy = 0;

        pBotonera.add(bInfo, gbc_bInfo);

        JButton bEliminarSel = new JButton("Eliminar alfombra");

        bEliminarSel.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc_bEliminarSel = new GridBagConstraints();

        gbc_bEliminarSel.fill = GridBagConstraints.BOTH;

        gbc_bEliminarSel.insets = new Insets(0, 0, 5, 0);

        gbc_bEliminarSel.gridx = 0;

        gbc_bEliminarSel.gridy = 1;

        pBotonera.add(bEliminarSel, gbc_bEliminarSel);

        JButton bEliminarTodas = new JButton("Eliminar TODAS");

        bEliminarTodas.setAlignmentX(Component.CENTER_ALIGNMENT);

        GridBagConstraints gbc_bEliminarTodas = new GridBagConstraints();

        gbc_bEliminarTodas.insets = new Insets(0, 0, 5, 0);

        gbc_bEliminarTodas.fill = GridBagConstraints.BOTH;

        gbc_bEliminarTodas.gridx = 0;

        gbc_bEliminarTodas.gridy = 2;

        pBotonera.add(bEliminarTodas, gbc_bEliminarTodas);

        // ==== CARGA INICIAL ====

        cargarIniciales();

        // ==== ACCIONES ====

        bEngadir.addActionListener(e -> {

            String modelo = safeText(tfModelo.getText());

            String cor = safeText(tfCor.getText());

            if (modelo.isEmpty() || cor.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Modelo e Cor son obrigatorios.", "Validación", JOptionPane.WARNING_MESSAGE);

                return;

            }

            Integer ancho = parseEntero(tfAncho.getText());

            Integer alto = parseEntero(tfAlto.getText());

            if (ancho == null || alto == null || ancho < 0 || alto < 0) {

                JOptionPane.showMessageDialog(this, "Ancho e Alto deben ser números (>= 0).", "Validación", JOptionPane.WARNING_MESSAGE);

                return;

            }

            agregarAlfombra(new Alfombra(modelo, cor, alto, ancho));

            tfModelo.setText("");

            tfCor.setText("");

            tfAncho.setText("");

            tfAlto.setText("");

            tfModelo.requestFocus();

        });

        bEliminarSel.addActionListener(e -> {

            int[] idx = listAlfombras.getSelectedIndices();

            if (idx.length == 0) {

                JOptionPane.showMessageDialog(this, "Selecciona polo menos unha alfombra.", "Info", JOptionPane.INFORMATION_MESSAGE);

                return;

            }

            // Borrar de atrás hacia delante para no descuadrar índices

            for (int i = idx.length - 1; i >= 0; i--) {

                listModel.remove(idx[i]);

            }

        });

        bEliminarTodas.addActionListener(e -> {

            if (listModel.isEmpty()) return;

            int r = JOptionPane.showConfirmDialog(this, "¿Seguro que queres borrar TODAS as alfombras?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (r == JOptionPane.YES_OPTION) listModel.clear();

        });

        bInfo.addActionListener(e -> {

            java.util.List<Alfombra> sel = listAlfombras.getSelectedValuesList();

            if (sel.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Non hai selección.", "Info", JOptionPane.INFORMATION_MESSAGE);

                return;

            }

            StringBuilder sb = new StringBuilder();

            for (Alfombra a : sel) {

                sb.append("Modelo: ").append(a.getModelo())

                  .append(" | Cor: ").append(a.getCor())

                  .append(" | Ancho: ").append(a.getAncho()).append(" cm")

                  .append(" | Alto: ").append(a.getAlto()).append(" cm")

                  .append('\n');

            }

            JOptionPane.showMessageDialog(this, sb.toString(), "Información", JOptionPane.PLAIN_MESSAGE);

        });

    }

    // ====== LÓGICA ======

    /** Carga los datos iniciales en el modelo al arrancar la interfaz. */

    private void cargarIniciales() {

        String[] modelos = {"Ceo cuberto", "Campo noviño", "Praia nascente", "Noite estrelada"};

        String[] cores = {"gris", "verde", "ocre", "negra"};

        int[] anchos = {100, 40, 200, 420};

        int[] altos = {400, 250, 70, 190};

        for (int i = 0; i < modelos.length; i++) {

            listModel.addElement(new Alfombra(modelos[i], cores[i], altos[i], anchos[i]));

            // Nota: en tu código original los parámetros estaban mezclados,

            // aquí mantengo: new Alfombra(modelo, cor, alto, ancho)

        }

    }

    /** Añade una alfombra al modelo y deja seleccionada la última fila. */

    private void agregarAlfombra(Alfombra a) {

        listModel.addElement(a);

        int last = listModel.getSize() - 1;

        if (last >= 0) {

            listAlfombras.setSelectedIndex(last);

            listAlfombras.ensureIndexIsVisible(last);

        }

    }

    private static String safeText(String s) {

        return s == null ? "" : s.trim();

    }

    private static Integer parseEntero(String s) {

        try {

            return Integer.parseInt(s.trim());

        } catch (Exception e) {

            return null;

        }

    }

}