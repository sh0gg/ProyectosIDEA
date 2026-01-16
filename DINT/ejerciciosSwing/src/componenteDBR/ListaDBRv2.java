package componenteDBR;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ListaDBRv2 extends JList<String> {

    private static final long serialVersionUID = 1L;

    // Colores por item
    private Map<String, Color> itemColors = new HashMap<>();

    // Colores configurables
    private Color colorSeleccionado1 = Color.WHITE;
    private Color colorSeleccionado2 = Color.WHITE;
    private Color colorSeleccionado3 = Color.WHITE;

    private List<Color> coloresDisponibles = new ArrayList<>();
    private Color defaultItemColor = Color.WHITE;

    private Random random = new Random();

    // ======================
    // CONSTRUCTOR
    // ======================

    public ListaDBRv2() {
        super(new DefaultListModel<>());

        if (isDesignTime()) {
            return;
        }

        setCellRenderer(new ColorRenderer());
        actualizarColoresDisponibles();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index < 0) return;

                String item = getModel().getElementAt(index);

                // CLICK SIMPLE → cambiar color
                if (e.getClickCount() == 1) {
                    cambiarColorItem(item);
                }

                // DOBLE CLICK → confirmar y eliminar
                if (e.getClickCount() == 2) {
                    int opcion = JOptionPane.showConfirmDialog(
                            ListaDBRv2.this,
                            "¿Eliminar \"" + item + "\"?",
                            "Confirmar",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (opcion == JOptionPane.YES_OPTION) {
                        removeItem(item);
                    }
                }
            }
        });
    }

    // ======================
    // PROPIEDADES
    // ======================

    public Color getDefaultItemColor() {
        return defaultItemColor;
    }

    public void setDefaultItemColor(Color defaultItemColor) {
        this.defaultItemColor = defaultItemColor;
        repaint();
    }

    public Color getColorSeleccionado1() {
        return colorSeleccionado1;
    }

    public void setColorSeleccionado1(Color c) {
        this.colorSeleccionado1 = c;
        actualizarColoresDisponibles();
    }

    public Color getColorSeleccionado2() {
        return colorSeleccionado2;
    }

    public void setColorSeleccionado2(Color c) {
        this.colorSeleccionado2 = c;
        actualizarColoresDisponibles();
    }

    public Color getColorSeleccionado3() {
        return colorSeleccionado3;
    }

    public void setColorSeleccionado3(Color c) {
        this.colorSeleccionado3 = c;
        actualizarColoresDisponibles();
    }

    // ======================
    // ITEMS
    // ======================

    public void addItem(String item) {
        ((DefaultListModel<String>) getModel()).addElement(item);
    }

    public void removeItem(String item) {
        ((DefaultListModel<String>) getModel()).removeElement(item);
        itemColors.remove(item);
        repaint();
    }

    public String[] getItems() {
        DefaultListModel<String> model = (DefaultListModel<String>) getModel();
        String[] items = new String[model.size()];
        model.copyInto(items);
        return items;
    }

    public void setItems(String[] items) {
        DefaultListModel<String> model = (DefaultListModel<String>) getModel();
        model.clear();
        itemColors.clear();

        if (items != null) {
            for (String item : items) {
                model.addElement(item);
            }
        }
        repaint();
    }

    // ======================
    // LÓGICA INTERNA
    // ======================

    private void cambiarColorItem(String item) {
        if (coloresDisponibles.isEmpty()) return;

        Color color = coloresDisponibles.get(
                random.nextInt(coloresDisponibles.size())
        );
        itemColors.put(item, color);
        repaint();
    }

    private void actualizarColoresDisponibles() {
        coloresDisponibles.clear();
        coloresDisponibles.add(colorSeleccionado1);
        coloresDisponibles.add(colorSeleccionado2);
        coloresDisponibles.add(colorSeleccionado3);
    }

    private static boolean isDesignTime() {
        try {
            return java.beans.Beans.isDesignTime();
        } catch (Throwable t) {
            return false;
        }
    }

    // ======================
    // RENDERER
    // ======================

    private class ColorRenderer extends DefaultListCellRenderer {

        private static final long serialVersionUID = 1L;

        @Override
        public Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {

            JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);

            label.setOpaque(true);
            label.setHorizontalAlignment(SwingConstants.CENTER);

            Color fondo = itemColors.getOrDefault(
                    value.toString(),
                    defaultItemColor
            );

            label.setBackground(isSelected
                    ? list.getSelectionBackground()
                    : fondo);

            return label;
        }
    }
}
