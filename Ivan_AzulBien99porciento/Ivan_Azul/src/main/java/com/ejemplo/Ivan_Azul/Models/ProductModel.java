package com.ejemplo.Ivan_Azul.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.Ivan_Azul.DBFactory.DBFactory;
import com.ejemplo.Ivan_Azul.Entities.Product;

public class ProductModel {
	
	Connection conexion = null;

    public ProductModel() throws SQLException {
	DataSource ds = DBFactory.getMySQLDataSource();
	conexion = ds.getConnection();
    }

    public Product read(Integer id) {
    	Product Product = null;
	Statement sentencia = null;

	String sql = "SELECT * " + "FROM products "
		+ "WHERE id = " + id;

	try {
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { // Si hay un cliente que existe
	    Product = new Product(
	    	rs.getString("supplier_ids"),
			rs.getInt("id"),
			rs.getString("product_code"),
			rs.getString("product_name"),
			rs.getString("description"),
			rs.getFloat("standard_cost"),
			rs.getFloat("list_price"),
			rs.getInt("reorder_level"),
			rs.getInt("target_level"),
			rs.getString("quantity_per_unit"),
			rs.getInt("discontinued"),
			rs.getInt("minimum_reorder_quantity"),
			rs.getString("category"),
			rs.getBlob("attachments"));
	    };
	    
	} catch (SQLException e) {
	    System.err.println("Error en read de Clientes: " + e.getMessage());
	    return null;
	}

	return Product;
    }

    /**
     * 
     * @param cliente
     * @return Devuelve el id del registro recien insertado
     */
    public Integer insert(Product Product) throws  SQLException {
	Integer id = null;
	PreparedStatement ps = null;
	String sql = "INSERT INTO products ( "
		+ "`supplier_ids`, `product_code`, `product_name`, "
		+ "`description`, `standard_cost`, `list_price` , `reorder_level`,"
		+ "`target_level`, `quantity_per_unit`, `discontinued`, `minimum_reorder_quantity`, `category`, `attachments`) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	try {
	    ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	    ps.setString(1, Product.getSupplier_ids());//Preguntar por posición
	    ps.setString(2, Product.getProduct_code());
	    ps.setString(3, Product.getProduct_name());
	    ps.setString(4, Product.getDescription());
	    ps.setFloat(5, Product.getStandard_cost());
	    ps.setFloat(6, Product.getList_price());
	    ps.setInt(7, Product.getReorder_level());
	    ps.setInt(8, Product.getTarget_level());
	    ps.setString(9, Product.getQuantity_per_unit());
	    ps.setInt(10, Product.getDiscontinued());
	    ps.setInt(11, Product.getMinimum_reorder_quantity());
	    ps.setString(12, Product.getCategory());
	    ps.setBlob(13, Product.getAttachments());
	    if (ps.executeUpdate() > 0) {
		ResultSet rs = ps.getGeneratedKeys();
		if (rs.next()) {
		    id = rs.getInt(1);
		}
	    }

	} catch (SQLException e) {
	    System.err.println("Error al insertar Customer: " + e.getMessage());
	    throw e;
	}

	return id;
    }

    public Boolean delete(Integer idproducto) throws SQLException {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "DELETE FROM products where id = ?";
	try {
	    ps = conexion.prepareStatement(sql);

	    ps.setInt(1, idproducto);

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al borrar Cliente: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public Boolean update(Product Product) throws SQLException  {
	Boolean resultado = false;

	PreparedStatement ps = null;
	String sql = "UPDATE products set "
		+ "supplier_ids = ?, "
		+ "product_code = ?, "
		+ "product_name = ?, "
		+ "description = ?, "
		+ "standard_cost = ?, "
		+ "list_price  = ?, "
		+ "reorder_level = ?, "
		+ "target_level = ?, "
		+ "quantity_per_unit = ?, "
		+ "discontinued = ?, "
		+ "minimum_reorder_quantity = ?, "
		+ "category = ?, "
		+ "attachments = ? "
		+ "where id = ?";
	try {
	    ps = conexion.prepareStatement(sql);
	    ps.setString(1, Product.getSupplier_ids());//Preguntar por posición
	    ps.setString(2, Product.getProduct_code());
	    ps.setString(3, Product.getProduct_name());
	    ps.setString(4, Product.getDescription());
	    ps.setFloat(5, Product.getStandard_cost());
	    ps.setFloat(6, Product.getList_price());
	    ps.setInt(7, Product.getReorder_level());
	    ps.setInt(8, Product.getTarget_level());
	    ps.setString(9, Product.getQuantity_per_unit());
	    ps.setInt(10, Product.getDiscontinued());
	    ps.setInt(11, Product.getMinimum_reorder_quantity());
	    ps.setString(12, Product.getCategory());
	    ps.setBlob(13, Product.getAttachments());

	    resultado = (ps.executeUpdate() > 0);

	} catch (SQLException e) {
	    System.err.println("Error al actualizar Cliente: " + e.getMessage());
	    throw e;
	}

	return resultado;
    }

    public ArrayList<Product> lista(String filtro, Integer limite, Integer offset)

    {
	ArrayList<Product> Product = new ArrayList<Product>();
	Statement sentencia = null;

	String sql = "SELECT * " 
		+ "FROM products ";

	try {
	    if (filtro != null)
		sql += " WHERE " + filtro;
	    if (limite != null)
		sql += " LIMIT " + limite;
	    if (offset != null)
		sql += " OFFSET " + offset;
	    sentencia = conexion.createStatement();
	    ResultSet rs = sentencia.executeQuery(sql);
	    while (rs.next()) { // Si todavía hay un cliente lo añado al array
	    	Product.add(new Product(
	    			rs.getString("supplier_ids"),
	    			rs.getInt("id"),
	    			rs.getString("product_code"),
	    			rs.getString("product_name"),
	    			rs.getString("description"),
	    			rs.getFloat("standard_cost"),
	    			rs.getFloat("list_price"),
	    			rs.getInt("reorder_level"),
	    			rs.getInt("target_level"),
	    			rs.getString("quantity_per_unit"),
	    			rs.getInt("discontinued"),
	    			rs.getInt("minimum_reorder_quantity"),
	    			rs.getString("category"),
	    			rs.getBlob("attachments")));
	    };
	} catch (SQLException e) {
	    System.err.println("Error en read de Clientes: " + e.getMessage());
	    return null;
	}

	return Product;
    }

}
