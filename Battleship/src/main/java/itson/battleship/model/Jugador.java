package itson.battleship.model;

import itson.battleship.enums.ColorJugador;
import itson.battleship.enums.EstadoJugador;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class Jugador {

    private String nombre;
    private EstadoJugador estado;
    private ColorJugador color;
    private Tablero tablero;

    public Jugador(String nombre, ColorJugador color, Tablero tablero) {
        this.nombre = nombre;
        this.color = color;
        this.tablero = tablero;
        this.estado = EstadoJugador.CONFIGURANDO;
    }

    public void personalizar(String nombre, ColorJugador color) {
        this.nombre = nombre;
        this.color = color;
    }

    public void confirmarTablero() {
        estado = EstadoJugador.LISTO;
    }

    public void abandonar() {
        estado = EstadoJugador.ABANDONADO;
    }

    public void setEstado(EstadoJugador estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public ColorJugador getColor() {
        return color;
    }

    public EstadoJugador getEstado() {
        return estado;
    }

    public Tablero getTablero() {
        return tablero;
    }
}
