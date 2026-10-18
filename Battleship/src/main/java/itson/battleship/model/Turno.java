package itson.battleship.model;

import java.time.Duration;
import java.time.Instant;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class Turno {

    private int tiempoLimite;
    private Instant inicioTurno;
    private long milisRestantes;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugadorEnTurno;

    public Turno(Jugador jugador1, Jugador jugador2, int tiempoLimite) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.tiempoLimite = tiempoLimite;
        this.inicioTurno = Instant.now();
        this.milisRestantes = tiempoLimite * 1000L;
    }

    public void seleccionarPrimerTurno() {
        jugadorEnTurno = jugador1;
    }

    public void iniciarTiempo() {
        inicioTurno = Instant.now();
        milisRestantes = tiempoLimite * 1000L;
    }

    public void controlarTiempo() {
        long transcurrido = Duration.between(inicioTurno, Instant.now()).toMillis();
        milisRestantes = tiempoLimite * 1000L - transcurrido;
        if (milisRestantes < 0) {
            milisRestantes = 0;
        }
    }

    public int obtenerTiempoRestante() {
        controlarTiempo();
        return (int) (milisRestantes / 1000);
    }

    public boolean validarTiempoTurno() {
        controlarTiempo();
        return milisRestantes > 0;
    }

    public void cambiarTurno() {
        if (jugadorEnTurno == jugador1) {
            jugadorEnTurno = jugador2;
        } else {
            jugadorEnTurno = jugador1;
        }
        iniciarTiempo();
    }

    public void darTurnoExtra() {
        iniciarTiempo();
    }

    public Jugador obtenerJugadorEnTurno() {
        return jugadorEnTurno;
    }
}
