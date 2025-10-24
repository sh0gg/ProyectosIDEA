package Calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {
    private JPanel panel1;
    private JLabel resultados;
    private JButton sumar, restar, multiplicar, division, clear, equals;
    private JButton[] numeros;

    private StringBuilder expresion = new StringBuilder();

    public Calculadora() {
        setTitle("Calculadora");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(5,5));
        resultados = new JLabel("0");
        resultados.setAlignmentX(Component.RIGHT_ALIGNMENT);
        resultados.setFont(new Font("Consolas", Font.PLAIN, 24));
        resultados.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        panel1.add(resultados, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new GridLayout(4,4,5,5));

        numeros = new JButton[10];
        for (int i = 0; i <= 9; i++) {
            numeros[i] = new JButton(String.valueOf(i));
        }

        sumar = new JButton("+");
        restar = new JButton("-");
        multiplicar = new JButton("*");
        division = new JButton("/");
        clear = new JButton("AC");
        equals = new JButton("=");

        // Añadir botones en orden
        botonesPanel.add(numeros[7]);
        botonesPanel.add(numeros[8]);
        botonesPanel.add(numeros[9]);
        botonesPanel.add(division);

        botonesPanel.add(numeros[4]);
        botonesPanel.add(numeros[5]);
        botonesPanel.add(numeros[6]);
        botonesPanel.add(multiplicar);

        botonesPanel.add(numeros[1]);
        botonesPanel.add(numeros[2]);
        botonesPanel.add(numeros[3]);
        botonesPanel.add(restar);

        botonesPanel.add(numeros[0]);
        botonesPanel.add(clear);
        botonesPanel.add(equals);
        botonesPanel.add(sumar);

        panel1.add(botonesPanel, BorderLayout.CENTER);
        add(panel1);

        pack();
        setLocationRelativeTo(null);

        asignarListeners();
    }

    private void asignarListeners() {
        ActionListener numerosListener = e -> {
            expresion.append(((JButton)e.getSource()).getText());
            actualizarPantalla();
        };

        for (JButton btn : numeros) {
            btn.addActionListener(numerosListener);
        }

        sumar.addActionListener(e -> clickOperador("+"));
        restar.addActionListener(e -> clickOperador("-"));
        multiplicar.addActionListener(e -> clickOperador("*"));
        division.addActionListener(e -> clickOperador("/"));
        clear.addActionListener(e -> clickClear());
        equals.addActionListener(e -> clickEquals());
    }

    private void actualizarPantalla() {
        resultados.setText(expresion.toString());
    }

    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private void clickOperador(String op) {
        if (expresion.length() == 0) {
            if (op.equals("-")) {
                expresion.append(op);
            }
            actualizarPantalla();
            return;
        }

        char ultimo = expresion.charAt(expresion.length() - 1);
        if (esOperador(ultimo)) {
            expresion.setCharAt(expresion.length() - 1, op.charAt(0));
        } else {
            expresion.append(op);
        }
        actualizarPantalla();
    }

    private void clickClear() {
        expresion.setLength(0);
        actualizarPantalla();
    }

    private void clickEquals() {
        try {
            String expr = expresion.toString();
            int posOp = -1;

            for (int i = 1; i < expr.length(); i++) {
                if (esOperador(expr.charAt(i))) {
                    posOp = i;
                    break;
                }
            }

            if (posOp == -1) return;

            double op1 = Double.parseDouble(expr.substring(0, posOp));
            double op2 = Double.parseDouble(expr.substring(posOp + 1));
            char operador = expr.charAt(posOp);

            double resultado = 0;
            switch (operador) {
                case '+': resultado = op1 + op2; break;
                case '-': resultado = op1 - op2; break;
                case '*': resultado = op1 * op2; break;
                case '/':
                    if (op2 == 0) {
                        resultados.setText("Error: Div/0");
                        expresion.setLength(0);
                        return;
                    }
                    resultado = op1 / op2;
                    break;
            }

            expresion.setLength(0);
            expresion.append(resultado);
            resultados.setText(String.valueOf(resultado));

        } catch (Exception e) {
            resultados.setText("Error");
            expresion.setLength(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculadora().setVisible(true);
        });
    }
}
