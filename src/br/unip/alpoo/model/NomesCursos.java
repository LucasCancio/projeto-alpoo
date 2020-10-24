package br.unip.alpoo.model;

public enum NomesCursos {
	ADMIN("Administração de Empresas"),
	BIOMEDICINA("BioMedicina"),
	CIENCIAS_BIOLOGICAS("Ciências Biológicas"),
	CIENCIAS_DA_COMPUTACAO("Ciencias da Computação"),
	DIREITO("Direito"),
	EDUCACAO_FISICA("Educação Física"),
	FARMACOLOGIA("Farmacologia"),
	REDE_DE_COMPUTADORES("Rede de Computadores"),
	SISTEMAS_DE_INFORMACOES("Sistemas de Informações");

	private String descricao;
	public String getDescricao() {
		return this.descricao;
	}
	
	NomesCursos(String descricao) {
		this.descricao = descricao;
	}
}
