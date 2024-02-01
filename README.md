Gestión de Base de Datos de Pokémon

Esta aplicación Java facilita la gestión de una base de datos de Pokémon, incluyendo Pokémons, Movimientos y Objetos. Proporciona un sistema de menú basado en la consola para que los usuarios realicen diversas operaciones como visualizar, agregar y actualizar registros en la base de datos.
Clases
1. ObjetoController
Descripción:

La clase ObjetoController gestiona las operaciones relacionadas con la tabla "objeto" en la base de datos. Incluye funcionalidades para ver todos los objetos, agregar un nuevo objeto, cargar objetos desde un archivo CSV y actualizar precios basándose en criterios de filtrado.
Métodos:

    vistaObjeto: Muestra todos los objetos en la tabla "objeto".
    addObjeto: Añade un nuevo objeto a la tabla "objeto".
    loadObjetosCSV: Carga objetos desde un archivo CSV e inserta en la tabla "objeto".
    actualizarPrecioObjetos: Actualiza los precios de los objetos según un criterio de filtrado.

2. MovimientoController
Descripción:

La clase MovimientoController gestiona las operaciones relacionadas con la tabla "movimiento" en la base de datos. Incluye funcionalidades para ver todos los movimientos, agregar un nuevo movimiento, cargar movimientos desde un archivo CSV, eliminar movimientos y mejorar las estadísticas de los movimientos.
Métodos:

    vistaMovimientos: Muestra todos los movimientos en la tabla "movimiento".
    addMovimiento: Agrega un nuevo movimiento a la tabla "movimiento".
    loadMovimientosCSV: Carga movimientos desde un archivo CSV e inserta en la tabla "movimiento".
    vaciarMovimientos: Elimina todos los registros de la tabla "movimiento".
    mejorarEstadisticasMovimiento: Mejora las estadísticas de un movimiento específico.

3. ACBMenu
Descripción:

La clase ACBMenu proporciona un sistema de menú basado en la consola para la interacción del usuario. Ofrece menús para Pokémons, Movimientos y Objetos, permitiendo a los usuarios realizar varias operaciones en la base de datos.
Métodos:

    mainMenu: Muestra el menú principal para seleccionar diferentes operaciones de entidad.
    selectPokemonMenu: Muestra el menú para seleccionar operaciones relacionadas con Pokémon.

Uso

    Compila y ejecuta la aplicación.
    Sigue las indicaciones del menú en pantalla para realizar las operaciones deseadas.

Dependencias

    Java 8 o superior
    JDBC (Java Database Connectivity)
    Biblioteca OpenCSV para operaciones con archivos CSV

Contribuciones

Siéntete libre de contribuir mediante informes de errores, solicitudes de funciones o envío de solicitudes de extracción.
