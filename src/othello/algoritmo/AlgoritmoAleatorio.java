/*
 */

package othello.algoritmo;

import othello.Utils.Casilla;
import othello.Utils.Tablero;

/**
 *
 * @author gusamasan
 */
public class AlgoritmoAleatorio extends Algoritmo{
// ----------------------------------------------------------------------

// ----------------------------------------------------------------------

    /** Constructores **************************************************/

    
    public AlgoritmoAleatorio(){

    }
    /*******************************************************************/
    

    @Override
    public Tablero obtenerNuevaConfiguracionTablero( Tablero tablero,short turno ){

        System.out.println( "analizando siguiente jugada con ALEATORIO" );
        Tablero tableroJugada= new Tablero();
        tableroJugada=tablero.copiarTablero();
         try{

            jugadaAleatoria(tableroJugada, turno);
            Thread.sleep( 1000 );
        }
        catch( Exception e ){
            e.printStackTrace();
        }
       

        return( tableroJugada );
    }


    public void jugadaAleatoria(Tablero tablero, int jugadorActualColor) {
		int fila, col;
		Casilla cas;



		//elegir aleatoriamente una casilla
		fila = (int) Math.floor(Math.random()*8);
		col = (int) Math.floor(Math.random()*8);
		//tomando esta casilla como punto de partida, elegir la primera
		//casilla a la que se pueda mover
		cas = new Casilla(0,0);

                if(jugadorActualColor==Casilla.FICHA_BLANCA)
                {
                    cas.asignarFichaBlanca();
                }
                else if(jugadorActualColor==Casilla.FICHA_NEGRA)
                {
                    cas.asignarFichaNegra();
                }
		for(int f=0; f<8 ; f++)
		   for(int c=0; c<8; c++) {
			  cas.fila = (fila + f) % 8;
			  cas.col = (col+c) % 8;
			  if (tablero.movLegal(cas)) {

				 tablero.ponerFicha(cas);
                                 return;
			  }
		   }

  }
}
