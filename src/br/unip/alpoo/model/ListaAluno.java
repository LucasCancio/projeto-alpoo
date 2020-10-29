package br.unip.alpoo.model;

public enum ListaAluno {
    ALUNO1("Rafael Salgado"),
    ALUNO2("Lucas Camargo"),
    ALUNO3("Caio Rodrigues"),
    ALUNO4("Wesley Barbosa");
    
    private String descricao;

    private ListaAluno(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

 
}