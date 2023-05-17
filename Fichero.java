package Gestion_Pedidos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Fichero {
	String ClientesTxt="C:/Users/jorge/eclipse-workspace/Practica_Git/src/Gestion_Pedidos/Clientes.txt";
	String ProductosTxt="C:/Users/jorge/eclipse-workspace/Practica_Git/src/Gestion_Pedidos/Productos.txt";
	
	/**
	 * Metodo que carga todos los clientes del fichero de texto en el Arraylist de clientes
	 * @param c Arraylist de clientes
	 */
	public void fileScannerCliente(ArrayList<Cliente>c) {
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
                c.add(cliente);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
		
		
	}
	/**
	 * Metodo que carga todos los productos del fichero de texto en el ArrayList de productos
	 * @param p ArrayList de productos
	 */
	public void fileScannerProducto(ArrayList<Producto>p) {
		try {
            File archivo = new File(ProductosTxt);
            Scanner sca = new Scanner(archivo);

            while (sca.hasNextLine()) {
                String linea = sca.nextLine();
                String[] datos = linea.split(",");
                String nombre = datos[0];
                String precio = datos[1];
        		
                Producto producto = new Producto(nombre,Double.parseDouble(precio));
                p.add(producto);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
            e.printStackTrace();
        }
		
		
	}
	
	/**
	 * Metodo para crear un cliente, que se guarden sus datos en el documentos de texto
	 * y que se añada al Arraylist de clientes.
	 * @param c Arrayist de clientes
	 */
	public void escribeClienteFichero(ArrayList<Cliente>c) {
		Scanner sca= new Scanner(System.in);
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual = new Date();
		String fechaFormateada = formatoFecha.format(fechaActual);
		
		System.out.println("Introduce el nombre:");
        String nombre = sca.nextLine();

        System.out.println("Introduce el apellido:");
        String apellido = sca.nextLine();
        
        String fechaAlta = fechaFormateada;

        //Comprobacion del numero de telefono
        boolean valido=false;
        
        String telefono = null;
        
        while(!valido) {
        	valido=true;
        	System.out.println("Introduce el teléfono:");
            telefono = sca.nextLine();
            
            if(telefono.length()==9&&(telefono.startsWith("6")||telefono.startsWith("7")||telefono.startsWith("8")||telefono.startsWith("9"))) {
            	for(int i=0;i<c.size();i++) {
            		if(c.get(i).getTelefono().equals(telefono)) {
            			valido=false;
            			System.out.println("El numero de telefono ya esta asociado con un cliente existente");
            		}
            	}
            	
            } else {
            	System.out.println("Numero no valido");
            	valido=false;
            }
        	
        }
        //Finaliza la comprobacion del telefono

        System.out.println("Introduce la dirección:");
        String direccion = sca.nextLine();
        

        Cliente cliente = new Cliente(nombre, apellido, fechaAlta, telefono, direccion);
        
        
        c.add(cliente);

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(ClientesTxt, true));
            printWriter.println(cliente.getNombre() + "," + cliente.getApellidos() + "," + cliente.getFechaDeAlta() + ","+ cliente.getTelefono() + "," + cliente.getDireccion());
            printWriter.close();
            System.out.println("El cliente ha sido agregado correctamente al archivo.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
		
	}
	
	/**
	 * Metodo para crear un producto, que se guarden sus datos en el documentos de texto
	 * y que se añada al Arraylist de productos.
	 * @param p Arrayist de productos
	 */
	public void escribeProductoFichero(ArrayList<Producto>p) {
		Scanner sca= new Scanner(System.in);
		
		//Comprobacion del numero de telefono
        boolean valido=false;
        
        String nombre = null;
        
        while(!valido) {
        	valido=true;
        	System.out.println("Introduce el nombre del producto:");
            nombre = sca.nextLine();
          
            	for(int i=0;i<p.size();i++) {
            		if(p.get(i).getNombre().equalsIgnoreCase(nombre)) {
            			valido=false;
            			System.out.println("El producto con ese nombre ya existe");
            		}
            	}
        	
        }
        //Finaliza la comprobacion del nombre del producto

        System.out.println("Introduce el precio:");
        BigDecimal precio = sca.nextBigDecimal();

        Producto producto = new Producto(nombre,  precio.doubleValue());
        p.add(producto);

        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(ProductosTxt, true));
            printWriter.println(producto.getNombre() + "," + producto.getPrecio());
            printWriter.close();
            System.out.println("El producto ha sido agregado correctamente al archivo.");

        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
		
	}

}
