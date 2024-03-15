
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Iterator;

public class StockArticulos {

	// ATRIBUTOS
	String nombre;
	double precioBruto;
	double IVA;
	int cantidad;

	// Lista para almacenar los productos
	static List<StockArticulos> inventario = new ArrayList<>();

	// CONSTRUCTORES
	public StockArticulos() {
		this.nombre = "##";
		this.precioBruto = 0.0;
		this.IVA = 0.0;
		this.cantidad = 0;
	}

	public StockArticulos(String nombre, double precioBruto, double IVA, int cantidad) {
		this.nombre = nombre;
		this.precioBruto = precioBruto;
		this.IVA = IVA;
		this.cantidad = cantidad;
	}

	// almacenamos en nombre el valor recogido por el JOption
	// StockArticulos producto = crea un nuevo objeto con los valores
	// proporcionados.
	// inventario.add = agrega el nuevo objeto al inventario.
	// IF = verificamos que el tamaño del inventario sea =< que 10.

	public static void agregarArticulo() {
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del artículo:");
		nombre = nombre.toUpperCase(); // Convertir a mayúsculas
		double precioBruto = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio bruto del artículo:"));
		double IVA = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el IVA del artículo:"));
		int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del artículo:"));

		StockArticulos producto = new StockArticulos(nombre, precioBruto, IVA, cantidad);
		inventario.add(producto);

		if (inventario.size() >= 10) {
			JOptionPane.showMessageDialog(null, "¡El inventario contiene 10 o más productos!", "Mensaje",
					JOptionPane.INFORMATION_MESSAGE);
		}
		JOptionPane.showMessageDialog(null, "¡Artículo agregado correctamente!", "Mensaje",
				JOptionPane.INFORMATION_MESSAGE);
	}

	// se pide el nombre y lo pasa a upperCase
	// crea un objeto iterator para recorrer el inventario, accede, elimina y
	// verifica.
	// while - inicia un bucle que recorre cada elemento mientras haya elementos no
	// visitados.
	// StockArticulos producto, obtienee el nombre, IF compara el nombre, si hay una
	// coincidencia
	// lo elimina y muestra un mensaje.

	public static void eliminarArticulo() {

		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del artículo a eliminar:");
		nombre = nombre.toUpperCase(); // Convertir a mayúsculas

		Iterator<StockArticulos> iterator = inventario.iterator();
		while (iterator.hasNext()) {
			StockArticulos producto = iterator.next();

			if (producto.nombre.equalsIgnoreCase(nombre)) {
				iterator.remove();
				JOptionPane.showMessageDialog(null, "¡Artículo eliminado correctamente!", "Mensaje",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}

		JOptionPane.showMessageDialog(null, "El artículo '" + nombre + "' no existe en el inventario.", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	// bucle que recorre todos los elementos del inventario. - producto
	// if - se compara el nombre del producto actual con el nombre proporcionado
	// equalsIgnoreCase = comparación no sensible a mayus o minus.

	public static StockArticulos buscarArticulo(String nombre) {
		for (StockArticulos producto : inventario) {
			if (producto.nombre.equalsIgnoreCase(nombre)) {
				return producto;
			}
		}
		return null;
	}

	// for each - recorre cada elemento del inventario. IF - comparada el nombre
	// del producto actual con el nombre proporcionado por el usuario
	// int nuevaCantidad = solicita la nueva cantidad - producto.cantidad= modifica
	// la q de producto actual x la ingresada
	// sino --- ERROR

	public static void modificarCantidad() {
		String nombre = JOptionPane.showInputDialog("Ingrese el nombre del artículo cuya cantidad desea modificar:");
		nombre = nombre.toUpperCase(); // Convertir a mayúsculas

		for (StockArticulos producto : inventario) {
			if (producto.nombre.equalsIgnoreCase(nombre)) {
				int nuevaCantidad = Integer.parseInt(
						JOptionPane.showInputDialog("Ingrese la nueva cantidad para el artículo '" + nombre + "':"));
				producto.cantidad = nuevaCantidad;
				JOptionPane.showMessageDialog(null, "¡Cantidad modificada correctamente!", "Mensaje",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		}
		JOptionPane.showMessageDialog(null, "El artículo '" + nombre + "' no existe en el inventario.", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	// StringBuilder stock = crea un objeto SB=stock e inicializa su contenido
	// en la cadena INVENTARIO ACTUAL
	// bucle recorre cada elemento y asigna producto - .append (añade)
	// Joption se imprime

	public static void mostrarStock() {
		StringBuilder stock = new StringBuilder("INVENTARIO ACTUAL:\n");

		for (StockArticulos producto : inventario) {
			stock.append(producto.nombre).append(": ").append(producto.cantidad).append("\n");
		}
		JOptionPane.showMessageDialog(null, stock.toString(), "Inventario Actual", JOptionPane.INFORMATION_MESSAGE);
	}

	// Crea SB lista, inicializa en la cadena
	// bucle recorre y almacena en producto, lista.append
	// JOptionPane, muestra un panel con la lista de productos - toString.

	public static void listarProductos() {
		StringBuilder lista = new StringBuilder("Productos disponibles:\n");
		for (StockArticulos producto : inventario) {
			lista.append(producto.nombre).append("\n");
		}
		JOptionPane.showMessageDialog(null, lista.toString(), "Lista de Productos", JOptionPane.PLAIN_MESSAGE);
	}

	
}
