package br.com.getfit.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "atividade_fisica")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtividadeFisica.findAll", query = "SELECT a FROM AtividadeFisica a")
    , @NamedQuery(name = "AtividadeFisica.findByIdAtividade", query = "SELECT a FROM AtividadeFisica a WHERE a.idAtividade = :idAtividade")
    , @NamedQuery(name = "AtividadeFisica.findByNome", query = "SELECT a FROM AtividadeFisica a WHERE a.nome = :nome")})
public class AtividadeFisica implements Serializable {

    @OneToMany(mappedBy = "idAtividade")
    private Collection<AtividadeFisicaOfertada> atividadeFisicaOfertadaCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAtividade")
    private Integer idAtividade;
    @Size(max = 255)
    @Column(name = "nome")
    private String nome;

    public AtividadeFisica() {
    }

    public AtividadeFisica(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public Integer getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(Integer idAtividade) {
        this.idAtividade = idAtividade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        if (!(object instanceof AtividadeFisica)) {
            return false;
        }
        AtividadeFisica other = (AtividadeFisica) object;
        if ((this.idAtividade == null && other.idAtividade != null) || (this.idAtividade != null && !this.idAtividade.equals(other.idAtividade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.AtividadeFisica[ idAtividade=" + idAtividade + " ]";
    }

    @XmlTransient
    public Collection<AtividadeFisicaOfertada> getAtividadeFisicaOfertadaCollection() {
        return atividadeFisicaOfertadaCollection;
    }

    public void setAtividadeFisicaOfertadaCollection(Collection<AtividadeFisicaOfertada> atividadeFisicaOfertadaCollection) {
        this.atividadeFisicaOfertadaCollection = atividadeFisicaOfertadaCollection;
    }
    
}
