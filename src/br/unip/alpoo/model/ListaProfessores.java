
package br.unip.alpoo.model;

public enum ListaProfessores {
    PROF1("Marcos Silva"),
    PROF2("Roberto Bianco"),
    PROF3("Marcelo Castro"),
    PROF4("Olavo Ito");
    
    private String descricao;

    private ListaProfessores(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
