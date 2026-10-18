package itson.battleship.model;

import itson.battleship.enums.OrientacionNave;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class Crucero extends Nave {

    public Crucero(OrientacionNave orientacion) {
        super(orientacion);
    }

    @Override
    public int getLongitud() {
        return 3;
    }
}
