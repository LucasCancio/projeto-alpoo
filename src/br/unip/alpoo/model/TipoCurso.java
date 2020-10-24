package br.unip.alpoo.model;

public enum TipoCurso {
	BACHAREL("Bacharel"), GESTAO("Gestão"), OUTROS("Outros");
	
	private String descricao;
	public String getDescricao() {
		 return this.descricao;
	}
	
	TipoCurso(String descricao) {
		this.descricao=descricao;
	}
}
