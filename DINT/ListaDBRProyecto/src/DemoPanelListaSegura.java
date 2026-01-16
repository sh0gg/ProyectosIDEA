import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class DemoPanelListaSegura {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                // Look&Feel por defecto del sistema (opcional)
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }

            JFrame frame = new JFrame("Demo - PanelListaSegura");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            PanelListaSegura panel = new PanelListaSegura();
            panel.setTitulo("Lista segura (Tarea 2)");
            panel.setMaxAltas(5);
            panel.setSeguridadActiva(true);
            panel.setItems(new String[] { "Paula", "Gemma", "Anton", "David" });

            frame.setContentPane(panel);
            frame.setSize(700, 420);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
