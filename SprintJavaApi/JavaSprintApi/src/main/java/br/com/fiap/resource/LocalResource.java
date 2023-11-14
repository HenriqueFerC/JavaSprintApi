package br.com.fiap.resource;

import java.sql.SQLException;

import br.com.fiap.exception.BadInfoException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Local;
import br.com.fiap.service.LocalService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

@Path("/local")
public class LocalResource {

	private LocalService localService;

	public LocalResource() throws ClassNotFoundException, SQLException {
		localService = new LocalService();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarLocal(Local local, @Context UriInfo uri) throws SQLException, BadInfoException {
		try {
			localService.cadastrar(local);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(local.getId()));
			
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response removerLocal(@PathParam("id") int id) throws SQLException {
		try {
			localService.remover(id);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarLocal(Local local, @PathParam("id") int id) throws SQLException {
		try {
			local.setId(id);
			localService.atualizar(local);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
