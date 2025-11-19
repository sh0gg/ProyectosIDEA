package dialogos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmXestorUsuarios extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNumeroUsuarios;
    private JButton btnNovoUsuario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmXestorUsuarios frame = new FrmXestorUsuarios();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public FrmXestorUsuarios() {
        setTitle("Xestor de usuarios");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 150);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel pCentro = new JPanel();
        contentPane.add(pCentro, BorderLayout.CENTER);
        pCentro.setLayout(new BorderLayout(0, 0));

        lblNumeroUsuarios = new JLabel("");
        lblNumeroUsuarios.setHorizontalAlignment(SwingConstants.CENTER);
        lblNumeroUsuarios.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pCentro.add(lblNumeroUsuarios, BorderLayout.CENTER);

        JPanel pSur = new JPanel();
        contentPane.add(pSur, BorderLayout.SOUTH);
        pSur.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnNovoUsuario = new JButton();
        pSur.add(btnNovoUsuario);

        // Listener botón "Novo usuario"
        btnNovoUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = AlmacenDeUsuarios.getSeguinteId();
                DlgDatosNovoUsuario dlg = new DlgDatosNovoUsuario(FrmXestorUsuarios.this, id);
                dlg.setModal(true);
                dlg.setLocationRelativeTo(FrmXestorUsuarios.this);
                dlg.setVisible(true);

                if (dlg.isAceptado()) {
                    Usuario u = dlg.getUsuarioCreado();
                    AlmacenDeUsuarios.engadirUsuario(u);
                    actualizarNumeroUsuarios();
                    actualizarTextoBotonNovoUsuario();
                }
            }
        });

        // Inicializamos textos
        actualizarNumeroUsuarios();
        actualizarTextoBotonNovoUsuario();
    }

    private void actualizarNumeroUsuarios() {
        lblNumeroUsuarios.setText("Número de usuarios rexistrados: " + AlmacenDeUsuarios.getNumeroUsuarios());
    }

    private void actualizarTextoBotonNovoUsuario() {
        int id = AlmacenDeUsuarios.getSeguinteId();
        btnNovoUsuario.setText("Novo usuario (ID-" + id + ")");
    }

}
