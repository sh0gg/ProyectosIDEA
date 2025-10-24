package ejercicio1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.FlowLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.ButtonGroup;

public class LayoutManagerFlowLayout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final Action action = new SwingAction();
	private JRadioButton rdbtnLtR;
	private JRadioButton rdbtnRtL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LayoutManagerFlowLayout frame = new LayoutManagerFlowLayout();
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
	public LayoutManagerFlowLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) contentPane.getLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btn1 = new JButton("Boton1");
		contentPane.add(btn1);
		
		JButton btn2 = new JButton("Boton2");
		contentPane.add(btn2);
		
		JButton btn3 = new JButton("Boton3");
		contentPane.add(btn3);
		
		JButton btn4 = new JButton("Boton4 ---- > MAS LARGO");
		contentPane.add(btn4);
		
		JButton btn5 = new JButton("Boton5");
		contentPane.add(btn5);
		
		rdbtnLtR = new JRadioButton("Izquierda a Derecha");
		buttonGroup.add(rdbtnLtR);
		contentPane.add(rdbtnLtR);

		rdbtnRtL = new JRadioButton("Derecha a Izquierda");
		buttonGroup.add(rdbtnRtL);
		contentPane.add(rdbtnRtL);
		
		JButton btnApply = new JButton("Aplicar orientacion");
		btnApply.setAction(action);
		contentPane.add(btnApply);

	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Aplicar orientacionsadsadasd");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			FlowLayout flowLayout = (FlowLayout) contentPane.getLayout();
		    
		    if (rdbtnLtR.isSelected()) {
		        flowLayout.setAlignment(FlowLayout.LEFT);
		    } else if (rdbtnRtL.isSelected()) {
		        flowLayout.setAlignment(FlowLayout.RIGHT);
		    }
		    
		    contentPane.revalidate();
		    contentPane.repaint();
		}
	}
}
