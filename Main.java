package Gestion_Pedidos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) {
		Fichero documentos=new Fichero();
		int opcion ;
		ArrayList<Cliente>clientesNuevos = new ArrayList<Cliente>();
		ArrayList<Cliente>clientesPrincipal=new ArrayList<Cliente>();
		ArrayList<Producto>productosNuevos = new ArrayList<Producto>();
		ArrayList<Producto>productosPrincipal=new ArrayList<Producto>();
		Cliente client= new Cliente();
		Producto product = new Producto();
		char eleccion;
		Scanner sc= new Scanner(System.in);
		Pedido pedi= new Pedido();
		PasarelaDePago pago=new PasarelaDePago();
		boolean bucle;
		bucle=true;
		boolean BaseDeDatos=false;
		Conexion conec = new Conexion();
		SimpleDateFormat formateador= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
		if(BaseDeDatos) {
			conec.cargarClientesBDD(clientesPrincipal);
			conec.cargarProductosBDD(productosPrincipal);
			
		} else {
			documentos.fileScannerCliente(clientesPrincipal);
			documentos.fileScannerProducto(productosPrincipal);
		}
		
		
		
		
		
		while(bucle) {
			System.out.println("\n\n\n#####MENU INICIAL#####");
			System.out.println("1. Crear Cliente");
			System.out.println("2. Crear Producto");
			System.out.println("3. Mostar Cliente");
			System.out.println("4. Mostrar Producto");
			System.out.println("5. Realizar pedido");
			System.out.println("6. Salir");
			opcion=sc.nextInt();
			
			switch (opcion) {
			case 1:{
				client.crearCliente(clientesNuevos, clientesPrincipal);
				
				boolean valido= false;
				
				while(!valido) {
					System.out.println("\n\nQuiere guardar el cliente (y/n) ?");
					
					eleccion=sc.next().charAt(0);
					
					if(eleccion=='y') {
						valido=true;
						if(BaseDeDatos) {
							conec.insertarCliente(clientesNuevos, clientesPrincipal);
						} else {
							documentos.guardarCliente(clientesNuevos, clientesPrincipal);
						}
						
						
					} else if(eleccion=='n'){
						valido=true;
						clientesNuevos.clear();
						
					} else {
						System.err.println("Opcion no valida");
					}
					
				}
			}
			break;
			
			case 2:{
				product.crearProducto(productosNuevos, productosPrincipal);
				
				boolean valido= false;
				
				while(!valido) {
					System.out.println("\n\nQuiere guardar el producto (y/n) ?");
					
					eleccion=sc.next().charAt(0);
					
					if(eleccion=='y') {
						valido=true;
						
						if(BaseDeDatos) {
							conec.insertarProducto(productosNuevos, productosPrincipal);
						} else {
							documentos.guardarProducto(productosNuevos, productosPrincipal);
						}
						
						
					} else if(eleccion=='n'){
						valido=true;
						productosNuevos.clear();
						
					} else {
						System.err.println("Opcion no valida");
					}
					
				}
			}
			break;
			
			case 3:{
				client.mostrarCliente(clientesPrincipal);
			}
			break;
			
			case 4:{
				product.mostrarProducto(productosPrincipal);
			}
			break;
			
			case 5:{
				pedi.inicioSesion(clientesPrincipal);
				pedi.agregarProducto1(productosPrincipal);
				
				if(BaseDeDatos) {
					conec.updateCantidadProducto(pedi.getProducto1());
				} else {
					documentos.cambiarCantidadProducto(productosPrincipal);
				}
				
				boolean valido=false;
				
				while(!valido) {
					System.out.println("\nDesea anadir otro producto a su pedido (y / n)");
					
					eleccion=sc.next().charAt(0);
					
					if(eleccion=='y') {
						valido=true;
						pedi.agregarProducto2(productosPrincipal);
						if(BaseDeDatos) {
							conec.updateCantidadProducto(pedi.getProducto2());
						}else {
							documentos.cambiarCantidadProducto(productosPrincipal);
						}
						
					} else if(eleccion=='n'){
						valido=true;
						System.out.println("\nEl precio actual de tu pedido es de "+pedi.getImporteTotal()+" euros");
					} else {
						System.err.println("Opcion no valida");
					}
					
				}
				
				
				pago.setImporte(pedi.getImporteTotal());
				pago.realizarPago(pedi);
				Date fechaActual= new Date();
				pedi.setFechaPedido(formateador.format(fechaActual));
				
				if(BaseDeDatos) {
					conec.insertarPedido(pedi);
				} else {
					documentos.guardarPedido(pedi);
				}
				
				pedi=null;
				
			}
			break;
			case 6:{
				bucle=false;
				System.out.println("Saliendo . . .");
			}
			break;
			default:{
				System.out.println("Opcion no valida");
				
			}
			break;
			}
				
		}
	}
}