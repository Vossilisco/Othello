/* 
 */

package othello.gui.accion;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import othello.gui.OthelloGUI;
import othello.gui.TableroGUI;

/**
 *
 * @author gusamasan
 */
public class AccionJuegoNuevo implements ActionListener{
// ------------------------------------------------------------------------

    private OthelloGUI othello;
    private TableroGUI elTablero;

// ------------------------------------------------------------------------

    /** Constructores ****************************************************/
    public AccionJuegoNuevo( OthelloGUI juego, TableroGUI tablero ){
        this.othello    = juego;
        this.elTablero  = tablero;        
    }
    /*********************************************************************/

    @Override
    public void actionPerformed( ActionEvent evento ){
    // ------------------------------------------------------------------------

        JButton botonLlamador;        

    // ------------------------------------------------------------------------

        botonLlamador   = ( JButton )evento.getSource();

        if( botonLlamador.getText().equals( "jugar" ) ){
            this.othello.cargarConfiguracion();
            this.othello.habilitarAvisos();
            botonLlamador.setText( "juego nuevo" );
            this.elTablero.habilitarTablero();
            this.othello.jugar();
        }
        else{
            this.othello.pararJuego();
            this.othello.deshabilitarAvisos();
            this.elTablero.getTablero().restarurarTablero();            
            this.elTablero.refrescarTablerGUI();
            this.elTablero.deshabilitarTablero();

            botonLlamador.setText( "jugar" );            
        }
    }

}
