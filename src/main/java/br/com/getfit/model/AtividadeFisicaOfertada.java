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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Francisco
 */
@Entity(name = "AtividadeFisicaOfertada")
@Table(name = "atividade_fisica_ofertada")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtividadeFisicaOfertada.findAll", query = "SELECT a FROM AtividadeFisicaOfertada a")
    , @NamedQuery(name = "AtividadeFisicaOfertada.findByInstrutor", query = "SELECT a FROM AtividadeFisicaOfertada a WHERE a.instrutor = :instrutor")
    , @NamedQuery(name = "AtividadeFisicaOfertada.findByHorario", query = "SELECT a FROM AtividadeFisicaOfertada a WHERE a.horario = :horario")
    , @NamedQuery(name = "AtividadeFisicaOfertada.findByPreco", query = "SELECT a FROM AtividadeFisicaOfertada a WHERE a.preco = :preco")
    , @NamedQuery(name = "AtividadeFisicaOfertada.findByIdOferta", query = "SELECT a FROM AtividadeFisicaOfertada a WHERE a.idOferta = :idOferta")})
public class AtividadeFisicaOfertada implements Serializable {
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
    private AtividadeFisica idAtividade;
    @JoinColumn(name = "IdCentro", referencedColumnName = "IdCentro")
    @ManyToOne
    private CentroEsportivo idCentro;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "atividadeFisicaOfertada")
    private Collection<Matricula> matriculaCollection;

    public AtividadeFisicaOfertada() {
    }

    public AtividadeFisicaOfertada(Integer idOferta) {
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

    public AtividadeFisica getIdAtividade() {
        return idAtividade;
    }

    public void setIdAtividade(AtividadeFisica idAtividade) {
        this.idAtividade = idAtividade;
    }

    public CentroEsportivo getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(CentroEsportivo idCentro) {
        this.idCentro = idCentro;
    }

    @XmlTransient
    public Collection<Matricula> getMatriculaCollection() {
        return matriculaCollection;
    }

    public void setMatriculaCollection(Collection<Matricula> matriculaCollection) {
        this.matriculaCollection = matriculaCollection;
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
        if (!(object instanceof AtividadeFisicaOfertada)) {
            return false;
        }
        AtividadeFisicaOfertada other = (AtividadeFisicaOfertada) object;
        if ((this.idOferta == null && other.idOferta != null) || (this.idOferta != null && !this.idOferta.equals(other.idOferta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.AtividadeFisicaOfertada[ idOferta=" + idOferta + " ]";
    }
    
}
