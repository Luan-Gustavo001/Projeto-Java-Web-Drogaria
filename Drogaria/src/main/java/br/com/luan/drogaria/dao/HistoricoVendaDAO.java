package br.com.luan.drogaria.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import br.com.luan.drogaria.domain.HistoricoVenda;
import br.com.luan.drogaria.util.HibernateUtil;

public class HistoricoVendaDAO extends GenericDAO<HistoricoVenda> {
	//-------------- LISTAR ORDENADO ----------------------
		@SuppressWarnings("unchecked")
		public List<HistoricoVenda> listarOrdenado(String compoOrdenação) {
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(HistoricoVenda.class);
				consulta.createAlias("pessoa", "p");
				// montando o alias do jeito que quiser
				//consulta.createAlias("p.cidade", "c");
				consulta.addOrder(Order.asc("p.nome"));
				List<HistoricoVenda> resultado = consulta.list();
				return resultado;
			} catch(RuntimeException erro) {
				throw erro;
			} finally {
				sessao.close();
			}
					
		}
	}

