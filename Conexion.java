package Gestion_Pedidos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Conexion {
	private static String selectTableSQL;
	private static String insertTableSQL;
	private static String updateTableSQL;
	
	private static final String NOMBRE_BD = "gestion_pedidos";
	private static final String UBICACION = "localhost";
	private static final String PUERTO = "3306";
	private static final String USUARIO = "root";
	private static final String CLAVE = "1234";

	// Para versión mysql-conector-java-5.1.6.jar + mysql Server 5.7
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + UBICACION + ":" + PUERTO + "/" + NOMBRE_BD
			+ "?characterEncoding=utf8";
	
	static {

		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}

	public Connection conectar() {
		Connection conexion = null;

		try {

			// Establecemos la conexión para eso java nos prporciona conexion =
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	}
	
	public static void cerrar_conexion(Connection cn, Statement stm, ResultSet rs) {
		// Liberar recursos revisar el orden en el que se cierran, orden inverso
		try {
			if (rs != null) {
				rs.close();
			}
			if (stm != null) {
				stm.close();
			}
			if (cn != null) {
				cn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println();
		}

	}
	
	public void cargarClientesBDD (ArrayList<Cliente>clientesPrincipal) {
		
		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;

		selectTableSQL = "SELECT * FROM cliente";

		try {
			// Abrimos la conexion con la base de datos
			cn = conexion.conectar();
			stm = cn.createStatement();
			// Pasamos la consulta al ResultSet
			rs = stm.executeQuery(selectTableSQL);

			while (rs.next()) {
				String nombre = rs.getString(1);
				String apellidos = rs.getString(2);
				String fechaDeAlta = rs.getString(3);
				String telefono = rs.getString(4);
				String direccion = rs.getString(5);
				
				Cliente cliente = new Cliente(nombre,apellidos,fechaDeAlta,telefono,direccion);
				
				clientesPrincipal.add(cliente);
			}
		} catch (SQLException e) { // TODO: handle exception

		} finally {
			cerrar_conexion(cn, stm, rs);
			

		}
	}
	
	public void cargarProductosBDD (ArrayList<Producto>productosPrincipal) {
			
			Conexion conexion = new Conexion();
			Connection cn = null;
			Statement stm = null;
			ResultSet rs = null;
	
			selectTableSQL = "SELECT * FROM producto";
	
			try {
				// Abrimos la conexion con la base de datos
				cn = conexion.conectar();
				stm = cn.createStatement();
				// Pasamos la consulta al ResultSet
				rs = stm.executeQuery(selectTableSQL);
	
				while (rs.next()) {
					String nombre = rs.getString(1);
					double precio = rs.getDouble(2);
					int cantidad = rs.getInt(3);
					
					
					
					Producto producto = new Producto(nombre,precio,cantidad);
					
					for(int i=0;i<cantidad;i++) {
			        	producto.getStock()[i]=1;
			        }
					
					productosPrincipal.add(producto);
				}
			} catch (SQLException e) { // TODO: handle exception
	
			} finally {
				cerrar_conexion(cn, stm, rs);
				
	
			}
		}
	
	public void insertarCliente(ArrayList<Cliente>clientesNuevos,ArrayList<Cliente>clientesPrincipal) {

			Conexion conexion = new Conexion();
			Connection cn = null;
			PreparedStatement ps = null;

			// Crear sentencia SQL para insertar en la base de datos
			insertTableSQL = "INSERT INTO cliente VALUES (?,?,?,?,?)";

			try {

				cn = conexion.conectar();
				ps = cn.prepareStatement(insertTableSQL);

				ps.setString(1,clientesNuevos.get(0).getNombre());
				ps.setString(2,clientesNuevos.get(0).getApellidos());
				ps.setString(3,clientesNuevos.get(0).getFechaDeAlta());
				ps.setString(4,clientesNuevos.get(0).getTelefono());
				ps.setString(5,clientesNuevos.get(0).getDireccion());
				
				ps.executeUpdate();
				clientesPrincipal.add(clientesNuevos.get(0));
				clientesNuevos.clear();

			} catch (SQLException e) { // TODO: handle exception

				e.printStackTrace();

			} finally { // Liberar recursos revisar el orden en el que se cierran
				cerrar_conexion(cn, ps, null);

			}

		
	}
	
	
	public void insertarProducto(ArrayList<Producto>productosNuevos,ArrayList<Producto>productosPrincipal) {

		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps = null;

		// Crear sentencia SQL para insertar en la base de datos
		insertTableSQL = "INSERT INTO producto VALUES (?,?,?)";

		try {

			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);

			ps.setString(1,productosNuevos.get(0).getNombre());
			ps.setDouble(2,productosNuevos.get(0).getPrecio());
			ps.setInt(3,productosNuevos.get(0).getCantidad());
			
			ps.executeUpdate();
			productosPrincipal.add(productosNuevos.get(0));
			productosNuevos.clear();

		} catch (SQLException e) { // TODO: handle exception

			e.printStackTrace();

		} finally { // Liberar recursos revisar el orden en el que se cierran
			cerrar_conexion(cn, ps, null);

		}

	
	}
	
	
	public void updateCantidadProducto(Producto p) {

		Conexion conexion = new Conexion();
		Connection cn = null;
		Statement stm = null;

		// Crear sentencia SQL para actualizar la cantidad de producto en la base de datos
		updateTableSQL = "UPDATE producto set cantidad =" + p.getCantidad() + " where nombre='"+p.getNombre()+"'";

		try {

			cn = conexion.conectar();
			stm = cn.createStatement();
			stm.executeUpdate(updateTableSQL);

		} catch (SQLException e) { // TODO: handle exception

			e.printStackTrace();

		} finally { // Liberar recursos revisar el orden en el que se cierran
			cerrar_conexion(cn, stm, null);

		}

	}
	
	
	public void insertarPedido(Pedido pedi) {

		Conexion conexion = new Conexion();
		Connection cn = null;
		PreparedStatement ps = null;

		// Crear sentencia SQL para insertar en la base de datos
		insertTableSQL = "INSERT INTO pedido VALUES (?,?,?,?,?)";

		try {

			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
			
			if(pedi.getProducto2()==null) {
				ps.setString(1,pedi.getCliente().getTelefono());
				ps.setString(2,pedi.getProducto1().getNombre());
				ps.setDouble(3,pedi.getPrecio1());
				ps.setInt(4,pedi.getUnidades1());
				ps.setString(5, pedi.getFechaPedido());
				ps.executeUpdate();
			} else {
				ps.setString(1,pedi.getCliente().getTelefono());
				ps.setString(2,pedi.getProducto1().getNombre());
				ps.setDouble(3,pedi.getPrecio1());
				ps.setInt(4,pedi.getUnidades1());
				ps.setString(5, pedi.getFechaPedido());
				ps.executeUpdate();
				ps.setString(1,pedi.getCliente().getTelefono());
				ps.setString(2,pedi.getProducto2().getNombre());
				ps.setDouble(3,pedi.getPrecio2());
				ps.setInt(4,pedi.getUnidades2());
				ps.setString(5, pedi.getFechaPedido());
				ps.executeUpdate();
				
			}

		} catch (SQLException e) { // TODO: handle exception

			e.printStackTrace();

		} finally { // Liberar recursos revisar el orden en el que se cierran
			cerrar_conexion(cn, ps, null);

		}

	
	}
	
	


}
