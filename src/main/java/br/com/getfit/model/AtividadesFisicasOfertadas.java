/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "atividades_fisicas_ofertadas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtividadesFisicasOfertadas.findAll", query = "SELECT a FROM AtividadesFisicasOfertadas a")
    , @NamedQuery(name = "AtividadesFisicasOfertadas.findByInstrutor", query = "SELECT a FROM AtividadesFisicasOfertadas a WHERE a.instrutor = :instrutor")
    , @NamedQuery(name = "AtividadesFisicasOfertadas.findByHorario", query = "SELECT a FROM AtividadesFisicasOfertadas a WHERE a.horario = :horario")
    , @NamedQuery(name = "AtividadesFisicasOfertadas.findByPreco", query = "SELECT a FROM AtividadesFisicasOfertadas a WHERE a.preco = :preco")
    , @NamedQuery(name = "AtividadesFisicasOfertadas.findByIdOferta", query = "SELECT a FROM AtividadesFisicasOfertadas a WHERE a.idOferta = :idOferta")})
public class AtividadesFisicasOfertadas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "instrutor")
    private String instrutor;
    @Size(max = 255)
    @Column(name = "horario")
    private String horario;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco")
    private BigDecimal preco;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOferta")
    private Integer idOferta;
    @JoinColumn(name = "idAtividade", referencedColumnName = "idAtividade")
    @ManyToOne
    private AtividadesFisicas idAtividade;
    @JoinColumn(name = "idCentro", referencedColumnName = "idCentro")
    @ManyToOne
    private CentroEsportivo idCentro;

    public AtividadesFisicasOfertadas() {
    }

    public AtividadesFisicasOfertadas(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public String getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(String instrutor) {
        this.instrutor = instrutor;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }

    public AtividadesFisicas getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(AtividadesFisicas idAtividade) {
        this.idAtividade = idAtividade;
    }

    public CentroEsportivo getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(CentroEsportivo idCentro) {
        this.idCentro = idCentro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOferta != null ? idOferta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtividadesFisicasOfertadas)) {
            return false;
        }
        AtividadesFisicasOfertadas other = (AtividadesFisicasOfertadas) object;
        if ((this.idOferta == null && other.idOferta != null) || (this.idOferta != null && !this.idOferta.equals(other.idOferta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.AtividadesFisicasOfertadas[ idOferta=" + idOferta + " ]";
    }
    
}
