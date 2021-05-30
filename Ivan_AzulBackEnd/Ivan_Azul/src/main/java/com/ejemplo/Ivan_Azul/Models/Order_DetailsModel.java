package com.ejemplo.Ivan_Azul.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.Ivan_Azul.DBFactory.DBFactory;
import com.ejemplo.Ivan_Azul.Entities.Order_Details;

public class Order_DetailsModel {

	static Connection conexion = null;
	//public static Integer idcliente;

	public Order_DetailsModel() throws SQLException {
		DataSource ds = DBFactory.getMySQLDataSource();
		conexion = ds.getConnection();
	}

	public Order_Details read(Integer id) {
		Order_Details Order_Details = null;
		Statement sentencia = null;

		String sql = "SELECT * "
				+ "FROM order_details " + "WHERE id = " + id;

		try {
			sentencia = conexion.createStatement();
			ResultSet rs = sentencia.executeQuery(sql);
			while (rs.next()) { // Si hay un detalles de producto que existe
				Order_Details = new Order_Details(rs.getInt("id"), rs.getInt("order_id"), rs.getInt("product_id"),
						rs.getFloat("quantity"), rs.getFloat("unit_price"), rs.getFloat("discount"),
						rs.getInt("status_id"), rs.getTimestamp("date_allocated"), rs.getInt("purchase_order_id"),
						rs.getInt("inventory_id"));
			}
			;

		} catch (SQLException e) {
			System.err.println("Error en read de detalles de producto: " + e.getMessage());
			return null;
		}

		return Order_Details;
	}

	/**
	 * 
	 * @param cliente
	 * @return Devuelve el id del registro recien insertado
	 */
	public Integer insert(Order_Details Order_Details) throws SQLException {
		Integer id = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO order_details ( " + "`order_id`, `product_id`, `quantity`, "
				+ "`unit_price`, `discount`, `status_id` , `date_allocated`," + "`purchase_order_id`, `inventory_id`) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, Order_Details.getOrder_id());
			ps.setInt(2, Order_Details.getProduct_id());
			ps.setFloat(3, Order_Details.getQuantity());
			ps.setFloat(4, Order_Details.getUnit_price());
			ps.setFloat(5, Order_Details.getDiscount());
			ps.setInt(6, Order_Details.getStatus_id());
			ps.setTimestamp(7, Order_Details.getDate_allocated());
			ps.setInt(8, Order_Details.getPurchase_order_id());
			ps.setInt(9, Order_Details.getInventory_id());
			if (ps.executeUpdate() > 0) {
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next()) {
					id = rs.getInt(1);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error al insertar detalles de producto: " + e.getMessage());
			throw e;
		}

		return id;
	}

	public Boolean delete(Integer idOrder_Details) throws SQLException {
		Boolean resultado = false;

		PreparedStatement ps = null;
		String sql = "DELETE FROM order_details where id = ?";
		try {
			ps = conexion.prepareStatement(sql);

			ps.setInt(1, idOrder_Details);

			resultado = (ps.executeUpdate() > 0);

		} catch (SQLException e) {
			System.err.println("Error al detalles de producto: " + e.getMessage());
			throw e;
		}

		return resultado;
	}	
	
	public Boolean update(Order_Details Order_Details) throws SQLException {
		Boolean resultado = false;

		PreparedStatement ps = null;
		String sql = "UPDATE order_details set " + "order_id = ?, " + "product_id = ?, " + "quantity = ?, "
				+ "unit_price = ?, " + "discount = ?, " + "status_id  = ?, " + "date_allocated = ?, "
				+ "purchase_order_id = ?, " + "inventory_id = ?, " + "where id = ? on update cascade";
		try {
			ps = conexion.prepareStatement(sql);
			ps.setInt(1, Order_Details.getOrder_id());
			ps.setInt(2, Order_Details.getProduct_id());
			ps.setFloat(3, Order_Details.getQuantity());
			ps.setFloat(4, Order_Details.getUnit_price());
			ps.setFloat(5, Order_Details.getDiscount());
			ps.setInt(6, Order_Details.getStatus_id());
			ps.setTimestamp(7, Order_Details.getDate_allocated());
			ps.setInt(8, Order_Details.getPurchase_order_id());
			ps.setInt(9, Order_Details.getInventory_id());

			resultado = (ps.executeUpdate() > 0);

		} catch (SQLException e) {
			System.err.println("Error al actualizar detalles de producto: " + e.getMessage());
			throw e;
		}

		return resultado;
	}

	public ArrayList<Order_Details> lista(String filtro, Integer limite, Integer offset)

    {
	ArrayList<Order_Details> Order_Details = new ArrayList<Order_Details>();
	Statement sentencia = null;

	String sql = "SELECT `id`, "
		+ "`order_id`, "
		+ "`product_id`, "
		+ "`quantity`, "
		+ "`unit_price`, "
		+ "`discount`, "
		+ "`status_id`, "
		+ "`date_allocated`,"
		+ "`purchase_order_id`, "
		+ "`inventory_id` "
		+ "FROM order_details ";

	try {
	    if (filtro != null)
		sql += " WHERE " + filtro;
	    if (limite != null)
		sql += " LIMIT " + limite;
	    if (offset != null)
		sql += " OFFSET " + offset;
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { // Si todavía hay un detalles de producto lo añado al array
	    	Order_Details.add(new Order_Details(
	    		rs.getInt("id"),
	    		rs.getInt("order_id"),
	    		rs.getInt("product_id"),
	    		rs.getFloat("quantity"),
	    		rs.getFloat("unit_price"),
	    		rs.getFloat("discount"),
	    		rs.getInt("status_id"),
	    		rs.getTimestamp("date_allocated"),
	    		rs.getInt("purchase_order_id"),
	    		rs.getInt("inventory_id")));
	    };
	} catch (SQLException e) {
	    System.err.println("Error en read de detalles de producto: " + e.getMessage());
	    return null;
	}

	return Order_Details;
    }

}
