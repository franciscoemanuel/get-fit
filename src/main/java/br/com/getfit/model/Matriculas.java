/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.getfit.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Francisco
 */
@Entity
@Table(name = "matriculas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matriculas.findAll", query = "SELECT m FROM Matriculas m")
    , @NamedQuery(name = "Matriculas.findByIdPessoa", query = "SELECT m FROM Matriculas m WHERE m.matriculasPK.idPessoa = :idPessoa")
    , @NamedQuery(name = "Matriculas.findByIdCentro", query = "SELECT m FROM Matriculas m WHERE m.matriculasPK.idCentro = :idCentro")
    , @NamedQuery(name = "Matriculas.findByIdAtividade", query = "SELECT m FROM Matriculas m WHERE m.matriculasPK.idAtividade = :idAtividade")})
public class Matriculas implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MatriculasPK matriculasPK;
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoas pessoas;
    @JoinColumn(name = "idCentro", referencedColumnName = "idCentro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CentroEsportivo centroEsportivo;
    @JoinColumn(name = "idAtividade", referencedColumnName = "idAtividade", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AtividadesFisicas atividadesFisicas;

    public Matriculas() {
    }

    public Matriculas(MatriculasPK matriculasPK) {
        this.matriculasPK = matriculasPK;
    }

    public Matriculas(int idPessoa, int idCentro, int idAtividade) {
        this.matriculasPK = new MatriculasPK(idPessoa, idCentro, idAtividade);
    }

    public MatriculasPK getMatriculasPK() {
        return matriculasPK;
    }

    public void setMatriculasPK(MatriculasPK matriculasPK) {
        this.matriculasPK = matriculasPK;
    }

    public Pessoas getPessoas() {
        return pessoas;
    }

    public void setPessoas(Pessoas pessoas) {
        this.pessoas = pessoas;
    }

    public CentroEsportivo getCentroEsportivo() {
        return centroEsportivo;
    }

    public void setCentroEsportivo(CentroEsportivo centroEsportivo) {
        this.centroEsportivo = centroEsportivo;
    }

    public AtividadesFisicas getAtividadesFisicas() {
        return atividadesFisicas;
    }

    public void setAtividadesFisicas(AtividadesFisicas atividadesFisicas) {
        this.atividadesFisicas = atividadesFisicas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculasPK != null ? matriculasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matriculas)) {
            return false;
        }
        Matriculas other = (Matriculas) object;
        if ((this.matriculasPK == null && other.matriculasPK != null) || (this.matriculasPK != null && !this.matriculasPK.equals(other.matriculasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.Matriculas[ matriculasPK=" + matriculasPK + " ]";
    }
    
}
