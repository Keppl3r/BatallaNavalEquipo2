package itson.battleship.model;

import itson.battleship.enums.ResultadoDisparo;
import itson.battleship.enums.EstadoPartida;
import itson.battleship.enums.EstadoNave;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public interface IModel {

    EstadoPartida obtenerEstadoPartida();

    Jugador obtenerGanador();

    Jugador obtenerJugadorEnTurno();

    int obtenerTiempoRestante();

    ResultadoDisparo obtenerResultadoDisparo();

    EstadoNave obtenerEstadoNaveImpactada();

    String obtenerTipoNaveImpactada();

    boolean hayPosicionInvalida();

    boolean hayTiempoAgotado();

    boolean esCoordenadaDentroDelTablero(Coordenadas coord);
}
