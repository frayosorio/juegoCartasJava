package juegocartas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Jugador {

    private int TOTAL_CARTAS = 10;

    private Carta[] cartas;

    public void repartir() {
        //Instanciar el vector de cartas
        cartas = new Carta[TOTAL_CARTAS];
        //instanciar las 10 cartas
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta();
        }
    }

    public void mostrar(JPanel pnl) {
        //limpiar el panel
        pnl.removeAll();
        //mostrar cada carta
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i].mostrar(10 + i * 50, 10, pnl);
        }
        //refrescar el objeto de despliegue
        pnl.repaint();
    }

    public void mostrar(JTable tbl) {
        String[] encabezados = new String[TOTAL_CARTAS];
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            encabezados[i] = cartas[i].obtenerNombre().toString() + " de " + cartas[i].obtenerPinta().toString();
        }
        DefaultTableModel dtm = new DefaultTableModel(null, encabezados) {
            @Override
            public Class<?> getColumnClass(int column) {
                return ImageIcon.class;
                //return Object.class;
            }
        };
        ImageIcon[] imagenes = new ImageIcon[TOTAL_CARTAS];
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            imagenes = cartas[i].mostrar(i, imagenes);
        }
        dtm.addRow(imagenes);

        tbl.setModel(dtm);
        tbl.setRowHeight(0, imagenes[0].getIconHeight());
    }

    private GrupoCarta[] grupos;
    private NombreCarta[] nombres;

    public void obtenerGrupos() {
        grupos = null;
        nombres = null;
        if (cartas != null) {

            int[] contadores = new int[13];
            //Se cuentan las cartas por el nombre
            for (Carta c : cartas) {
                contadores[c.obtenerNombre().ordinal()]++;
            }
            //se cuentan los grupos hallados
            int totalGrupos = 0;
            for (int c : contadores) {
                if (c >= 2) {
                    totalGrupos++;
                }
            }
            if (totalGrupos > 0) {
                //hallar los grupos
                grupos = new GrupoCarta[totalGrupos];
                nombres = new NombreCarta[totalGrupos];
                int pGrupo = 0;
                for (int i = 0; i < contadores.length; i++) {
                    if (contadores[i] >= 2) {
                        grupos[pGrupo] = GrupoCarta.values()[contadores[i]];
                        nombres[pGrupo] = NombreCarta.values()[i];
                        pGrupo++;
                    }
                }
            }
        }
    }

    public String obtenerMensajeGrupos() {
        if (grupos != null) {
            String mensaje = "Las grupos encontrados fueron:\n";
            for (int i = 0; i < grupos.length; i++) {
                mensaje += grupos[i].name() + " de " + nombres[i].name() + "\n";
            }
            return mensaje;
        }
        return "No hay grupos";
    }

}
