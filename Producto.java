package Gestion_Pedidos;

import java.util.Scanner;

public class Producto {
	private String Nombre;
	private Double Precio;
	private int Cantidad;
	private int[] Stock= {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
	
	Scanner sc=new Scanner(System.in);
	
	/**
	 * Contructor con parametros de Producto
	 * @param nombre
	 * @param precio
	 * @param cantidad
	 */
	public Producto(String nombre,Double precio, int cantidad) {
		this.Nombre=nombre.toUpperCase();
		this.Precio=(double)(Math.round(precio*100)/100);
		this.Cantidad=cantidad;
		
	}
	
	/**
	 * Contructor vacio de Producto
	 */
	public Producto() {
	}
	
	//GETERS AND SETERS//
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Double getPrecio() {
		return Precio;
	}

	public void setPrecio(Double precio) {
		Precio = precio;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	
	public int[] getStock() {
		return Stock;
	}

	public void setStock(int[] stock) {
		Stock = stock;
	}
	
	/**
	 * Metodo para crear productos
	 * @param p
	 */
	public void crearProducto(Producto p) {
		System.out.println("\nDigame el nombre del producto: ");
		p.setNombre(sc.nextLine());
		System.out.println("Digame el precio del producto: ");
		p.setPrecio(sc.nextDouble());
	}
	
	/**
	 * Metodo para mostrar la informacion de los productos
	 * @param p
	 */
	public void mostrarProducto(Producto p) {
		System.out.println("Nombre: "+p.getNombre());
		System.out.println("Precio: "+p.getPrecio()+" euros");
	}
	
	public void controlStock(int cantidad,Producto p) {
			
			//Si cantidad es mayor que el tamaño del array el cliente no podra comprar mas productos de los que hay en stock
			if(cantidad>p.getStock().length) {
				System.out.println("No tenemos tanta cantidad de producto");
				
				//Si la catidad es igual al tamaño del array podra comprarlo pero no se podran comprar mas productos de ese tipo
			}	else if(cantidad==p.getStock().length) {
				
				for (int i = 0; i < cantidad; i++) {
					p.getStock()[i]=0;
				    }
				
				System.out.println("Ha comprado todos los productos disponibles");
				
				System.out.println("Reponiendo . . . . .");
		    	
		    	for (int i = 0; i < p.getStock().length; i++) {
		    		p.getStock()[i]=1;
				    }
				
				
				/**
				 * Si la catidad es menor que el tamaño del array el cliente podra comprar la cantidad de productos deseada y se
				 * indicara los productos que queden en stock, si quedan 5 o menos se repondran los productos
				 */
			} else if(cantidad<p.getStock().length&&cantidad>0){
				
				for (int i = 0; i < cantidad; i++) {
					p.getStock()[i]=0;
				    }
				
				int contador = 0;
			    for (int i = 0; i < p.getStock().length; i++) {
			      if (p.getStock()[i]== 1) {
			        contador++;
			      }
			    }
			    
			    System.out.println("Quedan en stock "+contador+" productos");
			    
			    if(contador<=5) {
			    	System.out.println("Quedan pocas unidades");
			    	System.out.println("Reponiendo . . . . .");
			    	
			    	for (int i = 0; i < p.getStock().length; i++) {
			    		p.getStock()[i]=1;
					    }
			    }
			    
				
			}
		}
}
