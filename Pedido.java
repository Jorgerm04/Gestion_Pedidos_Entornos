package Gestion_Pedidos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class Pedido {
	
	private Cliente cliente;
	private Producto Producto1;
	private double precio1;
	private int unidades1;
	private Producto Producto2;
	private double precio2;
	private int unidades2;
	private double ImporteTotal;
	String fechaPedido;
	int opcion1,opcion2;

	Scanner sc=new Scanner(System.in);
	
	
	public Pedido(Cliente cliente, Producto producto1, double precio1, int unidades1, Producto producto2,
			double precio2, int unidades2, double importeTotal,String FechaPedido) {
		this.cliente = cliente;
		this.Producto1 = producto1;
		this.precio1 = precio1;
		this.unidades1 = unidades1;
		this.Producto2 = producto2;
		this.precio2 = precio2;
		this.unidades2 = unidades2;
		this.ImporteTotal = importeTotal;
		this.fechaPedido=FechaPedido;
	}
	
	/**
	 * Contructor vacio de la clase Pedido
	 */
	 public Pedido() {
		 
	 }
	 
	 //GETERS AND SETERS//
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Producto getProducto1() {
		return Producto1;
	}

	public void setProducto1(Producto producto1) {
		Producto1 = producto1;
	}

	public Producto getProducto2() {
		return Producto2;
	}

	public void setProducto2(Producto producto2) {
		Producto2 = producto2;
	}
	
	public int getUnidades1() {
		return unidades1;
	}

	public void setUnidades1(int unidades1) {
		this.unidades1 = unidades1;
	}

	public int getUnidades2() {
		return unidades2;
	}

	public void setUnidades2(int unidades2) {
		this.unidades2 = unidades2;
	}

	public double getImporteTotal() {
		return ImporteTotal;
	}

	public void setImporteTotal(double importeTotal) {
		ImporteTotal = importeTotal;
	}
	
	public double getPrecio1() {
		return precio1;
	}

	public void setPrecio1(double precio1) {
		this.precio1 = precio1;
	}

	public double getPrecio2() {
		return precio2;
	}

	public void setPrecio2(double precio2) {
		this.precio2 = precio2;
	}
	
	

	public String getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(String fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public void inicioSesion(ArrayList<Cliente>clientesPricipal) {
		boolean valido = false;
				
				while(!valido) {
					String telefono;
					System.out.println("Introduzaca su numero de telefono para realizar el pedido");
					telefono=sc.nextLine();
					for(int i=0;i<clientesPricipal.size();i++){
						if(clientesPricipal.get(i).getTelefono().equals(telefono)) {
							System.out.println("Telefono encontrado");
							valido=true;
							setCliente(clientesPricipal.get(i));
						}
					}
					if(!valido) {
						System.out.println("El telefono "+telefono+"no corresponde a ningun cliente");
					} 
				}
		
	}
	
	
	public void agregarProducto1(ArrayList<Producto>productosPrincipal) {
		
		System.out.println("Selecciona un producto");
		
		for(int i=0;i<productosPrincipal.size();i++){
				System.out.println(i+1+". "+productosPrincipal.get(i).getNombre());
		}
		
		opcion1=sc.nextInt();
		sc.nextLine();
		
		for(int i=0;i<productosPrincipal.size();i++){
			if(opcion1-1==i) {
				setPrecio1(productosPrincipal.get(i).getPrecio());
				boolean valido=false;
				while(!valido) {
						System.out.println("\nCuantas unidades quiere");
						setUnidades1(sc.nextInt());
						if(getUnidades1()<=30&&getUnidades1()>0) {
							productosPrincipal.get(i).controlStock(getUnidades1(),productosPrincipal.get(i));
							setImporteTotal(getUnidades1()*getPrecio1());
							setProducto1(productosPrincipal.get(i));
							System.out.println("\nEl precio actual de tu pedido es de "+getImporteTotal()+" euros");
							valido=true;
						} else {
							System.out.println("Unidad no valida");
						}
					
						
				}
				
				
		}
		}
	}
	
	
	public void agregarProducto2(ArrayList<Producto>productosPrincipal) {
		
		System.out.println("Selecciona un producto");
		
		for(int i=0;i<productosPrincipal.size();i++){
				System.out.println(i+1+". "+productosPrincipal.get(i).getNombre());
		}
		
		opcion2=sc.nextInt();
		sc.nextLine();
		
		for(int i=0;i<productosPrincipal.size();i++){
			if(opcion2-1==i) {
				setPrecio2(productosPrincipal.get(i).getPrecio());
				boolean valido=false;
				while(!valido) {
					System.out.println("\nCuantas unidades quiere");
					setUnidades2(sc.nextInt());
					if(getUnidades2()<31&&getUnidades2()>0) {
						productosPrincipal.get(i).controlStock(getUnidades2(),productosPrincipal.get(i));
						setImporteTotal(getUnidades2()*getPrecio2()+getImporteTotal());
						setProducto2(productosPrincipal.get(i));
						System.out.println("\nEl precio actual de tu pedido es de "+getImporteTotal()+" euros");
						valido=true;
						}else {
							System.out.println("Unidades no validas");
						}
				}
				
				
				
		}
		}
	}
	
	/**
	 * Metodo que muestra el precio, la cantidad de productos y mas informacion sobre el pedido realizado
	 */
	public void mostrarPedido() {
		 System.out.println("\nCANT.\t\tPRODUCTO\t\tPRECIO UD.\t\tTOTAL");
	        System.out.println("=====\t\t========\t\t========\t\t=====");
	        System.out.println(getUnidades1()+"\t\t"+Producto1.getNombre()+"\t\t\t"+Producto1.getPrecio()+"\t\t\t"+Producto1.getPrecio()*getUnidades1());
	        
	        if(Producto2==null) {
	        	System.out.println("\nTOTAL\t\t-------------------------------------------->\t"+getImporteTotal());
	        	
	        } else {
	        	System.out.println(getUnidades2()+"\t\t"+Producto2.getNombre()+"\t\t\t"+Producto2.getPrecio()+"\t\t\t"+Producto2.getPrecio()*getUnidades2());
		        System.out.println("\nTOTAL\t\t-------------------------------------------->\t"+getImporteTotal());
	        }     
	}
}
