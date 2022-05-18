package br.com.luan.drogaria.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.luan.drogaria.bean.AutenticacaoBean;

@SuppressWarnings("serial")
public class AutenticacaoListener implements PhaseListener{

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();
		System.out.println("Página Atual: " + paginaAtual);
		
		boolean ehPaginaDeAutenticacao = paginaAtual.contains("autenticacao.xhtml");		
		if (!ehPaginaDeAutenticacao) {
		AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");
		System.out.println("AutenticacaoBean: " + autenticacaoBean);
		
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		
	}

	@Override
	public PhaseId getPhaseId() {
		
		return PhaseId.RESTORE_VIEW;
	}
	

}