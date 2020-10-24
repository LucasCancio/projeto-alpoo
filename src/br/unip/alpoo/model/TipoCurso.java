package br.unip.alpoo.model;

public enum TipoCurso {
	BACHAREL("Bacharel"), GESTAO("Gest�o"), OUTROS("Outros");
	
	private String descricao;
	public String getDescricao() {
		 return this.descricao;
	}
	
	TipoCurso(String descricao) {
		this.descricao=descricao;
	}
}
