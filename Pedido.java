package Gestion_Pedidos;

import java.util.ArrayList;
import java.util.Scanner;


public class Pedido {
	
	private Cliente cliente;
	private Producto Producto1;
	private Producto Producto2;
	private Double ImporteTotal;
	private PasarelaDePago Pago;
	private String Estado;
	int opcion1,opcion2;

	Scanner sc=new Scanner(System.in);
	
	/**
	 * Constructor con parametros del pedido
	 * @param cliente
	 * @param producto1
	 * @param producto2
	 * @param importeTotal
	 * @param pago
	 * @param estado
	 */
	public Pedido(Cliente cliente,Producto producto1,Producto producto2, Double importeTotal,PasarelaDePago pago,String estado) {
		this.cliente=cliente;
		this.Producto1=producto1;
		this.Producto2=producto2;
		this.ImporteTotal=importeTotal;
		this.Pago=pago;
		this.Estado=estado;
	}
	 /**
	  * Contructor con parametros de pedido pero en este caso con un solo producto,
	  * ya que se puede dar el caso de que el cliente solo pida un producto en vez de dos
	  * 
	  * @param cliente
	  * @param producto1
	  * @param importeTotal
	  * @param pago
	  * @param estado
	  */
	public Pedido(Cliente cliente,Producto producto1, Double importeTotal,PasarelaDePago pago,String estado) {
		this.cliente=cliente;
		this.Producto1=producto1;
		this.ImporteTotal=importeTotal;
		this.Pago=pago;
		this.Estado=estado;
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

	public Double getImporteTotal() {
		return ImporteTotal;
	}

	public void setImporteTotal(Double importeTotal) {
		ImporteTotal = importeTotal;
	}

	public PasarelaDePago getPago() {
		return Pago;
	}

	public void setPago(PasarelaDePago pago) {
		Pago = pago;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
	public void inicioSesion(ArrayList<Cliente>c) {
		boolean valido = false;
				
				while(!valido) {
					String telefono;
					System.out.println("Introduzaca su numero de telefono para realizar el pedido");
					telefono=sc.nextLine();
					for(int i=0;i<c.size();i++){
						if(c.get(i).getTelefono().equals(telefono)) {
							System.out.println("Telefono encontrado");
							valido=true;
							setCliente(c.get(i));
						}
					}
					if(!valido) {
						System.out.println("El telefono "+telefono+"no corresponde a ningun cliente");
					} 
				}
		
	}
	
	
	public void agregarProducto1(ArrayList<Producto>p) {
		int unidades1;
		double price1;
		
		System.out.println("Selecciona un producto");
		
		for(int i=0;i<p.size();i++){
				System.out.println(i+1+". "+p.get(i).getNombre());
		}
		
		opcion1=sc.nextInt();
		sc.nextLine();
		
		for(int i=0;i<p.size();i++){
			if(opcion1-1==i) {
				price1=p.get(i).getPrecio();
				boolean valido=false;
				while(!valido) {
						System.out.println("\nCuantas unidades quiere");
						unidades1=sc.nextInt();
						if(unidades1<=30||unidades1>0) {
							p.get(i).setCantidad(unidades1);
							price1=unidades1*price1;
							p.get(i).controlStock(unidades1,p.get(i));
							price1=p.get(i).getPrecio();	
							valido=true;
						} else {
							System.out.println("Unidad no valida");
						}
					
						
				}
				setImporteTotal(price1);
				setProducto1(p.get(i));
				
		}
		}
		System.out.println("\nEl precio actual de tu pedido es de "+getImporteTotal()+" euros");
		
	}
	
	
	public void agregarProducto2(ArrayList<Producto>p) {
		int unidades2;
		double price2;
		
		System.out.println("Selecciona un producto");
		
		for(int i=0;i<p.size();i++){
				System.out.println(i+1+". "+p.get(i).getNombre());
		}
		
		opcion2=sc.nextInt();
		sc.nextLine();
		
		for(int i=0;i<p.size();i++){
			if(opcion2-1==i) {
				price2=p.get(i).getPrecio();
				boolean valido=false;
				while(!valido) {
					System.out.println("\nCuantas unidades quiere");
					unidades2=sc.nextInt();
					if(unidades2<31&&unidades2>0) {
						p.get(i).setCantidad(unidades2);
						price2=unidades2*price2;
						p.get(i).controlStock(unidades2,p.get(i));
						price2=p.get(i).getPrecio();
						valido=true;
						}else {
							System.out.println("Unidades no validas");
						}
				}
				
				setImporteTotal(price2+getImporteTotal());
				setProducto2(p.get(i));
				
		}
		}
		System.out.println("\nEl precio actual de tu pedido es de "+getImporteTotal()+" euros");
		
	}
	
	/**
	 * Metodo que muestra el precio, la cantidad de productos y mas informacion sobre el pedido realizado
	 */
	public void mostrarPedido() {
		 System.out.println("\nCANT.\t\tPRODUCTO\t\tPRECIO UD.\t\tTOTAL");
	        System.out.println("=====\t\t========\t\t========\t\t=====");
	        System.out.println(Producto1.getCantidad()+"\t\t"+Producto1.getNombre()+"\t\t\t"+Producto1.getPrecio()+"\t\t\t"+Producto1.getPrecio()*Producto1.getCantidad());
	        
	        if(Producto2==null) {
	        	System.out.println("\nTOTAL\t\t-------------------------------------------->\t"+getImporteTotal());
	        	
	        } else {
	        	System.out.println(Producto2.getCantidad()+"\t\t"+Producto2.getNombre()+"\t\t\t"+Producto2.getPrecio()+"\t\t\t"+Producto2.getPrecio()*Producto2.getCantidad());
		        System.out.println("\nTOTAL\t\t-------------------------------------------->\t"+getImporteTotal());	
	        }     
	}
}
