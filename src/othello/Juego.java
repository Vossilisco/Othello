/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package othello;

import othello.Utils.Tablero;
import othello.jugador.Jugador;


public class Juego extends Thread {
// ----------------------------------------------------------------------------

    private GUI intefazGUI;

    private boolean pararJuego;

    private short turno;

    private Jugador jugadorConFichaBlanca,
            jugadorConFichaNegra,
            jugadorDeTurno;

    private Tablero tableroFichaBlanca, tableroFichaNegra;

// ----------------------------------------------------------------------------
    /**
     * Constructores *******************************************************
     */
    public Juego(GUI gui) {
        this.intefazGUI = gui;
        this.pararJuego = false;
    }

    /**
     * **********************************************************************
     */

    public static void esperarUnRato() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Jugador getJugadorConFichaBlanca() {
        return jugadorConFichaBlanca;
    }

    public void setJugadorConFichaBlanca(Jugador jugadorConFichaBlanca) {
        this.jugadorConFichaBlanca = jugadorConFichaBlanca;
    }

    public Jugador getJugadorConFichaNegra() {
        return jugadorConFichaNegra;
    }

    public void setJugadorConFichaNegra(Jugador jugadorConFichaNegra) {
        this.jugadorConFichaNegra = jugadorConFichaNegra;
    }

    public Jugador getJugadorDeTurno() {
        return jugadorDeTurno;
    }

    public void setJugadorDeTurno(Jugador jugadorDeTurno) {
        this.jugadorDeTurno = jugadorDeTurno;
    }

    public boolean estaParado() {
        return (this.pararJuego);
    }

    @Override
    public void run() {

        jugar();

    }

    public void jugar() {
        // ------------------------------------------------------------------------

        Tablero nuevoTablero;

    // ------------------------------------------------------------------------
        this.pararJuego = false;
        this.turno = ConstanteOthello.TURNO_FICHA_NEGRA;

        while (!pararJuego) {

            /**/
            System.out.println("********************************");

            nuevoTablero = jugadorDeTurno.getTablero();

            nuevoTablero.imprimirTablero();

            System.out.println("*********************************");

            if (nuevoTablero.PuedeJugar(turno)) {
                nuevoTablero = this.jugadorDeTurno.jugar(turno);
            } else {
                //nuevoTablero = null;
                this.intefazGUI.mostrarAlerta("El jugador con ficha " + this.jugadorDeTurno.getColorFichaAsignada() + " ha pasado de turno!");

                nuevoTablero = this.jugadorDeTurno.jugar(turno);

            }

            System.out.println("##################################");

            if (nuevoTablero != null) {
                nuevoTablero.imprimirTablero();
            }

            System.out.println("##################################");

            /**/
            if (!pararJuego && nuevoTablero != null) {

                this.intefazGUI.setTablero(nuevoTablero);
                this.intefazGUI.refrescarTablero();

                avisarGUI(nuevoTablero);

                if (turno == ConstanteOthello.TURNO_FICHA_NEGRA) {

                    this.jugadorDeTurno = this.jugadorConFichaBlanca;
                    this.intefazGUI.avisarTurnoFichaBlanca();
                    turno = ConstanteOthello.TURNO_FICHA_BLANCA;
                    this.tableroFichaNegra = nuevoTablero;
                } else {  // Mueve la blanca OK
                    this.jugadorDeTurno = this.jugadorConFichaNegra;
                    this.intefazGUI.avisarTurnoFichaNegra();
                    turno = ConstanteOthello.TURNO_FICHA_NEGRA;
                    this.tableroFichaBlanca = nuevoTablero;
                }

                this.jugadorDeTurno.setTablero(nuevoTablero);
            } else { // pasa de turno
        if( turno == ConstanteOthello.TURNO_FICHA_NEGRA ){
            this.jugadorDeTurno = this.jugadorConFichaBlanca;
            this.intefazGUI.avisarTurnoFichaBlanca();
            turno = ConstanteOthello.TURNO_FICHA_BLANCA;
            this.tableroFichaNegra = null;
            this.jugadorDeTurno.setTablero( this.tableroFichaBlanca );
        } else {
            this.jugadorDeTurno = this.jugadorConFichaNegra;
            this.intefazGUI.avisarTurnoFichaNegra();
            turno = ConstanteOthello.TURNO_FICHA_NEGRA;
            this.tableroFichaBlanca = null;
            this.jugadorDeTurno.setTablero( this.tableroFichaNegra );
        }
        if(this.tableroFichaNegra == null && this.tableroFichaBlanca == null) {
            pararJuego = true;
            this.intefazGUI.finDelJuegoGui();
        }
    }

            this.esperarUnRato();
            this.intefazGUI.limpiarAvisoPasoTurno();

        }
    }

    public void pararJuego() {
        this.pararJuego = true;
    }

    public void avisarGUI(Tablero tablero) {

        // Avisar a quien le toca jugar humano/maquina
        if (this.jugadorConFichaBlanca == this.jugadorDeTurno) {
            if (this.jugadorConFichaNegra.esHumano()) {
                this.intefazGUI.juegaHumano();
            } else {
                this.intefazGUI.juegaCpu();
            }
        } else {
            if (this.jugadorConFichaBlanca.esHumano()) {
                this.intefazGUI.juegaHumano();
            } else {
                this.intefazGUI.juegaCpu();
            }
        }

        this.intefazGUI.actualizarBlancas(tablero.cantidad(1));
        this.intefazGUI.actualizarNegras(tablero.cantidad(-1));

        boolean puedeJugarB = tablero.PuedeJugar(ConstanteOthello.TURNO_FICHA_BLANCA);
        boolean puedeJugarN = tablero.PuedeJugar(ConstanteOthello.TURNO_FICHA_NEGRA);

        if (puedeJugarB) {
            this.intefazGUI.setEstadoBlancas("Blancas: Correcto");
        } else {
            this.intefazGUI.setEstadoBlancas("Blancas: No puede mover");
        }
        if (puedeJugarN) {
            this.intefazGUI.setEstadoNegras("Negras: Correcto");
        } else {
            this.intefazGUI.setEstadoNegras("Negras: No puede mover");
        }

        if (!puedeJugarB && !puedeJugarN) {
            this.pararJuego = true;
            this.intefazGUI.finDelJuegoGui();

        }

    }

}
