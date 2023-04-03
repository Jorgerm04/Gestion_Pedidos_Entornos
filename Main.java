package Gestion_Pedidos;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[]args) {
		Fichero documentos=new Fichero();
		int opcion ;
		ArrayList<Cliente>clientes=new ArrayList<Cliente>();
		ArrayList<Producto>productos=new ArrayList<Producto>();
		Cliente client= new Cliente();
		Producto product = new Producto();
		char eleccion;
		Scanner sc= new Scanner(System.in);
		Pedido pedi= new Pedido();
		PasarelaDePago pago=new PasarelaDePago();
		boolean bucle;
		bucle=true;
		
		documentos.fileScannerCliente(clientes);
		documentos.fileScannerProducto(productos);
		
		
		
		while(bucle==true) {
			System.out.println("\n\n\n#####MENU INICIAL#####");
			System.out.println("1.Crear Cliente");
			System.out.println("2.Crear Producto");
			System.out.println("3.Mostar Cliente");
			System.out.println("4.Mostrar Producto");
			System.out.println("5.Realizar pedido");			
			opcion=sc.nextInt();
			
			switch (opcion) {
			case 1:{
				documentos.escribeClienteFichero(clientes);
			}
			break;
			
			case 2:{
				documentos.escribeProductoFichero(productos);
			}
			break;
			
			case 3:{
				client.mostrarCliente(clientes);
			}
			break;
			
			case 4:{
				product.mostrarProducto(productos);
			}
			break;
			
			case 5:{
				bucle=false;
				pedi.inicioSesion(clientes);
				pedi.agregarProducto1(productos);
				
				System.out.println("\nDesea anadir otro producto a su pedido (y / n)");
				
				eleccion=sc.next().charAt(0);
				
				if(eleccion=='y') {
					pedi.agregarProducto2(productos);	
					sc.nextLine();
					
				} else if(eleccion=='n'){
					System.out.println("\nEl precio actual de tu pedido es de "+pedi.getImporteTotal()+" euros");
					
				} else {
					System.err.println("Opcion no valida");
				}
				pago.setImporte(pedi.getImporteTotal());
				pago.realizarPago(pedi);
				
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