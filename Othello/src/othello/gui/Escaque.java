/*
 */

package othello.gui;
import javax.swing.*;

import othello.Utils.Casilla;

/**
 *
 * @author gusamasan
 */
public class Escaque extends JButton{
// ------------------------------------------------------------------------

    int     columna, fila;

    short   colorFicha;

// ------------------------------------------------------------------------

    /** Constructores ****************************************************/
    public Escaque(){
        super();      
    }

    public Escaque( int fila, int columna ){
        super();

        this.fila       = fila;
        this.columna    = columna;
    }
    /*********************************************************************/


    private void inicializar(){
    }

    public short getColorFicha() {
        return colorFicha;
    }

    public int getColumna() {
        return columna;
    }

    public int getFila() {
        return fila;
    }

    public void limpiar(){
        this.colorFicha = Casilla.FICHA_TRANSPARENTE;
        this.setIcon(null);
    }

    private ImageIcon obtenerImagen()
    {
        return( new ImageIcon( super.getClass().getResource( "./recurso/negro.ico" ) ));
    }

    public void mostrarFichaBlanca(){
        this.colorFicha = Casilla.FICHA_BLANCA;
        this.setIcon( new ImageIcon( Main.class.getClassLoader().getResource("./recurso/blanco001.png" ) ) );
    }

    public void mostrarFichaNegra(){
        this.colorFicha = Casilla.FICHA_NEGRA;
        this.setIcon( new ImageIcon( Main.class.getClassLoader().getResource("./recurso/negro001.png" ) ) );
    }
}
