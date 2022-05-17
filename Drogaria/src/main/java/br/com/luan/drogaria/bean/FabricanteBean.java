package br.com.luan.drogaria.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import org.omnifaces.util.Messages;

import com.google.gson.Gson;

//import br.com.luan.drogaria.dao.FabricanteDAO;
import br.com.luan.drogaria.domain.Fabricante;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FabricanteBean implements Serializable {
	private Fabricante fabricante;
	private List<Fabricante> fabricantes;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fabricante> getFabricantes() {
		return fabricantes;
	}

	public void setFabricantes(List<Fabricante> fabricantes) {
		this.fabricantes = fabricantes;
	}

	@PostConstruct
	public void listar() {
		try {

//		FabricanteDAO fabricanteDAO = new FabricanteDAO();
//		fabricantes = fabricanteDAO.listar();

			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://192.168.0.114:8080/Drogaria/rest/fabricante");
			String json = caminho.request().get(String.class);

			Gson gson = new Gson();
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);

			fabricantes = Arrays.asList(vetor);
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os fabricantes");
			erro.printStackTrace();
		}
	}

	public void novo() {
		fabricante = new Fabricante();

	}

	public void salvar() {
		try {
//			FabricanteDAO fabricanteDAO = new FabricanteDAO();
//			fabricanteDAO.merge(fabricante);
//			
//			fabricante  = new Fabricante();
//			fabricantes = fabricanteDAO.listar();
			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://192.168.0.114:8080/Drogaria/rest/fabricante");

			Gson gson = new Gson();

			String json = gson.toJson(fabricante);
			caminho.request().post(Entity.json(json));

			fabricante = new Fabricante();

			json = caminho.request().get(String.class);
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			fabricantes = Arrays.asList(vetor);

			Messages.addGlobalInfo("Fabricante salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o fabricante");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");

			Client cliente = ClientBuilder.newClient();
			WebTarget caminho = cliente.target("http://192.168.0.114:8080/Drogaria/rest/fabricante");
			WebTarget caminhoExcluir = caminho.path("{codigo}").resolveTemplate("codigo", fabricante.getCodigo());
			
			caminhoExcluir.request().delete();
			String json = caminho.request().get(String.class);
			
			Gson gson = new Gson();
			Fabricante[] vetor = gson.fromJson(json, Fabricante[].class);
			
			fabricantes = Arrays.asList(vetor);

			Messages.addGlobalInfo("Fabricante excluido com sucesso");
			Messages.addGlobalInfo("Fabricante: " + fabricante.getDescricao());
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar excluir um fabricante");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {

		fabricante = (Fabricante) evento.getComponent().getAttributes().get("fabricanteSelecionado");
		// Messages.addGlobalInfo("Estado: " + estado.getNome() + " " + "Sigla: " +
		// estado.getSigla());
	}
}
