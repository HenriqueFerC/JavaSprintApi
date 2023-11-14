package br.com.fiap.resource;

import java.sql.SQLException;

import br.com.fiap.exception.BadInfoException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Formulario;
import br.com.fiap.service.FormularioService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("/formulario")
public class FormularioResource {
	
	private FormularioService formService;
	
	public FormularioResource() throws ClassNotFoundException, SQLException {
		formService = new FormularioService();
	}
	
	@GET
	@Path("/guincho/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarGuincho(@PathParam("id") int id) throws SQLException {
		return Response.ok(formService.consultarGuincho(id)).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response consultarFormulario(@PathParam("id") int id) throws SQLException {
		try {
			return Response.ok(formService.consultarFormulario(id)).build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarFormulario(Formulario formulario, @Context UriInfo uri) throws SQLException, BadInfoException {
		try {
			formService.cadastrar(formulario);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(formulario.getId()));
			return Response.created(uriBuilder.build()).build();
			
		} catch (BadInfoException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
			
	}
	
	@DELETE
	@Path("/{id}")
	public Response removerFormulario(@PathParam("id") int id) throws SQLException {
		try {
			formService.remover(id);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarFormulario(Formulario formulario, @PathParam("id") int id) throws SQLException {
		try {
			formulario.setId(id);
			formService.atualizar(formulario);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
