package itson.battleship.observer;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public interface Observado {

    void agregarObservador(Observador observador);

    void quitarObservador(Observador observador);

    void notificarObservadores();
}
