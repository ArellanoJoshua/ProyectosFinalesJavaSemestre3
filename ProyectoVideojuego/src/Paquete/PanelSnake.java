package Paquete;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelSnake extends JPanel {

    Color colorSnake = Color.BLUE;
    Color colorcomida = Color.RED;
    int TamañoMaximo, Tamaño, Cantidad, Residuo;
    List<int[]> snake = new ArrayList<>();
    int[] comida = new int[2];
    String direccion = "Derecha";

    String direccionproxima = "Derecha";

    Thread hilo;
    Caminante camino;

    public PanelSnake(int TamañoMaximo, int Cantidad) {

        this.TamañoMaximo = TamañoMaximo;
        this.Cantidad = Cantidad;
        this.Tamaño = TamañoMaximo / Cantidad;
        this.Residuo = TamañoMaximo % Cantidad;
        int[] a = {Cantidad / 2 - 1, Cantidad / 2 - 1};
        int[] b = {Cantidad / 2, Cantidad / 2 - 1};
        snake.add(a);
        snake.add(b);
        generarcomida();

        camino = new Caminante(this);
        hilo = new Thread(camino);
        hilo.start();

    }

    @Override
    public void paint(Graphics pintor) {
        super.paint(pintor);
        pintor.setColor(colorSnake);

        //Pintando serpiente 
        for (int[] par : snake) {
            pintor.fillRect(Residuo / 2 + par[0] * Tamaño, Residuo / 2 + par[1] * Tamaño, Tamaño - 1, Tamaño - 1);
        }
        //Pintando comida
        pintor.setColor(colorcomida);
        pintor.fillRect(Residuo / 2 + comida[0] * Tamaño, Residuo / 2 + comida[1] * Tamaño, Tamaño - 1, Tamaño - 1);
    }

    public void avanzar() {

        igualardir();

        int[] ultimo = snake.get(snake.size() - 1);
        int agregarX = 0;
        int agregarY = 0;

        switch (direccion) {
            case "Derecha":
                agregarX = 1;
                break;
            case "Izquierda":
                agregarX = -1;
                break;
            case "Arriba":
                agregarY = -1;
                break;
            case "Abajo":
                agregarY = 1;
                break;
        }

        int[] nuevo = {Math.floorMod(ultimo[0] + agregarX, Cantidad),
            Math.floorMod(ultimo[1] + agregarY, Cantidad)};

        boolean existe = false;
        for (int i = 0; i < snake.size(); i++) {
            if (nuevo[0] == snake.get(i)[0] && nuevo[1] == snake.get(i)[1]) {
                existe = true;
                break;
            }
        }
        if (existe) {
            JOptionPane.showMessageDialog(this, "Has perdido!!");
        } else {
            if (nuevo[0] == comida[0] && nuevo[1] == comida[1]) {
                snake.add(nuevo);
                generarcomida();
            } else {
                snake.add(nuevo);
                snake.remove(0);
            }
        }

    }

    public void generarcomida() {
        boolean existe = false;
        int a = (int) (Math.random() * Cantidad);
        int b = (int) (Math.random() * Cantidad);

        for (int[] par : snake) {
            if (par[0] == a && par[1] == b) {
                existe = true;
                generarcomida();
                break;
            }
        }
        if (!existe) {
            this.comida[0] = a;
            this.comida[1] = b;
        }
    }

    public void cambiardireccion(String dir) {
        if ((this.direccion.equals("Derecha") || this.direccion.equals("Izquierda")) && (dir.equals("Arriba") || dir.equals("Abajo"))) {
            this.direccionproxima = dir;
        }
        if ((this.direccion.equals("Arriba") || this.direccion.equals("Abajo")) && (dir.equals("Izquierda") || dir.equals("Derecha"))) {
            this.direccionproxima = dir;
        }

    }

    public void igualardir() {
        this.direccion = this.direccionproxima;

    }
}
