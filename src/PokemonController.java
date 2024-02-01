
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * Controlador para gestionar operaciones relacionadas con la entidad Pokemon en la base de datos.
 */
public class PokemonController {

    // Conexión a la base de datos
    private Connection connection;

    // Menú asociado al controlador
    private ACBMenu menu = new ACBMenu();

    /**
     * Constructor que recibe una conexión a la base de datos.
     *
     * @param connection Conexión a la base de datos.
     */
    public PokemonController(Connection connection) {
        this.connection = connection;
    }

    /**
     * Muestra los detalles de todos los Pokémon almacenados en la base de datos.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    public void vistaPokemon() throws SQLException, IOException {

        // Crear una declaración para ejecutar la consulta SQL
        Statement st = connection.createStatement();
        ResultSet rs;

        // Ejecutar la consulta SQL para obtener todos los Pokémon
        rs = st.executeQuery("SELECT * FROM pokemon");

        // Recorrer los resultados y mostrar los detalles de cada Pokémon
        while (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre") + " " +
                    "Número: " + rs.getString("numero") + " " +
                    "Tipo Primario: " + rs.getString("tipo_primario") + " " +
                    "Tipo Secundario: " + rs.getString("tipo_secundario") + " " +
                    "Habilidad/es: " + rs.getString("habilidad") + " " +
                    "Habilidad Oculta: " + rs.getString("habilidad_oculta") + " " +
                    "Objeto Equipado: " + rs.getString("objeto_equipado") + " " +
                    "PS base: " + rs.getString("hp") + " " +
                    "Ataque Base: " + rs.getString("ataque") + " " +
                    "Defensa Base: " + rs.getString("defensa") + " " +
                    "Velocidad Base: " + rs.getString("velocidad") + " " +
                    "Ataque Esp. Base: " + rs.getString("ataque_especial") + " " +
                    "Defensa Esp. Base: " + rs.getString("defensa_especial") + " " +
                    "Movimiento 1: " + rs.getString("movimiento1") + " " +
                    "Movimiento 2: " + rs.getString("movimiento2") + " " +
                    "Movimiento 3: " + rs.getString("movimiento3") + " " +
                    "Movimiento 4: " + rs.getString("movimiento4"));
        }

        // Cerrar recursos
        rs.close();
        st.close();
    }

    /**
     * Muestra detalles específicos de los Pokémon almacenados en la base de datos según la opción seleccionada.
     *
     * @param opcion Opción que determina qué atributo o conjunto de atributos mostrar.
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    public void vistaPokemon(int opcion) throws SQLException, IOException {

        Statement st = connection.createStatement();
        ResultSet rs;
        String atributo = "*";
        switch (opcion) {
            case 1:
                atributo = "nombre";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Nombre: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 2:
                atributo = "numero";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Numero: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 3:
                atributo = "tipo_primario";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Tipo Primario: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 4:
                atributo = "tipo_secundario";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Tipo Secundario: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 5:
                atributo = "tipo_primario, tipo_secundario";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Ambos tipos: " + rs.getString("tipo_primario") + " | " + rs.getString("tipo_secundario"));
                }
                rs.close();
                break;
            case 6:
                atributo = "objeto_equipado";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Objeto Equipado: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 7:
                atributo = "hp";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Hp: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 8:
                atributo = "ataque";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Ataque: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 9:
                atributo = "defensa";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Defensa: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 10:
                atributo = "velocidad";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Velocidad: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 11:
                atributo = "ataque_especial";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Ataque Especial: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 12:
                atributo = "defensa_especial";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Defensa Especial: " + rs.getString(atributo));
                }
                rs.close();
                break;
            case 13:
                atributo = "movimiento1, movimiento2, movimiento3, movimiento4";
                rs = st.executeQuery("SELECT " + atributo + " FROM pokemon");
                while (rs.next()) {
                    System.out.println("Movimientos: " + rs.getString("movimiento1") + " | " + rs.getString("movimiento2") + " | " + rs.getString("movimiento3") + " | " + rs.getString("movimiento4"));
                }
                rs.close();
                break;


        }


        st.close();
    }

    /**
     * Filtra y muestra los detalles de un Pokémon según su nombre.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    public void filtroNombre() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Cuál es el nombre que quieres usar de filtro?");
        String nombre = br.readLine();
        String query = "SELECT * FROM pokemon WHERE nombre = '" + nombre + "'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre"));
            System.out.println("Número: " + rs.getInt("numero"));
            System.out.println("Tipo primario: " + rs.getString("tipo_primario"));
            System.out.println("Tipo secundario: " + rs.getString("tipo_secundario"));
            System.out.println("Habilidad: " + rs.getString("habilidad"));
            System.out.println("Habilidad oculta: " + rs.getString("habilidad_oculta"));
            System.out.println("Objeto equipado: " + rs.getString("objeto_equipado"));
            System.out.println("HP: " + rs.getInt("hp"));
            System.out.println("Ataque: " + rs.getInt("ataque"));
            System.out.println("Defensa: " + rs.getInt("defensa"));
            System.out.println("Velocidad: " + rs.getInt("velocidad"));
            System.out.println("Ataque especial: " + rs.getInt("ataque_especial"));
            System.out.println("Defensa especial: " + rs.getInt("defensa_especial"));
            System.out.println("Movimiento 1: " + rs.getString("movimiento1"));
            System.out.println("Movimiento 2: " + rs.getString("movimiento2"));
            System.out.println("Movimiento 3: " + rs.getString("movimiento3"));
            System.out.println("Movimiento 4: " + rs.getString("movimiento4"));
            System.out.println();
        }
    }

    /**
     * Elimina todos los Pokémon de un tipo específico, ya sea como tipo primario o secundario.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    public void purgaTipos() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("¿Cuál es el tipo que quieres purgar?");
        String tipo = br.readLine();
        String query = "DELETE FROM pokemon WHERE tipo_primario = '" + tipo + "' OR tipo_secundario = '" + tipo + "'";
        try (Statement st = connection.createStatement()) {
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Se eliminaron " + rowsAffected + " registros.");
        }
    }

    /**
     * Elimina un Pokémon específico según su nombre.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    public void purgaNombre() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Cuál es el nombre que quieres usar de filtro?");
        String nombre = br.readLine();
        String query = "DELETE FROM pokemon WHERE nombre = '" + nombre + "'";
        try (Statement st = connection.createStatement()) {
            int rowsAffected = st.executeUpdate(query);
        }
    }

    /**
     * Filtra Pokémon según un stat y un valor mínimo especificados.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    public void filtroStat() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Cual es el stat que quieres usar de filtro?");
        String stat = br.readLine();
        System.out.println("Cual es el minimo que quieres usar de filtro?");
        String num = br.readLine();
        String query = "SELECT * FROM pokemon WHERE " + stat + " > '" + num + "'";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre") + "Número: " + rs.getInt("numero")
                    + "Tipo primario: " + rs.getString("tipo_primario")
                    + "Tipo secundario: " + rs.getString("tipo_secundario")
                    + "Habilidad: " + rs.getString("habilidad")
                    + "Habilidad oculta: " + rs.getString("habilidad_oculta")
                    + "Objeto equipado: " + rs.getString("objeto_equipado")
                    + "HP: " + rs.getInt("hp")
                    + "Ataque: " + rs.getInt("ataque")
                    + "Defensa: " + rs.getInt("defensa")
                    + "Velocidad: " + rs.getInt("velocidad")
                    + "Ataque especial: " + rs.getInt("ataque_especial")
                    + "Defensa especial: " + rs.getInt("defensa_especial")
                    + "Movimiento 1: " + rs.getString("movimiento1")
                    + "Movimiento 2: " + rs.getString("movimiento2")
                    + "Movimiento 3: " + rs.getString("movimiento3")
                    + "Movimiento 4: " + rs.getString("movimiento4"));
        }
    }

    /**
     * Añade un nuevo Pokémon a la base de datos.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    public void addPokemon() throws SQLException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Inserta su nombre: ");
        String nombre = br.readLine();
        System.out.println("Inserta su numero de la pokedex: ");
        int numero = Integer.parseInt(br.readLine());
        System.out.println("Inserta su tipo primario: ");
        String tipo_primario = br.readLine();
        System.out.println("Inserta su tipo secundario: ");
        String tipo_secundario = br.readLine();
        System.out.println("Inserta su habilidad: ");
        String habilidad = br.readLine();
        System.out.println("Inserta su habilidad oculta: ");
        String habilidad_hidden = br.readLine();
        System.out.println("Inserta un objeto equipado: ");
        String item = br.readLine();
        System.out.println("Inserta su stat base de vida: ");
        int hp = Integer.parseInt(br.readLine());
        System.out.println("Inserta su stat base de ataque: ");
        int atq = Integer.parseInt(br.readLine());
        System.out.println("Inserta su stat base de defensa: ");
        int def = Integer.parseInt(br.readLine());
        System.out.println("Inserta su stat base de velocidad: ");
        int vel = Integer.parseInt(br.readLine());
        System.out.println("Inserta su stat base de ataque especial: ");
        int sAtq = Integer.parseInt(br.readLine());
        System.out.println("Inserta su stat base de defensa especial: ");
        int sDef = Integer.parseInt(br.readLine());
        System.out.println("Inserta su primer movimiento: ");
        String mov1 = br.readLine();
        System.out.println("Inserta su segundo movimiento: ");
        String mov2 = br.readLine();
        System.out.println("Inserta su tercer movimiento: ");
        String mov3 = br.readLine();
        System.out.println("Inserta su cuarto movimiento: ");
        String mov4 = br.readLine();

        try {
            String sql = "INSERT INTO pokemon (nombre, numero, tipo_primario, tipo_secundario, habilidad, habilidad_oculta, objeto_equipado, hp, ataque, defensa, velocidad, ataque_especial, defensa_especial, movimiento1, movimiento2, movimiento3, movimiento4) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setInt(2, numero);
            pst.setString(3, tipo_primario);
            pst.setString(4, tipo_secundario);
            pst.setString(5, habilidad);
            pst.setString(6, habilidad_hidden);
            pst.setString(7, item);
            pst.setInt(8, hp);
            pst.setInt(9, atq);
            pst.setInt(10, def);
            pst.setInt(11, vel);
            pst.setInt(12, sAtq);
            pst.setInt(13, sDef);
            pst.setString(14, mov1);
            pst.setString(15, mov2);
            pst.setString(16, mov3);
            pst.setString(17, mov4);
            pst.executeUpdate();
        } catch (Exception w) {
            System.out.println("Error al insertar el pokemon en la base de datos comprueba que:");
            System.out.println("1) El numero de pokemon no este usado por otro");
            System.out.println("2) El objeto equipado este registrado en la tabla objetos");
            System.out.println("3) Los movimientos aprendidos estén registrado en la tabla movimientos");
            System.out.println("4) Los campos que requieran un int no hayas puesto algún String");
        }
    }

    /**
     * Carga datos de Pokémon desde un archivo CSV a la base de datos.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    void loadPokemonsCSV() throws SQLException, IOException {
        System.out.println("WARNING: El objeto equipado y los movimientos tienen que estar registrados en la tabla objeto y movimiento respectivamente");
        JFileChooser fileChooser = new JFileChooser();

        // Configurar el filtro para mostrar solo archivos con extensión CSV
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV (*.csv)", "csv");
        fileChooser.setFileFilter(filter);
        File selectedFile = null;
        // Mostrar la ventana de selección de archivos
        int result = fileChooser.showOpenDialog(null);

        // Verificar si el usuario seleccionó un archivo
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtener el archivo seleccionado
            selectedFile = fileChooser.getSelectedFile();
            System.out.println("Archivo seleccionado: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No se seleccionó ningún archivo.");
            return;
        }

        int i = 0;
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(selectedFile))
                .withSkipLines(1) // Para omitir la primera línea si es un encabezado
                .build()) {

            String[] datos;
            while ((datos = reader.readNext()) != null) {
                System.out.println(datos[0] + " " + datos[1] + " " + datos[2] + " " + datos[3] + " " + datos[4] + " " + datos[5] + " " +
                        datos[6] + " " + datos[7] + " " + datos[8] + " " + datos[9] + " " + datos[10] + " " + datos[11] + " " +
                        datos[12] + " " + datos[13] + " " + datos[14] + " " + datos[15] + " " + datos[16] + " añadido");

                String sql = "INSERT INTO pokemon (nombre, numero, tipo_primario, tipo_secundario, habilidad, habilidad_oculta, " +
                        "objeto_equipado, hp, ataque, defensa, velocidad, ataque_especial, defensa_especial, movimiento1, movimiento2, " +
                        "movimiento3, movimiento4) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, datos[0]);
                pst.setInt(2, Integer.parseInt(datos[1]));
                pst.setString(3, datos[2]);
                pst.setString(4, datos[3]);
                pst.setString(5, datos[4]);
                pst.setString(6, datos[5]);
                pst.setString(7, datos[6]);
                pst.setInt(8, Integer.parseInt(datos[7]));
                pst.setInt(9, Integer.parseInt(datos[8]));
                pst.setInt(10, Integer.parseInt(datos[9]));
                pst.setInt(11, Integer.parseInt(datos[10]));
                pst.setInt(12, Integer.parseInt(datos[11]));
                pst.setInt(13, Integer.parseInt(datos[12]));
                pst.setString(14, datos[13]);
                pst.setString(15, datos[14]);
                pst.setString(16, datos[15]);
                pst.setString(17, datos[16]);
                pst.executeUpdate();

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(i + " Pokemons añadidos a la base de datos");
    }

    /**
     * Elimina todos los registros de la tabla pokemon.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema de entrada/salida.
     */
    void vaciarPokemons() throws SQLException, IOException {
        String query = "DELETE FROM pokemon";
        try (Statement st = connection.createStatement()) {
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Se vació la tabla pokemon. Se eliminaron " + rowsAffected + " registros.");
        }
    }

}
