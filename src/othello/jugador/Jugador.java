/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello.jugador;

import othello.Juego;
import othello.Utils.Tablero;
import othello.algoritmo.Algoritmo;

/**
 *
 * @author gusamasan
 */
public abstract class Jugador{
// -----------------------------------------------------------------------------

    protected String colorFichaAsignada;

    protected Juego       elJuego;
    
    protected Tablero     tablero;

    protected Algoritmo   algoritmo;

// -----------------------------------------------------------------------------

    /** Constructores *********************************************************/
    public Jugador(){

    }
    /**************************************************************************/


    public String getColorFichaAsignada(){
        return( this.colorFichaAsignada );
    }

    public void setColorFichaAsignada( String color ){
        this.colorFichaAsignada = color;
    }

    public Algoritmo getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(Algoritmo algoritmo) {
        this.algoritmo = algoritmo;
    }

    public Juego getJuego(){
        return( this.elJuego );
    }

    public void setJuego( Juego juego ){
        this.elJuego = juego;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public abstract boolean esHumano();

    public abstract Tablero jugar(short turno);

    
}
