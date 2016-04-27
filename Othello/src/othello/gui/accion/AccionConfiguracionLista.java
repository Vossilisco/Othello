/* 
 */

package othello.gui.accion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import othello.ConstanteOthello;
import othello.jugador.*;
import othello.gui.OthelloGUI;

/**
 *
 * @author gusamasan
 */
public class AccionConfiguracionLista implements ActionListener{
// ------------------------------------------------------------------------

    private OthelloGUI  othello;
    private JTabbedPane pestanias;

// ------------------------------------------------------------------------

    /** Constructores ****************************************************/
    public AccionConfiguracionLista( OthelloGUI juego,  JTabbedPane unasPestanias ){
        this.othello    = juego;
        this.pestanias  = unasPestanias;
    }
    /*********************************************************************/

    public void actionPerformed( ActionEvent evento ){    

        //this.othello.cargarConfiguracion();
        
        this.pestanias.setSelectedIndex(0);
        
    }

}
