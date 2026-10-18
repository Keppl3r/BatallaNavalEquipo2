package itson.battleship.model;

import itson.battleship.enums.ResultadoDisparo;
import itson.battleship.enums.EstadoNave;
import java.time.Instant;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class Disparo {

    private Coordenadas coordenadas;
    private ResultadoDisparo resultado;
    private Instant tiempo;
    private Tablero tablero;
    private Nave naveImpactada;
    private boolean naveHundida;

    public Disparo(Coordenadas coordenadas, Tablero tablero) {
        this.coordenadas = coordenadas;
        this.tablero = tablero;
        this.tiempo = Instant.now();
        this.naveImpactada = null;
        this.naveHundida = false;
    }

    public boolean validarPosicion() {
        if (!tablero.validarCoordenada(coordenadas)) {
            return false;
        }
        if (tablero.existeDisparoPrevio(coordenadas)) {
            return false;
        }
        return true;
    }

    public ResultadoDisparo evaluarDisparo() {
        Nave nave = tablero.buscarNaveEnCasilla(coordenadas);
        if (nave == null) {
            resultado = ResultadoDisparo.AGUA;
            return resultado;
        }
        nave.registrarImpacto();
        naveHundida = nave.estaHundida();
        naveImpactada = nave;
        resultado = ResultadoDisparo.IMPACTO;
        return resultado;
    }

    public ResultadoDisparo getResultado() {
        return resultado;
    }

    public Coordenadas getCoordenadas() {
        return coordenadas;
    }

    public Instant getTiempo() {
        return tiempo;
    }

    public Nave getNaveImpactada() {
        return naveImpactada;
    }

    public EstadoNave getEstadoNaveImpactada() {
        if (naveImpactada == null) {
            return null;
        }
        if (naveHundida) {
            return EstadoNave.HUNDIDA;
        }
        return EstadoNave.AVERIADA;
    }
}