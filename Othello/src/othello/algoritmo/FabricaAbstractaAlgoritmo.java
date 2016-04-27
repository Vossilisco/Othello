/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello.algoritmo;

import othello.ConstanteOthello;
/**
 *
 * @author gusamasan
 */
public class FabricaAbstractaAlgoritmo {

    public static Algoritmo obtenerAlgoritmo( String eleccionAlgoritmo, int profundidad ){
    // ------------------------------------------------------------------------

        Algoritmo algoritmo;

    // ------------------------------------------------------------------------


        if( eleccionAlgoritmo.equals(  ConstanteOthello.ALGORITMO_PODA_ALFA_BETA   ) )
            algoritmo = new AlgoritmoPodaAlfaBeta();
        else if( eleccionAlgoritmo.equals(  ConstanteOthello.ALGORITMO_MINIMAX        ) )
            algoritmo = new AlgoritmoMiniMax();
        else if( eleccionAlgoritmo.equals(  ConstanteOthello.ALGORITMO_ALEATORIO        ) )
            algoritmo = new AlgoritmoAleatorio();
        else
            algoritmo = new AlgoritmoHumano();


        algoritmo.setProfundidad(profundidad);

        return( algoritmo );
    }
}
