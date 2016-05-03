package othello.Utils;

public class Heuristica {

    /**
     * Aumenta el valor de las casillas exteriores.
     * 
     * @param tablero tablero con una configuración de fichas
     * @param playerColor color del jugador que llama a la función
     * @return valor para de la situación del tablero
     */
    public static int h1(Tablero tablero, int playerColor) {

        int score = Puntos(playerColor, tablero) - Puntos(-playerColor, tablero);
        int negras = -1;

        // Si el juego se ha terminado.
        if (tablero.EsFinalDeJuego()) {
            // if player has won
            if (score > 0) {
                return 100;
            } // if player has lost (or tied)
            else {
                return -100;
            }
        } else {
            int casillaBuena = 0;
            Casilla[][] valorActual;
            valorActual = tablero.getMatrizTablero();

            //JUGADOR DE FICHAS NEGRAS:
            if (playerColor == negras) {
                for (int i = 0; i < 8; i++) {
                    if (valorActual[0][i].esNegra()) {
                        casillaBuena = casillaBuena + 20;
                    }
                    if (valorActual[i][0].esNegra()) {
                        casillaBuena = casillaBuena + 20;
                    }
                }
                //JUGADOR DE FICHAS BLANCAS:
            } else {

                for (int i = 0; i < 8; i++) {
                    if (valorActual[0][i].esBlanca()) {
                        casillaBuena = casillaBuena + 20;
                    }
                    if (valorActual[i][0].esBlanca()) {
                        casillaBuena = casillaBuena + 20;
                    }
                }
            }

            return score + casillaBuena;
        }
    }

    //Una heuristica basica
    public static int h2(Tablero tablero, int playerColor) {
        int score = Puntos(playerColor, tablero) - Puntos(-playerColor, tablero);

        // If the game is over
        if (tablero.EsFinalDeJuego()) {
            // if player has won
            if (score > 0) {
                return 100;
            } // if player has lost (or tied)
            else {
                return -100;
            }
        }

        // if game isn't over, return relative advatage
        return score;
    }

    public static int Puntos(int playerColor, Tablero tablero) {
        int points = 0;

        for (int x = 0; x < Tablero.CANTIDAD_FILAS_DEFECTO; x++) {
            for (int y = 0; y < Tablero.CANTIDAD_COLUMNAS_DEFECTO; y++) {
                if (tablero.getMatrizTablero()[x][y].obtenerColorFicha() == playerColor) {
                    points++;
                }
            }
        }

        return points;
    }

}
