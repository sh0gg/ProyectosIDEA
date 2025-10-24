package ejercicio1;

import java.awt.EventQueue;
import javax.swing.JFrame;
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
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Contextual extends JFrame {
	
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
	public Contextual() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(getContentPane(), popupMenu);
		
		JMenu mnNewMenu = new JMenu("OPTIONS");
		popupMenu.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("WOLOLOOOO");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cortar madeira");
		mnNewMenu_2.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("BOOOOOOm");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmThisIsA = new JMenuItem("This is a menu item");
		mnNewMenu.add(mntmThisIsA);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenu mnNewMenu_1 = new JMenu("CLICK AQUI!!");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem(":3");
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JSeparator separator_1 = new JSeparator();
		mnNewMenu.add(separator_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("buh");
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("Hoy de comer");
		popupMenu.add(mnNewMenu_3);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem = new JRadioButtonMenuItem("Tillas");
		mnNewMenu_3.add(rdbtnmntmNewRadioItem);
		
		JRadioButtonMenuItem rdbtnmntmNewRadioItem_1 = new JRadioButtonMenuItem("Na");
		mnNewMenu_3.add(rdbtnmntmNewRadioItem_1);
		
		JMenu mnNewMenu_4 = new JMenu("NO LO ABRAS CARLOS");
		popupMenu.add(mnNewMenu_4);
		
		JMenu mnNewMenu_5 = new JMenu("Carlos es");
		mnNewMenu_4.add(mnNewMenu_5);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("Un guapeton");
		mnNewMenu_5.add(chckbxmntmNewCheckItem);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem_1 = new JCheckBoxMenuItem("Buena persona");
		mnNewMenu_5.add(chckbxmntmNewCheckItem_1);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem_2 = new JCheckBoxMenuItem("Un poco tonto");
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

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}

