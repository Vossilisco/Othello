/*
 */

package othello.jugador;

import othello.Juego;
import othello.algoritmo.*;
import othello.ConstanteOthello;
import othello.algoritmo.FabricaAbstractaAlgoritmo;

/**
 *
 * @author gusamasan
 */
public class FabricaAbstractaJugador {

    public static Jugador obtenerJugador( String tipoJugador, String eleccionAlgoritmo, int profundidad, Juego elJuego ){
    // --------------------------------------------------------------------------------

        Algoritmo   algoritmo;
        Jugador     jugador;
        
    // --------------------------------------------------------------------------------

        if( tipoJugador.equals( ConstanteOthello.JUGADOR_HUMANO ) ){
            jugador = new JugadorHumano();

            jugador.setAlgoritmo( new AlgoritmoHumano() );

        }else{
            jugador     = new JugadorMaquina();

            algoritmo   = FabricaAbstractaAlgoritmo
                                .obtenerAlgoritmo(eleccionAlgoritmo, profundidad );

            jugador.setAlgoritmo( algoritmo );
        }        

        jugador.setJuego( elJuego );
        
        return( jugador );
    }
}
