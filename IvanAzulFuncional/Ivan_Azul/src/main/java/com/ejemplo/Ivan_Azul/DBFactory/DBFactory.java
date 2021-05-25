package com.ejemplo.Ivan_Azul.DBFactory;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.ejemplo.Ivan_Azul.Entities.Customer;
import com.ejemplo.Ivan_Azul.Models.CustomersModel;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBFactory {
    static final String JDBCHOST = "jdbc:mysql://localhost:3306/northwind?useSSL=false";
    static final String JDBCUSER = "northwind";
    static final String JDBCPASSWD = "northwind";

    public static DataSource getMySQLDataSource() {

	MysqlDataSource mysqlDS = null;

	mysqlDS = new MysqlDataSource();
	mysqlDS.setURL(JDBCHOST);
	mysqlDS.setUser(JDBCUSER);
	mysqlDS.setPassword(JDBCPASSWD);

	return mysqlDS;
    }
    
  //Para mostrar todos los datos por java
    public static void main(String[] args) {
		
		try {
			
			Customer cliente;
			CustomersModel misClientes = new CustomersModel();
			System.out.println("Conexion a la BBDD con éxito");
			cliente = misClientes.read(11);
			if(cliente != null)
			{
				System.out.println(cliente.toString());
				Integer id = misClientes.insert(cliente);
				System.out.println("ACabo de inserta erl cliente: "+ id);
			}
			else
				System.err.println("No he podido leer el cliente");
			
			
			ArrayList<Customer> clientes = misClientes.lista("job_title=\"Purchasing Manager\"", 100, 1);
			System.out.println("Todos los Purchasing Managers ( " + clientes.size() + "): ");
			
			for(int i = 0;i<clientes.size();i++) {
				System.out.println(clientes.get(i).toString());
			}
			
			
		} catch (SQLException e) {
				System.err.println("Error al conectarse: "+e.getMessage());
				System.exit(1);
		}
		

		
	}

}
