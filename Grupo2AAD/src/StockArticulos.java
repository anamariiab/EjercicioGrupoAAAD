
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class StockArticulos {
	
	public static List<StockArticulos> listaDeInstancias = new ArrayList<>();

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
		
		agregarInstancia(this);
	}
	

// Método para añadir una instancia a la lista
    public static void agregarInstancia(StockArticulos instancia) {
    inventario.add(instancia);
}

// Método para obtener la lista de instancias
    public static List<StockArticulos> obtenerListaDeInstancias() {
    return inventario;
}

	// almacenamos en nombre el valor 
	// StockArticulos producto = crea un nuevo objeto con los valores
	// proporcionados.
	// inventario.add = agrega el nuevo objeto al inventario.
	// IF = verificamos que el tamaño del inventario sea =< que 10.

	// StringBuilder stock = crea un objeto SB=stock e inicializa su contenido
	// en la cadena INVENTARIO ACTUAL
	// bucle recorre cada elemento y asigna producto - .append (añade)
	// se imprime
	
	public static void mostrarStock() {                                       
		Scanner scanner = new Scanner(System.in);
		StringBuilder stock = new StringBuilder("INVENTARIO ACTUAL:\n");
	    for (StockArticulos producto : inventario) {
	        stock.append(producto.nombre).append(": ").append(producto.cantidad).append("\n");
	    }
	    System.out.println(stock.toString());
	}

	public static void agregarArticulo() {                                 
		Scanner scanner = new Scanner(System.in);
	    
	    System.out.print("Ingrese el nombre del artículo: ");
	    String nombre = scanner.nextLine();
	    nombre = nombre.toUpperCase(); // Convertir a mayúsculas
	    
	    System.out.print("Ingrese el precio bruto del artículo: ");
	    double precioBruto = scanner.nextDouble();
	    
	    System.out.print("Ingrese el IVA del artículo: ");
	    double IVA = scanner.nextDouble();
	    
	    System.out.print("Ingrese la cantidad del artículo: ");
	    int cantidad = scanner.nextInt();

	    StockArticulos producto = new StockArticulos(nombre, precioBruto, IVA, cantidad);
	    inventario.add(producto);

	    if (inventario.size() >= 10) {
	        System.out.println("¡El inventario contiene 10 o más productos!");
	    }
	    System.out.println("¡Artículo agregado correctamente!");
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

		Scanner scanner = new Scanner(System.in);

	    System.out.print("Ingrese el nombre del artículo a eliminar: ");
	    String nombre = scanner.nextLine();
	    nombre = nombre.toUpperCase(); // Convertir a mayúsculas

	    Iterator<StockArticulos> iterator = inventario.iterator();
	    while (iterator.hasNext()) {
	        StockArticulos producto = iterator.next();

	        if (producto.nombre.equalsIgnoreCase(nombre)) {
	            iterator.remove();
	            System.out.println("¡Artículo eliminado correctamente!");
	            return;
	        }
	    }

	    System.out.println("El artículo '" + nombre + "' no existe en el inventario.");
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
	    Scanner scanner = new Scanner(System.in);

	    System.out.print("Ingrese el nombre del artículo cuya cantidad desea modificar: ");
	    String nombre = scanner.nextLine();
	    nombre = nombre.toUpperCase(); // Convertir a mayúsculas

	    for (StockArticulos producto : inventario) {
	        if (producto.nombre.equalsIgnoreCase(nombre)) {
	            System.out.print("Ingrese la nueva cantidad para el artículo '" + nombre + "': ");
	            int nuevaCantidad = scanner.nextInt();
	            producto.cantidad = nuevaCantidad;
	            System.out.println("¡Cantidad modificada correctamente!");
	            return;
	        }
	    }

	    System.out.println("El artículo " + nombre + " no existe en el inventario.");
	}

	// StringBuilder stock = crea un objeto SB=stock e inicializa su contenido
	// en la cadena INVENTARIO ACTUAL
	// bucle recorre cada elemento y asigna producto - .append (añade)
	// lista de productos - toString.

	public static void listarProductos() {                        
	    StringBuilder lista = new StringBuilder("Productos disponibles:\n");
	    for (StockArticulos producto : inventario) {
	        lista.append(producto.nombre).append("\n");
	    }
	    System.out.println(lista.toString());
	}

}