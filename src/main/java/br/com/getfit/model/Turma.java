/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "turma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t")
    , @NamedQuery(name = "Turma.findByInstrutor", query = "SELECT t FROM Turma t WHERE t.instrutor = :instrutor")
    , @NamedQuery(name = "Turma.findByHorario", query = "SELECT t FROM Turma t WHERE t.horario = :horario")
    , @NamedQuery(name = "Turma.findByPreco", query = "SELECT t FROM Turma t WHERE t.preco = :preco")
    , @NamedQuery(name = "Turma.findByIdTurma", query = "SELECT t FROM Turma t WHERE t.idTurma = :idTurma")})
public class Turma implements Serializable {

    @JoinTable(name = "pessoa_turmas", joinColumns = {
        @JoinColumn(name = "idTurma", referencedColumnName = "idTurma")}, inverseJoinColumns = {
        @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", table = "usuario")})
    @ManyToMany
    private Collection<Usuario> usuarioCollection;

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
    @Column(name = "idTurma")
    private Integer idTurma;
    @JoinColumn(name = "IdCentro", referencedColumnName = "IdCentro")
    @ManyToOne
    private CentroEsportivo idCentro;
    @JoinColumn(name = "idAtividade", referencedColumnName = "idAtividade")
    @ManyToOne
    private AtividadeFisica idAtividade;

    public Turma() {
    }

    public Turma(Integer idTurma) {
        this.idTurma = idTurma;
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

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public CentroEsportivo getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(CentroEsportivo idCentro) {
        this.idCentro = idCentro;
    }

    public AtividadeFisica getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(AtividadeFisica idAtividade) {
        this.idAtividade = idAtividade;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurma != null ? idTurma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.idTurma == null && other.idTurma != null) || (this.idTurma != null && !this.idTurma.equals(other.idTurma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.Turma[ idTurma=" + idTurma + " ]";
    }

    @XmlTransient
    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }
    

}
