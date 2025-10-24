package ejercicio6;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tarefa6DavidBesada extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfPeza;
	private JTextField tfPrezoUd;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tarefa6DavidBesada frame = new Tarefa6DavidBesada();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tarefa6DavidBesada() {
		setTitle("Compoñentes básicos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{227, 218, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel pInput = new JPanel();
		GridBagConstraints gbc_pInput = new GridBagConstraints();
		gbc_pInput.insets = new Insets(0, 0, 5, 5);
		gbc_pInput.fill = GridBagConstraints.BOTH;
		gbc_pInput.gridx = 0;
		gbc_pInput.gridy = 0;
		contentPane.add(pInput, gbc_pInput);
		GridBagLayout gbl_pInput = new GridBagLayout();
		gbl_pInput.columnWidths = new int[]{81, 48, 64, 0};
		gbl_pInput.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_pInput.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_pInput.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		pInput.setLayout(gbl_pInput);
		
		JLabel lblPeza = new JLabel("Peza");
		GridBagConstraints gbc_lblPeza = new GridBagConstraints();
		gbc_lblPeza.anchor = GridBagConstraints.WEST;
		gbc_lblPeza.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeza.gridx = 0;
		gbc_lblPeza.gridy = 0;
		pInput.add(lblPeza, gbc_lblPeza);
		
		tfPeza = new JTextField();
		GridBagConstraints gbc_tfPeza = new GridBagConstraints();
		gbc_tfPeza.gridwidth = 2;
		gbc_tfPeza.insets = new Insets(0, 0, 5, 5);
		gbc_tfPeza.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPeza.gridx = 1;
		gbc_tfPeza.gridy = 0;
		pInput.add(tfPeza, gbc_tfPeza);
		tfPeza.setColumns(10);
		
		JLabel lblPrezoUd = new JLabel("Prezo/Unidade");
		GridBagConstraints gbc_lblPrezoUd = new GridBagConstraints();
		gbc_lblPrezoUd.anchor = GridBagConstraints.WEST;
		gbc_lblPrezoUd.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrezoUd.gridx = 0;
		gbc_lblPrezoUd.gridy = 1;
		pInput.add(lblPrezoUd, gbc_lblPrezoUd);
		
		tfPrezoUd = new JTextField();
		GridBagConstraints gbc_tfPrezoUd = new GridBagConstraints();
		gbc_tfPrezoUd.insets = new Insets(0, 0, 5, 5);
		gbc_tfPrezoUd.fill = GridBagConstraints.HORIZONTAL;
		gbc_tfPrezoUd.gridx = 1;
		gbc_tfPrezoUd.gridy = 1;
		pInput.add(tfPrezoUd, gbc_tfPrezoUd);
		tfPrezoUd.setColumns(10);
		
		JPanel pUnidadesAdq = new JPanel();
		pUnidadesAdq.setAlignmentY(Component.TOP_ALIGNMENT);
		pUnidadesAdq.setBorder(new TitledBorder(null, "Unidades adquiridas", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pUnidadesAdq = new GridBagConstraints();
		gbc_pUnidadesAdq.gridwidth = 3;
		gbc_pUnidadesAdq.insets = new Insets(0, 0, 5, 5);
		gbc_pUnidadesAdq.fill = GridBagConstraints.BOTH;
		gbc_pUnidadesAdq.gridx = 0;
		gbc_pUnidadesAdq.gridy = 2;
		pInput.add(pUnidadesAdq, gbc_pUnidadesAdq);
		pUnidadesAdq.setLayout(new BoxLayout(pUnidadesAdq, BoxLayout.Y_AXIS));
		
		JRadioButton rb1 = new JRadioButton("1-10");
		buttonGroup.add(rb1);
		pUnidadesAdq.add(rb1);
		
		JRadioButton rb2 = new JRadioButton("15-50 (Desconto 5%)");
		buttonGroup.add(rb2);
		pUnidadesAdq.add(rb2);
		
		JRadioButton rb3 = new JRadioButton("> 50 (Desconto 15%)");
		rb3.setAlignmentY(10.0f);
		buttonGroup.add(rb3);
		pUnidadesAdq.add(rb3);
		
		JPanel pDescontosAd = new JPanel();
		pDescontosAd.setBorder(new TitledBorder(null, "Descontos adicionais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pDescontosAd = new GridBagConstraints();
		gbc_pDescontosAd.insets = new Insets(0, 0, 0, 5);
		gbc_pDescontosAd.gridwidth = 3;
		gbc_pDescontosAd.fill = GridBagConstraints.BOTH;
		gbc_pDescontosAd.gridx = 0;
		gbc_pDescontosAd.gridy = 3;
		pInput.add(pDescontosAd, gbc_pDescontosAd);
		pDescontosAd.setLayout(new BoxLayout(pDescontosAd, BoxLayout.Y_AXIS));
		
		JCheckBox cb1 = new JCheckBox("Empregado (Desconto 10%)");
		pDescontosAd.add(cb1);
		
		JCheckBox cb2 = new JCheckBox("Cliente premium (Desconto 5%)");
		pDescontosAd.add(cb2);
		
		JPanel pOutput = new JPanel();
		pOutput.setBorder(new TitledBorder(null, "Resultado do c\u00E1lculo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pOutput = new GridBagConstraints();
		gbc_pOutput.insets = new Insets(0, 0, 5, 0);
		gbc_pOutput.fill = GridBagConstraints.BOTH;
		gbc_pOutput.gridx = 1;
		gbc_pOutput.gridy = 0;
		contentPane.add(pOutput, gbc_pOutput);
		GridBagLayout gbl_pOutput = new GridBagLayout();
		gbl_pOutput.columnWidths = new int[]{206, 0};
		gbl_pOutput.rowHeights = new int[]{201, 0};
		gbl_pOutput.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pOutput.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pOutput.setLayout(gbl_pOutput);
		
		JTextArea textArea = new JTextArea();
		textArea.setBackground(new Color(255, 255, 128));
		textArea.setColumns(1);
		textArea.setRows(1);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(10, 10, 10, 10);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		pOutput.add(textArea, gbc_textArea);
		
		JPanel pButtons = new JPanel();
		GridBagConstraints gbc_pButtons = new GridBagConstraints();
		gbc_pButtons.gridwidth = 2;
		gbc_pButtons.insets = new Insets(0, 0, 0, 5);
		gbc_pButtons.fill = GridBagConstraints.BOTH;
		gbc_pButtons.gridx = 0;
		gbc_pButtons.gridy = 1;
		contentPane.add(pButtons, gbc_pButtons);
		GridBagLayout gbl_pButtons = new GridBagLayout();
		gbl_pButtons.columnWidths = new int[]{220, 220, 0};
		gbl_pButtons.rowHeights = new int[]{23, 0};
		gbl_pButtons.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_pButtons.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pButtons.setLayout(gbl_pButtons);
		
		JButton bReset = new JButton("Novo cálculo");
		bReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPeza.setText("");
				tfPrezoUd.setText("");
				buttonGroup.clearSelection();
				cb1.setSelected(false);
				cb2.setSelected(false);
				textArea.setText("");
			}
		});
		GridBagConstraints gbc_bReset = new GridBagConstraints();
		gbc_bReset.fill = GridBagConstraints.BOTH;
		gbc_bReset.insets = new Insets(0, 100, 0, 5);
		gbc_bReset.gridx = 0;
		gbc_bReset.gridy = 0;
		pButtons.add(bReset, gbc_bReset);
		
		JButton bSend = new JButton("Calcular");
		bSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = tfPeza.getText().trim();
				String prezoStr = tfPrezoUd.getText().trim();

				if (nome.isEmpty()) {
					textArea.setText("Erro: o campo 'Peza' é obrigatorio.");
					return;
				}

				if (prezoStr.isEmpty()) {
					textArea.setText("Erro: o campo 'Prezo/Unidade' é obrigatorio.");
					return;
				}

				boolean soloDigitos = prezoStr.chars().allMatch(Character::isDigit);
				if (!soloDigitos) {
					textArea.setText("Erro: 'Prezo/Unidade' debe conter só números enteiros.");
					return;
				}

				if (prezoStr.length() > 7) {
					textArea.setText("Erro: 'Prezo/Unidade' non pode superar 7 caracteres.");
					return;
				}

				if (buttonGroup.getSelection() == null) {
					textArea.setText("Erro: escolle un tramo de 'Unidades adquiridas'.");
					return;
				}

				int prezoUnidade = Integer.parseInt(prezoStr);

				boolean isRb1Selected = rb1.isSelected();
		        boolean isRb2Selected = rb2.isSelected();
		        boolean isRb3Selected = rb3.isSelected();

		        int descontoRadio = 0;
		        if (isRb1Selected) {
		        	descontoRadio = 0;
		        } else if (isRb2Selected) {
		        	descontoRadio = 5;
		        } else if (isRb3Selected) {
		        	descontoRadio = 15;
		        }

		        int descontoChecks = 0;
		        if (cb1.isSelected()) descontoChecks += 10; // empregado
		        if (cb2.isSelected()) descontoChecks += 5;  // premium

		        double descontoTotalPct = descontoRadio + descontoChecks;
		        double prezoFinal = prezoUnidade * (1 - (descontoTotalPct / 100.0));

		        StringBuilder sb = new StringBuilder();
		        sb.append("Peza : ").append(nome).append("\n");
		        sb.append("Prezo/Unidade sen desconto: ").append(prezoUnidade).append("\n\n");

		        if (descontoRadio == 0 && descontoChecks == 0) {
		        	sb.append("Non hai descontos aplicables sobre o prezo final");
		        } else {
		        	sb.append("Descontos aplicados:\n");
		        	sb.append("-----------------------------------");
		        	if (descontoRadio != 0) {
		        		sb.append("\nDesconto por unidades adquiridas: ").append(descontoRadio).append("%");
		        	}
		        	if (descontoChecks != 0) {
		        		sb.append("\nDescontos adicionais: ").append(descontoChecks).append("%");
		        	}
		        	sb.append("\nDesconto total sobre o precio final: ")
		        		.append((int)descontoTotalPct).append("%")
		        		.append("\nPrezo con desconto: ").append(String.format("%.2f", prezoFinal));
		        }

		        textArea.setText(sb.toString());
			}
		});
		GridBagConstraints gbc_bSend = new GridBagConstraints();
		gbc_bSend.anchor = GridBagConstraints.EAST;
		gbc_bSend.insets = new Insets(0, 5, 0, 100);
		gbc_bSend.fill = GridBagConstraints.BOTH;
		gbc_bSend.gridx = 1;
		gbc_bSend.gridy = 0;
		pButtons.add(bSend, gbc_bSend);
	}
}