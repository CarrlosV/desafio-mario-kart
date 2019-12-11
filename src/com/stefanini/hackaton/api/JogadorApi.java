package com.stefanini.hackaton.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stefanini.hackaton.dto.JogadorDto;
import com.stefanini.hackaton.dto.PersonagemDto;
import com.stefanini.hackaton.service.JogadorService;

@Path("/jogador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JogadorApi {
	
	@Inject
	private JogadorService jogadorService;

	@GET
	public Response listar() {
		return Response.ok(jogadorService.listar()).build();
	}

	@GET
	@Path("/{id}")
	public Response obter(@PathParam("id") Integer id) {
		return Response.ok(jogadorService.obter(id)).build();
	}

	@POST
	public Response inserir(JogadorDto dto) {
		return Response.ok(jogadorService.inserir(dto)).build();
	}

//	Codigos comentados pois não foi solicitado a criação  do put e do delete
//	@PUT
//	@Path("/{id}")
//	public Response alterar(JogadorDto dto, @PathParam("id") Integer id) {
//		return Response.ok(jogadorService.alterar(dto)).build();
//	}
//
//	@DELETE
//	@Path("/{id}")
//	public Response excluir(@PathParam("id") Integer id) {
//		jogadorService.excluir(id);
//		return Response.ok().build();
//	}

}
