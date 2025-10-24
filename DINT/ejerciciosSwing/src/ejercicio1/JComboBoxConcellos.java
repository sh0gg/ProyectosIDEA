package ejercicio1;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemEvent;

public class JComboBoxConcellos extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> cbProvincias;
    private JComboBox<String> cbConcellos;
    private DefaultComboBoxModel<String> modeloProvincias;
    private DefaultComboBoxModel<String> modeloConcellos;
    private JLabel lblConcelloSeleccionado;

    private String[] concellosCorunha={"Betanzos","Ferrol","Pontedeume"};
    private String[] concellosLugo={"Foz","Quiroga","Triacastela"};
    private String[] concellosOurense={"Bande","Castro Caldelas","Maside"};
    private String[] concellosPontevedra={"Cangas","Bueu","Marín","Pontevedra","Tomiño"};

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JComboBoxConcellos frame = new JComboBoxConcellos();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public JComboBoxConcellos() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 250);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(10, 10));

        JPanel panelGeneral = new JPanel();
        contentPane.add(panelGeneral, BorderLayout.CENTER);
        panelGeneral.setLayout(new BorderLayout(10, 10));

        JPanel pProvincias = new JPanel();
        pProvincias.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
                new EtchedBorder(EtchedBorder.LOWERED, null, null)), "Provincias e Concellos",
                TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
        panelGeneral.add(pProvincias, BorderLayout.CENTER);
        GridBagLayout gbl_pProvincias = new GridBagLayout();
        gbl_pProvincias.columnWidths = new int[]{100, 200, 0};
        gbl_pProvincias.rowHeights = new int[]{40, 40, 40, 0};
        gbl_pProvincias.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_pProvincias.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
        pProvincias.setLayout(gbl_pProvincias);

        JLabel lblProvincia = new JLabel("Provincia");
        GridBagConstraints gbc_lblProvincia = new GridBagConstraints();
        gbc_lblProvincia.insets = new Insets(10, 10, 5, 5);
        gbc_lblProvincia.anchor = GridBagConstraints.WEST;
        gbc_lblProvincia.gridx = 0;
        gbc_lblProvincia.gridy = 0;
        pProvincias.add(lblProvincia, gbc_lblProvincia);

        cbProvincias = new JComboBox<>();
        GridBagConstraints gbc_cbProvincias = new GridBagConstraints();
        gbc_cbProvincias.insets = new Insets(10, 0, 5, 10);
        gbc_cbProvincias.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbProvincias.gridx = 1;
        gbc_cbProvincias.gridy = 0;
        pProvincias.add(cbProvincias, gbc_cbProvincias);

        JLabel lblConcello = new JLabel("Concello");
        GridBagConstraints gbc_lblConcello = new GridBagConstraints();
        gbc_lblConcello.anchor = GridBagConstraints.WEST;
        gbc_lblConcello.insets = new Insets(0, 10, 5, 5);
        gbc_lblConcello.gridx = 0;
        gbc_lblConcello.gridy = 1;
        pProvincias.add(lblConcello, gbc_lblConcello);

        cbConcellos = new JComboBox<>();
        GridBagConstraints gbc_cbConcellos = new GridBagConstraints();
        gbc_cbConcellos.insets = new Insets(0, 0, 5, 10);
        gbc_cbConcellos.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbConcellos.gridx = 1;
        gbc_cbConcellos.gridy = 1;
        pProvincias.add(cbConcellos, gbc_cbConcellos);

        lblConcelloSeleccionado = new JLabel("Concello seleccionado: ");
        GridBagConstraints gbc_lblConcelloSeleccionado = new GridBagConstraints();
        gbc_lblConcelloSeleccionado.insets = new Insets(10, 10, 10, 10);
        gbc_lblConcelloSeleccionado.gridwidth = 2;
        gbc_lblConcelloSeleccionado.gridx = 0;
        gbc_lblConcelloSeleccionado.gridy = 2;
        pProvincias.add(lblConcelloSeleccionado, gbc_lblConcelloSeleccionado);

        modeloProvincias = new DefaultComboBoxModel<>();
        modeloConcellos = new DefaultComboBoxModel<>();
        cbProvincias.setModel(modeloProvincias);
        cbConcellos.setModel(modeloConcellos);

        modeloProvincias.addElement("A Coruña");
        modeloProvincias.addElement("Lugo");
        modeloProvincias.addElement("Ourense");
        modeloProvincias.addElement("Pontevedra");

        cbProvincias.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                int idx = cbProvincias.getSelectedIndex();
                cargarConcellos(idx);
                if (modeloConcellos.getSize() > 0) cbConcellos.setSelectedIndex(0);
            }
        });

        cbConcellos.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED && cbConcellos.getSelectedIndex() != -1) {
                String nome = (String) cbConcellos.getSelectedItem();
                lblConcelloSeleccionado.setText("Concello seleccionado: " + nome.toUpperCase());
            }
        });

        cbProvincias.setSelectedIndex(0);
    }

    private void cargarConcellos(int idx) {
        modeloConcellos.removeAllElements();
        switch (idx) {
            case 0:
                for (String s : concellosCorunha) modeloConcellos.addElement(s);
                break;
            case 1:
                for (String s : concellosLugo) modeloConcellos.addElement(s);
                break;
            case 2:
                for (String s : concellosOurense) modeloConcellos.addElement(s);
                break;
            case 3:
                for (String s : concellosPontevedra) modeloConcellos.addElement(s);
                break;
        }
    }
}
