package Gestion_Pedidos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class FicheroTest {

	@Test
    public void testGuardarCliente1() {
		Fichero documento= new Fichero();
		ArrayList<Cliente>clientesNuevos = new ArrayList<Cliente>();
		ArrayList<Cliente>clientesPrincipal = new ArrayList<Cliente>();
        Cliente cliente = new Cliente("John", "Wick","04/06/2023", "888888888", "Calle 4");
        clientesNuevos.add(cliente);

        documento.guardarCliente(clientesNuevos, clientesPrincipal);

        Assertions.assertEquals(1, clientesPrincipal.size());
        Assertions.assertTrue(clientesNuevos.isEmpty());
    }
}
