package Gestion_Pedidos;

import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
	private String Nombre;
	private String Apellidos;
	private String FechaDeAlta;
	private String Telefono;
	private String Direccion;

	private String Historial;
	
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Contructor con parametros de Cliente
	 * @param nombre
	 * @param apellidos
	 * @param fecha 
	 * @param telefono
	 * @param direccion
	 * @param historial
	 */
	public  Cliente(String nombre,String apellidos,String fecha,String telefono,String direccion,String historial){
		this.Nombre=nombre.toLowerCase();
		this.Apellidos=apellidos.toUpperCase();
		this.FechaDeAlta=fecha;
		this.Telefono=telefono;
		this.Direccion=direccion;
		this.Historial=historial;
	}
	/**
	 * Constructor sin historial
	 * @param nombre
	 * @param apellidos
	 * @param fecha
	 * @param telefono
	 * @param direccion
	 * @param historial
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
	//GETTERS AND SETTERS//
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


	public String getHistorial() {
		return Historial;
	}

	public void setHistorial(String historial) {
		Historial = historial;
	}
	
	public void añadirHistorial(String cadena) {
		Historial=Historial.concat(cadena);
	}
	
	/**
	 * Metodo para mostrar los distintos clientes
	 * @param c
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
