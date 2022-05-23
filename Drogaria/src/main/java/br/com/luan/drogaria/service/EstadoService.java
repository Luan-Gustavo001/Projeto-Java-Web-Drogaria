package br.com.luan.drogaria.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;

import br.com.luan.drogaria.dao.EstadoDAO;
import br.com.luan.drogaria.domain.Estado;

@Path("estado")
public class EstadoService {
	// http://192.168.0.114:8080/Drogaria/rest/estado
		@GET
		public String listar() {
			EstadoDAO estadoDAO = new EstadoDAO();
			List<Estado> estados = estadoDAO.listar("nome");

			Gson gson = new Gson();
			String json = gson.toJson(estados);

			return json;
		}
}
