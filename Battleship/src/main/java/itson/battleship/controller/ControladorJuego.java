package itson.battleship.controller;

import itson.battleship.model.Coordenadas;
import itson.battleship.model.IPartida;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class ControladorJuego implements IControlador {

    private IPartida partida;

    public ControladorJuego(IPartida partida) {
        this.partida = partida;
    }

    @Override
    public void seleccionarDisparo(Coordenadas coord) {
        partida.realizarDisparo(coord);
    }
}
