package itson.battleship.model;

import itson.battleship.enums.OrientacionNave;
import itson.battleship.enums.EstadoCasilla;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class Tablero {

    private int filas;
    private int columnas;
    private Casilla[][] casillas;
    private List<Nave> naves;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.casillas = new Casilla[filas][columnas];
        this.naves = new ArrayList<>();
        for (int y = 0; y < filas; y++) {
            for (int x = 0; x < columnas; x++) {
                casillas[y][x] = new Casilla(new Coordenadas(x, y));
            }
        }
    }

    public boolean validarCoordenada(Coordenadas coord) {
        if (coord.getX() < 0 || coord.getX() >= columnas) {
            return false;
        }
        if (coord.getY() < 0 || coord.getY() >= filas) {
            return false;
        }
        return true;
    }

    public boolean colocarNave(Nave nave, Coordenadas coord) {
        int pasoX = 0;
        int pasoY = 0;
        if (nave.getOrientacion() == OrientacionNave.HORIZONTAL) {
            pasoX = 1;
        } else {
            pasoY = 1;
        }

        for (int i = 0; i < nave.getLongitud(); i++) {
            Coordenadas actual = new Coordenadas(coord.getX() + i * pasoX, coord.getY() + i * pasoY);
            if (!validarCoordenada(actual)) {
                return false;
            }
            if (hayNaveCerca(actual)) {
                return false;
            }
        }

        for (int i = 0; i < nave.getLongitud(); i++) {
            Coordenadas actual = new Coordenadas(coord.getX() + i * pasoX, coord.getY() + i * pasoY);
            getCasilla(actual).setNave(nave);
        }
        naves.add(nave);
        return true;
    }

    private boolean hayNaveCerca(Coordenadas coord) {
        for (int dy = -1; dy <= 1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                Coordenadas vecina = new Coordenadas(coord.getX() + dx, coord.getY() + dy);
                if (validarCoordenada(vecina) && getCasilla(vecina).tieneNave()) {
                    return true;
                }
            }
        }
        return false;
    }

    public Casilla getCasilla(Coordenadas coord) {
        return casillas[coord.getY()][coord.getX()];
    }

    public boolean existeDisparoPrevio(Coordenadas coord) {
        return getCasilla(coord).estaDisparada();
    }

    public Nave buscarNaveEnCasilla(Coordenadas coord) {
        return getCasilla(coord).getNave();
    }

    public EstadoCasilla marcarCasilla(Coordenadas coord) {
        Casilla casilla = getCasilla(coord);
        casilla.marcarDisparada();
        return casilla.getEstado();
    }

    public EstadoCasilla obtenerEstadoCasilla(Coordenadas coord) {
        return getCasilla(coord).getEstado();
    }

    public int contarNavesSinHundir() {
        int cantidad = 0;
        for (Nave nave : naves) {
            if (!nave.estaHundida()) {
                cantidad++;
            }
        }
        return cantidad;
    }
}
