package dialogos;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDatosNovoUsuario extends JDialog {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPanel = new JPanel();
    private JTextField txtNome;
    private JTextField txtApelidos;
    private JTextField txtProvincia;

    private boolean aceptado = false;
    private Usuario usuarioCreado;
    private int id;

    public boolean isAceptado() {
        return aceptado;
    }

    public Usuario getUsuarioCreado() {
        return usuarioCreado;
    }

    /**
     * Launch the application. (para probalo illado se queres)
     */
    public static void main(String[] args) {
        try {
            DlgDatosNovoUsuario dialog = new DlgDatosNovoUsuario(null, 1);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public DlgDatosNovoUsuario(JDialog parent, int id) {
        this.id = id;

        setTitle("Datos do novo usuario (ID-" + id + ")");
        setBounds(100, 100, 400, 220);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
        gbl_contentPanel.rowHeights = new int[]{0, 0, 0};
        gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0};
        gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblNome = new JLabel("Nome:");
            GridBagConstraints gbc_lblNome = new GridBagConstraints();
            gbc_lblNome.insets = new Insets(0, 0, 10, 5);
            gbc_lblNome.anchor = GridBagConstraints.WEST;
            gbc_lblNome.gridx = 0;
            gbc_lblNome.gridy = 0;
            contentPanel.add(lblNome, gbc_lblNome);
        }
        {
            txtNome = new JTextField();
            GridBagConstraints gbc_txtNome = new GridBagConstraints();
            gbc_txtNome.gridwidth = 2;
            gbc_txtNome.insets = new Insets(0, 0, 10, 0);
            gbc_txtNome.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtNome.gridx = 1;
            gbc_txtNome.gridy = 0;
            contentPanel.add(txtNome, gbc_txtNome);
            txtNome.setColumns(10);
        }
        {
            JLabel lblApelidos = new JLabel("Apelidos:");
            GridBagConstraints gbc_lblApelidos = new GridBagConstraints();
            gbc_lblApelidos.anchor = GridBagConstraints.WEST;
            gbc_lblApelidos.insets = new Insets(0, 0, 10, 5);
            gbc_lblApelidos.gridx = 0;
            gbc_lblApelidos.gridy = 1;
            contentPanel.add(lblApelidos, gbc_lblApelidos);
        }
        {
            txtApelidos = new JTextField();
            GridBagConstraints gbc_txtApelidos = new GridBagConstraints();
            gbc_txtApelidos.gridwidth = 2;
            gbc_txtApelidos.insets = new Insets(0, 0, 10, 0);
            gbc_txtApelidos.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtApelidos.gridx = 1;
            gbc_txtApelidos.gridy = 1;
            contentPanel.add(txtApelidos, gbc_txtApelidos);
            txtApelidos.setColumns(10);
        }
        {
            JLabel lblProvincia = new JLabel("Provincia:");
            GridBagConstraints gbc_lblProvincia = new GridBagConstraints();
            gbc_lblProvincia.insets = new Insets(0, 0, 0, 5);
            gbc_lblProvincia.anchor = GridBagConstraints.WEST;
            gbc_lblProvincia.gridx = 0;
            gbc_lblProvincia.gridy = 2;
            contentPanel.add(lblProvincia, gbc_lblProvincia);
        }
        {
            txtProvincia = new JTextField();
            txtProvincia.setEditable(false);
            GridBagConstraints gbc_txtProvincia = new GridBagConstraints();
            gbc_txtProvincia.insets = new Insets(0, 0, 0, 5);
            gbc_txtProvincia.fill = GridBagConstraints.HORIZONTAL;
            gbc_txtProvincia.gridx = 1;
            gbc_txtProvincia.gridy = 2;
            contentPanel.add(txtProvincia, gbc_txtProvincia);
            txtProvincia.setColumns(10);
        }
        {
            JButton btnSeleccionarProvincia = new JButton("Seleccionar...");
            GridBagConstraints gbc_btnSeleccionarProvincia = new GridBagConstraints();
            gbc_btnSeleccionarProvincia.gridx = 2;
            gbc_btnSeleccionarProvincia.gridy = 2;
            contentPanel.add(btnSeleccionarProvincia, gbc_btnSeleccionarProvincia);

            btnSeleccionarProvincia.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    DlgProvincia dlg = new DlgProvincia(DlgDatosNovoUsuario.this);
                    dlg.setModal(true);
                    dlg.setLocationRelativeTo(DlgDatosNovoUsuario.this);
                    dlg.setVisible(true);

                    if (dlg.isAceptado()) {
                        String prov = dlg.getProvinciaSeleccionada();
                        if (prov != null) {
                            txtProvincia.setText(prov);
                        }
                    }
                }
            });
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("Aceptar");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (validarDatos()) {
                            usuarioCreado = new Usuario(
                                    DlgDatosNovoUsuario.this.id,
                                    txtNome.getText().trim(),
                                    txtApelidos.getText().trim(),
                                    txtProvincia.getText().trim());
                            aceptado = true;
                            dispose();
                        }
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

    private boolean validarDatos() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "O nome non pode estar baleiro",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            txtNome.requestFocus();
            return false;
        }
        if (txtApelidos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Os apelidos non poden estar baleiros",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            txtApelidos.requestFocus();
            return false;
        }
        if (txtProvincia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar unha provincia",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}
