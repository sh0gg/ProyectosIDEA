import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * Panel compuesto (Tarea 2):
 * - Integra ListaDBRv2 (David), MiComponenteX (Pedro) y BotonEspectacular (Yago).
 * - Idea: "Lista segura" => hasta completar verificación (BotonEspectacular),
 *   no se puede añadir ni interactuar con la lista.
 */
public class PanelListaSegura extends JPanel {

    private static final long serialVersionUID = 1L;

    // Componentes integrados
    private final ListaDBRv2 lista = new ListaDBRv2();
    private final MiComponenteX btnAgregar = new MiComponenteX();
    private final BotonEspectacular btnVerificar = new BotonEspectacular();

    // Swing estándar
    private final JTextField txtNuevo = new JTextField(18);
    private final JLabel lblTitulo = new JLabel("PanelListaSegura");
    private final JLabel lblEstado = new JLabel("Bloqueado");

    // ==========
    // Propiedades del panel (mínimo 2)
    // ==========
    private boolean seguridadActiva = true;
    private int maxAltas = 5;
    private String titulo = "PanelListaSegura";

    public PanelListaSegura() {
        super(new BorderLayout(8, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // ===== NORTH (título + estado) =====
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        top.add(lblTitulo);
        top.add(new JLabel(" | Estado:"));
        top.add(lblEstado);
        add(top, BorderLayout.NORTH);

        // ===== CENTER (lista) =====
        add(new JScrollPane(lista), BorderLayout.CENTER);

        // ===== SOUTH (controles) =====
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        btnAgregar.setText("Agregar");
        bottom.add(new JLabel("Nuevo:"));
        bottom.add(txtNuevo);
        bottom.add(btnAgregar);
        bottom.add(new JLabel(" | Verificación:"));
        bottom.add(btnVerificar);
        add(bottom, BorderLayout.SOUTH);

        // Config inicial
        aplicarConfiguracion();

        // ===== Integración / Interacción =====

        // 1) Botón de Yago: revela letras en cada click (MouseListener).
        //    Para saber si está completo SIN volver a llamar a completarVerificacion(),
        //    comprobamos si ya no quedan '-' en el texto.
        btnVerificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // OJO: el propio BotonEspectacular ya hace completarVerificacion()
                // en su mouseClicked interno. Aquí solo comprobamos el resultado.
                if (!seguridadActiva) return;

                String t = btnVerificar.getText();
                if (t != null && !t.contains("-")) {
                    setSeguridadActiva(false);
                } else {
                    lblEstado.setText("Verificando...");
                }
            }
        });

        // 2) Botón de Pedro: añade items, pero solo si no está bloqueado
        btnAgregar.addActionListener(e -> {
            if (seguridadActiva) return;

            String txt = txtNuevo.getText();
            if (txt == null) return;

            txt = txt.trim();
            if (txt.isEmpty()) return;

            lista.addItem(txt);
            txtNuevo.setText("");
        });
    }

    private void aplicarConfiguracion() {
        // Título
        lblTitulo.setText(titulo);

        // Límite de altas -> se lo pasamos al componente de Pedro
        btnAgregar.setMaxClicks(maxAltas);

        // Bloqueo/desbloqueo
        boolean bloqueado = seguridadActiva;

        lista.setEnabled(!bloqueado);
        txtNuevo.setEnabled(!bloqueado);
        btnAgregar.setEnabled(!bloqueado);

        lblEstado.setText(bloqueado ? "Bloqueado" : "Desbloqueado");
        revalidate();
        repaint();
    }

    // ======================
    // Getters/Setters (propiedades públicas para WindowBuilder)
    // ======================

    public boolean isSeguridadActiva() {
        return seguridadActiva;
    }

    public void setSeguridadActiva(boolean seguridadActiva) {
        this.seguridadActiva = seguridadActiva;
        aplicarConfiguracion();
    }

    public int getMaxAltas() {
        return maxAltas;
    }

    public void setMaxAltas(int maxAltas) {
        if (maxAltas < 1) maxAltas = 1;
        this.maxAltas = maxAltas;
        aplicarConfiguracion();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = (titulo == null) ? "" : titulo;
        aplicarConfiguracion();
    }

    // Atajos para reutilización (delegan en ListaDBRv2)
    public String[] getItems() {
        return lista.getItems();
    }

    public void setItems(String[] items) {
        lista.setItems(items);
    }

    // Si quieres acceder a los componentes desde fuera (opcional, útil para pruebas)
    public ListaDBRv2 getLista() {
        return lista;
    }

    public MiComponenteX getBtnAgregar() {
        return btnAgregar;
    }

    public BotonEspectacular getBtnVerificar() {
        return btnVerificar;
    }
}
