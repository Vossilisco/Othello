package othello.algoritmo;

import othello.Utils.Casilla;
import othello.Utils.Heuristica;
import othello.Utils.Tablero;
import java.util.ArrayList;

public class AlgoritmoMiniMax extends Algoritmo {

    public static int blanca = 1;
    public static int negra = -1;
    public int min = 0;
    public int max = 0;

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
        //Si llega a la profundidad máxima:
        if (prof == 0 || tablero.EsFinalDeJuego()) {
            return recuentoTotalfichas(tablero, playerColor);
        }
        if (!tablero.PuedeJugar(jugadorActual)) {
            if (this.getProfundidad() == prof) {
                return recuentoTotalfichas(tablero, playerColor);
            }
            jugadorActual = -jugadorActual;
        }
        Casilla casillaMov = null;
        // Genera una simulación del tablero con posibles movimientos
        for (int fila = 0; fila < Tablero.CANTIDAD_FILAS_DEFECTO; fila++) {
            for (int colum = 0; colum < Tablero.CANTIDAD_COLUMNAS_DEFECTO; colum++) {
                Casilla cas = new Casilla(fila, colum);
                if (jugadorActual == Casilla.FICHA_BLANCA) {
                    cas.asignarFichaBlanca();
                } 
                if (jugadorActual == Casilla.FICHA_NEGRA){
                    cas.asignarFichaNegra();
                }
                if (tablero.movLegal(cas)) {
                    Tablero tableroJugada = tablero.copiarTablero();
                    tableroJugada.ponerFicha(cas);
                    // Se avanza movimientos intercalando jugadores hasta llegar a la profundidad deseada
                    int mov = miniMax(tableroJugada, prof - 1, -jugadorActual);
                    // MAX
                    if (jugadorActual == playerColor) {
                        if (mov >= max) {
                           casillaMov = cas;
                            max = mov;
                        }
                        if (mov > min) {
                            min = mov;
                        }
                    } else { // MIN
                        if (mov <= max) {
                           casillaMov = cas;
                            max = mov;
                        }
                        if (mov < min) {
                            max = mov;
                        }
                    }
                }
            }

            

            
        }
        if (casillaMov != null) {
                tablero.ponerFicha(casillaMov);
            }

            if (jugadorActual == this.playerColor) {
                return min;
            } else {
                return max;
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

}
