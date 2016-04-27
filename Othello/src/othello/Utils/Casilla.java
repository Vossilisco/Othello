/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello.Utils;

/**
 *
 * @author Lamia
 */

public class Casilla{      
   // ------------------------------------------------------------------------

    private boolean blancoHabilitado,negroHabilitado;

    public int fila;
    public int col;

    public static short FICHA_NEGRA         = -1;
    public static short FICHA_BLANCA        = 1;
    public static short FICHA_TRANSPARENTE  = 0;

    private short   colorFicha;

// ------------------------------------------------------------------------

    /** Constructores ****************************************************/
    public Casilla(){
        this.colorFicha = Casilla.FICHA_TRANSPARENTE;

        this.blancoHabilitado   = false;
        this.negroHabilitado    = false;
    }

    public Casilla(int fila, int col){
      this.fila = fila;
      this.col  = col;
      this.colorFicha = Casilla.FICHA_TRANSPARENTE;

      this.blancoHabilitado   = false;
      this.negroHabilitado    = false;
    }
    /*********************************************************************/


    public void deshabilitar(){
        this.colorFicha         = Casilla.FICHA_TRANSPARENTE;
        this.blancoHabilitado   = false;
        this.negroHabilitado    = false;
    }

    /**
     * Indica si la casilla está habilitada o no para colocar en ella una ficha
     * BLANCA
     *
     * @return <code>true</code> si está habilitada; <code>false</code> en caso contrario
     *
     * @autor gusamasan
     */
    public boolean  fichaBlancaHabilitada(){
        return( this.blancoHabilitado );
    }


    /**
     * Indica si la casilla está habilitada o no para colocar en ella una ficha
     * NEGRA
     *
     * @return <code>true</code> si está habilitada; <code>false</code> en caso contrario
     *
     * @autor gusamasan
     */
    public boolean fichaNegraHabilitada(){
        return( this.fichaNegraHabilitada() );
    }

    /**
     * Indica si la casilla está habilitada o no para colocar en ella una ficha
     *
     * @return <code>true</code> si está habilitada; <code>false</code> en caso contrario
     *
     * @autor gusamasan
     */
    public boolean estaHabilitada(){
        return( this.fichaBlancaHabilitada() || this.fichaNegraHabilitada() );
    }

    /**
     * Indica si la casilla representa o no a una ficha negra
     *
     * @return <code>true</code> si la ficha representa a una ficha negra;
     *          <code>false</code> en caso contrario
     *
     * @autor gusamasan
     */
    public boolean esNegra(){

        if( this.colorFicha == Casilla.FICHA_NEGRA )
            return( true );
        else
            return( false );
    }

    /**
     * Indica si la casilla representa o no a una ficha blanca
     *
     * @return <code>true</code> si la ficha representa a una ficha blanca;
     *          <code>false</code> en caso contrario
     *
     * @autor gusamasan
     */
    public boolean esBlanca(){
        if( this.colorFicha == Casilla.FICHA_BLANCA )
            return( true );
        else
            return( false );
    }


    /**
     * Indica si la casilla está o no vacía.
     *
     * @return <code>true</code> si la casilla está vacía; <code>false</code> en
     *          caso contrario
     */
    public boolean estaVacia(){
        if( this.colorFicha == Casilla.FICHA_TRANSPARENTE )
            return( true );
        else
            return( false );
    }

    /**
     * Asigna una ficha blanca a la instancia de esta clase
     *
     * @autor gusamasan
     */
    public void asignarFichaBlanca(){        
        this.deshabilitar();
        this.colorFicha = Casilla.FICHA_BLANCA;
    }

    /**
     * Asigna una ficha negra a la instancia de esta clase
     *
     * @autor gusamasan
     */
    public void asignarFichaNegra(){        
        this.deshabilitar();
        this.colorFicha = Casilla.FICHA_NEGRA;
    }


    /**
     * Retorna el color asignado a la ficha de esta casilla
     *
     * @return <code>short</code> que representa el color
     */
    public short obtenerColorFicha(){
    // ----------------------------------------------------------------------
    // ----------------------------------------------------------------------

        return( this.colorFicha );
    }

    /**
     * Indica si la Casilla pasada como parAmetro tiene el mismo color de ficha
     * que la asignada a esta casilla
     *
     * @param   objeto <code>Casilla</code> que representa la casilla cuyo color
     *          se desea comparar
     * 
     * @return  <code>true</code> si tienen fichas con el mismo color;<code>false</code>
     *          en caso contrario
     */
    public boolean tieneElMismoColorDe( Casilla otraCasilla ){

        if( this.colorFicha == otraCasilla.obtenerColorFicha() )
            return( true );
        else
            return( false );
    }


    /**
     * Voltea o cambia el color de la ficha representada por la casilla; si el
     * color actual es negro, asigna el color blanco; en caso contrario, asigna
     * el color negro.
     *
     * @autor gusamasan
     */
    public void voltear(){
        if( this.colorFicha == Casilla.FICHA_BLANCA )
            this.colorFicha = Casilla.FICHA_NEGRA;
        else
            this.colorFicha = Casilla.FICHA_BLANCA;
    }

    public Casilla copiarCasilla() {
        Casilla casillanueva=new Casilla(this.fila, this.col);
        if(obtenerColorFicha() == FICHA_BLANCA)
            casillanueva.asignarFichaBlanca();
        else if(obtenerColorFicha() == FICHA_NEGRA)
            casillanueva.asignarFichaNegra();

        return casillanueva;

    }

   
}