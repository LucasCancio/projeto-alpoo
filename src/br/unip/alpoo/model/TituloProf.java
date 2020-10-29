package br.unip.alpoo.model;

public enum TituloProf {
    BACHAREL("Bacharel"), 
    Especialista("Especialista Lato Sensu"), 
    MESTRADO("Mestrado"), 
    DOUTORADO("Doutorado");
	
    private String descricao;

    private TituloProf(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
