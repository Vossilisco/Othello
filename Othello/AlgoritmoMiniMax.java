package othello.algoritmo;

import othello.Utils.Casilla;
import othello.Utils.Heuristica;
import othello.Utils.Tablero;
import java.util.ArrayList;


public class AlgoritmoMiniMax extends Algoritmo {

    public static int blanca=1;
    public static int negra=-1;
    
    private int playerColor;

    public AlgoritmoMiniMax() {

    }

    @Override
    public Tablero obtenerNuevaConfiguracionTablero(Tablero tablero, short turno) {

        System.out.println("Analizando siguiente jugada con MINIMAX...");
        this.playerColor = turno;
        Tablero tableroJugada = tablero.copiarTablero();
        try {
            miniMax(tableroJugada, this.getProfundidad(), playerColor);
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (tableroJugada);
    }

    /**
     *
     * Éste es el método que tenemos que implementar.
     *
     * Algoritmo AlfaBeta para determinar cuál es el siguiente mejor movimiento
     *
     * @param tablero Configuración actual del tablero
     * @param prof Profundidad de búsqueda
     * @param jugadorActual Nos indica a qué jugador (FICHA_BLANCA ó
     * FICHA_NEGRA) le toca
     * @return
     */
    public int miniMax(Tablero tablero, int prof, int jugadorActual) {
        int movimiento = 1;
        //JUEGA BLANCAS
        if (jugadorActual == blanca) {

            int[] posibles;
            
            
            
            Casilla cas;
            cas = new Casilla(4, 5);
            cas.asignarFichaNegra();

            if (tablero.movLegal(cas)) {
                tablero.ponerFicha(cas);
            }
            
            System.out.println(jugadorActual);
            System.out.println(tablero.valorMovimiento(cas, jugadorActual));

            System.out.println("Fin de mi turno");
            
            return movimiento;

            //JUEGA NEGRAS
        } else {
            System.out.printf("Soy el jugador de las NEGRAS ");
            System.out.println("Fin de mi turno");
            return movimiento;
        }
    }

    /**
     * Devuelve el numero de fichas de nuestro color que hay en el tablero.
     *
     * @param tablero Configuración actual del tablero
     * @param jugadorActual Nos indica a qué jugador (FICHA_BLANCA ó
     * FICHA_NEGRA) contaremos sus fichas.
     * @return contador de fichas del color del jugador
     */
    public int recuentoTotalfichas(Tablero tablero, int jugadorActual) {
        Casilla[][] tabla = tablero.getMatrizTablero();

        if (jugadorActual == 1) {
            int contBlanca = 0;

            for (int i = 0; i < 7; ++i) {
                for (int j = 0; j < 7; ++j) {
                    if (tabla[i][j].esBlanca()) {
                        ++contBlanca;
                    }
                }
            }
            return contBlanca;
            
        } else {
            int contNegras = 0;

            for (int i = 0; i < 7; ++i) {
                for (int j = 0; j < 7; ++j) {
                    if (tabla[i][j].esNegra()) {
                        ++contNegras;
                    }
                }
            }
            return contNegras;
        }
    }
    
  /*  public int[] recuentoPosibles(Tablero tablero, int jugadorActual){
        for
    }*/
    
    
    
}
