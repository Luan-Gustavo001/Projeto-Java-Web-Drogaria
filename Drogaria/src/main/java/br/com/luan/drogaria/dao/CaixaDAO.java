package br.com.luan.drogaria.dao;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.luan.drogaria.domain.Caixa;
import br.com.luan.drogaria.util.HibernateUtil;

public class CaixaDAO extends GenericDAO<Caixa>{
		public Caixa buscar(Date dataAbertura){
			Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(Caixa.class);
				consulta.add(Restrictions.eq("dataAbertura", dataAbertura));
				Caixa resultado = (Caixa) consulta.uniqueResult();
				return resultado;
			} catch (RuntimeException erro) {
				throw erro;
			}finally {
				sessao.close();
			}
		}

}
