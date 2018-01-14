package br.com.getfit.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Francisco
 */
@Embeddable
public class MatriculaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "idPessoa")
    private int idPessoa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdCentro")
    private int idCentro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idOferta")
    private int idOferta;

    public MatriculaPK() {
    }

    public MatriculaPK(int idPessoa, int idCentro, int idOferta) {
        this.idPessoa = idPessoa;
        this.idCentro = idCentro;
        this.idOferta = idOferta;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public int getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(int idCentro) {
        this.idCentro = idCentro;
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPessoa;
        hash += (int) idCentro;
        hash += (int) idOferta;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MatriculaPK)) {
            return false;
        }
        MatriculaPK other = (MatriculaPK) object;
        if (this.idPessoa != other.idPessoa) {
            return false;
        }
        if (this.idCentro != other.idCentro) {
            return false;
        }
        if (this.idOferta != other.idOferta) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.MatriculaPK[ idPessoa=" + idPessoa + ", idCentro=" + idCentro + ", idOferta=" + idOferta + " ]";
    }
    
}