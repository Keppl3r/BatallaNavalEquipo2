package itson.battleship.model;

import itson.battleship.enums.EstadoCasilla;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class Casilla {

    private EstadoCasilla estado;
    private Coordenadas coordenadas;
    private Nave nave;

    public Casilla(Coordenadas coordenadas) {
        this.coordenadas = coordenadas;
        this.estado = EstadoCasilla.SIN_DISPARAR;
        this.nave = null;
    }

    public boolean tieneNave() {
        return nave != null;
    }

    public Nave getNave() {
        return nave;
    }

    public void setNave(Nave nave) {
        this.nave = nave;
    }

    public void marcarDisparada() {
        estado = EstadoCasilla.DISPARADA;
    }

    public boolean estaDisparada() {
        return estado == EstadoCasilla.DISPARADA;
    }

    public EstadoCasilla getEstado() {
        return estado;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }
}