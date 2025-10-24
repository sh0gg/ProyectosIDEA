package ejercicio1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Desplegable extends JFrame {
	
	private static String resultadoComida = "";
	private static String atributosCarlos;
	private static boolean isChecked1 = false;
	private static boolean isChecked2 = false;
	private static boolean isChecked3 = false;	

	private static final long serialVersionUID = 1L;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Desplegable frame = new Desplegable();
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
	public Desplegable() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("");
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("OPTIONS");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("WOLOLOOOO");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cortar madeira");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "DESCANSAR");
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("BOOOOOOm");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "booooooooom");
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmThisIsA = new JMenuItem("This is a menu item");
		mnNewMenu.add(mntmThisIsA);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenu mnNewMenu_1 = new JMenu("CLICK AQUI!!");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem(":3");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Hoy vas a comer: " + resultadoComida + " y Carlos es: " + atrCarlos(atributosCarlos) );
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("buh");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("Hoy de comer");
		menuBar.add(mnNewMenu_3);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Tillas");
		rdbtnmntmNewRadioItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultadoComida = "";
				resultadoComida = rdbtnmntmNewRadioItem.getText();
			}
		});
		buttonGroup.add(rdbtnmntmNewRadioItem);
		mnNewMenu_3.add(rdbtnmntmNewRadioItem);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Na");
		rdbtnmntmNewRadioItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultadoComida = "";
				resultadoComida = rdbtnmntmNewRadioItem_1.getText();
			}
		});
		buttonGroup.add(rdbtnmntmNewRadioItem_1);
		mnNewMenu_3.add(rdbtnmntmNewRadioItem_1);
		
		JMenu mnNewMenu_4 = new JMenu("NO LO ABRAS CARLOS");
		menuBar.add(mnNewMenu_4);
		
		JMenu mnNewMenu_5 = new JMenu("Carlos es");
		mnNewMenu_4.add(mnNewMenu_5);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Un guapeton");
		chckbxmntmNewCheckItem.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isChecked1) {
					isChecked1 = true;
				} else { isChecked1 = false; }
			}
		});
		mnNewMenu_5.add(chckbxmntmNewCheckItem);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem_1 = new JCheckBoxMenuItem("Buena persona");
		chckbxmntmNewCheckItem_1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isChecked2) {
					isChecked2 = true;
				} else { isChecked2 = false; }
			}
		});
		mnNewMenu_5.add(chckbxmntmNewCheckItem_1);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem_2 = new JCheckBoxMenuItem("Un poco tonto");
		chckbxmntmNewCheckItem_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (!isChecked3) {
					isChecked3 = true;
				} else { isChecked3 = false; }
			}
		});
		mnNewMenu_5.add(chckbxmntmNewCheckItem_2);

	}
	
	static String atrCarlos(String str) {
		StringBuilder sb = new StringBuilder();
		
		if (isChecked1) {
			sb.append(" Guapo");
		}
		if (isChecked2) {
			sb.append(" Buena Persona");
		}
		if (isChecked3) {
			sb.append(" Un poco tonto");
		}
		
		return str = sb.toString();
	}

}
