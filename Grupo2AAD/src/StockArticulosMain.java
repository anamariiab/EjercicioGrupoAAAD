import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class StockArticulosMain {

	public static void main(String[] args) {

		// Lista para almacenar los productos

		StockArticulos leche = new StockArticulos("LECHE", 1.10, 4, 20);
		StockArticulos pan = new StockArticulos("PAN", 0.90, 4, 30);
		StockArticulos huevos = new StockArticulos("HUEVOS", 2.50, 4, 40);
		StockArticulos arroz = new StockArticulos("ARROZ", 1.30, 21, 15);
		StockArticulos pasta = new StockArticulos("PASTA", 1.25, 21, 25);
		StockArticulos tomates = new StockArticulos("TOMATES", 2.35, 21, 35);
		StockArticulos patatas = new StockArticulos("PATATAS", 2.10, 21, 10);
		StockArticulos manzanas = new StockArticulos("MANZANAS", 1.75, 21, 45);

		List<StockArticulos> lista = StockArticulos.obtenerListaDeInstancias();

		Scanner sc = new Scanner(System.in);
		boolean otroCliente = true;
		double gananciasBrutoTotal = 0;
		double gananciasNetoTotal = 0;

		while (otroCliente) {
			HashMap<String, Double[]> compra = new HashMap<>();

			System.out.println("Este es el stock actual de la tienda:\n");
			StockArticulos.mostrarStock();

			System.out.println("-- INICIO COMPRA --");
			comprarArticulos(sc, compra);
			imprimirResumenCompra(compra);

			double totalCompraBruto = calcularTotalCompraBruto(compra);
			double totalCompraNeto = calcularTotalCompraNeto(compra);
			gananciasBrutoTotal += totalCompraBruto;
			gananciasNetoTotal += totalCompraNeto;
			imprimirTotalesCompra(totalCompraBruto, totalCompraNeto);
			procesarPago(sc, totalCompraNeto);

			System.out.println("¿Hay otro cliente? (Y/N)");
			String respuesta = sc.nextLine().toUpperCase();

			if (!respuesta.equals("Y")) {
				otroCliente = false;
				System.out.println("\n-- CAJA FINAL --" + "\nGanancias totales (Bruto): " + gananciasBrutoTotal
						+ "\nGanancias totales (Neto): " + gananciasNetoTotal);

			} else {
				mostrarOpcionesInventario();
			}

			boolean continuar = true; // Variable para controlar si continuar en el bucle while

			while (continuar) {
				System.out.println("\nSeleccione una opción:\n" + "1. Agregar artículo\n" + "2. Eliminar artículo\n"
						+ "3. Modificar cantidad\n" + "4. Mostrar stock\n" + "5. Repetir Compra" + "\n6. Salir");
				String opcion = sc.nextLine();

				switch (opcion) {
				case "1":
					StockArticulos.agregarArticulo();
					System.out.println("Agregar artículo");
					break;
				case "2":
					StockArticulos.eliminarArticulo();
					System.out.println("Eliminar artículo");
					break;
				case "3":
					StockArticulos.modificarCantidad();
					System.out.println("Modificar cantidad");
					break;
				case "4":
					StockArticulos.mostrarStock();
					// Preguntar al usuario si desea continuar después de mostrar el stock
					System.out.println("Mostrar stock");
					System.out.println("¿Desea realizar otra acción? (S/N)");
					String respuesta1 = sc.nextLine().toUpperCase();
					continuar = respuesta1.equalsIgnoreCase("S");
					break;
				case "5":
					System.out.println("-- INICIO COMPRA --");
					repetirCompra();
					repetirInventario();

				case "6":
					System.out.println("-- PROGRAMA FINALIZADO! --");
					continuar = false; // Salir del bucle
					break;

				default:
					System.out.println("Opción no válida!");
//					CIERRE SWITCH
				}
			}
		}

//				CIERRE WHILE
			}
		
//			CIERRE MAIN 
	

	// METODOS EJ2

	// Va pidiendo los artículos conforme pasan por caja
	public static void comprarArticulos(Scanner sc, HashMap<String, Double[]> compra) {
		double totalDeArticulos = 0;
		do {
			System.out.println("Ingrese el nombre del artículo:");
			String nombreProductoComprado = sc.nextLine();

			// Buscar el artículo en el inventario
			StockArticulos articulo = StockArticulos.buscarArticulo(nombreProductoComprado);

			if (articulo != null) {
				System.out.println("Cantidad disponible: " + articulo.cantidad);

				// Verificar si hay suficiente cantidad en el inventario
				System.out.println("¿Cuántos desea comprar?");
				int cantidad = Integer.parseInt(sc.nextLine());
				if (cantidad > articulo.cantidad) {
					System.out.println(
							"No hay suficiente cantidad disponible. Se agregarán " + articulo.cantidad + " unidades.");
					cantidad = articulo.cantidad; // Comprar la cantidad máxima disponible
				}

				// Agregar el artículo a la compra
				double precioConIVA = calcularPrecioConIVA(articulo.precioBruto, articulo.IVA);
				compra.put(nombreProductoComprado,
						new Double[] { articulo.precioBruto, precioConIVA, articulo.IVA, (double) cantidad });
				totalDeArticulos++;
				// Restar la cantidad comprada del inventario
				articulo.cantidad -= cantidad;
			} else {
				System.out.println("El artículo no está disponible en el inventario.");
			}

			System.out.println("¿Desea añadir otro artículo? (Y/N)");
		} while (sc.nextLine().equalsIgnoreCase("Y"));
	}

	// Método para procesar el pago
	public static void procesarPago(Scanner sc, double totalCompraNeto) {
		System.out.println("\n¿Efectivo o tarjeta?");
		String tipoPago = sc.nextLine().toUpperCase();

		switch (tipoPago) {
		case "EFECTIVO":
			System.out.println("Introduzca la cantidad entregada:");
			double efectivoEntregado = Double.parseDouble(sc.nextLine());
			double cambio = efectivoEntregado - totalCompraNeto;
			if (cambio >= 0) {
				System.out.println("Pago recibido. Su cambio es: " + cambio);
			} else {
				System.out.println("La cantidad entregada es insuficiente.");
			}
			break;
		case "TARJETA":
			System.out.println("Pago con tarjeta realizado.");
			break;
		default:
			System.out.println("Método de pago no reconocido (escribe 'efectivo' o 'tarjeta' la siguiente vez).");
			break;
		}
	}
	
	// repetir inventario
	public static void repetirInventario() {
		HashMap<String, Double[]> compra = new HashMap<>();

		Scanner sc = new Scanner(System.in);

		boolean continuar = true; // Variable para controlar si continuar en el bucle while

		while (continuar) {
			System.out.println("\nSeleccione una opción:\n" + "1. Agregar artículo\n" + "2. Eliminar artículo\n"
					+ "3. Modificar cantidad\n" + "4. Mostrar stock\n" + "5. Repetir Compra" + "\n6. Salir");
			String opcion = sc.nextLine();

			switch (opcion) {
			case "1":
				StockArticulos.agregarArticulo();
				System.out.println("Agregar artículo");
				break;
			case "2":
				StockArticulos.eliminarArticulo();
				System.out.println("Eliminar artículo");
				break;
			case "3":
				StockArticulos.modificarCantidad();
				System.out.println("Modificar cantidad");
				break;
			case "4":
				StockArticulos.mostrarStock();
				// Preguntar al usuario si desea continuar después de mostrar el stock
				System.out.println("Mostrar stock");
				System.out.println("¿Desea realizar otra acción? (S/N)");
				String respuesta1 = sc.nextLine().toUpperCase();
				continuar = respuesta1.equalsIgnoreCase("S");
				break;
			case "5":
				System.out.println("-- INICIO COMPRA --");
				comprarArticulos(sc, compra);
				

			case "6":
				System.out.println();
				continuar = false; // Salir del bucle
				break;

			default:
				System.out.println("Opción no válida!");
//				CIERRE SWITCH
			}
		}
	}
	// repetir la compra
	public static void repetirCompra() {

		Scanner sc = new Scanner(System.in);
		boolean otroCliente = true;
		double gananciasBrutoTotal = 0;
		double gananciasNetoTotal = 0;

		while (otroCliente) {
			HashMap<String, Double[]> compra = new HashMap<>();

			System.out.println("Este es el stock actual de la tienda:\n");
			StockArticulos.mostrarStock();

			System.out.println("-- INICIO COMPRA --");
			comprarArticulos(sc, compra);
			imprimirResumenCompra(compra);

			double totalCompraBruto = calcularTotalCompraBruto(compra);
			double totalCompraNeto = calcularTotalCompraNeto(compra);
			gananciasBrutoTotal += totalCompraBruto;
			gananciasNetoTotal += totalCompraNeto;
			imprimirTotalesCompra(totalCompraBruto, totalCompraNeto);
			procesarPago(sc, totalCompraNeto);

			System.out.println("¿Hay otro cliente? (Y/N)");
			String respuesta = sc.nextLine().toUpperCase();
			if (!respuesta.equals("Y")) {
				otroCliente = false;
				System.out.println("\n-- CAJA FINAL --" + "\nGanancias totales (Bruto): " + gananciasBrutoTotal
						+ "\nGanancias totales (Neto): " + gananciasNetoTotal);

			} else {
				mostrarOpcionesInventario();
			}
		}
	}

	// Te da el tiquet de la compra
	public static void imprimirResumenCompra(HashMap<String, Double[]> compra) {
		System.out.println("-- RESUMEN DE LA COMPRA --");
		for (String nombreProducto : compra.keySet()) {
			Double[] detalles = compra.get(nombreProducto);

			double precioBruto = detalles[0];
			double precioNeto = detalles[1];
			double iva = detalles[2];
			System.out.println("Producto: " + nombreProducto + "\nPrecio Bruto: " + precioBruto + "€ / Precio Neto: "
					+ precioNeto + "€ / IVA: " + iva + "%");
		}
	}

	// Devuelve por consola Total Bruto y Total Neto
	public static void imprimirTotalesCompra(double totalCompraBruto, double totalCompraNeto) {
		System.out.println(
				"Total de la compra (Bruto): " + totalCompraBruto + "\nTotal de la compra (Neto): " + totalCompraNeto);
	}

//			Método para mostrar opciones del inventario
	public static void mostrarOpcionesInventario() {
		System.out.println("\nOpciones del inventario:" + "\n1. Agregar artículo" + "\n2. Eliminar artículo"
				+ "\n3. Modificar cantidad" + "\n4. Mostrar stock" + "\n5. Salir");
	}

	// Calcula el precio neto a partir del bruto y su IVA
	public static double calcularPrecioConIVA(double precio, double iva) {
		return precio * (1 + iva / 100);
	}

	// Calcula el total Bruto de la compra
	public static double calcularTotalCompraBruto(HashMap<String, Double[]> compra) {
		double totalCompraBruto = 0;
		for (Double[] detalles : compra.values()) {
			totalCompraBruto += detalles[0];
		}
		return totalCompraBruto;
	}

	// Calcula el total Neto de la compra
	public static double calcularTotalCompraNeto(HashMap<String, Double[]> compra) {
		double totalCompraNeto = 0;
		for (Double[] detalles : compra.values()) {
			totalCompraNeto += detalles[1];
		}
		return totalCompraNeto;
	}
}