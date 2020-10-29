package br.unip.alpoo.model;

public enum TipoDisciplina {
    MATEMATICA("MAT235: Matematica Discreta"),
    JAVA("JAV10: Aplicando java"),
    BANCODEDADOS("BD2: Banco de Dados 2"),
    ESTRUTURADADOS("ED1: Estrutura de Dados");
    
    private String descricao;

    private TipoDisciplina(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    

    
}
