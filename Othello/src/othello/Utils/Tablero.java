/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello.Utils;

import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * @author user
 */
public class Tablero {
// ----------------------------------------------------------------------

    public static final int   CANTIDAD_FILAS_DEFECTO    = 8;
    public static final int   CANTIDAD_COLUMNAS_DEFECTO = 8;

    private int canidadColumnas ,
                cantidadFilas;

    private Casilla[][] matrizTablero;
    
// ----------------------------------------------------------------------


    /** Constructores *****************************************************/
    public Tablero(){
        this.cantidadFilas      = Tablero.CANTIDAD_FILAS_DEFECTO;
        this.canidadColumnas    = Tablero.CANTIDAD_COLUMNAS_DEFECTO;

        this.cagarMatrizTablero();
        this.restarurarTablero();
        
    }
    /**********************************************************************/


    /**
     * Carga instancias de Casilla en cada posición de la matriz tablero
     *
     * @autor gusamasan
     */
    private void cagarMatrizTablero(){
    // ------------------------------------------------------------------

        int columna, fila;

    // ------------------------------------------------------------------

        this.matrizTablero  = new Casilla[ this.cantidadFilas ][ this.canidadColumnas ];

        for( fila =0; fila < this.cantidadFilas; fila++ ){
            for( columna=0; columna < this.canidadColumnas; columna++ ){
                this.matrizTablero[ fila ][ columna ] = new Casilla( fila, columna );
            }
        }
        
    }


    /**
     * Restaura el tablero a la configuración inicial del juego
     *
     * @autor gusamasan
     */
    public void restarurarTablero(){
    // ----------------------------------------------------------------
        int columna, fila;
    // ----------------------------------------------------------------

        for( fila =0; fila < this.cantidadFilas; fila++ ){
            for( columna=0; columna < this.canidadColumnas; columna++ ){
                this.matrizTablero[ fila ][ columna ].deshabilitar();
            }
        }
        
        this.matrizTablero[ 3 ][ 3 ].asignarFichaBlanca();
        this.matrizTablero[ 3 ][ 4 ].asignarFichaNegra();
        this.matrizTablero[ 4 ][ 4 ].asignarFichaBlanca();
        this.matrizTablero[ 4 ][ 3 ].asignarFichaNegra();
    }

    /**
     * Retorna la cantidad de columnas que conforman el tablero
     *
     * @return <code>int</code> que representa la cantidad
     *
     * @autor gusamasan
     */
    public int getCanidadColumnas() {
        return canidadColumnas;
    }

    /**
     * Asigna la cantidad de columnas que conformarAn el tablero
     *
     * @param <code>int canidadColumnas</code> que representa la cantidad
     *
     * @autor gusamasan
     */
    public void setCanidadColumnas(int canidadColumnas) {
        this.canidadColumnas = canidadColumnas;
    }

    /**
     * Retorna la cantidad de filas que conforman el tablero
     *
     * @return <code>int</code> que representa la cantidad
     *
     * @autor gusamasan
     */
    public int getCantidadFilas() {
        return cantidadFilas;
    }

    /**
     * Asigna la cantidad de filas que conformarAn el tablero
     *
     * @param <code>int canidadFilas</code> que representa la cantidad
     *
     * @autor gusamasan
     */
    public void setCantidadFilas(int cantidadFilas) {
        this.cantidadFilas = cantidadFilas;
    }


    /**
     * Retorna la matriz que representa, internamente, al tablero
     *
     * @return matriz del tipo <code>Casilla[][]</code>
     *
     * @autor gusamasan
     */
    public Casilla[][] getMatrizTablero() {
        return matrizTablero;
    }

    
    /**
     * Asigna la matriz que representa, internamente, al tablero
     *
     * @param matriz del tipo <code>Casilla[][]</code>
     *
     * @autor gusamasan
     */
    public void setMatrizTablero(Casilla[][] matrizTablero) {
        this.matrizTablero = matrizTablero;
    }



    public boolean ponerFicha(Casilla cas) {
   	  ArrayList pivotes;
   	  Iterator it;
          int icon =  cas.obtenerColorFicha();
          

   	  //comprobar que el destino está vacío
   	  if (matrizTablero[cas.fila][cas.col].obtenerColorFicha() != Casilla.FICHA_TRANSPARENTE)
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
          if (icon == Casilla.FICHA_BLANCA)
              matrizTablero[cas.fila][cas.col].asignarFichaBlanca();
          else
               matrizTablero[cas.fila][cas.col].asignarFichaNegra();
   	  return true;
   }

    private ArrayList crearListaPivotes(Casilla cas, int icon) {
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

    private Casilla buscarPivote(Casilla cas, int incFila, int incCol, int icon) {
   	  int f,c;

          //a partir de dónde se empieza a buscar el pivote
   	  f = cas.fila + incFila;
   	  c = cas.col + incCol;

   	  //no buscar el pivote fuera del tablero
   	  if ((f<0)||(f>7)||(c<0)||(c>7))
   	     return null;

   	  //tiene que haber al menos una ficha del color contrario que "flanquear"
   	  //entre la nueva ficha y el pivote
   	  if ((matrizTablero[f][c].obtenerColorFicha() == Casilla.FICHA_TRANSPARENTE)||((matrizTablero[f][c].obtenerColorFicha()==icon)))
   	     return null;

   	  //buscar y devolver posición del pivote
   	  while ((f>=0)&&(f<8)&&(c>=0)&&(c<8)) {
         //hemos llegado a una casilla vacía, no hay pivote
   	     if (matrizTablero[f][c].obtenerColorFicha() == Casilla.FICHA_TRANSPARENTE)
   	        return null;
   	     //pivote encontrado (ficha del mismo color)
   	     if (matrizTablero[f][c].obtenerColorFicha()==icon)
   	        return new Casilla(f,c);
   	     //siguiente casilla
             f += incFila;
   	     c += incCol;
   	  }

   	  //no se ha encontrado pivote
   	  return null;
   }

    private void voltearFichas(Casilla cas, Casilla pivote, int icon) {

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
                 if(icon==Casilla.FICHA_BLANCA)
                 {
                     matrizTablero[f][c].asignarFichaBlanca();
                 }
                 if(icon==Casilla.FICHA_NEGRA)
                 {
                     matrizTablero[f][c].asignarFichaNegra();
                 }
              }
   }


     public boolean  EsFinalDeJuego()
    {
        if (PuedeJugar(Casilla.FICHA_NEGRA) || PuedeJugar(Casilla.FICHA_BLANCA))
            return false;
        else
            return true;
    }

    public boolean  PuedeJugar(int player)
      {
        int x;
        int y;

        for (x = 0; x < CANTIDAD_FILAS_DEFECTO; x++)
        {
            for (y = 0; y < CANTIDAD_COLUMNAS_DEFECTO; y++)
            {    Casilla cas=new Casilla(x,y);
                 if (Casilla.FICHA_BLANCA==player)
                 { cas.asignarFichaBlanca();
                 }
                 if (Casilla.FICHA_NEGRA==player) {
                    cas.asignarFichaNegra();
                 }
                if (movLegal(cas))
                    return true;
            }
        }

        return false;
      }
      public boolean movLegal(Casilla c) {
   	      int incFila, incCol;

              //comprobar que la casilla esté vacía
              if (!matrizTablero[c.fila][c.col].estaVacia())
                 return false;

                  //recorrer las 8 direcciones posibles
                  for (incFila=-1; incFila<=1; incFila++)
                     for (incCol=-1; incCol<=1; incCol++)
                        if ((incCol!=0)||(incFila!=0)) {
                           //buscar un pivote en esa dirección
                           if (buscarPivote( c, incFila, incCol, c.obtenerColorFicha())!=null)
                              return true;
                        }
              return false;
      }


    public ArrayList<Casilla> generarMovimiento(int jugador)
    {
        ArrayList<Casilla> movimientos = new ArrayList<Casilla>();

        for (int x = 0; x < CANTIDAD_FILAS_DEFECTO; x++)
        {
            for (int y = 0; y < CANTIDAD_COLUMNAS_DEFECTO; y++)
            {
                int value = valorMovimiento(this.matrizTablero[x][y], jugador);
                if (value > 0)
                    movimientos.add(new Casilla(x, y));
            }
        }
        return movimientos;
    }

    public int valorMovimiento(Casilla cas, int jugador)
        {
            //If the space is occupied, return 0
            if (cas.obtenerColorFicha() != Casilla.FICHA_TRANSPARENTE)
                return 0;

            int value = 0;
            int xDireccion; //direccion Horizontal
            int yDireccion; //direccion Vertical
            int distancia; //distancia
            int xTemp;
            int yTemp;

            for (xDireccion = -1; xDireccion <= 1; xDireccion++)
            {
                for (yDireccion = -1; yDireccion <= 1; yDireccion++)
                {
                    if (!(xDireccion == 0 && yDireccion == 0))
                    {
                        distancia = 1;
                        xTemp = cas.fila + xDireccion;
                        yTemp = cas.col + yDireccion;
                        while (espacioValido(xTemp, yTemp) && matrizTablero[xTemp][yTemp].obtenerColorFicha() == -jugador)
                        {
                            distancia++;
                            xTemp += xDireccion;
                            yTemp += yDireccion;
                        }
                        if (distancia > 1 && espacioValido(xTemp, yTemp) && matrizTablero[xTemp][yTemp].obtenerColorFicha() == jugador)
                            value += distancia - 1;
                    }
                }
            }

            return value;
        }

     public boolean  espacioValido(int fila, int col)
    {
        if (fila < 0 || col < 0 || fila >= CANTIDAD_FILAS_DEFECTO || col >= CANTIDAD_COLUMNAS_DEFECTO)
            return false;
        else
            return true;
    }
     public void imprimirTablero()
     {
         System.out.println();
         for (int x = 0 ; x < CANTIDAD_FILAS_DEFECTO ; x ++ )
         {
             for (int y = 0 ; y < CANTIDAD_COLUMNAS_DEFECTO ; y ++ )
             {
                 if(matrizTablero[x][y].obtenerColorFicha() > -1) System.out.print(" "+matrizTablero[x][y].obtenerColorFicha() + " ");
                 else                                             System.out.print(matrizTablero[x][y].obtenerColorFicha() + " ");
             }
             System.out.println();
         }
     }

     public Tablero copiarTablero()
     {
         Tablero nuevoTablero=new Tablero();
         Casilla[][] nuevaCasillas= new Casilla[CANTIDAD_FILAS_DEFECTO][CANTIDAD_COLUMNAS_DEFECTO];
         for(int  fila =0; fila < this.CANTIDAD_FILAS_DEFECTO; fila++ ){

            for(int  columna =0; columna < this.CANTIDAD_COLUMNAS_DEFECTO; columna++ ){
                nuevaCasillas[fila][columna]= (matrizTablero[fila][columna]).copiarCasilla();

            }
         }
         nuevoTablero.setMatrizTablero(nuevaCasillas);
         return nuevoTablero;
     }
     
     
    public int cantidad(int ficha)
    {
        int puntos = 0;

        for (int x = 0; x < Tablero.CANTIDAD_FILAS_DEFECTO; x++){
            for (int y = 0; y < Tablero.CANTIDAD_COLUMNAS_DEFECTO; y++){
                if (matrizTablero[x][y].obtenerColorFicha() == ficha){
                    puntos++;
                }
            }
        }
        
        return puntos;
    }
}
