package br.com.fiap.resource;

import java.sql.SQLException;

import br.com.fiap.exception.BadInfoException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Veiculo;
import br.com.fiap.service.VeiculoService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.core.Response.Status;

@Path("/veiculo")
public class VeiculoResource {
	private VeiculoService veiculoService;

	public VeiculoResource() throws ClassNotFoundException, SQLException {
		veiculoService = new VeiculoService();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarVeiculo(Veiculo veiculo, @Context UriInfo uri) throws SQLException, BadInfoException {
		try {
			veiculoService.cadastrar(veiculo);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(veiculo.getId()));
			
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response removerVeiculo(@PathParam("id") int id) throws SQLException {
		try {
			veiculoService.remover(id);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarVeiculo(Veiculo veiculo, @PathParam("id") int id) throws SQLException {
		try {
			veiculo.setId(id);
			veiculoService.atualizar(veiculo);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
