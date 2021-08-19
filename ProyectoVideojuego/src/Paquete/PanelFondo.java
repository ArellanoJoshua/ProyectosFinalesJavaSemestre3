package Paquete;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanelFondo extends JPanel {

    Color colorFondo = Color.GRAY;
    int TamañoMaximo, Tamaño, Cantidad, Residuo;

    public PanelFondo(int TamañoMaximo, int Cantidad) {

        this.TamañoMaximo = TamañoMaximo;
        this.Cantidad = Cantidad;
        this.Tamaño = TamañoMaximo / Cantidad;
        this.Residuo = TamañoMaximo % Cantidad;

    }

    @Override
    public void paint(Graphics pintor) {
        super.paint(pintor);
        pintor.setColor(colorFondo);

        for (int i = 0; i < Cantidad; i++) {
            for (int j = 0; j < Cantidad; j++) {
                pintor.fillRect(Residuo / 2 + i * Tamaño, Residuo / 2 + j * Tamaño, Tamaño - 1, Tamaño - 1);
            }
        }
    }

}
