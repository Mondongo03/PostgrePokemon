import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ACBMenu {
	private int option;

	public ACBMenu() {
		super();
	}

	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {

			System.out.println(" \nMENU PRINCIPAL \n");

			System.out.println("1. Mostrar pokemons");
			System.out.println("2. Mostrar algún atributo de los pokemons");
			System.out.println("3. Filtrar pokemons por nombre");
			System.out.println("4. Crear un Pokemon");
			System.out.println("5. Insertar Pokemons desde un CSV");
			System.out.println("6. Filtar pokemons por algún stat mayor que...");
			System.out.println("7. Eliminar un pokemon por nombre");
			System.out.println("8. Eliminar pokemons por tipo");
			System.out.println("9. Purgar Pokemons");
			System.out.println();
			System.out.println("10. Mostrar movimientos");
			System.out.println("11. Crear un Movimiento");
			System.out.println("12. Mejorar un Movimiento");
			System.out.println("13. Insertar Movimientos desde un CSV");
			System.out.println("14. Purgar Movimientos");
			System.out.println();
			System.out.println("15. Mostrar objetos");
			System.out.println("16. Crear un Objeto");
			System.out.println("17.Aumentar el precio de objetos con X precio");
			System.out.println("18. Insertar Objetos desde un CSV");

			System.out.println("0. Salir");
			System.out.println("Elige una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no vàlid");
				e.printStackTrace();

			}

		} while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
				&& option != 8 && option != 9 && option != 10 && option != 11&& option != 12&& option != 13&& option != 14&& option != 15&& option != 16&& option != 17&& option != 18);

		return option;
	}

	public int selectPokemonMenu() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		do {

			System.out.println(" \nQue campo quieres mostrar en la vista? \n");

			System.out.println("1.Nombre");
			System.out.println("2.Numero");
			System.out.println("3.Tipo Primario");
			System.out.println("4.Tipo Secundario");
			System.out.println("5.Ambos Tipos");
			System.out.println("6.Objeto Equipado");
			System.out.println("7.Hp");
			System.out.println("8.Ataque");
			System.out.println("9.Defensa");
			System.out.println("10.Velocidad");
			System.out.println("11.Ataque Especial");
			System.out.println("12.Defensa Especial");
			System.out.println("13.Movimientos");
			System.out.println("0.Todos los campos");

			try {
				option = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("valor no vàlid");
				e.printStackTrace();

			}

		} while (option != 1 && option != 2 && option != 3 && option != 4 && option != 5 && option != 6 && option != 7
				&& option != 8 && option != 9 && option != 10 && option != 11 && option != 12 && option != 13 && option != 0);

		return option;
	}

}
