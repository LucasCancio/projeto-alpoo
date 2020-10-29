package br.unip.alpoo.model;

public enum ListaCursos {
    MATEMATICA("Matematica Discreta"),
    JAVA("Aplicando java"),
    BANCODEDADOS("Banco de Dados 2"),
    ESTRUTURADADOS("Estrutura de Dados");
    
    private String descricao;

    private ListaCursos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    

    
}
