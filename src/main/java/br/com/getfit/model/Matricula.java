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
@Table(name = "matricula")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matricula.findAll", query = "SELECT m FROM Matricula m")
    , @NamedQuery(name = "Matricula.findByIdPessoa", query = "SELECT m FROM Matricula m WHERE m.matriculaPK.idPessoa = :idPessoa")
    , @NamedQuery(name = "Matricula.findByIdCentro", query = "SELECT m FROM Matricula m WHERE m.matriculaPK.idCentro = :idCentro")
    , @NamedQuery(name = "Matricula.findByIdOferta", query = "SELECT m FROM Matricula m WHERE m.matriculaPK.idOferta = :idOferta")})
public class Matricula implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MatriculaPK matriculaPK;
    @JoinColumn(name = "idPessoa", referencedColumnName = "idPessoa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    @JoinColumn(name = "IdCentro", referencedColumnName = "IdCentro", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CentroEsportivo centroEsportivo;
    @JoinColumn(name = "idOferta", referencedColumnName = "idOferta", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AtividadeFisicaOfertada atividadeFisicaOfertada;

    public Matricula() {
    }

    public Matricula(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public Matricula(int idPessoa, int idCentro, int idOferta) {
        this.matriculaPK = new MatriculaPK(idPessoa, idCentro, idOferta);
    }

    public MatriculaPK getMatriculaPK() {
        return matriculaPK;
    }

    public void setMatriculaPK(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public CentroEsportivo getCentroEsportivo() {
        return centroEsportivo;
    }

    public void setCentroEsportivo(CentroEsportivo centroEsportivo) {
        this.centroEsportivo = centroEsportivo;
    }

    public AtividadeFisicaOfertada getAtividadeFisicaOfertada() {
        return atividadeFisicaOfertada;
    }

    public void setAtividadeFisicaOfertada(AtividadeFisicaOfertada atividadeFisicaOfertada) {
        this.atividadeFisicaOfertada = atividadeFisicaOfertada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (matriculaPK != null ? matriculaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matricula)) {
            return false;
        }
        Matricula other = (Matricula) object;
        if ((this.matriculaPK == null && other.matriculaPK != null) || (this.matriculaPK != null && !this.matriculaPK.equals(other.matriculaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.Matricula[ matriculaPK=" + matriculaPK + " ]";
    }
    
}
