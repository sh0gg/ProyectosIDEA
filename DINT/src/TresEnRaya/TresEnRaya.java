package tresenraya;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class TresEnRaya extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    // VARIABLES, CONSTANTES Y METODOS QUE VOY A USAR
    
    // bool para el cambio de turno
    static boolean turno = false;
    
    // label con el turno
    static JLabel lblTurno = new JLabel("Es el turno de: " + uActual(turno));

    
    // botones -- ESTO ESTÁ REFACTORIZADO 
    /* Antes tenía btn00, btn01, btn02... y era un asco tener que trabajar con ellos,
     * por lo que hice dos matrices, una de botones y otra para el tablero y saber si han sido pulsados o no. */
    static JButton[][] botones = new JButton[3][3];

    // método para establecer que esta jugando uno u otro
    static String usuarioActual = "";

    static String uActual(boolean t) {
        if (!t) {
            return "Usuario 1";
        } else {
            return "Usuario 2";
        }
    }

    // matriz para saber si se ha pulsado un boton -- 0=vacio, 1=usuario1, 2=usuario2
    static int[][] tablero = new int[3][3];

    // la partida se va a acabar cuando todos los botones se hayan pulsado y nadie gane (empate)
    // o cuando se cumpla que tengan el mismo valor 3 botones en una diagonal, horizontal o vertical.
    
    public static boolean haGanado() {
        // comprobar filas y columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] != 0 && tablero[i][0] == tablero[i][1] && tablero[i][1] == tablero[i][2])
                return true;
            if (tablero[0][i] != 0 && tablero[0][i] == tablero[1][i] && tablero[1][i] == tablero[2][i])
                return true;
        }
        // comprobar diagonales
        if (tablero[0][0] != 0 && tablero[0][0] == tablero[1][1] && tablero[1][1] == tablero[2][2])
            return true;
        if (tablero[0][2] != 0 && tablero[0][2] == tablero[1][1] && tablero[1][1] == tablero[2][0])
            return true;

        return false;
    }
    
    // con esto comprobamos que no quede ningun boton pulsado y si hay empate
    public static boolean empate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == 0) {
                    return false; // queda alguna casilla vacía
                }
            }
        }
        return true;
    }
    
    //metodo para reinciar
    private void reiniciarPartida() {
        turno = false;
        lblTurno.setText("Es el turno de: " + uActual(turno));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = 0;
                botones[i][j].setText("-");
                botones[i][j].setBackground(null);
                botones[i][j].setEnabled(true);
            }
        }
    }

    // extra: bloquear las casillas completamente tras termianr la partida
    private void bloquearTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botones[i][j].setEnabled(false);
            }
        }
    }


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TresEnRaya frame = new TresEnRaya();
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
    public TresEnRaya() {
        setTitle("TRES EN RAYA - DAVID BESADA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);

        JMenuBar mbOpciones = new JMenuBar();
        setJMenuBar(mbOpciones);

        JMenu mnOpciones = new JMenu("Opciones");
        mbOpciones.add(mnOpciones);

        JMenuItem mntmReset = new JMenuItem("Reiniciar partida");
        mntmReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciarPartida();
            }
        });
        mntmReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        mnOpciones.add(mntmReset);

        JMenuItem mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mntmSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        mnOpciones.add(mntmSalir);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panelTurnos = new JPanel();
        contentPane.add(panelTurnos, BorderLayout.NORTH);
        panelTurnos.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblTurno.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
        panelTurnos.add(lblTurno);

        JPanel panelJuego = new JPanel();
        contentPane.add(panelJuego, BorderLayout.CENTER);
        GridBagLayout gbl_panelJuego = new GridBagLayout();
        gbl_panelJuego.columnWidths = new int[] {125, 125, 125};
        gbl_panelJuego.rowHeights = new int[] {125, 125, 125};
        gbl_panelJuego.columnWeights = new double[]{0.0, 0.0, 0.0};
        gbl_panelJuego.rowWeights = new double[]{0.0, 0.0, 0.0};
        panelJuego.setLayout(gbl_panelJuego);

        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                botones[fila][col] = new JButton("-");
                final int x = fila;
                final int y = col;

                botones[fila][col].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (tablero[x][y] == 0) {
                            tablero[x][y] = turno ? 2 : 1;
                            botones[x][y].setText(turno ? "2" : "1");
                            botones[x][y].setBackground(turno ? Color.pink : Color.cyan);
                            turno = !turno;
                            lblTurno.setText("Es el turno de: " + uActual(turno));
                            if (haGanado()) {
                                lblTurno.setText("¡" + uActual(!turno) + " ha ganado!");
                                bloquearTablero();
                            } else if (empate()) {
                                lblTurno.setText("¡Empate!");
                            }
                        } else {
                            lblTurno.setText("¡Esa casilla ya está usada!");
                        }
                    }
                });

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.BOTH;
                gbc.insets = new Insets(0, 0, 5, 5);
                gbc.gridx = col;
                gbc.gridy = fila;
                panelJuego.add(botones[fila][col], gbc);
            }
        }

    }
}
