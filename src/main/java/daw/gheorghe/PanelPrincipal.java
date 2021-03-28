package daw.gheorghe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author george
 */
public class PanelPrincipal extends JPanel implements ActionListener {

    private PanelBotones botonera;
    private JTextArea areaTexto;
    private int tipoOperacion;

    public PanelPrincipal() {
        initComponents();
        tipoOperacion = -1;
    }

    private void initComponents() {
        botonera = new PanelBotones();
        areaTexto = new JTextArea(10, 50);

        areaTexto.setEditable(false);
        areaTexto.setBackground(Color.white);

        this.setLayout(new BorderLayout());

        this.add(areaTexto, BorderLayout.NORTH);
        this.add(botonera, BorderLayout.SOUTH);

        for (JButton boton : this.botonera.getGrupoBotones()) {
            boton.addActionListener(this);
        }

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();

        activarBotones();

        if (o instanceof JButton) {
            JButton cadena = (JButton) o;

            // En una nueva variable vamos almacenando los botones que vamos pulsando
            String numeros = areaTexto.getText() + cadena.getText();

            String otro = "";

            // Vamos mostrando en la calculadora los botones que vamos pulsando
            areaTexto.setText(numeros);

            // Si el botón que pulsamos es la "C", se borra todo el contenido 
            if (o.equals(botonera.getGrupoBotones()[15])) {
                areaTexto.setText("");
            }

            // En este if solo entrará si el String contiene el simbolo "=". Usamos el método replaceAll para
            // reemplazar el simbolo "=" con un espacio en blanco 
            if (numeros.contains("=")) {
                otro = numeros.replaceAll("[=]", "");
            }

            // En un array de String vamos a almacenar los números con los que haremos las operaciones
            String[] numero1 = null;

            // En estás líneas de códigos vamos a quitar los simbolos de operación del String mediante el método split
            if (numeros.contains("+")) {
                numero1 = otro.split("\\+"); // En el array vamos almacenar los dos operando 
                tipoOperacion = 1; // El tipo de operación es una suma
            } else if (numeros.contains("-")) {
                numero1 = otro.split("\\-");
                tipoOperacion = 2; // El tipo de operación es una resta
            } else if (numeros.contains("*")) {
                numero1 = otro.split("\\*");
                tipoOperacion = 3; // El tipo de operación es una multiplicación
            } else if (numeros.contains("/")) {
                numero1 = otro.split("\\/");
                tipoOperacion = 4; // El tipo de operación es una división
            }

            // Cuando pulsamos un botón para hacer alguna operación, automáticamento todos los botones
            // de operación se desactivan
            if (o.equals(botonera.getGrupoBotones()[10]) || o.equals(botonera.getGrupoBotones()[11]) || o.equals(botonera.getGrupoBotones()[12]) || o.equals(botonera.getGrupoBotones()[13])
                    || o.equals(botonera.getGrupoBotones()[14]) || o.equals(botonera.getGrupoBotones()[15])) {
                desactivarBotones();
            }

            // Si pulsamos el botón "=" se realizará la operación aritmética que hayamos pulsado anteriormente
            if (o.equals(botonera.getGrupoBotones()[14])) {
                switch (tipoOperacion) {
                    case 1: // Sumar
                        areaTexto.setText(numeros + String.valueOf(sumar(numero1[0], numero1[1])));
                        break;
                    case 2: // Restar
                        areaTexto.setText(numeros + String.valueOf(restar(numero1[0], numero1[1])));
                        break;
                    case 3: // Multiplicar
                        areaTexto.setText(numeros + String.valueOf(multiplicar(numero1[0], numero1[1])));
                        break;
                    case 4: // Dividir
                        areaTexto.setText(numeros + String.valueOf(dividir(numero1[0], numero1[1])));
                        break;
                }
            }
        }
    }

    // Método que desactiva los botones para hacer las operaciones aritméticas
    private void desactivarBotones() {
        for (int i = 10; i < botonera.getGrupoBotones().length - 1; i++) {
            botonera.getGrupoBotones()[i].setEnabled(false);
        }
    }

    // Método que activa los botones para hacer las operaciones aritméticas
    private void activarBotones() {
        for (int i = 10; i <= 14; i++) {
            botonera.getGrupoBotones()[i].setEnabled(true);
        }
    }

    // Método para sumar 
    private int sumar(String num1, String num2) {
        int resultado;

        resultado = Integer.parseInt(num1) + Integer.parseInt(num2);

        return resultado;
    }

    // Método para restar
    private int restar(String num1, String num2) {
        int resultado;

        resultado = Integer.parseInt(num1) - Integer.parseInt(num2);

        return resultado;
    }

    // Método para multiplicar
    private int multiplicar(String num1, String num2) {
        int resultado;

        resultado = Integer.parseInt(num1) * Integer.parseInt(num2);

        return resultado;
    }

    // Método para dividir
    private double dividir(String num1, String num2) {
        double resultado;

        resultado = Double.parseDouble(num1) / Double.parseDouble(num2);

        return resultado;
    }
}
