package itson.battleship;

import itson.battleship.controller.ControladorJuego;
import itson.battleship.controller.IControlador;
import itson.battleship.model.Barco;
import itson.battleship.enums.ColorJugador;
import itson.battleship.model.Coordenadas;
import itson.battleship.model.Crucero;
import itson.battleship.enums.EstadoPartida;
import itson.battleship.model.Jugador;
import itson.battleship.enums.OrientacionNave;
import itson.battleship.model.Partida;
import itson.battleship.model.PortaAviones;
import itson.battleship.model.Submarino;
import itson.battleship.model.Tablero;
import itson.battleship.view.PantallaPrincipal;
import java.util.Scanner;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class main {

    public static void main(String[] args) {
        Jugador jugador1 = new Jugador("Jugador 1", ColorJugador.AZUL, armarTablero1());
        Jugador jugador2 = new Jugador("Jugador 2", ColorJugador.ROJO, armarTablero2());
        jugador1.confirmarTablero();
        jugador2.confirmarTablero();

        Partida partida = new Partida("Batalla Naval", jugador1, jugador2, 30);
        IControlador controlador = new ControladorJuego(partida);
        Scanner scanner = new Scanner(System.in);

        partida.iniciar();

        PantallaPrincipal pantalla1 = new PantallaPrincipal(controlador, partida, jugador1, scanner);
        PantallaPrincipal pantalla2 = new PantallaPrincipal(controlador, partida, jugador2, scanner);
        partida.agregarObservador(pantalla1);
        partida.agregarObservador(pantalla2);

        while (partida.obtenerEstadoPartida() == EstadoPartida.EN_CURSO) {
            if (partida.obtenerJugadorEnTurno() == jugador1) {
                pantalla1.iniciar();
            } else {
                pantalla2.iniciar();
            }
        }
    }

    private static Tablero armarTablero1() {
        Tablero tablero = new Tablero(10, 10);
        tablero.colocarNave(new PortaAviones(OrientacionNave.HORIZONTAL), new Coordenadas(0, 0));
        tablero.colocarNave(new PortaAviones(OrientacionNave.VERTICAL), new Coordenadas(9, 0));
        tablero.colocarNave(new Crucero(OrientacionNave.HORIZONTAL), new Coordenadas(0, 3));
        tablero.colocarNave(new Crucero(OrientacionNave.VERTICAL), new Coordenadas(5, 3));
        tablero.colocarNave(new Submarino(OrientacionNave.HORIZONTAL), new Coordenadas(7, 5));
        tablero.colocarNave(new Submarino(OrientacionNave.VERTICAL), new Coordenadas(0, 6));
        tablero.colocarNave(new Submarino(OrientacionNave.HORIZONTAL), new Coordenadas(2, 8));
        tablero.colocarNave(new Submarino(OrientacionNave.VERTICAL), new Coordenadas(9, 8));
        tablero.colocarNave(new Barco(OrientacionNave.HORIZONTAL), new Coordenadas(7, 2));
        tablero.colocarNave(new Barco(OrientacionNave.HORIZONTAL), new Coordenadas(3, 5));
        tablero.colocarNave(new Barco(OrientacionNave.HORIZONTAL), new Coordenadas(7, 9));
        return tablero;
    }

    private static Tablero armarTablero2() {
        Tablero tablero = new Tablero(10, 10);
        tablero.colocarNave(new PortaAviones(OrientacionNave.VERTICAL), new Coordenadas(0, 0));
        tablero.colocarNave(new PortaAviones(OrientacionNave.HORIZONTAL), new Coordenadas(5, 9));
        tablero.colocarNave(new Crucero(OrientacionNave.HORIZONTAL), new Coordenadas(2, 0));
        tablero.colocarNave(new Crucero(OrientacionNave.VERTICAL), new Coordenadas(9, 2));
        tablero.colocarNave(new Submarino(OrientacionNave.HORIZONTAL), new Coordenadas(4, 3));
        tablero.colocarNave(new Submarino(OrientacionNave.VERTICAL), new Coordenadas(7, 3));
        tablero.colocarNave(new Submarino(OrientacionNave.HORIZONTAL), new Coordenadas(1, 6));
        tablero.colocarNave(new Submarino(OrientacionNave.VERTICAL), new Coordenadas(4, 6));
        tablero.colocarNave(new Barco(OrientacionNave.HORIZONTAL), new Coordenadas(7, 0));
        tablero.colocarNave(new Barco(OrientacionNave.HORIZONTAL), new Coordenadas(9, 7));
        tablero.colocarNave(new Barco(OrientacionNave.HORIZONTAL), new Coordenadas(1, 9));
        return tablero;
    }
}