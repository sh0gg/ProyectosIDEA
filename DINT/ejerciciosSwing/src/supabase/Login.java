package supabase;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.Action;

public class Login extends JDialog {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField tfMail;
    private JPasswordField pfContraseña;
    private final Action action = new SwingAction();
    private final Action action_1 = new SwingAction_1();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Login frame = new Login();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Login() {
        setTitle("SuperAplicación - Login");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 390, 268);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel pDatos = new JPanel();
        contentPane.add(pDatos, BorderLayout.CENTER);
        pDatos.setLayout(new BorderLayout(0, 0));

        JPanel pTitulo = new JPanel();
        pDatos.add(pTitulo, BorderLayout.NORTH);
        pTitulo.setLayout(new GridLayout(1, 0, 10, 10));

        JLabel lblTitulo = new JLabel("Inicio de sesión");
        lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        pTitulo.add(lblTitulo);

        JPanel pCajas = new JPanel();
        pDatos.add(pCajas, BorderLayout.CENTER);
        GridBagLayout gbl_pCajas = new GridBagLayout();
        gbl_pCajas.columnWidths = new int[]{0, 0, 0};
        gbl_pCajas.rowHeights = new int[]{0, 0, 0};
        gbl_pCajas.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
        gbl_pCajas.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        pCajas.setLayout(gbl_pCajas);

        JLabel lblMail = new JLabel("E-mail");
        GridBagConstraints gbc_lblMail = new GridBagConstraints();
        gbc_lblMail.anchor = GridBagConstraints.EAST;
        gbc_lblMail.insets = new Insets(50, 35, 20, 5);
        gbc_lblMail.gridx = 0;
        gbc_lblMail.gridy = 0;
        pCajas.add(lblMail, gbc_lblMail);

        tfMail = new JTextField();
        GridBagConstraints gbc_tfMail = new GridBagConstraints();
        gbc_tfMail.insets = new Insets(50, 0, 20, 25);
        gbc_tfMail.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfMail.gridx = 1;
        gbc_tfMail.gridy = 0;
        pCajas.add(tfMail, gbc_tfMail);
        tfMail.setColumns(10);

        JLabel lblContraseña = new JLabel("Contraseña");
        GridBagConstraints gbc_lblContraseña = new GridBagConstraints();
        gbc_lblContraseña.anchor = GridBagConstraints.EAST;
        gbc_lblContraseña.insets = new Insets(0, 0, 0, 5);
        gbc_lblContraseña.gridx = 0;
        gbc_lblContraseña.gridy = 1;
        pCajas.add(lblContraseña, gbc_lblContraseña);

        pfContraseña = new JPasswordField();
        GridBagConstraints gbc_pfContraseña = new GridBagConstraints();
        gbc_pfContraseña.insets = new Insets(0, 0, 0, 25);
        gbc_pfContraseña.fill = GridBagConstraints.HORIZONTAL;
        gbc_pfContraseña.gridx = 1;
        gbc_pfContraseña.gridy = 1;
        pCajas.add(pfContraseña, gbc_pfContraseña);

        JPanel pBoton = new JPanel();
        contentPane.add(pBoton, BorderLayout.SOUTH);
        pBoton.setLayout(new GridLayout(0, 4, 5, 10));

        JLabel label = new JLabel("");
        pBoton.add(label);

        JLabel label_1 = new JLabel("");
        pBoton.add(label_1);

        JLabel label_2 = new JLabel("");
        pBoton.add(label_2);

        JButton bEnviar = new JButton("Enviar");
        bEnviar.setAction(action);
        pBoton.add(bEnviar);


        JLabel label_3 = new JLabel("");
        pBoton.add(label_3);

        JLabel label_4 = new JLabel("");
        pBoton.add(label_4);

        JLabel lblRegistro = new JLabel("¿No tienes cuenta?");
        lblRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
        pBoton.add(lblRegistro);

        JButton bRegistro = new JButton("Regístrate");
        bRegistro.setAction(action_1);
        pBoton.add(bRegistro);
    }

    private String extraerCampoJson(String json, String campo) {
        if (json == null) return null;
        String pattern = "\"" + campo + "\":\"";
        int index = json.indexOf(pattern);
        if (index == -1) return null;
        int start = index + pattern.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return null;
        return json.substring(start, end);
    }

    private class SwingAction extends AbstractAction {
        public SwingAction() {
            putValue(NAME, "Enviar");
            putValue(SHORT_DESCRIPTION, "Comprueba el login y salta el dialogo");
        }
        public void actionPerformed(ActionEvent e) {
            try {
                String contraseña = new String(pfContraseña.getPassword());
                System.out.println(contraseña);
                String loginResult = SupabaseAuth.login(tfMail.getText(), new String(pfContraseña.getPassword()));
                System.out.println(loginResult);
                if (loginResult.contains("access_token")) {
                    DialogoLogin dialog = new DialogoLogin("¡Login correcto!");
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                    String accessToken = extraerCampoJson(loginResult, "access_token");
                    if (accessToken != null && !accessToken.isEmpty()) {
                        InterfazTrabajadores frame = new InterfazTrabajadores();
                        frame.setSesion(tfMail.getText().trim(), accessToken);
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                        dispose();
                    } else {
                        DialogoLogin dialogError = new DialogoLogin("Login correcto, pero no se pudo leer el token.");
                        dialogError.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialogError.setVisible(true);
                    }
                } else if (loginResult.toLowerCase().contains("error")) {
                    if (loginResult.contains("\"code\":400")) {
                        DialogoLogin dialog = new DialogoLogin("¡Login incorrecto!");
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                    }
                    if (loginResult.contains("500")) {
                        DialogoLogin dialog = new DialogoLogin("¡Sin acceso al servicio de login!");
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setVisible(true);
                    }
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private class SwingAction_1 extends AbstractAction {
        public SwingAction_1() {
            putValue(NAME, "Regístrate");
            putValue(SHORT_DESCRIPTION, "Abre el frame del registro");
        }
        public void actionPerformed(ActionEvent e) {
            Registro frame = new Registro();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}
