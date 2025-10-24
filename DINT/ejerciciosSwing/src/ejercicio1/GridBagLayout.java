package ejercicio1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GridBagLayout extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNome;
	private JTextField tfApe1;
	private JTextField tfApe2;
	private JTextField tfIdade;
	private JTextField tfFixo;
	private JTextField tfMobil;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GridBagLayout frame = new GridBagLayout();
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
	public GridBagLayout() {
		setTitle("Exemplo GridBagLayout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		java.awt.GridBagLayout gbl_contentPane = new java.awt.GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{34, 33, 85, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNome = new JLabel("Nome");
		GridBagConstraints gbc_lblNome = new GridBagConstraints();
		gbc_lblNome.anchor = GridBagConstraints.WEST;
		gbc_lblNome.insets = new Insets(0, 0, 5, 5);
		gbc_lblNome.gridx = 0;
		gbc_lblNome.gridy = 0;
		contentPane.add(lblNome, gbc_lblNome);
		
		tfNome = new JTextField();
		GridBagConstraints gbc_tfNome = new GridBagConstraints();
		gbc_tfNome.insets = new Insets(0, 0, 5, 5);
		gbc_tfNome.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfNome.gridx = 1;
		gbc_tfNome.gridy = 0;
		contentPane.add(tfNome, gbc_tfNome);
		tfNome.setColumns(10);
		
		JLabel lblApelido1 = new JLabel("Apelido 1");
		GridBagConstraints gbc_lblApelido1 = new GridBagConstraints();
		gbc_lblApelido1.anchor = GridBagConstraints.WEST;
		gbc_lblApelido1.insets = new Insets(0, 0, 5, 5);
		gbc_lblApelido1.gridx = 2;
		gbc_lblApelido1.gridy = 0;
		contentPane.add(lblApelido1, gbc_lblApelido1);
		
		tfApe1 = new JTextField();
		tfApe1.setColumns(10);
		GridBagConstraints gbc_tfApe1 = new GridBagConstraints();
		gbc_tfApe1.insets = new Insets(0, 0, 5, 5);
		gbc_tfApe1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfApe1.gridx = 3;
		gbc_tfApe1.gridy = 0;
		contentPane.add(tfApe1, gbc_tfApe1);
		
		JLabel lblApelido2 = new JLabel("Apelido 2");
		GridBagConstraints gbc_lblApelido2 = new GridBagConstraints();
		gbc_lblApelido2.anchor = GridBagConstraints.WEST;
		gbc_lblApelido2.insets = new Insets(0, 0, 5, 5);
		gbc_lblApelido2.gridx = 4;
		gbc_lblApelido2.gridy = 0;
		contentPane.add(lblApelido2, gbc_lblApelido2);
		
		tfApe2 = new JTextField();
		tfApe2.setColumns(10);
		GridBagConstraints gbc_tfApe2 = new GridBagConstraints();
		gbc_tfApe2.insets = new Insets(0, 0, 5, 5);
		gbc_tfApe2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfApe2.gridx = 5;
		gbc_tfApe2.gridy = 0;
		contentPane.add(tfApe2, gbc_tfApe2);
		
		JButton btnGardar = new JButton("Gardar");
		GridBagConstraints gbc_btnGardar = new GridBagConstraints();
		gbc_btnGardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGardar.insets = new Insets(0, 0, 5, 0);
		gbc_btnGardar.gridx = 6;
		gbc_btnGardar.gridy = 0;
		contentPane.add(btnGardar, gbc_btnGardar);
		
		JLabel lblObservacions = new JLabel("Observacións");
		GridBagConstraints gbc_lblObservacions = new GridBagConstraints();
		gbc_lblObservacions.anchor = GridBagConstraints.WEST;
		gbc_lblObservacions.insets = new Insets(0, 0, 5, 5);
		gbc_lblObservacions.gridx = 0;
		gbc_lblObservacions.gridy = 1;
		contentPane.add(lblObservacions, gbc_lblObservacions);
		
		JTextArea textAreaObserv = new JTextArea();
		GridBagConstraints gbc_textAreaObserv = new GridBagConstraints();
		gbc_textAreaObserv.gridheight = 2;
		gbc_textAreaObserv.gridwidth = 5;
		gbc_textAreaObserv.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaObserv.fill = GridBagConstraints.BOTH;
		gbc_textAreaObserv.gridx = 1;
		gbc_textAreaObserv.gridy = 1;
		contentPane.add(textAreaObserv, gbc_textAreaObserv);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnLimpar = new GridBagConstraints();
		gbc_btnLimpar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLimpar.anchor = GridBagConstraints.NORTH;
		gbc_btnLimpar.insets = new Insets(0, 0, 5, 0);
		gbc_btnLimpar.gridx = 6;
		gbc_btnLimpar.gridy = 1;
		contentPane.add(btnLimpar, gbc_btnLimpar);
		
		JButton btnPechar = new JButton("Pechar");
		GridBagConstraints gbc_btnPechar = new GridBagConstraints();
		gbc_btnPechar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnPechar.anchor = GridBagConstraints.NORTH;
		gbc_btnPechar.insets = new Insets(0, 0, 5, 0);
		gbc_btnPechar.gridx = 6;
		gbc_btnPechar.gridy = 2;
		contentPane.add(btnPechar, gbc_btnPechar);
		
		JLabel lblIdade = new JLabel("Idade");
		GridBagConstraints gbc_lblIdade = new GridBagConstraints();
		gbc_lblIdade.anchor = GridBagConstraints.WEST;
		gbc_lblIdade.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdade.gridx = 0;
		gbc_lblIdade.gridy = 3;
		contentPane.add(lblIdade, gbc_lblIdade);
		
		tfIdade = new JTextField();
		GridBagConstraints gbc_tfIdade = new GridBagConstraints();
		gbc_tfIdade.insets = new Insets(0, 0, 5, 5);
		gbc_tfIdade.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfIdade.gridx = 1;
		gbc_tfIdade.gridy = 3;
		contentPane.add(tfIdade, gbc_tfIdade);
		tfIdade.setColumns(10);
		
		JLabel lblTlfFix = new JLabel("Tlf. Fixo");
		GridBagConstraints gbc_lblTlfFix = new GridBagConstraints();
		gbc_lblTlfFix.anchor = GridBagConstraints.WEST;
		gbc_lblTlfFix.insets = new Insets(0, 0, 5, 5);
		gbc_lblTlfFix.gridx = 0;
		gbc_lblTlfFix.gridy = 4;
		contentPane.add(lblTlfFix, gbc_lblTlfFix);
		
		tfFixo = new JTextField();
		GridBagConstraints gbc_tfFixo = new GridBagConstraints();
		gbc_tfFixo.gridwidth = 5;
		gbc_tfFixo.insets = new Insets(0, 0, 5, 5);
		gbc_tfFixo.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfFixo.gridx = 1;
		gbc_tfFixo.gridy = 4;
		contentPane.add(tfFixo, gbc_tfFixo);
		tfFixo.setColumns(10);
		
		JLabel lblTlfMob = new JLabel("Tlf. Mobil");
		GridBagConstraints gbc_lblTlfMob = new GridBagConstraints();
		gbc_lblTlfMob.anchor = GridBagConstraints.WEST;
		gbc_lblTlfMob.insets = new Insets(0, 0, 0, 5);
		gbc_lblTlfMob.gridx = 0;
		gbc_lblTlfMob.gridy = 5;
		contentPane.add(lblTlfMob, gbc_lblTlfMob);
		
		tfMobil = new JTextField();
		tfMobil.setColumns(10);
		GridBagConstraints gbc_tfMobil = new GridBagConstraints();
		gbc_tfMobil.gridwidth = 5;
		gbc_tfMobil.insets = new Insets(0, 0, 0, 5);
		gbc_tfMobil.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfMobil.gridx = 1;
		gbc_tfMobil.gridy = 5;
		contentPane.add(tfMobil, gbc_tfMobil);

	}

}
