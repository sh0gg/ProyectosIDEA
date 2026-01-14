package componente;

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
import javax.swing.SwingConstants;

/**
 * Componente JList personalizado que permite asignar colores de fondo
 * individuales a cada elemento y detectar clic simple y doble clic
 * mediante listeners personalizados.
 */
public class ListaDBR extends JList<String> {

    private static final long serialVersionUID = 1L;

    // Colores asignados a cada elemento
    private Map<String, Color> itemColors = new HashMap<>();

    // Colores configurables para el cambio aleatorio
    private Color colorSeleccionado1 = Color.WHITE;
    private Color colorSeleccionado2 = Color.WHITE;
    private Color colorSeleccionado3 = Color.WHITE;

    // Lista interna de colores disponibles
    private List<Color> coloresDisponibles = new ArrayList<>();

    // Color por defecto de los elementos
    private Color defaultItemColor = Color.WHITE;

    // Generador aleatorio
    private Random random = new Random();

    // Listeners personalizados
    private ItemClickListener singleClickListener;
    private ItemClickListener doubleClickListener;

    // ======================
    // CONSTRUCTOR
    // ======================

    public ListaDBR() {
        super(new DefaultListModel<>());
        setCellRenderer(new ColorRenderer());
        actualizarColoresDisponibles();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index < 0) return;

                String item = getModel().getElementAt(index);

                if (e.getClickCount() == 1 && singleClickListener != null) {
                    singleClickListener.onItemClick(item, index);
                }

                if (e.getClickCount() == 2 && doubleClickListener != null) {
                    doubleClickListener.onItemClick(item, index);
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

    public ItemClickListener getSingleClickListener() {
        return singleClickListener;
    }

    public void setSingleClickListener(ItemClickListener singleClickListener) {
        this.singleClickListener = singleClickListener;
    }

    public ItemClickListener getDoubleClickListener() {
        return doubleClickListener;
    }

    public void setDoubleClickListener(ItemClickListener doubleClickListener) {
        this.doubleClickListener = doubleClickListener;
    }

    public Map<String, Color> getItemColors() {
        return itemColors;
    }

    public void setItemColors(Map<String, Color> itemColors) {
        this.itemColors = itemColors;
        repaint();
    }
    
    // ITEMS DE LA LISTA
    
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
    // MÉTODOS DE UTILIDAD
    // ======================

    public void addItem(String item) {
        ((DefaultListModel<String>) getModel()).addElement(item);
    }

    public void removeItem(String item) {
        ((DefaultListModel<String>) getModel()).removeElement(item);
        itemColors.remove(item);
        repaint();
    }

    public void cambiarColorItem(String item) {
        if (coloresDisponibles.isEmpty()) return;

        Color color = coloresDisponibles.get(
                random.nextInt(coloresDisponibles.size())
        );
        itemColors.put(item, color);
        repaint();
    }

    // ======================
    // MÉTODOS PRIVADOS
    // ======================

    private void actualizarColoresDisponibles() {
        coloresDisponibles.clear();
        coloresDisponibles.add(colorSeleccionado1);
        coloresDisponibles.add(colorSeleccionado2);
        coloresDisponibles.add(colorSeleccionado3);
    }

    // ======================
    // RENDERIZADO PERSONALIZADO
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

    // ======================
    // LISTENER PERSONALIZADO
    // ======================

    @FunctionalInterface
    public interface ItemClickListener {
        void onItemClick(String item, int index);
    }
}
