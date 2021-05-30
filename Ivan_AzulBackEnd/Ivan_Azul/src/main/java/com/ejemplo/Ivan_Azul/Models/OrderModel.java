package com.ejemplo.Ivan_Azul.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.Ivan_Azul.DBFactory.DBFactory;
import com.ejemplo.Ivan_Azul.Entities.Order;

public class OrderModel {
	

	static Connection conexion = null;
	//public static Integer idcliente;

	    public OrderModel() throws SQLException {
		DataSource ds = DBFactory.getMySQLDataSource();
		conexion = ds.getConnection();
	    }

	    public Order read(Integer id) {
	    Order Order = null;
		Statement sentencia = null;

		String sql = "SELECT * " + "FROM orders "
			+ "WHERE id = " + id;

		try {
		    sentencia = conexion.createStatement();
		    ResultSet rs = sentencia.executeQuery(sql);
		    while (rs.next()) { // Si hay un pedido que existe
		    Order = new Order(
				rs.getInt("id"),
				rs.getInt("employee_id"),
				rs.getInt("customer_id"),
				rs.getTimestamp("shipped_date"),
				rs.getInt("shipper_id"),
				rs.getString("ship_name"),
				rs.getString("ship_address"),
				rs.getString("ship_city"),
				rs.getString("ship_state_province"),
				rs.getString("ship_zip_postal_code"),
				rs.getString("ship_country_region"),
				rs.getFloat("shipping_fee"),
				rs.getFloat("taxes"),
				rs.getString("payment_type"),
				rs.getTimestamp("paid_date"),
				rs.getString("notes"),
				rs.getFloat("tax_rate"),
				rs.getInt("tax_status_id"),
				rs.getInt("status_id"));
		    };
		    
		} catch (SQLException e) {
		    System.err.println("Error en read de pedidos: " + e.getMessage());
		    return null;
		}

		return Order;
	    }
	    
	    public Order readbyCliente(Integer customer_id) {
		    Order Order = null;
			Statement sentencia = null;

			String sql = "SELECT * " + "FROM orders "
				+ "WHERE customer_id = " + customer_id;

			try {
			    sentencia = conexion.createStatement();
			    ResultSet rs = sentencia.executeQuery(sql);
			    while (rs.next()) { // Si hay un pedido que existe
			    Order = new Order(
					rs.getInt("id"),
					rs.getInt("employee_id"),
					rs.getInt("customer_id"),
					rs.getTimestamp("shipped_date"),
					rs.getInt("shipper_id"),
					rs.getString("ship_name"),
					rs.getString("ship_address"),
					rs.getString("ship_city"),
					rs.getString("ship_state_province"),
					rs.getString("ship_zip_postal_code"),
					rs.getString("ship_country_region"),
					rs.getFloat("shipping_fee"),
					rs.getFloat("taxes"),
					rs.getString("payment_type"),
					rs.getTimestamp("paid_date"),
					rs.getString("notes"),
					rs.getFloat("tax_rate"),
					rs.getInt("tax_status_id"),
					rs.getInt("status_id"));
			    };
			    
			} catch (SQLException e) {
			    System.err.println("Error en read de pedidos: " + e.getMessage());
			    return null;
			}

			return Order;
		    }

	    /**
	     * 
	     * @param cliente
	     * @return Devuelve el id del registro recien insertado
	     */
	    public Integer insert(Order Order) throws  SQLException {
		Integer id = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO orders ( "
			+ "`employee_id`, `customer_id`, "
			+ "`shipped_date`, `shipper_id`, `ship_name` , `ship_address`,"
			+ "`ship_city`, `ship_state_province`, `ship_zip_postal_code`, `ship_country_region`, `shipping_fee`, "
			+ "`taxes`, `payment_type`, `paid_date`, `notes`, `tax_rate`, `tax_status_id`, `status_id`) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
		    ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		    

		    if ( Order.getEmployee_id() == null)
		    {
			    ps.setNull(1, 0);
		    }
		    else
		    {
			    ps.setLong(1,Order.getEmployee_id());	
		    }
		    
		    
		    if ( Order.getCustomer_id() == null)
		    {
			    ps.setNull(2, 0);
		    }
		    else
		    {
			    ps.setLong(2, Order.getCustomer_id());	
		    }
		    
		    		    		  
		    
		    
		    
		    
		    if ( Order.getShipped_date() == null)
		    {
			    ps.setNull(3, 0);
		    }
		    else
		    {
			    ps.setTimestamp(3, Order.getShipped_date());	
		    }
		    
		    
		   
		    if ( Order.getShipper_id() == null)
		    {
			    ps.setNull(4, 0);
		    }
		    else
		    {
			    ps.setInt(4, Order.getShipper_id());	
		    }
		    
		    
		    ps.setString(5, Order.getShip_name());
		    
		    
		    
		 
		    if (Order.getShip_address() == null)
		    {
			    ps.setNull(6, 0);
		    }
		    else
		    {
			    ps.setString(6,Order.getShip_address());	
		    }
		    
		    
		    ps.setString(7, Order.getShip_city());
		    
		    
		    
		    if ( Order.getShip_state_province() == null)
		    {
			    ps.setNull(8, 0);
		    }
		    else
		    {
			    ps.setString(8, Order.getShip_state_province());	
		    }
		    
		    
		    ps.setString(9, Order.getShip_zip_postal_code());
		    
		    
		    
		    
		    if ( Order.getShip_country_region() == null)
		    {
			    ps.setNull(10, 0);
		    }
		    else
		    {
			    ps.setString(10, Order.getShip_country_region());	
		    }
		    		    
		    
		    
		    if ( Order.getShipping_fee() == null)
		    {
			    ps.setNull(11, 0);
		    }
		    else
		    {
			    ps.setFloat(11, Order.getShipping_fee());	
		    }
		    
		    
		   
		    if ( Order.getTaxes() == null)
		    {
			    ps.setNull(12, 0);
		    }
		    else
		    {
			    ps.setFloat(12,Order.getTaxes());	
		    }
		    
		    
		    
		    ps.setString(13, Order.getPayment_type());
		    
		    
		    
		  
		    if (Order.getPaid_date() == null)
		    {
			    ps.setNull(14, 0);
		    }
		    else
		    {
			    ps.setTimestamp(14,Order.getPaid_date());	
		    }
		    
		    
		 
		    if (Order.getPaid_date() == null)
		    {
			    ps.setNull(15, 0);
		    }
		    else
		    {
			    ps.setString(15,Order.getNotes());	
		    }
		    
		    
		   
		    if ( Order.getTax_rate() == null)
		    {
			    ps.setNull(16, 0);
		    }
		    else
		    {
			    ps.setFloat(16,Order.getTax_rate());	
		    }
		    
		    
		  
		    if ( Order.getTax_status_id() == null)
		    {
			    ps.setNull(17, 0);
		    }
		    else
		    {
			    ps.setInt(17,Order.getTax_status_id());	
		    }
		    
	
		    if ( Order.getStatus_id() == null)
		    {
			    ps.setNull(18, 0);
		    }
		    else
		    {
			    ps.setInt(18,Order.getStatus_id());	
		    }
		    if (ps.executeUpdate() > 0) {
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
			    id = rs.getInt(1);
			}
		    }

		} catch (SQLException e) {
		    System.err.println("Error al insertar pedido: " + e.getMessage());
		    throw e;
		}

		return id;
	    }

	    public Boolean delete(Integer idpedido) throws SQLException {
			Boolean resultado = false;
			PreparedStatement ps1 = null;
			PreparedStatement ps2 = null;
			PreparedStatement ps3 = null;
			String sql1 = "DELETE FROM order_details where order_id = ?";
			String sql2 = "DELETE FROM invoices where order_id = ?";
			String sql3 = "DELETE FROM orders where id = ?";
			
			try {
				
				ps1 = conexion.prepareStatement(sql1);

			    ps1.setInt(1, idpedido);

			    resultado = (ps1.executeUpdate() > 0);
			    
			    ps2 = conexion.prepareStatement(sql2);

			    ps2.setInt(1, idpedido);

			    resultado = (ps2.executeUpdate() > 0);
				
			    ps3 = conexion.prepareStatement(sql3);

			    ps3.setInt(1, idpedido);

			    resultado = (ps3.executeUpdate() > 0);

			} catch (SQLException e) {
			    System.err.println("Error al borrar pedido: " + e.getMessage());
			    throw e;
			}

			return resultado;
		    }

	    public Boolean update(Order Order) throws SQLException  {
		Boolean resultado = false;

		PreparedStatement ps = null;
		String sql = "UPDATE customers set "
			+ "employee_id = ?, "
			+ "customer_id = ?, "
			+ "shipped_date = ?, "
			+ "shipper_id = ?, "
			+ "ship_name  = ?, "
			+ "ship_address = ?, "
			+ "ship_city = ?, "
			+ "ship_state_province = ?, "
			+ "ship_zip_postal_code = ?, "
			+ "ship_country_region = ?, "
			+ "shipping_fee = ?, "
			+ "taxes = ?, "
			+ "payment_type = ?, "
			+ "paid_date = ?, "
			+ "notes = ?, "
			+ "tax_rate = ? "
			+ "tax_status_id = ? "
			+ "status_id = ? "
			+ "where id = ?";
		try {
		    ps = conexion.prepareStatement(sql);
		
			    ps.setLong(1,Order.getEmployee_id());	
		   
		    
		    ps.setLong(2, Order.getCustomer_id());
	
		    ps.setTimestamp(3, Order.getShipped_date());
		    ps.setInt(4, Order.getShipper_id());
		    ps.setString(5, Order.getShip_name());
		    ps.setString(6, Order.getShip_address());
		    ps.setString(7, Order.getShip_city());
		    ps.setString(8, Order.getShip_state_province());
		    ps.setString(9, Order.getShip_zip_postal_code());
		    ps.setString(10, Order.getShip_country_region());
		    ps.setFloat(11, Order.getShipping_fee());
		    ps.setFloat(12, Order.getTaxes());
		    ps.setString(13, Order.getPayment_type());
		    ps.setTimestamp(14, Order.getPaid_date());
		    ps.setString(15, Order.getNotes());
		    ps.setFloat(16, Order.getTax_rate());
		    ps.setInt(17, Order.getTax_status_id());
		    ps.setInt(18, Order.getStatus_id());
		    resultado = (ps.executeUpdate() > 0);

		} catch (SQLException e) {
		    System.err.println("Error al actualizar pedido: " + e.getMessage());
		    throw e;
		}

		return resultado;
	    }

	    public ArrayList<Order> lista(String filtro, Integer limite, Integer offset)

	    {
		ArrayList<Order> Order = new ArrayList<Order>();
		Statement sentencia = null;

		String sql = "SELECT * " 
			+ "FROM orders";

		try {
		    if (filtro != null)
			sql += " WHERE " + filtro;
		    if (limite != null)
			sql += " LIMIT " + limite;
		    if (offset != null)
			sql += " OFFSET " + offset;
		    sentencia = conexion.createStatement();
		    ResultSet rs = sentencia.executeQuery(sql);
		    while (rs.next()) { // Si todavía hay un pedido lo añado al array
			Order.add(new Order(
					rs.getInt("id"),
					rs.getInt("employee_id"),
					rs.getInt("customer_id"),
					rs.getTimestamp("shipped_date"),
					rs.getInt("shipper_id"),
					rs.getString("ship_name"),
					rs.getString("ship_address"),
					rs.getString("ship_city"),
					rs.getString("ship_state_province"),
					rs.getString("ship_zip_postal_code"),
					rs.getString("ship_country_region"),
					rs.getFloat("shipping_fee"),
					rs.getFloat("taxes"),
					rs.getString("payment_type"),
					rs.getTimestamp("paid_date"),
					rs.getString("notes"),
					rs.getFloat("tax_rate"),
					rs.getInt("tax_status_id"),
					rs.getInt("status_id")));
		    };
		} catch (SQLException e) {
		    System.err.println("Error en read de pedidos: " + e.getMessage());
		    return null;
		}

		return Order;
	    }
	    
	    public ArrayList<Order> listaPedidosClientes(Integer customer_id)

	    {
	    	
	    	ArrayList<Order> Order = new ArrayList<Order>();
			Statement sentencia = null;

			String sql = "SELECT * " 
				+ "FROM orders WHERE customer_id = "+ customer_id;

			try {
			    sentencia = conexion.createStatement();
			    ResultSet rs = sentencia.executeQuery(sql);
			    while (rs.next()) { // Si todavía hay un pedido lo añado al array
				Order.add(new Order(
						rs.getInt("id"),
						rs.getInt("employee_id"),
						rs.getInt("customer_id"),
						rs.getTimestamp("shipped_date"),
						rs.getInt("shipper_id"),
						rs.getString("ship_name"),
						rs.getString("ship_address"),
						rs.getString("ship_city"),
						rs.getString("ship_state_province"),
						rs.getString("ship_zip_postal_code"),
						rs.getString("ship_country_region"),
						rs.getFloat("shipping_fee"),
						rs.getFloat("taxes"),
						rs.getString("payment_type"),
						rs.getTimestamp("paid_date"),
						rs.getString("notes"),
						rs.getFloat("tax_rate"),
						rs.getInt("tax_status_id"),
						rs.getInt("status_id")));
			    };
			} catch (SQLException e) {
			    System.err.println("Error en read de pedidos: " + e.getMessage());
			    return null;
			}

			return Order;
	    }

}
