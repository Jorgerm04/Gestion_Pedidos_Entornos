package Gestion_Pedidos;

import java.util.Date;
import java.util.Scanner;

public class PasarelaDePago {
	private double Importe;
	public long CodigoPago;
	
	/**
	 * Contructor de PasarelaDePago con parametros
	 * @param importe importe que hay que pagar por el pedido
	 * @param codigoPago codigo que se genera al pagar el pedido
	 */
	public PasarelaDePago(double importe, long codigoPago) {
		this.Importe = (double)(Math.round(importe*100)/100);
		this.CodigoPago = codigoPago;
	}
	
	/**
	 * Contructor vacio
	 */
	public PasarelaDePago() {
		
	}
	
	public double getImporte() {
		return Importe;
	}

	public void setImporte(double importe) {
		Importe = importe;
	}

	public long getCodigoPago() {
		return CodigoPago;
	}

	public void setCodigoPago(long codigoPago) {
		CodigoPago = codigoPago;
	}
	
	/**
	 * Metodo para realizar el pado del pedido
	 * @param p pedido realizado
	 */
	public void realizarPago(Pedido p){
		int opcion;
		Scanner sc=new Scanner(System.in);
		
		System.out.println("\nComo desea realizar el pago: ");
		System.out.println("1. Efectivo");
		System.out.println("2. Tarjeta");
		System.out.println("3. Cuenta bancaria");
		
		opcion=sc.nextInt();
		sc.nextLine();
		
		long codigoLong;
		String codigoString;
		switch(opcion) {
		case 1:{
			PagarEfectivo();
			p.mostrarPedido();
			
			codigoLong=getCodigoPago();
			System.out.println("Codigo de pago: "+getCodigoPago());
			
			codigoString=String.valueOf(codigoLong);
		}
		break;
		
		case 2:{
			PagoTarjeta();
			p.mostrarPedido();
			codigoLong=getCodigoPago();
			System.out.println("Codigo de pago: "+getCodigoPago());
			
			codigoString=String.valueOf(codigoLong);	
		}
		break;
		
		case 3:{
			PagoCuenta();
			p.mostrarPedido();
			codigoLong=getCodigoPago();
			System.out.println("Codigo de pago: "+getCodigoPago());
			
			codigoString=String.valueOf(codigoLong);
		}
		break;
		
		default:{
			System.err.println("Opcion no valida");
		}
		break;
		}
		
	}
	
	/**
	 * Metodo para pagar el pedido en efectivo
	 */
	public void PagarEfectivo() {
		
		Scanner sc=new Scanner(System.in);
		double pago;
		double cambio;
		int quinientos,doscientos,cien,cincuenta,veinte,diez,cinco,un_euro=0,cent;
        double residuo;
        double dinero_falta;
        
        Date codigo=new Date();
		
		System.out.println("Introduzca el importe que va a entregar, si le sobra dinero se lo devolveremos sin problema: ");
        pago=sc.nextDouble();
        if(pago>0&&pago<=9999999){
        	if(pago==getImporte()){
        		System.out.println("Ha pagado el importe exacto, por lo tanto, no le sobra nada.");
        	} else if(pago>getImporte()) {
                cambio = pago-getImporte();

                quinientos=(int)cambio/500;
                residuo=cambio%500;



                doscientos=(int)residuo/200;
                residuo=residuo%200;



                cien=(int)residuo/100;
                residuo=residuo%100;



                cincuenta=(int)residuo/50;
                residuo=residuo%50;



                veinte=(int)residuo/20;
                residuo=residuo%20;



                diez=(int)residuo/10;
                residuo=residuo%10;


                cinco=(int)residuo/5;
                residuo=residuo%5;

                while (residuo>=1){
                    un_euro++;
                    residuo--;
                };

                residuo*=100;
                residuo=Math.round(residuo);

                cent=(int)(residuo);

                System.out.println("\nAquí tiene su cambio");

                System.out.println("\nBilletes de 500 euros:  "+quinientos);
                System.out.println("\nBilletes de 200 euros:  "+doscientos);
                System.out.println("\nBilletes de 100 euros:  "+cien);
                System.out.println("\nBilletes de 50 euros:   "+cincuenta);
                System.out.println("\nBilletes de 20 euros:   "+veinte);
                System.out.println("\nBilletes de 10 euros:   "+diez);
                System.out.println("\nBilletes de 5 euros:    "+cinco);
                System.out.println("\nMonedas de 1 euro:      "+un_euro);
                System.out.println("\nCentimos:               "+cent);
                setImporte(0.0);
                setCodigoPago(codigo.getTime());

        	} else{
        		dinero_falta=getImporte()-pago;
        		System.err.println("No lo ha pagado todo.");
            }
        } else {
            if(pago<0){
                System.out.println("No puede pagar con valores negativos");
            } else{
                System.out.println("No manejamos cantidades tan grandes de dinero");
            }
        }
	}
	
	/**
	 * Metodo para pagar con tarjeta
	 */
	public void PagoTarjeta() {
		String tarjeta;
		Scanner sc=new Scanner(System.in);
		Date codigo=new Date();
		
		System.out.println("Este cajero solo acepta las siguientes tarjetas:"+
                "\n\nVisa (Por ejemplo: 4567 1234 5678 4321)" +
                "\nMastercard (Por ejemplo: 5678 1234 5678 9876)" +
                "\nAmericanExpress (Por ejemplo: 3456 012345 98765)"+
                "\n\nEl primer digito debe obedecer al ejemlo para ser válida la tarjeta"+
                "\n\nIntroduzca su número de tarjeta con los espacios necesarios: ");
        tarjeta=sc.nextLine();//Si no pongo antes de este scanner algo no me leera la línea y se la saltará. Por eso lo pongo dos veces.

        if(tarjeta.startsWith("3")) {
            if (tarjeta.length() == 17) {
                System.out.println("****Su pago de " + (double)(Math.round(getImporte()*100)/100) + " euros se ha realizado correctamente con su tarjeta American Express****"+
                        "\n*** GRACIAS *** :)");
                setCodigoPago(codigo.getTime());
            } else {
                System.err.println("Su numero de tarjeta no es correcto. Asegurese de que cumple con los espacios y todos los digitos necesarios para que sea válido");
            }
        } else if (tarjeta.startsWith("4")) {
            if (tarjeta.length()==19){
                System.out.println("**** Su pago de " + (double)(Math.round(getImporte()*100)/100) + " euros se ha realizado correctamente con su tarjeta Visa ****"+
                        "\n*** GRACIAS *** :)");
                setCodigoPago(codigo.getTime());
            } else {
                System.err.println("Su numero de tarjeta no es correcto. Asegurese de que cumple con los espacios y todos los digitos necesarios para que sea válido");
            }
        } else  if (tarjeta.startsWith("5")) {
            if (tarjeta.length() == 19) {
                System.out.println("****Su pago de " + (double)(Math.round(getImporte()*100)/100) + " euros se ha realizado correctamente con su tarjeta Mastercard****"+
                        "\n*** GRACIAS *** :)");
                setCodigoPago(codigo.getTime());
            } else {
                System.err.println("Su numero de tarjeta no es correcto. Asegurese de que cumple con los espacios y todos los digitos necesarios para que sea válido");
            }
        } else {
            System.out.println("\nLa tarjeta no es Visa, Mastercard o AmericanExpress");
        }
	}
	
	/**
	 * Metodo para pagar con cuenta bancaria
	 */
	public void PagoCuenta() {
		String cuenta;
		Scanner sc=new Scanner(System.in);
		Date codigo=new Date();
		
		System.out.println("Este cajero solo acepta cuentas espanolas:"+
                "\n(Por ejemplo: ES12 4567 1234 5678 4321 0987)" +
                "\n\nLas dos primeras letras deben obedecer al ejemlo para ser válida la cuenta"+
                "\n\nIntroduzca su número de cuenta con 24 caracteres y 5 espacios: ");
        cuenta=sc.nextLine();

        if(cuenta.startsWith("ES")) {
            if (cuenta.length() == 29) {
                System.out.println("****Su pago de " + (double)(Math.round(getImporte()*100)/100) + " euros se ha realizado correctamente con su cuenta****"+
                        "\n*** GRACIAS *** :)");
                setCodigoPago(codigo.getTime());
            } else {
                System.err.println("Su numero de cuenta no es correcto. Asegurese de que cumple con los espacios y todos los digitos necesarios para que sea válido");
            }
        } else {
        	System.err.println("Su numero de cuenta no es correcto. Asegurese de que cumple con los espacios y todos los digitos necesarios para que sea válido");	
        }
		
	}


}
