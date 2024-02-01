import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * Controlador que gestiona las operaciones relacionadas con la tabla "movimiento" en la base de datos.
 * Se utiliza para realizar operaciones como la visualización de movimientos, la adición de nuevos movimientos,
 * la mejora de estadísticas de movimientos, la carga de movimientos desde un archivo CSV y la eliminación de movimientos.
 */
public class MovimientoController {

    private Connection connection;

    /**
     * Constructor de la clase MovimientoController.
     *
     * @param connection La conexión a la base de datos que se utilizará en el controlador.
     */
    MovimientoController(Connection connection) {
        this.connection = connection;
    }

    /**
     * Muestra todos los movimientos almacenados en la base de datos, imprimiendo sus detalles en la consola.
     *
     * @throws SQLException Si hay algún error al acceder a la base de datos.
     * @throws IOException  Si hay algún error de entrada/salida al interactuar con el usuario.
     */
    void vistaMovimientos() throws SQLException, IOException {

        Statement st = connection.createStatement();
        ResultSet rs;

        rs = st.executeQuery("SELECT * FROM movimiento");
        while (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre") + " " +
                    "Tipo: " + rs.getString("tipo") + " " +
                    "Categoria: " + rs.getString("categoria") + " " +
                    "Poder: " + rs.getString("poder") + " " +
                    "PP: " + rs.getString("pp") + " " +
                    "Precisión: " + rs.getString("precision") + " " +
                    "Descripción: " + rs.getString("descripcion"));
        }

        rs.close();
        st.close();
    }

    /**
     * Agrega un nuevo movimiento a la base de datos solicitando al usuario la entrada de sus detalles.
     *
     * @throws SQLException Si hay algún error al acceder a la base de datos.
     * @throws IOException  Si hay algún error de entrada/salida al interactuar con el usuario.
     */
    void addMovimiento() throws SQLException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Inserta su nombre: ");
        String nombre = br.readLine();
        System.out.println("Inserta su tipo: ");
        String tipo = br.readLine();
        System.out.println("Inserta su categoria ");
        String categoria = br.readLine();
        System.out.println("Inserta su poder: ");
        int poder = Integer.parseInt(br.readLine());
        System.out.println("Inserta sus PP: ");
        int pp = Integer.parseInt(br.readLine());
        System.out.println("Inserta su precisión: ");
        String precision = br.readLine();
        System.out.println("Inserta una descripción: ");
        String descripcion = br.readLine();


        try {
            String sql = "INSERT INTO movimiento (nombre, tipo, categoria, poder, pp, precision, descripcion) VALUES (?,?,?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, tipo);
            pst.setString(3, categoria);
            pst.setInt(4, poder);
            pst.setInt(5, pp);
            pst.setString(6, precision);
            pst.setString(7, descripcion);
            pst.executeUpdate();
        } catch (Exception w) {
            System.out.println("Error al insertar el movimiento en la base de datos comprueba que:");
            System.out.println("1) El nombre del movimiento no este usado por otro");
            System.out.println("4) Los campos que requieran un int no hayas puesto algún String");
        }
    }

    /**
     * Carga los datos de movimientos desde un archivo CSV seleccionado por el usuario y los inserta en la base de datos.
     *
     * @throws SQLException Si hay algún error al acceder a la base de datos.
     * @throws IOException  Si hay algún error de entrada/salida al interactuar con el usuario o leer el archivo CSV.
     */
    void loadMovimientosCSV() throws SQLException, IOException {
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
                System.out.println(datos[0]);
                String sql = "INSERT INTO movimiento (nombre, tipo, categoria, poder, pp, precision, descripcion) VALUES(?,?,?,?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, datos[0]);
                pst.setString(2, datos[1]);
                pst.setString(3, datos[2]);

                if (datos[3].isEmpty()) {
                    pst.setInt(4, 0);
                } else {
                    pst.setInt(4, Integer.parseInt(datos[3]));
                }

                pst.setInt(5, Integer.parseInt(datos[4]));
                pst.setString(6, datos[5]);
                pst.setString(7, datos[6]);
                pst.executeUpdate();

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(i + " Movimientos añadidos a la base de datos");
    }

    /**
     * Elimina todos los registros de la tabla "movimiento" en la base de datos, dejándola vacía.
     *
     * @throws SQLException Si hay algún error al acceder a la base de datos.
     * @throws IOException  Si hay algún error de entrada/salida al interactuar con el usuario.
     */
    void vaciarMovimientos() throws SQLException, IOException {
        String query = "DELETE FROM movimiento";
        try (Statement st = connection.createStatement()) {
            int rowsAffected = st.executeUpdate(query);
            System.out.println("Se vació la tabla movimiento. Se eliminaron " + rowsAffected + " registros.");
        }
    }

    /**
     * Mejora las estadísticas de un movimiento específico en la base de datos.
     * Se solicitará al usuario que ingrese el nombre del movimiento a mejorar y luego se mostrarán
     * las estadísticas actuales del movimiento. A continuación, se pedirá al usuario que seleccione
     * la estadística (poder, pp o precisión) que desea mejorar, así como el nuevo valor que desea asignarle.
     * Las estadísticas del movimiento se actualizarán en la base de datos.
     *
     * @throws SQLException Si hay algún error al acceder a la base de datos.
     * @throws IOException  Si hay algún error de entrada/salida al interactuar con el usuario.
     */
    void mejorarEstadisticasMovimiento() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("¿Cuál es el movimiento que quieres mejorar?");
        String mov = br.readLine();

        String queryConsulta = "SELECT poder, pp, precision FROM movimiento WHERE nombre = '" + mov + "'";
        Statement stConsulta = connection.createStatement();
        ResultSet rs = stConsulta.executeQuery(queryConsulta);

        // Verificar si existe una fila en el ResultSet
        if (rs.next()) {
            System.out.println("Estas son las estadísticas actuales del movimiento:\n" +
                    "Poder: " + rs.getString("poder") + "\n" +
                    "PP: " + rs.getString("pp") + "\n" +
                    "Precisión: " + rs.getString("precision"));

            // Obtener nuevas estadísticas
            System.out.println("¿Cuál es la estadística que quieres mejorar?(Escribela en minúscula)");
            String stat = br.readLine();
            System.out.println("¿Qué valor quieres asignarle?");
            String valor = br.readLine();

            // Actualizar estadísticas
            String queryUpdate = "UPDATE movimiento SET " + stat + " = '" + valor + "' WHERE nombre = '" + mov + "'";
            Statement stUpdate = connection.createStatement();
            int rowsAffected = stUpdate.executeUpdate(queryUpdate);
            System.out.println("Se actualizaron " + rowsAffected + " registros.");
        } else {
            System.out.println("No se encontró el movimiento '" + mov + "'");
        }

        // Cerrar recursos
        rs.close();
        stConsulta.close();

    }

}
