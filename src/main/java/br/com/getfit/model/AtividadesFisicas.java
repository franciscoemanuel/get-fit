/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "atividades_fisicas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtividadesFisicas.findAll", query = "SELECT a FROM AtividadesFisicas a")
    , @NamedQuery(name = "AtividadesFisicas.findByNome", query = "SELECT a FROM AtividadesFisicas a WHERE a.nome = :nome")
    , @NamedQuery(name = "AtividadesFisicas.findByIdAtividade", query = "SELECT a FROM AtividadesFisicas a WHERE a.idAtividade = :idAtividade")})
public class AtividadesFisicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAtividade")
    private Integer idAtividade;
    @ManyToMany(mappedBy = "atividadesFisicasCollection")
    private Collection<Pessoas> pessoasCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividadesFisicas")
    private Collection<Matriculas> matriculasCollection;
    @OneToMany(mappedBy = "idAtividade")
    private Collection<AtividadesFisicasOfertadas> atividadesFisicasOfertadasCollection;

    public AtividadesFisicas() {
    }

    public AtividadesFisicas(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    @XmlTransient
    public Collection<Pessoas> getPessoasCollection() {
        return pessoasCollection;
    }

    public void setPessoasCollection(Collection<Pessoas> pessoasCollection) {
        this.pessoasCollection = pessoasCollection;
    }

    @XmlTransient
    public Collection<Matriculas> getMatriculasCollection() {
        return matriculasCollection;
    }

    public void setMatriculasCollection(Collection<Matriculas> matriculasCollection) {
        this.matriculasCollection = matriculasCollection;
    }

    @XmlTransient
    public Collection<AtividadesFisicasOfertadas> getAtividadesFisicasOfertadasCollection() {
        return atividadesFisicasOfertadasCollection;
    }

    public void setAtividadesFisicasOfertadasCollection(Collection<AtividadesFisicasOfertadas> atividadesFisicasOfertadasCollection) {
        this.atividadesFisicasOfertadasCollection = atividadesFisicasOfertadasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtividade != null ? idAtividade.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtividadesFisicas)) {
            return false;
        }
        AtividadesFisicas other = (AtividadesFisicas) object;
        if ((this.idAtividade == null && other.idAtividade != null) || (this.idAtividade != null && !this.idAtividade.equals(other.idAtividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.AtividadesFisicas[ idAtividade=" + idAtividade + " ]";
    }
    
}
