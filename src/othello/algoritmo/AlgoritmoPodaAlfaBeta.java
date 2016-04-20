/*
 */

package othello.algoritmo;

import othello.Utils.Casilla;
import othello.Utils.Heuristica;
import othello.Utils.Tablero;
import java.util.ArrayList;

/**
 *
 * @author gusamasan
 */
public class AlgoritmoPodaAlfaBeta extends Algoritmo{
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

    /** Constructores **************************************************/
    private int playerColor;
    public AlgoritmoPodaAlfaBeta(){

    }
    /*******************************************************************/
    

    @Override
    public Tablero obtenerNuevaConfiguracionTablero( Tablero tablero, short turno ){

        System.out.println( "analizando siguiente jugada con ALFABETA" );
        this.playerColor=turno;
        Tablero tableroJugada=tablero.copiarTablero();
         try{
             int beta = Integer.MAX_VALUE;
             int alfa = Integer.MIN_VALUE;
             alfaBeta(tableroJugada, this.getProfundidad(), playerColor, alfa, beta);
            Thread.sleep( 1000 );
        }
        catch( Exception e ){
            e.printStackTrace();
        }

        return( tableroJugada );
    }

     /**
     *
     * Éste es el método que tenemos que implementar.
     * 
     * Algoritmo AlfaBeta para determinar cuál es el siguiente mejor movimiento
     * 
     * @param tablero
     * Configuración actual del tablero
     * @param prof
     * Profundidad de búsqueda
     * @param jugadorActual
     * Nos indica a qué jugador (FICHA_BLANCA ó FICHA_NEGRA) le toca
     * @param alfa
     * @param beta
     * Parámetros alfa y beta del algoritmo
     * @return
     */
    public int alfaBeta(Tablero tablero, int prof, int jugadorActual, int alfa, int beta)
    {        

		// ...

        // Retornar el valor para el mejor movimiento
        if (jugadorActual == this.playerColor)
            return alfa;

        else
            return beta;
    }
}
