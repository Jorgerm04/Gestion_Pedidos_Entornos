package Gestion_Pedidos;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ProductoTest {

	@Test
	public void testControlStock1() {
		// Crear instancia de Producto con cantidad 30
        Producto producto = new Producto("Platano", 1.2,30 );
        for(int i=0;i<30;i++) {
        	producto.getStock()[i]=1;
        }
        // Realizar control de stock para comprar 5 productos
        producto.controlStock(5, producto);
        // Verificar que la cantidad se actualizó correctamente
        Assertions.assertEquals(25, producto.getCantidad());
        // Verificar que los primeros 5 elementos del stock se actualizaron a 0
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(0, producto.getStock()[i]);
        }
        // Verificar que los elementos restantes del stock se mantuvieron en 1
        for (int i = 5; i < producto.getStock().length; i++) {
            Assertions.assertEquals(1, producto.getStock()[i]);
        }
	}
	
	@Test
	public void testControlStock2() {
		// Crear instancia de Producto con cantidad 30
        Producto producto = new Producto("Platano", 1.2,30 );
        for(int i=0;i<30;i++) {
        	producto.getStock()[i]=1;
        }
        // Realizar control de stock para comprar 31 productos
        producto.controlStock(31, producto);
        // Verificar que la cantidad se actualizó correctamente
        Assertions.assertEquals(30, producto.getCantidad());
        // Verificar que los elementos del stock siguen siendo todo 1
        for (int i = 0; i < 30; i++) {
            Assertions.assertEquals(1, producto.getStock()[i]);
        }
	}
	
	@Test
	public void testControlStock3() {
		// Crear instancia de Producto con cantidad 30
        Producto producto = new Producto("Platano", 1.2,30 );
        for(int i=0;i<30;i++) {
        	producto.getStock()[i]=1;
        }
        // Realizar control de stock para comprar 30 productos
        producto.controlStock(30, producto);
        // Verificar que la cantidad se actualizó correctamente
        Assertions.assertEquals(30, producto.getCantidad());
        /** Verificar que los elementos del stock siguen siendo todo 1 
         *  porque se ha repuesto**/
        for (int i = 0; i < 30; i++) {
            Assertions.assertEquals(1, producto.getStock()[i]);
        }
	}
	
	@Test
	public void testControlStock4() {
		// Crear instancia de Producto con cantidad 30
        Producto producto = new Producto("Platano", 1.2,30 );
        for(int i=0;i<30;i++) {
        	producto.getStock()[i]=1;
        }
        // Realizar control de stock para comprar 26 productos
        producto.controlStock(26, producto);
        /**Verificar que la cantidad se actualizó correctamente
         * ya que si quedan 5 o menos productos se repone
         */
        Assertions.assertEquals(30, producto.getCantidad());
        /** Verificar que los elementos del stock siguen siendo todo 1 
         *  porque se han repuesto**/
        for (int i = 0; i < 30; i++) {
            Assertions.assertEquals(1, producto.getStock()[i]);
        }
	}
	
	

}
