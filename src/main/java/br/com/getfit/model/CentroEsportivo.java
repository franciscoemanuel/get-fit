package br.com.getfit.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "centro_esportivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CentroEsportivo.findAll", query = "SELECT c FROM CentroEsportivo c")
    , @NamedQuery(name = "CentroEsportivo.findByLocalizacao", query = "SELECT c FROM CentroEsportivo c WHERE c.localizacao = :localizacao")
    , @NamedQuery(name = "CentroEsportivo.findByDescricao", query = "SELECT c FROM CentroEsportivo c WHERE c.descricao = :descricao")
    , @NamedQuery(name = "CentroEsportivo.findByIdCentro", query = "SELECT c FROM CentroEsportivo c WHERE c.idCentro = :idCentro")})
public class CentroEsportivo extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 255)
    @Column(name = "localizacao")
    private String localizacao;
    @Size(max = 255)
    @Column(name = "descricao")
    private String descricao;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdCentro")
    private Integer idCentro;

    public CentroEsportivo() {
    }

    public CentroEsportivo(Integer idCentro) {
        this.idCentro = idCentro;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(Integer idCentro) {
        this.idCentro = idCentro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCentro != null ? idCentro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CentroEsportivo)) {
            return false;
        }
        CentroEsportivo other = (CentroEsportivo) object;
        if ((this.idCentro == null && other.idCentro != null) || (this.idCentro != null && !this.idCentro.equals(other.idCentro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.CentroEsportivo[ idCentro=" + idCentro + " ]";
    }
    
}
