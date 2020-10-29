package br.unip.alpoo.model;

public enum EspecialidadeProf {
    DIGITO("Digito"), 
    INFORMATICA("Informatica"), 
    MATEMATICA("Matematica"), 
    MEDICINA("Medicina"),
    OUTROS("Outros");
	
    private String descricao;

    private EspecialidadeProf(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
