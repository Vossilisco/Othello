/*
 */

package othello.jugador;

import othello.Utils.Tablero;

/**
 *
 * @author gusamasan
 */
public class JugadorMaquina extends Jugador{
// -----------------------------------------------------------------------------

// -----------------------------------------------------------------------------

    /** Constructores *********************************************************/
    public JugadorMaquina(){

    }
    /**************************************************************************/

    @Override
    public boolean esHumano(){
        return( false );
    }
    
    @Override
    public Tablero jugar(short turno){

        return( this.algoritmo
                    .obtenerNuevaConfiguracionTablero( this.tablero, turno )
               );
        
    }
}
