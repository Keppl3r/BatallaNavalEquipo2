package itson.battleship.model;

import itson.battleship.enums.OrientacionNave;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class Barco extends Nave {

    public Barco(OrientacionNave orientacion) {
        super(orientacion);
    }

    @Override
    public int getLongitud() {
        return 1;
    }
}
