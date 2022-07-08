package juegocartas;

import java.util.Random;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Carta {

    private int indice;
    Random r = new Random();

    //metodo constructor
    public Carta() {
        indice = r.nextInt(52) + 1;
    }

    public void mostrar(int x, int y, JPanel pnl) {
        //definir el nombre del archivo de la imagen de la carta
        String nombreArchivo = "/cartas/Carta" + String.valueOf(indice) + ".jpg";

        //cargar la imagen
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(nombreArchivo));
        //Instanciar un objeto para desplegar imagenes
        JLabel lblCarta = new JLabel(imgCarta);
        //Definir la ubicacion del objeto de despliegue y sus dimensiones
        lblCarta.setBounds(x, y, imgCarta.getIconWidth(), imgCarta.getIconHeight());
        //Agregar el objeto de despliegue al contendedor de despliegue
        pnl.add(lblCarta);
    }
    
    public ImageIcon[] mostrar(int columna, ImageIcon[] imagenes){
        //definir el nombre del archivo de la imagen de la carta
        String nombreArchivo = "/cartas/Carta" + String.valueOf(indice) + ".jpg";
        //cargar la imagen
        ImageIcon imgCarta = new ImageIcon(getClass().getResource(nombreArchivo));
        //Asignarla al vector de imagenes
        imagenes[columna]=imgCarta;
        //retornar el vector de imagenes actualizado
        return imagenes;
    }

    public Pinta obtenerPinta() {
        //devolver la pinta dependiendo del rango en el que está el índice
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta obtenerNombre() {
        int n = indice % 13;
        if (n == 0) {
            n = 12;
        } else {
            n--;
        }
        return NombreCarta.values()[n];
    }
    
    public int obtenerIndice(){
        return indice;
    }
    

}
