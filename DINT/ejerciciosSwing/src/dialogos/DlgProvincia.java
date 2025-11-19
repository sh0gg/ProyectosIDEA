package dialogos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgProvincia extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JComboBox<String> comboProvincias;
    private boolean aceptado = false;

    public boolean isAceptado() {
        return aceptado;
    }

    public String getProvinciaSeleccionada() {
        if (aceptado) {
            return (String) comboProvincias.getSelectedItem();
        }
        return null;
    }

    /**
     * Launch the application. (para probalo illado se queres)
     */
    public static void main(String[] args) {
        try {
            DlgProvincia dialog = new DlgProvincia(null);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgProvincia(JDialog parent) {
        setTitle("Seleccionar provincia");
        setBounds(100, 100, 350, 150);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0};
        gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblProvincia = new JLabel("Provincia:");
            GridBagConstraints gbc_lblProvincia = new GridBagConstraints();
            gbc_lblProvincia.insets = new Insets(0, 0, 0, 5);
            gbc_lblProvincia.anchor = GridBagConstraints.WEST;
            gbc_lblProvincia.gridx = 0;
            gbc_lblProvincia.gridy = 0;
            contentPanel.add(lblProvincia, gbc_lblProvincia);
        }
        {
            comboProvincias = new JComboBox<String>();
            comboProvincias.addItem("A Coruña");
            comboProvincias.addItem("Lugo");
            comboProvincias.addItem("Ourense");
            comboProvincias.addItem("Pontevedra");
            GridBagConstraints gbc_comboProvincias = new GridBagConstraints();
            gbc_comboProvincias.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboProvincias.gridx = 1;
            gbc_comboProvincias.gridy = 0;
            contentPanel.add(comboProvincias, gbc_comboProvincias);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Aceptar");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        aceptado = true;
                        dispose();
                    }
                });
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
            {
                JButton cancelButton = new JButton("Cancelar");
                cancelButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        aceptado = false;
                        dispose();
                    }
                });
                buttonPane.add(cancelButton);
            }
        }
    }

}
