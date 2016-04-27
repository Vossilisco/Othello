/* 
 */

package othello.jugador;

import othello.Utils.Tablero;

import othello.Juego;

/**
 *
 * @author gusamasan
 */
public class JugadorHumano extends Jugador{
// -----------------------------------------------------------------------------

    private boolean continuar;
    
// -----------------------------------------------------------------------------

    /** Constructores *********************************************************/
    public JugadorHumano(){
        this.continuar = false;
    }
    /**************************************************************************/    

    public void continuar(){
        this.continuar = true;
    }

    @Override
    public boolean esHumano(){
        return( true );
    }

    @Override
    public Tablero jugar(short turno){
    // ----------------------------------------------------------------------

    // ----------------------------------------------------------------------

        while( !continuar && !this.elJuego.estaParado() ){
            Juego.esperarUnRato();
            System.out.println( "Esperando " );
        }

        this.continuar = false;

        //if( !this.elJuego.estaParado() ){
        return( this.algoritmo
                    .obtenerNuevaConfiguracionTablero( this.tablero,turno  )
              );
    }
}
