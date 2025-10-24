package ejercicio1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JCheckBox extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JCheckBox frame = new JCheckBox();
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
	public JCheckBox() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		javax.swing.JCheckBox chkAceptar = new javax.swing.JCheckBox("Do you accept?");
		GridBagConstraints gbc_chkAceptar = new GridBagConstraints();
		gbc_chkAceptar.gridwidth = 2;
		gbc_chkAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_chkAceptar.gridx = 1;
		gbc_chkAceptar.gridy = 1;
		contentPane.add(chkAceptar, gbc_chkAceptar);
		
		JButton btnAceptar = new JButton("Acept");
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 2;
		contentPane.add(btnAceptar, gbc_btnAceptar);
		
		JButton btnDeclinar = new JButton("Decline");
		GridBagConstraints gbc_btnDeclinar = new GridBagConstraints();
		gbc_btnDeclinar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDeclinar.insets = new Insets(0, 0, 5, 5);
		gbc_btnDeclinar.gridx = 2;
		gbc_btnDeclinar.gridy = 2;
		contentPane.add(btnDeclinar, gbc_btnDeclinar);
		
		JButton btnContinuar = new JButton("Continue");
		btnContinuar.setEnabled(false);
		GridBagConstraints gbc_btnContinuar = new GridBagConstraints();
		gbc_btnContinuar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnContinuar.insets = new Insets(0, 0, 5, 5);
		gbc_btnContinuar.gridwidth = 2;
		gbc_btnContinuar.gridx = 1;
		gbc_btnContinuar.gridy = 3;
		contentPane.add(btnContinuar, gbc_btnContinuar);
		
		chkAceptar.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if (chkAceptar.isSelected()) {
					btnContinuar.setEnabled(true);
				} else {
					btnContinuar.setEnabled(false);
				}
			}
		});
		
		btnDeclinar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnContinuar.setEnabled(false);
				chkAceptar.setSelected(false);
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnContinuar.setEnabled(true);
				chkAceptar.setSelected(true);
			}
		});
		
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

}
