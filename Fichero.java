package Gestion_Pedidos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Fichero {
	String ClientesTxt="C:/Users/jorge/eclipse-workspace/Practica_Git/src/Gestion_Pedidos/Clientes.txt";
	String ProductosTxt="C:/Users/jorge/eclipse-workspace/Practica_Git/src/Gestion_Pedidos/Productos.txt";
	String PedidosTxt="C:/Users/jorge/eclipse-workspace/Practica_Git/src/Gestion_Pedidos/Pedidos.txt";
	/**
	 * Metodo que carga todos los clientes del fichero de texto en el Arraylist de clientes
	 * @param clientesPricipal Arraylist de clientes guardados
	 */
	public void fileScannerCliente(ArrayList<Cliente>clientesPrincipal) {
		try {
            File archivo = new File(ClientesTxt);
            Scanner sca = new Scanner(archivo);

            while (sca.hasNextLine()) {
                String linea = sca.nextLine();
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String apellido = datos[1];
                String fecha = datos[2];
                String telefono = datos[3];
                String direccion = datos[4];
        		
                Cliente cliente = new Cliente(nombre, apellido,fecha,telefono, direccion);
                clientesPrincipal.add(cliente);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
		
		
	}
	/**
	 * Metodo que carga todos los productos del fichero de texto en el ArrayList de productos
	 * @param productosPricipal ArrayList de productos guardados
	 */
	public void fileScannerProducto(ArrayList<Producto>productoPrincipal) {
		try {
            File archivo = new File(ProductosTxt);
            Scanner sca = new Scanner(archivo);

            while (sca.hasNextLine()) {
                String linea = sca.nextLine();
                String[] datos = linea.split(",");
                
                if (datos.length >= 2) {
                    String nombre = datos[0];
                    String precio = datos[1];
                    String cantidad = datos[2];
                    Producto producto = new Producto(nombre,Double.parseDouble(precio),Integer.parseInt(cantidad));
                    for(int i=0;i<Integer.parseInt(cantidad);i++) {
			        	producto.getStock()[i]=1;
			        }
                    productoPrincipal.add(producto);
                } else {
                    System.out.println("Sin contenido");
                    
                }
                
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
		
		
	}
	
	public void guardarCliente(ArrayList<Cliente>clientesNuevos,ArrayList<Cliente>clientesPrincipal) {
		

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(ClientesTxt, true));
            printWriter.println(clientesNuevos.get(0).getNombre() + "," + clientesNuevos.get(0).getApellidos() 
            		+ "," + clientesNuevos.get(0).getFechaDeAlta() + ","+ clientesNuevos.get(0).getTelefono() 
            		+ "," + clientesNuevos.get(0).getDireccion());
            printWriter.close();
            System.out.println("El cliente ha sido guadado correctamente al archivo.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
        
        clientesPrincipal.add(clientesNuevos.get(0));
        clientesNuevos.clear();
		
	}
	
	public void guardarProducto(ArrayList<Producto>productosNuevos,ArrayList<Producto>productosPrincipal) {

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(ProductosTxt,true));
            printWriter.println(productosNuevos.get(0).getNombre() + "," + productosNuevos.get(0).getPrecio()+ "," + productosNuevos.get(0).getCantidad());
            printWriter.close();
            System.out.println("El producto ha sido agregado correctamente al archivo.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
        
        productosPrincipal.add(productosNuevos.get(0));
        productosNuevos.clear();
		
	}
	
	public void cambiarCantidadProducto(ArrayList<Producto>productosPrincipal) {

        try {
        	 PrintWriter printWriterBorrar = new PrintWriter(new FileWriter(ProductosTxt));
             printWriterBorrar.close();
        	
        	for(int i=0;i<productosPrincipal.size();i++) {
        		PrintWriter printWriter = new PrintWriter(new FileWriter(ProductosTxt,true));
                printWriter.println(productosPrincipal.get(i).getNombre() + "," + productosPrincipal.get(i).getPrecio()+ "," + productosPrincipal.get(i).getCantidad());
                printWriter.close();
        	}
           
            

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
	}
	
	public void guardarPedido(Pedido pedi) {

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(PedidosTxt,true));
            if(pedi.getProducto2()==null) {
                printWriter.println(pedi.getCliente().getTelefono() + "," +pedi.getProducto1().getNombre()+ "," +pedi.getPrecio1()+","+pedi.getUnidades1()+","+pedi.getFechaPedido());
            } else {
            	printWriter.println(pedi.getCliente().getTelefono() + "," +pedi.getProducto1().getNombre()+ "," +pedi.getPrecio1()+","+pedi.getUnidades1()+","+pedi.getFechaPedido());
            	printWriter.println(pedi.getCliente().getTelefono() + "," +pedi.getProducto2().getNombre()+ "," +pedi.getPrecio2()+","+pedi.getUnidades2()+","+pedi.getFechaPedido());
            }
            printWriter.close();

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
		
	}

}
