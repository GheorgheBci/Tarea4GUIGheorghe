package daw.gheorghe;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author george
 */
public class PanelPrincipal extends JPanel {

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
    }
}
