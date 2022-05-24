package br.com.luan.drogaria.enumeracao;

public enum TipoUsuario {
		ADMINISTRADOR,
		GERENTE,
		RECEPCAO;
	
	@Override
	public String toString() {
		switch (this) {
		case ADMINISTRADOR:
			return "Administrador";
		case GERENTE:
			return "Gerente";
		case RECEPCAO:
			return "Recepção";
		default:
			return null;
		}
	}
}
