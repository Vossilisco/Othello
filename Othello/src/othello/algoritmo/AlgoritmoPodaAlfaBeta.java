package othello.algoritmo;

import othello.Utils.Casilla;
import othello.Utils.Heuristica;
import othello.Utils.Tablero;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AlgoritmoPodaAlfaBeta extends Algoritmo {

    private int playerColor;

    public AlgoritmoPodaAlfaBeta() {

    }

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
        //Si llega a la profundidad máxima, aplicamos heurística:
        if (prof == 0) {
            return Heuristica.h1(tablero, playerColor);
        }
        if (tablero.EsFinalDeJuego()) {
            return Heuristica.h1(tablero, playerColor);
        }
        if (this.getProfundidad() == prof && !tablero.PuedeJugar(jugadorActual)) {
            return Heuristica.h1(tablero, playerColor);
        }

        // Cuando dado un nodo, el jugador correspondiente no puede realizar movimiento, se cambia turno (pasa turno)
        if (!tablero.PuedeJugar(jugadorActual)) {
            jugadorActual = -jugadorActual;
        }
        // Genera una simulación del tablero con posibles movimientos
        Casilla casillaMov = null;
        for (int fila = 0; fila < 8; fila++) {

            for (int colum = 0; colum < 8; colum++) {

                Casilla cas = new Casilla(fila, colum);

                if (jugadorActual == Casilla.FICHA_NEGRA) {
                    cas.asignarFichaNegra();
                }
                if (jugadorActual == Casilla.FICHA_BLANCA) {
                    cas.asignarFichaBlanca();
                }

                // Crea un nodo por cada posible movimiento
                if (tablero.movLegal(cas)) {
                    Tablero tableroJugada = tablero.copiarTablero();
                    tableroJugada.ponerFicha(cas);

                    // Se avanza movimientos intercalando jugadores hasta llegar a la profundidad deseada
                    int mov = alfaBeta(tableroJugada, prof - 1, -jugadorActual, alfa, beta);

                    // Jugador Max
                    if (jugadorActual == playerColor) {
                        if (mov > beta) {
                            return mov;
                        }
                        if (mov > alfa) {
                            alfa = mov;
                            casillaMov = cas;
                        }

                    } else {
                        // Jugador Min
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

}
