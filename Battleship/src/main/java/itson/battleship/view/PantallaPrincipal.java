package itson.battleship.view;

import itson.battleship.controller.IControlador;
import itson.battleship.model.Coordenadas;
import itson.battleship.enums.EstadoNave;
import itson.battleship.enums.EstadoPartida;
import itson.battleship.model.IModel;
import itson.battleship.model.Jugador;
import itson.battleship.enums.ResultadoDisparo;
import itson.battleship.observer.Observador;
import java.util.Scanner;

/**
 *
 * @author Jesús Rodrigo Villegas Argüelles - 261186
 */
public class PantallaPrincipal implements Observador {

    private IControlador controlador;
    private IModel modelo;
    private Jugador jugador;
    private Scanner scanner;
    private Coordenadas ultimaCoordenada;
    private Jugador turnoAnterior;

    public PantallaPrincipal(IControlador controlador, IModel modelo, Jugador jugador, Scanner scanner) {
        this.controlador = controlador;
        this.modelo = modelo;
        this.jugador = jugador;
        this.scanner = scanner;
        this.turnoAnterior = modelo.obtenerJugadorEnTurno();
        mostrarTurno();
    }

    public void iniciar() {
        while (modelo.obtenerEstadoPartida() == EstadoPartida.EN_CURSO
                && modelo.obtenerJugadorEnTurno() == jugador) {
            System.out.print("[" + jugador.getNombre() + "] Coordenada: ");
            if (!scanner.hasNextLine()) {
                System.exit(0);
            }
            String texto = scanner.nextLine();
            Coordenadas coord = convertirCoordenadas(texto);
            hacerClicEnCasilla(coord);
        }
    }

    public void hacerClicEnCasilla(Coordenadas coord) {
        ultimaCoordenada = coord;
        controlador.seleccionarDisparo(coord);
    }

    private Coordenadas convertirCoordenadas(String texto) {
        String limpio = texto.trim().toUpperCase();
        if (limpio.length() < 2) {
            return new Coordenadas(-1, -1);
        }
        int x = limpio.charAt(0) - 'A';
        int y;
        try {
            y = Integer.parseInt(limpio.substring(1)) - 1;
        } catch (NumberFormatException e) {
            return new Coordenadas(-1, -1);
        }
        return new Coordenadas(x, y);
    }

    private void imprimir(String mensaje) {
        System.out.println("[" + jugador.getNombre() + "] " + mensaje);
    }

    private void mostrarTurno() {
        imprimir("Turno de " + modelo.obtenerJugadorEnTurno().getNombre());
    }

    private void mostrarDisparo(String mensaje) {
        if (turnoAnterior == jugador) {
            imprimir(mensaje);
        } else {
            imprimir(turnoAnterior.getNombre() + " disparo: " + mensaje);
        }
    }

    @Override
    public void actualizarPantalla() {
        mostrarResultado();
        turnoAnterior = modelo.obtenerJugadorEnTurno();
    }

    private void mostrarResultado() {
        boolean disparoPropio = turnoAnterior == jugador;

        if (modelo.hayTiempoAgotado()) {
            if (disparoPropio) {
                imprimir("Tiempo agotado, perdiste tu turno");
            } else {
                imprimir("Tiempo agotado, " + turnoAnterior.getNombre() + " perdio su turno");
            }
            mostrarTurno();
            return;
        }

        if (modelo.hayPosicionInvalida()) {
            if (!disparoPropio) {
                return;
            }
            if (modelo.esCoordenadaDentroDelTablero(ultimaCoordenada)) {
                imprimir("Ya disparaste a esta casilla. Selecciona otra");
            } else {
                imprimir("Las coordenadas estan fuera del tablero. Selecciona otra casilla");
            }
            return;
        }

        if (modelo.obtenerResultadoDisparo() == ResultadoDisparo.AGUA) {
            mostrarDisparo("Agua");
            mostrarTurno();
            return;
        }

        if (modelo.obtenerEstadoNaveImpactada() == EstadoNave.HUNDIDA) {
            mostrarDisparo("Impacto, nave hundida (" + modelo.obtenerTipoNaveImpactada() + ")");
        } else {
            mostrarDisparo("Impacto, nave averiada");
        }

        if (modelo.obtenerEstadoPartida() == EstadoPartida.FINALIZADA) {
            String ganador = modelo.obtenerGanador().getNombre();
            if (disparoPropio) {
                imprimir("Victoria! " + ganador + " hundio todas las naves del oponente");
            } else {
                imprimir("Derrota! " + ganador + " hundio todas tus naves");
            }
        } else {
            imprimir(modelo.obtenerJugadorEnTurno().getNombre() + " conserva el turno");
        }
    }
}