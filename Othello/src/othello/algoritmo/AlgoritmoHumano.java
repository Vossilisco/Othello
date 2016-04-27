/*
 */

package othello.algoritmo;

import othello.Utils.Casilla;
import othello.Utils.Tablero;

/**
 *
 * @author gusamasan
 */
public class AlgoritmoHumano extends Algoritmo{
// ----------------------------------------------------------------------

    private Casilla casillaJugada;
    
// ----------------------------------------------------------------------

    /** Constructores **************************************************/
    public AlgoritmoHumano(){

    }
    /*******************************************************************/
    

    public void asignarCasillaJuagada( Casilla casilla ){
        this.casillaJugada = casilla;
    }

    @Override
    public Tablero obtenerNuevaConfiguracionTablero( Tablero tablero, short turno ){

        System.out.println( "analizando siguiente jugada con HUMANO" );

        while(!tablero.ponerFicha( this.casillaJugada ));

        return( tablero );
    }
}
