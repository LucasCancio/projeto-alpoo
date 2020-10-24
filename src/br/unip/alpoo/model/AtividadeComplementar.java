package br.unip.alpoo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_ATIVIDADES")
@NamedQueries({
    @NamedQuery(name = "AtividadeComplementar.getPorTipo",
                // :nomeDoParametro veja abaixo o exemplo tipo
            query = "Select a from AtividadeComplementar a where a.tipo = :tipo")
})
public class AtividadeComplementar implements Serializable{
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")    
    private Integer id;
    @Column(name = "TITULO", nullable = false)
    private String titulo;
    @Column(name = "TIPO", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoAtividade tipo;
    @Column(name = "DATA_REAL", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataRealizacao;
    @Column(name = "QTDE_HORAS", nullable = false)    
    private int qtdeHoras;   
    @Column(name = "SITUACAO", nullable = false)
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public AtividadeComplementar() {
    }

    public AtividadeComplementar(String titulo, TipoAtividade tipo, Date dataRealizacao, int qtdeHoras, Situacao situacao) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.dataRealizacao = dataRealizacao;
        this.qtdeHoras = qtdeHoras;
        this.situacao = situacao;
    }

    
    public AtividadeComplementar(Integer id, String titulo, TipoAtividade tipo, Date dataRealizacao, int qtdeHoras, Situacao situacao) {
        this.id = id;
        this.titulo = titulo;
        this.tipo = tipo;
        this.dataRealizacao = dataRealizacao;
        this.qtdeHoras = qtdeHoras;
        this.situacao = situacao;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoAtividade getTipo() {
        return tipo;
    }

    public void setTipo(TipoAtividade tipo) {
        this.tipo = tipo;
    }

    public Date getDataRealizacao() {
        return dataRealizacao;
    }

    public void setDataRealizacao(Date dataRealizacao) {
        this.dataRealizacao = dataRealizacao;
    }

    public int getQtdeHoras() {
        return qtdeHoras;
    }

    public void setQtdeHoras(int qtdeHoras) {
        this.qtdeHoras = qtdeHoras;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.titulo);
        hash = 97 * hash + Objects.hashCode(this.tipo);
        hash = 97 * hash + Objects.hashCode(this.dataRealizacao);
        hash = 97 * hash + this.qtdeHoras;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AtividadeComplementar other = (AtividadeComplementar) obj;
        if (this.qtdeHoras != other.qtdeHoras) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.dataRealizacao, other.dataRealizacao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AtividadeComplementar{" + "id=" + id + ", titulo=" + titulo + ", tipo=" + tipo + ", dataRealizacao=" + dataRealizacao + ", qtdeHoras=" + qtdeHoras + ", situacao=" + situacao + '}';
    }
}
