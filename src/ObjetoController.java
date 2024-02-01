
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.sql.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

/**
 * Controlador para gestionar operaciones relacionadas con la tabla "objeto" en la base de datos.
 */
public class ObjetoController {

    private Connection connection;
    private ACBMenu menu = new ACBMenu();

    /**
     * Constructor que recibe una conexión a la base de datos.
     *
     * @param connection Conexión a la base de datos.
     */
    public ObjetoController(Connection connection) {
        this.connection = connection;
    }

    /**
     * Muestra todos los objetos en la tabla "objeto".
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema al leer desde la entrada estándar.
     */

    public void vistaObjeto() throws SQLException, IOException {
        Statement st = connection.createStatement();
        ResultSet rs;

        rs = st.executeQuery("SELECT * FROM objeto");
        while (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre") + " " +
                    "Generación: " + rs.getString("generación") + " " +
                    "Precio Compra: " + rs.getString("precio_compra") + " " +
                    "Precio Venta: " + rs.getString("precio_venta") + " " +
                    "Tipo: " + rs.getString("tipo"));
        }

        rs.close();
        st.close();
    }

    /**
     * Añade un nuevo objeto a la tabla "objeto".
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema al leer desde la entrada estándar.
     */
    public void addObjeto() throws SQLException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Inserta el nombre del objeto: ");
        String nombre = br.readLine();
        System.out.println("Inserta la generación del objeto: ");
        String generacion = br.readLine();
        System.out.println("Inserta el precio de compra del objeto: ");
        String precioCompra = br.readLine();
        System.out.println("Inserta el precio de venta del objeto: ");
        String precioVenta = br.readLine();
        System.out.println("Inserta el tipo del objeto: ");
        String tipo = br.readLine();

        try {
            String sql = "INSERT INTO objeto (nombre, generación, precio_compra, precio_venta, tipo) VALUES (?,?,?,?,?)";

            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2, generacion);
            pst.setString(3, precioCompra);
            pst.setString(4, precioVenta);
            pst.setString(5, tipo);

            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error al insertar el objeto en la base de datos. Asegúrate de que todos los campos sean válidos, o que no este registrado ya.");
        }
    }

    /**
     * Carga objetos desde un archivo CSV y los inserta en la tabla "objeto".
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema al leer desde el archivo o la entrada estándar.
     */
    void loadObjetosCSV() throws SQLException, IOException {
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
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(selectedFile)).withSkipLines(1).build()) {

            String[] datos;
            while ((datos = reader.readNext()) != null) {
                System.out.println(datos[0]);
                String sql = "INSERT INTO objeto (nombre, generación, precio_compra, precio_venta, tipo) VALUES(?,?,?,?,?)";
                PreparedStatement pst = connection.prepareStatement(sql);
                pst.setString(1, datos[0]);
                pst.setString(2, datos[1]);
                pst.setString(3, datos[2]);
                pst.setString(4, datos[3]);
                pst.setString(5, datos[4]);
                pst.executeUpdate();

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(i + " Objetos añadidos a la base de datos");
    }

    /**
     * Actualiza el precio de los objetos filtrados por la primera cifra.
     *
     * @throws SQLException Si hay un problema al ejecutar la consulta SQL.
     * @throws IOException  Si hay un problema al leer desde la entrada estándar.
     */
    public void actualizarPrecioObjetos() throws SQLException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ingresa la primera cifra para filtrar objetos por precio: ");
        String cifra1 = br.readLine();

        String queryConsulta = "SELECT COUNT(*) FROM objeto WHERE precio_compra LIKE '" + cifra1 + "%'";
        try (Statement stConsulta = connection.createStatement();
             ResultSet rsConsulta = stConsulta.executeQuery(queryConsulta)) {

            if (rsConsulta.next() && rsConsulta.getInt(1) > 0) {
                System.out.println("Ingresa la nueva cifra para actualizar el precio de los objetos: ");
                String cifra2 = br.readLine();

                String queryUpdate = "UPDATE objeto SET precio_compra = '" + cifra2 + "' WHERE precio_compra LIKE '" + cifra1 + "%'";
                try (Statement stUpdate = connection.createStatement()) {
                    int rowsAffected = stUpdate.executeUpdate(queryUpdate);
                    System.out.println("Se actualizaron " + rowsAffected + " registros.");
                }
            } else {
                System.out.println("No se encontraron objetos con la primera cifra de precio '" + cifra1 + "'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}