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

import com.ejemplo.Ivan_Azul.Entities.Order;
import com.ejemplo.Ivan_Azul.Models.OrderModel;

@Path("pedidos")
public class OrderRest {
	static OrderModel orders;

    public OrderRest() {

	try {
		orders = new OrderModel();
	} catch (SQLException e) {
	    System.err.println("No puedo abrir la conexion con 'orders': " + e.getMessage());
	}
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("filter") String filter, 
	                 @QueryParam("limit") Integer limit, 
	                 @QueryParam("offset") Integer offset) {
	Response respuesta = Response.status(Response.Status.NOT_FOUND).build();
	
	if (orders != null) {
	    ArrayList<Order> listaPedidos = orders.lista(filter, limit, offset);
	    if (listaPedidos != null) {
		respuesta = Response.status(Response.Status.OK).entity(listaPedidos).build();
	    }

	}
	return respuesta;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response read(@PathParam("id") Integer id) {
	
	Response respuesta = Response.status(Response.Status.NOT_FOUND).entity("No he encotrado").build();
	
	if (orders != null) {
	    Order order = orders.read(id);
	    if (order != null) {
		respuesta = Response.status(Response.Status.OK).entity(order).build();
	    }
	}
	return respuesta;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Order order) {
	Integer idpedido;
	Response response;
	try {
	    idpedido = orders.insert(order);
	    response = Response.status(Response.Status.CREATED).entity(idpedido).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.CONFLICT).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Order order) {
	Boolean pedidoactualizado;
	Response response;
	try {
		pedidoactualizado = orders.update(order);
	    response = Response.status(Response.Status.OK).entity(pedidoactualizado).build();
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
	Boolean pedidoactualizado;
	Response response;
	try {
		pedidoactualizado = orders.delete(id);
	    response = Response.status(Response.Status.OK).entity(pedidoactualizado).build();
	} catch (Exception e) {
	    response = Response.status(Response.Status.NOT_FOUND).entity("ERROR: " + e.getCause() + " " + e.getMessage())
		    .build();
	}
	return response;
    }

}
