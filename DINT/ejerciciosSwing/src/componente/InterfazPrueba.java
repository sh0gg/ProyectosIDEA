package componente;

import javax.swing.*;
import java.awt.*;

public class InterfazPrueba extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new InterfazPrueba().setVisible(true);
        });
    }

    public InterfazPrueba() {
        setTitle("Demo ListaDBR");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        ListaDBR lista = new ListaDBR();
        lista.setItems(new String[] {"Paula", "Gemma", "Anton", "Leire", "Pepe", "Jose", "Bartolomé"});
        lista.setColorSeleccionado3(new Color(255, 255, 0));
        lista.setColorSeleccionado2(new Color(128, 0, 255));
        lista.setColorSeleccionado1(new Color(0, 255, 64));

        
        // LISTENER DE CLICK
        
        lista.setSingleClickListener((item, index) -> {
            lista.cambiarColorItem(item);
        });

        // LISTENER DE DOBLE CLICK
        lista.setDoubleClickListener((item, index) -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar \"" + item + "\"?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (opcion == JOptionPane.YES_OPTION) {
                lista.removeItem(item);
            }
        });

        getContentPane().add(new JScrollPane(lista));
    }
}
