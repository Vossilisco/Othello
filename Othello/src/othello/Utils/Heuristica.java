/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello.Utils;

/**
 *
 * @author user
 */
public class Heuristica {


    public static int h1(Tablero tablero)
    {
        return 0;
    }


    //Una heuristica posible a usar
    public static int h2(Tablero tablero,int playerColor)
    {
        int score = Puntos(playerColor, tablero) - Puntos(-playerColor, tablero);

        // If the game is over
        if (tablero.EsFinalDeJuego())
        {
            // if player has won
            if (score > 0)
                return 100;
            // if player has lost (or tied)
            else
                return -100;
        }

        // if game isn't over, return relative advatage
        return score;
    }

    public static int Puntos(int playerColor, Tablero tablero)
    {
        int points = 0;

        for (int x = 0; x < Tablero.CANTIDAD_FILAS_DEFECTO; x++)
            for (int y = 0; y < Tablero.CANTIDAD_COLUMNAS_DEFECTO; y++)
                if (tablero.getMatrizTablero()[x][y].obtenerColorFicha() == playerColor)
                    points++;

        return points;
    }



}
