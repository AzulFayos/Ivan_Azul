package com.ejemplo.Ivan_Azul.Rest;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ejemplo.Ivan_Azul.Entities.Product;
import com.ejemplo.Ivan_Azul.Models.ProductModel;

@Path("productos")
public class ProductRest {
	 static ProductModel products;

	    public ProductRest() {

		try {
			products = new ProductModel();
		} catch (SQLException e) {
		    System.err.println("No puedo abrir la conexion con 'Customers': " + e.getMessage());
		}
	    }

	    @GET
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response list(@QueryParam("filter") String filter, 
		                 @QueryParam("limit") Integer limit, 
		                 @QueryParam("offset") Integer offset) {
		Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
		
		if (products != null) {
		    ArrayList<Product> listaProductos = products.lista(filter, limit, offset);
		    if (listaProductos != null) {
			respuesta = Response.status(Response.Status.OK).entity(listaProductos).build();
		    }

		}
		return respuesta;
	    }

	    @GET
	    @Path("/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response read(@PathParam("id") Integer id) {
		
		Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("No he encotrado").build();
		
		if (products != null) {
			Product producto = products.read(id);
		    if (producto != null) {
			respuesta = Response.status(Response.Status.OK).entity(producto).build();
		    }
		}
		return respuesta;
	    }

	    @POST
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response add(Product producto) {
		Integer idproducto;
		Response response;
		try {
			idproducto = products.insert(producto);
		    response = Response.status(Response.Status.CREATED).entity(idproducto).build();
		} catch (Exception e) {
		    response = Response.status(Response.Status.CONFLICT).entity("ERROR: " + e.getCause() + " " + e.getMessage())
			    .build();
		}
		return response;
	    }

	    @PUT
	    @Produces(MediaType.APPLICATION_JSON)
	    @Consumes(MediaType.APPLICATION_JSON)
	    public Response update(Product producto) {
		Boolean productoactualizado;
		Response response;
		try {
			productoactualizado = products.update(producto);
		    response = Response.status(Response.Status.OK).entity(productoactualizado).build();
		} catch (Exception e) {
		    response = Response.status(Response.Status.NOT_MODIFIED).entity("ERROR: " + e.getCause() + " " + e.getMessage())
			    .build();
		}
		return response;
	    }
	    
	    
	    @DELETE
	    @Path("/{id}")
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response delete(@PathParam("id") Integer id) {
		Boolean productoactualizado;
		Response response;
		try {
			productoactualizado = products.delete(id);
		    response = Response.status(Response.Status.OK).entity(productoactualizado).build();
		} catch (Exception e) {
		    response = Response.status(Response.Status.NOT_FOUND).entity("ERROR: " + e.getCause() + " " + e.getMessage())
			    .build();
		}
		return response;
	    }

}
