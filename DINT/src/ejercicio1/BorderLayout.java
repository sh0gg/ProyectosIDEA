package ejercicio1;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class BorderLayout extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public BorderLayout() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 5, 20));
		
		JButton btnNorte = new JButton("1");
		add(btnNorte);
		
		JButton btnSur = new JButton("2");
		add(btnSur);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Combo1", "Combo2", "Combo3"}));
		comboBox.setPreferredSize(new Dimension(300, 20));
		add(comboBox);
		
		JButton btnOeste = new JButton("4");
		add(btnOeste);
		
		JComboBox comboBox2 = new JComboBox();
		comboBox2.setModel(new DefaultComboBoxModel(new String[] {"Combo2", "Combo3", "Combo4"}));
		add(comboBox2);

	}

}
