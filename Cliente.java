package Gestion_Pedidos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Cliente {
	private String Nombre;
	private String Apellidos;
	private String FechaDeAlta;
	private String Telefono;
	private String Direccion;
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Contructor con parametros de Cliente
	 * @param nombre nomnbre del cliente
	 * @param apellidos apellidos del cliente
	 * @param fecha fecha de alta del cliente
	 * @param telefono telefono del cliente
	 * @param direccion direccion del cliente
	 */
	public  Cliente(String nombre,String apellidos,String fecha,String telefono,String direccion){
		this.Nombre=nombre.toLowerCase();
		this.Apellidos=apellidos.toUpperCase();
		this.FechaDeAlta=fecha;
		this.Telefono=telefono;
		this.Direccion=direccion;
	}
	
	/**
	 * Constructor vacio de Cliente
	 */
	public Cliente() {
		
	}
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre.toLowerCase();
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos.toUpperCase();
	}
	
	public String getFechaDeAlta() {
		return FechaDeAlta;
	}

	public void setFechaDeAlta(String fechaDeAlta) {
		FechaDeAlta = fechaDeAlta;
	}
	

	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
	    Telefono=telefono;
	}
	
	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}
	
	/**
	 * Metodo para crear un cliente y añadirlo al ArrayList de clientes nuevos
	 * @param clientesNuevos ArrayList de clientes recien creados
	 * @param clientesPrincipal ArrayList de clientes guardados
	 */
	public void crearCliente(ArrayList<Cliente>clientesNuevos,ArrayList<Cliente>clientesPrincipal) {
		Scanner sca= new Scanner(System.in);
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		Date fechaActual = new Date();
		String fechaFormateada = formatoFecha.format(fechaActual);
		
		System.out.println("Introduce el nombre:");
        String nombre = sca.nextLine();

        System.out.println("Introduce el apellido:");
        String apellido = sca.nextLine();
        
        String fechaAlta = fechaFormateada;

        /**
         * Comprobacion del telefono
         */
        boolean valido=false;
        
        String telefono=null;
        
        while(!valido) {
        	System.out.println("Introduce el telefono:");
            telefono = sca.nextLine();
            
            if(telefono.length()==9&&(telefono.startsWith("6")||telefono.startsWith("7")||telefono.startsWith("8")||telefono.startsWith("9"))) {
            	valido=true;
            	for(int i=0;i<clientesPrincipal.size();i++) {
            		if(clientesPrincipal.get(i).getTelefono().equals(telefono)) {
            			valido=false;
            			System.out.println("El numero de telefono ya esta asociado con un cliente existente");
            		}
            	}
            	
            } else {
            	System.out.println("Numero no valido");
            	valido=false;
            }
        	
        }
        /**
         * Finaliza la comprobacion del telefono
         */

        System.out.println("Introduce la direccion:");
        String direccion = sca.nextLine();
        

        Cliente cliente = new Cliente(nombre, apellido, fechaAlta, telefono, direccion);
        
        
        clientesNuevos.add(cliente);
	}
	
	/**
	 * Metodo para mostrar los datos de un cliente seleccionado
	 * @param c ArrayList de clientes guardados 
	 */
	public void mostrarCliente(ArrayList<Cliente> c) {
		boolean valido = false;
		
		while(!valido) {
			String telefono;
			System.out.println("Introduzaca el numero de telefono del cliente que quiere mostrar");
			telefono=sc.nextLine();
			for(int i=0;i<c.size();i++){
				if(c.get(i).getTelefono().equals(telefono)) {
					System.out.println("Nombre: "+c.get(i).getNombre());
					System.out.println("Apellido: "+c.get(i).getApellidos());
					System.out.println("Telefono: "+c.get(i).getTelefono());
					System.out.println("Direccion: "+c.get(i).getDireccion());
					valido=true;
				}
			}
			if(!valido) {
				System.out.println("Cliente no encontrado con el telefono "+telefono);
			} 
		}
	}
}
