/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "avaliacao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Avaliacao.findAll", query = "SELECT a FROM Avaliacao a")
    , @NamedQuery(name = "Avaliacao.findByNota", query = "SELECT a FROM Avaliacao a WHERE a.nota = :nota")
    , @NamedQuery(name = "Avaliacao.findByComentario", query = "SELECT a FROM Avaliacao a WHERE a.comentario = :comentario")
    , @NamedQuery(name = "Avaliacao.findByIdAvaliacao", query = "SELECT a FROM Avaliacao a WHERE a.idAvaliacao = :idAvaliacao")
    , @NamedQuery(name = "Avaliacao.findByDataCriacao", query = "SELECT a FROM Avaliacao a WHERE a.dataCriacao = :dataCriacao")})
public class Avaliacao implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "nota")
    private BigDecimal nota;
    @Size(max = 255)
    @Column(name = "comentario")
    private String comentario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAvaliacao")
    private Integer idAvaliacao;
    @Column(name = "dataCriacao")
    @Temporal(TemporalType.DATE)
    private Date dataCriacao;
    @JoinTable(name = "avaliacoes_centros", joinColumns = {
        @JoinColumn(name = "idAvaliacao", referencedColumnName = "idAvaliacao")}, inverseJoinColumns = {
        @JoinColumn(name = "idCentro", referencedColumnName = "idCentro")})
    @ManyToMany
    private Collection<CentroEsportivo> centroEsportivoCollection;
    @ManyToMany(mappedBy = "avaliacaoCollection")
    private Collection<Pessoas> pessoasCollection;

    public Avaliacao() {
    }

    public Avaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    @XmlTransient
    public Collection<CentroEsportivo> getCentroEsportivoCollection() {
        return centroEsportivoCollection;
    }

    public void setCentroEsportivoCollection(Collection<CentroEsportivo> centroEsportivoCollection) {
        this.centroEsportivoCollection = centroEsportivoCollection;
    }

    @XmlTransient
    public Collection<Pessoas> getPessoasCollection() {
        return pessoasCollection;
    }

    public void setPessoasCollection(Collection<Pessoas> pessoasCollection) {
        this.pessoasCollection = pessoasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAvaliacao != null ? idAvaliacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avaliacao)) {
            return false;
        }
        Avaliacao other = (Avaliacao) object;
        if ((this.idAvaliacao == null && other.idAvaliacao != null) || (this.idAvaliacao != null && !this.idAvaliacao.equals(other.idAvaliacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.Avaliacao[ idAvaliacao=" + idAvaliacao + " ]";
    }
    
}
