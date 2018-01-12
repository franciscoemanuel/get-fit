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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "pessoas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pessoas.findAll", query = "SELECT p FROM Pessoas p")
    , @NamedQuery(name = "Pessoas.findByBio", query = "SELECT p FROM Pessoas p WHERE p.bio = :bio")
    , @NamedQuery(name = "Pessoas.findByNome", query = "SELECT p FROM Pessoas p WHERE p.nome = :nome")
    , @NamedQuery(name = "Pessoas.findBySexo", query = "SELECT p FROM Pessoas p WHERE p.sexo = :sexo")
    , @NamedQuery(name = "Pessoas.findByIdPessoa", query = "SELECT p FROM Pessoas p WHERE p.idPessoa = :idPessoa")})
public class Pessoas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "bio")
    private String bio;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;
    @Column(name = "sexo")
    private Character sexo;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPessoa")
    private Integer idPessoa;
    @JoinTable(name = "pessoas_atividades", joinColumns = {
        @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")}, inverseJoinColumns = {
        @JoinColumn(name = "idAtividade", referencedColumnName = "idAtividade")})
    @ManyToMany
    private Collection<AtividadesFisicas> atividadesFisicasCollection;
    @JoinTable(name = "pessoas_avaliacoes", joinColumns = {
        @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa")}, inverseJoinColumns = {
        @JoinColumn(name = "idAvaliacao", referencedColumnName = "idAvaliacao")})
    @ManyToMany
    private Collection<Avaliacao> avaliacaoCollection;
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    @ManyToOne
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pessoas")
    private Collection<Matriculas> matriculasCollection;

    public Pessoas() {
    }

    public Pessoas(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Integer getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    @XmlTransient
    public Collection<AtividadesFisicas> getAtividadesFisicasCollection() {
        return atividadesFisicasCollection;
    }

    public void setAtividadesFisicasCollection(Collection<AtividadesFisicas> atividadesFisicasCollection) {
        this.atividadesFisicasCollection = atividadesFisicasCollection;
    }

    @XmlTransient
    public Collection<Avaliacao> getAvaliacaoCollection() {
        return avaliacaoCollection;
    }

    public void setAvaliacaoCollection(Collection<Avaliacao> avaliacaoCollection) {
        this.avaliacaoCollection = avaliacaoCollection;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<Matriculas> getMatriculasCollection() {
        return matriculasCollection;
    }

    public void setMatriculasCollection(Collection<Matriculas> matriculasCollection) {
        this.matriculasCollection = matriculasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPessoa != null ? idPessoa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoas)) {
            return false;
        }
        Pessoas other = (Pessoas) object;
        if ((this.idPessoa == null && other.idPessoa != null) || (this.idPessoa != null && !this.idPessoa.equals(other.idPessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.Pessoas[ idPessoa=" + idPessoa + " ]";
    }
    
}
