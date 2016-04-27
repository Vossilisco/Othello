/*
 */

package othello.gui;

import othello.Utils.Casilla;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import othello.gui.accion.AccionJugadaHumano;
import othello.Utils.Tablero;

/**
 *
 * @author gusamasan
 */
public class TableroGUI extends JPanel{
// ------------------------------------------------------------------------

    private OthelloGUI  othelloGUI;
    
    private int cantidadColumnas    ,
                cantidadFilas;

    public int turno;

    public static final int   CANTIDAD_FILAS_DEFECTO    = 8;
    public static final int   CANTIDAD_COLUMNAS_DEFECTO = 8;
    public static final int HUMANO = 1;
    public static final int MAQUINA = 2;

    private Escaque[][] contenedorEscaques;

    private Tablero tablero;
    
// ------------------------------------------------------------------------

    /** Constructores *****************************************************/
    public TableroGUI( OthelloGUI othello ){
        super();

        this.othelloGUI = othello;
        //this.
        //java.awt.Dimension tamanio;

        //tamanio = new java.awt.Dimension( 500, 600 );
        
        //this.setPreferredSize( tamanio );

        turno = HUMANO;

        this.tablero    = new Tablero();
        this.inicializar();
        this.habilitarDeshabilitarTablero( false );
    }
    /**********************************************************************/

    private void colocarPrimerasFichas(){
        this.contenedorEscaques[3][3].mostrarFichaBlanca();
        this.contenedorEscaques[3][4].mostrarFichaNegra();
        this.contenedorEscaques[4][3].mostrarFichaNegra();
        this.contenedorEscaques[4][4].mostrarFichaBlanca();
    }

    private void inicializar(){
    // ------------------------------------------------------------------------

    // ------------------------------------------------------------------------

        this.cantidadFilas      = TableroGUI.CANTIDAD_FILAS_DEFECTO;
        this.cantidadColumnas   = TableroGUI.CANTIDAD_COLUMNAS_DEFECTO;
        

        this.setLayout( new GridLayout( this.cantidadFilas + 1,
                                        this.cantidadColumnas + 1 )
                       );

        contenedorEscaques  = new Escaque[ this.cantidadFilas ][ this.cantidadColumnas ];

        this.rellenarTablero();

        this.colocarPrimerasFichas();
        
        //agregarManejadorDeClick();
    }

    public Tablero getTablero(){
        return( this.tablero );
    }

    public void setTablero( Tablero pTablero ){
        this.tablero    = pTablero;
    }

    public void refrescarTablero(){
        this.refrescarTablerGUI();
    }

    public void refrescarTablerGUI(){
    // ------------------------------------------------------------------------

        int columna, fila;

        Casilla[][] matrizTablero;

    // ------------------------------------------------------------------------

        
        matrizTablero   = this.tablero.getMatrizTablero();

        for( fila =0; fila < this.cantidadFilas; fila++ ){

            for( columna =0; columna < this.cantidadColumnas; columna++ ){

                if( !matrizTablero[ fila ][ columna ].estaVacia() ){
                    if( matrizTablero[ fila ][ columna ].esNegra() )
                        this.contenedorEscaques[ fila ][ columna ].mostrarFichaNegra();                    
                    else
                        this.contenedorEscaques[ fila ][ columna ].mostrarFichaBlanca();                    
                }
                else
                    this.contenedorEscaques[ fila ][ columna ].limpiar();                

            }
        }
        
    }
    
    public void reiniciarTablero(){
    // ------------------------------------------------------------------------

        int columna, fila;

    // ------------------------------------------------------------------------

        for( fila =0; fila < this.cantidadFilas; fila++ ){
            
            for( columna =0; columna < this.cantidadColumnas; columna++ ){
                this.contenedorEscaques[ fila ][ columna ].limpiar();
            }
        }

        this.colocarPrimerasFichas();
    }

    
    private void rellenarTablero(){
    // ------------------------------------------------------------------------        
        int indice, fila, columna;

        Escaque unEscaque;

        String abecedario[]   = {"A", "B", "C", "D", "E", "F", "G", "H" };
    // ------------------------------------------------------------------------

        this.add( new JLabel( "" ) );

        // Agregando primera lInea (letras)
        for( indice=0; indice < this.cantidadFilas; indice++ )
            this.add( new JLabel("" + abecedario[ indice ], JLabel.CENTER ) );

        // Agregando los escaques (como botones por defecto)
        for( fila= 0; fila < this.cantidadFilas; fila++ ){
            
            // Agregando primera columna (nUmeros)
            this.add( new JLabel( "" + ( fila + 1 ), JLabel.RIGHT ) );
            for( columna= 0; columna < this.cantidadColumnas; columna++ ){
                
                    unEscaque   = new Escaque( fila, columna );
                    unEscaque.addActionListener( new AccionJugadaHumano( this.othelloGUI ) );
                    //unEscaque.setBackground( new java.awt.Color( 255, 255, 255 ) );
                    this.contenedorEscaques[ fila ][ columna ] = unEscaque;                    
                    this.add( unEscaque );
                    
            }
        }
    }

    private void agregarManejadorDeClick() {
        for( int fila= 0; fila < this.cantidadFilas; fila++ ){
            for( int columna= 0; columna < this.cantidadColumnas; columna++ ){
                final int f = fila;
                final int c = columna;
                contenedorEscaques[fila][columna].addMouseListener( new MouseListener() {

                    @Override
                    public void mouseClicked(MouseEvent e) {
                            Casilla cas = new Casilla(f,c);
                            comprobarJugada(cas);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }
        }
    }
    private void comprobarJugada(Casilla cas) {
        if (ponerFicha(cas)) {
            //actualizar tablero en el GUI
            repaint();
            cambiarTurno();
        }
    }

    public void cambiarTurno() {
      if (turno==HUMANO)
         turno = MAQUINA;
      else
         turno = HUMANO;
   }

    public boolean ponerFicha(Casilla cas) {
   	  ArrayList pivotes;
   	  Iterator it;
          Icon icon =  null;
          if (turno == HUMANO)
              icon = new ImageIcon( Main.class.getClassLoader().getResource("./recurso/negro001.png" ));
          else
              icon = new ImageIcon( Main.class.getClassLoader().getResource("./recurso/blanco001.png" ));

   	  //comprobar que el destino está vacío
   	  if (contenedorEscaques[cas.fila][cas.col].getIcon() != null)
   	     return false;

   	  //buscar la/s ficha/s que hace/n de pivote
   	  pivotes = crearListaPivotes(cas, icon);

   	  //si no hay pivotes, el movimiento no es válido
   	  if (pivotes.isEmpty())
   	     return false;

   	  //si hay pivotes, se deben cambiar de color las fichas entre la posición
   	  //actual y cada uno de los pivotes
   	  it = pivotes.iterator();
   	  while (it.hasNext()) {
   	     voltearFichas(cas, (Casilla)it.next(),icon);
   	  }

   	  //colocar la nueva ficha en el tablero
          if (turno == HUMANO)
              contenedorEscaques[cas.fila][cas.col].mostrarFichaNegra();
          else
              contenedorEscaques[cas.fila][cas.col].mostrarFichaBlanca();
   	  return true;
   }

    private ArrayList crearListaPivotes(Casilla cas, Icon icon) {
   	  ArrayList pivotes;
   	  Casilla piv;
   	  int incFila, incCol;

   	  pivotes = new ArrayList();
   	  //recorrer las 8 direcciones posibles
   	  for (incFila=-1; incFila<=1; incFila++)
   	     for (incCol=-1; incCol<=1; incCol++)
   	        if ((incCol!=0)||(incFila!=0)) {
   	           //buscar un pivote en esa dirección
   	           piv = buscarPivote(cas, incFila, incCol, icon);
   	           if (piv!=null)
   	              pivotes.add(piv);
   	        }

      return pivotes;
   }

    private Casilla buscarPivote(Casilla cas, int incFila, int incCol, Icon icon) {
   	  int f,c;

          //a partir de dónde se empieza a buscar el pivote
   	  f = cas.fila + incFila;
   	  c = cas.col + incCol;

   	  //no buscar el pivote fuera del tablero
   	  if ((f<0)||(f>7)||(c<0)||(c>7))
   	     return null;

   	  //tiene que haber al menos una ficha del color contrario que "flanquear"
   	  //entre la nueva ficha y el pivote
   	  if ((contenedorEscaques[f][c].getIcon() == null)||((contenedorEscaques[f][c].getIcon().toString().compareTo(icon.toString()) == 0)))
   	     return null;

   	  //buscar y devolver posición del pivote
   	  while ((f>=0)&&(f<8)&&(c>=0)&&(c<8)) {
         //hemos llegado a una casilla vacía, no hay pivote
   	     if (contenedorEscaques[f][c].getIcon() == null)
   	        return null;
   	     //pivote encontrado (ficha del mismo color)
   	     if (contenedorEscaques[f][c].getIcon().toString().compareTo(icon.toString()) == 0)
   	        return new Casilla(f,c);
   	     //siguiente casilla
             f += incFila;
   	     c += incCol;
   	  }

   	  //no se ha encontrado pivote
   	  return null;
   }

    public void deshabilitarTablero(){
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------

        this.habilitarDeshabilitarTablero( false );
    }

    public void habilitarTablero(){
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------

        this.habilitarDeshabilitarTablero( true );
    }


    private void habilitarDeshabilitarTablero( boolean habilitado ){
    // ------------------------------------------------------------------------

        int columna, fila;

    // ------------------------------------------------------------------------

        for( fila=0; fila < this.cantidadFilas; fila++ ){
            for( columna=0; columna < this.cantidadColumnas; columna++ ){
                this.contenedorEscaques[ fila ][ columna ].setEnabled( habilitado );                
            }
        }
    }

    private void voltearFichas(Casilla cas, Casilla pivote, Icon icon) {

                  int incFila, incCol;
                  int numFilas, numCols;
                  int numVolteos;
                  int i, f, c;

                  numFilas = pivote.fila - cas.fila;
                  numCols = pivote.col - cas.col;
                  //ver la dirección del pivote con respecto a la nueva ficha
                  if (numFilas!=0)
                 incFila = numFilas/Math.abs(numFilas);
              else
                 incFila = 0;
              if (numCols!=0)
                 incCol = numCols/Math.abs(numCols);
              else
                 incCol = 0;

              numVolteos = Math.max(Math.abs(numFilas), Math.abs(numCols))-1;
              f = cas.fila;
              c = cas.col;
              //"dar la vuelta" a las fichas
              for(i=0; i<numVolteos; i++)
              {
                 f += incFila;
                 c += incCol;
                 contenedorEscaques[f][c].setIcon(icon);
              }
   }
}
