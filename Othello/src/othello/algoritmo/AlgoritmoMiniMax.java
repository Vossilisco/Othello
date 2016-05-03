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
     * Algoritmo Minimax para determinar cuál es el siguiente mejor movimiento
     *
     * @param tablero Configuración actual del tablero
     * @param prof Profundidad de búsqueda
     * @param jugadorActual Nos indica a qué jugador (FICHA_BLANCA ó
     * FICHA_NEGRA) le toca
     * @return
     */
    public int miniMax(Tablero tablero, int prof, int jugadorActual) {
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
                    int mov = miniMax(tableroJugada, prof - 1, -jugadorActual);

                    // Jugador Max
                    if (jugadorActual == playerColor) {
                        if (mov >= max) {
                            casillaMov = cas;
                            max = mov;
                        }
                        if (mov > min) {
                            min = mov;
                        }
                    // Jugador Min   
                    } else {

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
}
