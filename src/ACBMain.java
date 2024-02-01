import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

/**
 * Clase principal que contiene el método main para ejecutar la aplicación.
 */
public class ACBMain {

    public static void main(String[] args) throws IOException, SQLException, ParseException {
        // Instancias de controladores y menú
        ACBMenu menu = new ACBMenu();
        ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
        Connection c = connectionFactory.connect();
        PokemonController pokemonController = new PokemonController(c);
        ObjetoController objetoController = new ObjetoController(c);
        MovimientoController movimientoController = new MovimientoController(c);

        // Inicialización del bucle principal
        int option = menu.mainMenu();
        while (option > 0 && option < 19) {
            switch (option) {
                case 1:
                    pokemonController.vistaPokemon();
                    break;

                case 2:
                    pokemonController.vistaPokemon(menu.selectPokemonMenu());
                    break;

                case 3:
                    pokemonController.filtroNombre();
                    break;

                case 4:
                    pokemonController.addPokemon();
                    break;

                case 5:
                    pokemonController.loadPokemonsCSV();
                    break;

                case 6:
                    pokemonController.filtroStat();
                    break;

                case 7:
                    pokemonController.purgaNombre();
                    break;

                case 8:
                    pokemonController.purgaTipos();
                    break;

                case 9:
                    pokemonController.vaciarPokemons();
                    break;

                case 10:
                    movimientoController.vistaMovimientos();
                    break;

                case 11:
                    movimientoController.addMovimiento();
                    break;

                case 12:
                    movimientoController.mejorarEstadisticasMovimiento();
                    break;

                case 13:
                    movimientoController.loadMovimientosCSV();
                    break;

                case 14:
                    movimientoController.vaciarMovimientos();
                    break;

                case 15:
                    objetoController.vistaObjeto();
                    break;

                case 16:
                    objetoController.addObjeto();
                    break;

                case 17:
                    objetoController.actualizarPrecioObjetos();
                    break;

                case 18:
                    objetoController.loadObjetosCSV();
                    break;
                case 0: System.exit(0);

                default:
                    System.out.println("Introduce alguna de las opciones anteriores");
                    break;
            }

            // Obtener la siguiente opción del menú
            option = menu.mainMenu();
        }
    }
}

