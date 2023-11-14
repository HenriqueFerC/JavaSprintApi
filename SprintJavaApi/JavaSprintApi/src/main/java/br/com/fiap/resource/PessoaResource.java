package br.com.fiap.resource;

import java.sql.SQLException;

import br.com.fiap.exception.BadInfoException;
import br.com.fiap.exception.IdNotFoundException;
import br.com.fiap.model.Pessoa;
import br.com.fiap.service.PessoaService;
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

@Path("/pessoa")
public class PessoaResource {
	private PessoaService pessoaService;

	public PessoaResource() throws ClassNotFoundException, SQLException {
		pessoaService = new PessoaService();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrarPessoa(Pessoa pessoa, @Context UriInfo uri) throws SQLException, BadInfoException {
		try {
			pessoaService.cadastrar(pessoa);
			UriBuilder uriBuilder = uri.getAbsolutePathBuilder();
			uriBuilder.path(String.valueOf(pessoa.getId()));
			
			return Response.created(uriBuilder.build()).build();
		} catch (BadInfoException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response removerPessoa(@PathParam("id") int id) throws SQLException {
		try {
			pessoaService.remover(id);
			return Response.noContent().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarPessoa(Pessoa pessoa, @PathParam("id") int id) throws SQLException {
		try {
			pessoa.setId(id);
			pessoaService.atualizar(pessoa);
			return Response.ok().build();
		} catch (IdNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		} catch (BadInfoException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
