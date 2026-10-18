package itson.battleship.model;

import itson.battleship.enums.OrientacionNave;
import itson.battleship.enums.EstadoNave;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public abstract class Nave {

    private OrientacionNave orientacion;
    private EstadoNave estado;
    private int cantidadImpactos;

    public Nave(OrientacionNave orientacion) {
        this.orientacion = orientacion;
        this.estado = EstadoNave.SIN_DANOS;
        this.cantidadImpactos = 0;
    }

    public abstract int getLongitud();

    public void registrarImpacto() {
        cantidadImpactos++;
        if (cantidadImpactos >= getLongitud()) {
            estado = EstadoNave.HUNDIDA;
        } else {
            estado = EstadoNave.AVERIADA;
        }
    }

    public boolean estaHundida() {
        return estado == EstadoNave.HUNDIDA;
    }

    public OrientacionNave getOrientacion() {
        return orientacion;
    }

    public EstadoNave getEstado() {
        return estado;
    }

    public String getTipo() {
        return getClass().getSimpleName();
    }
}
