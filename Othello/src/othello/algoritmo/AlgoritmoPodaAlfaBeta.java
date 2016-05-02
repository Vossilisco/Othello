/*
 */
package othello.algoritmo;

import othello.Utils.Casilla;
import othello.Utils.Heuristica;
import othello.Utils.Tablero;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gusamasan
 */
public class AlgoritmoPodaAlfaBeta extends Algoritmo {
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------
    /**
     * Constructores *************************************************
     */
    private int playerColor;

    public AlgoritmoPodaAlfaBeta() {

    }

    /**
     * ****************************************************************
     */

    @Override
    public Tablero obtenerNuevaConfiguracionTablero(Tablero tablero, short turno) {

        System.out.println("analizando siguiente jugada con ALFABETA");
        this.playerColor = turno;
        Tablero tableroJugada = tablero.copiarTablero();
        try {
            int beta = Integer.MAX_VALUE;
            int alfa = Integer.MIN_VALUE;
            alfaBeta(tableroJugada, this.getProfundidad(), playerColor, alfa, beta);
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
     * @param alfa
     * @param beta Parámetros alfa y beta del algoritmo
     * @return
     */
    public int alfaBeta(Tablero tablero, int prof, int jugadorActual, int alfa, int beta) {
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
                if (jugadorActual == Casilla.FICHA_NEGRA) {
                    cas.asignarFichaNegra();
                }
                if (tablero.movLegal(cas)) {
                    Tablero tableroJugada = tablero.copiarTablero();
                    tableroJugada.ponerFicha(cas);
                    // Se avanza movimientos intercalando jugadores hasta llegar a la profundidad deseada
                    int mov = alfaBeta(tableroJugada, prof - 1, -jugadorActual, alfa, beta);
                    // MAX
                    if (jugadorActual == playerColor) {
                        if (mov > beta) {
                            return mov;
                        }
                        if (mov > alfa) {
                            alfa = mov;
                            casillaMov = cas;
                        }

                    } else { // MIN
                        if (mov < alfa) {
                            return mov;
                        }
                        if (mov < beta) {
                            beta = mov;
                            casillaMov = cas;
                        }

                    }
                }
            }
        }
        if (casillaMov != null) {
            tablero.ponerFicha(casillaMov);
        }

        if (jugadorActual == this.playerColor) {
            return alfa;
        } else {
            return beta;
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
