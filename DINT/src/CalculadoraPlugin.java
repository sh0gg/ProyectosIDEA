import javax.swing.*;

public class CalculadoraPlugin {
    private JPanel panel1;
    private JButton sumar;
    private JButton restar;
    private JButton multiplicar;
    private JButton division;
    private JButton clear;
    private JLabel resultados;
    private JButton equals;
    private JButton a7;
    private JButton a8;
    private JButton a9;
    private JButton a4;
    private JButton a5;
    private JButton a6;
    private JButton a1;
    private JButton a2;
    private JButton a3;
    private JButton a0;

    private StringBuilder expresion = new StringBuilder(); // inicializo para ir guardando la expresion

    public void empezar() {
        clickClear();

        a0.addActionListener(e -> clickNum("0"));
        a1.addActionListener(e -> clickNum("1"));
        a2.addActionListener(e -> clickNum("2"));
        a3.addActionListener(e -> clickNum("3"));
        a4.addActionListener(e -> clickNum("4"));
        a5.addActionListener(e -> clickNum("5"));
        a6.addActionListener(e -> clickNum("6"));
        a7.addActionListener(e -> clickNum("7"));
        a8.addActionListener(e -> clickNum("8"));
        a9.addActionListener(e -> clickNum("9"));

        sumar.addActionListener(e -> clickOperador("+"));
        restar.addActionListener(e -> clickOperador("-"));
        multiplicar.addActionListener(e -> clickOperador("*"));
        division.addActionListener(e -> clickOperador("/"));

        equals.addActionListener(e -> clickEquals());
        clear.addActionListener(e -> clickClear());
    }

    private void actualizarPantalla() {
        resultados.setText(expresion.toString());
    }

    private void clickNum(String num) {
        expresion.append(num);
        actualizarPantalla();
    }

    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private void clickOperador(String op) {
        if (expresion.length() == 0) {
            // evita error aunque permite meter el - para introducir negativos
            if (op.equals("-")) {
                expresion.append(op);
            }
            actualizarPantalla();
            return;
        }

        char ultimo = expresion.charAt(expresion.length() - 1);
        if (esOperador(ultimo)) {
            // cambia el operador por el nuevo introducido
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

            // busca el operador, saltándose el - en caso de negativo
            for (int i = 1; i < expr.length(); i++) {
                if (esOperador(expr.charAt(i))) {
                    posOp = i;
                    break;
                }
            }

            if (posOp == -1) {
                // si no hay operador, no hace nada
                return;
            }

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
                default:
                    resultados.setText("Error");
                    expresion.setLength(0);
                    return;
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
            CalculadoraPlugin calc = new CalculadoraPlugin();
            JFrame frame = new JFrame("Calculadora");
            frame.setContentPane(calc.panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            calc.empezar();
        });
    }
}
