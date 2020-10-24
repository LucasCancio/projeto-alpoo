package br.unip.alpoo.model;

public enum NomesCursos {
	ADMIN("Administra��o de Empresas"),
	BIOMEDICINA("BioMedicina"),
	CIENCIAS_BIOLOGICAS("Ci�ncias Biol�gicas"),
	CIENCIAS_DA_COMPUTACAO("Ciencias da Computa��o"),
	DIREITO("Direito"),
	EDUCACAO_FISICA("Educa��o F�sica"),
	FARMACOLOGIA("Farmacologia"),
	REDE_DE_COMPUTADORES("Rede de Computadores"),
	SISTEMAS_DE_INFORMACOES("Sistemas de Informa��es");

	private String descricao;
	public String getDescricao() {
		return this.descricao;
	}
	
	NomesCursos(String descricao) {
		this.descricao = descricao;
	}
}
