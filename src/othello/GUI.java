/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package othello;


import othello.Utils.Tablero;

/**
 *
 * @author gusamasan
 */
public interface GUI {

    public void avisarTurnoFichaBlanca();

    public void avisarTurnoFichaNegra();

    public void avisarPasoTurnoFichaBlanca();

    public void avisarPasoTurnoFichaNegra();
    
    public Tablero getTablero();

    public void setTablero( Tablero tablero );

    public void limpiarAvisoPasoTurno();

    public void refrescarTablero();
    
    public void juegaCpu();
    
    public void juegaHumano();
    
    public void actualizarBlancas(int num);
    
    public void actualizarNegras(int num);
    
    public void setEstadoBlancas(String texto);
    
    public void setEstadoNegras(String texto);
    
    public void finDelJuegoGui();
    
    public void mostrarAlerta(String texto);
}
