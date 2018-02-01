package br.com.getfit.model;

import br.com.getfit.validation.PersistedOnlyValidations;
import br.com.getfit.validation.Unique;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Francisco
 */
@Unique(columnName = "email", groups = PersistedOnlyValidations.class, message = "Esse e-mail já foi cadastrado!")
@Entity
@Table(name = "usuario")
@XmlRootElement
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findBySenha", query = "SELECT u FROM Usuario u WHERE u.senha = :senha")
    , @NamedQuery(name = "Usuario.findByEmail", query = "SELECT u FROM Usuario u WHERE u.email = :email")
    , @NamedQuery(name = "Usuario.findByTipoUsuario", query = "SELECT u FROM Usuario u WHERE u.tipoUsuario = :tipoUsuario")
    , @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.idUsuario = :idUsuario")})
public abstract class Usuario implements Serializable {

    @ManyToMany(mappedBy = "usuarioCollection")
    private Collection<Turma> turmaCollection;

    @NotNull
    @Size(min = 3, max = 255, message = "O nome deve ter entre 3 a 255 caracteres")
    @Column(name = "nome")
    private String nome;

    private static final long serialVersionUID = 1L;
    @NotNull
    @Size(max = 255)
    @Column(name = "senha")
    private String senha;
    @NotNull
    @Email
    @Size(min = 6, max = 255, message = "O e-mail deve ter entre 6 a 255 caracteres")
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "tipoUsuario")
    private String tipoUsuario;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idUsuario")
    private Integer idUsuario;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<Pessoa> pessoaCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CentroEsportivo> centroEsportivoCollection;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @XmlTransient
    public Collection<Pessoa> getPessoaCollection() {
        return pessoaCollection;
    }

    public void setPessoaCollection(Collection<Pessoa> pessoaCollection) {
        this.pessoaCollection = pessoaCollection;
    }

    @XmlTransient
    public Collection<CentroEsportivo> getCentroEsportivoCollection() {
        return centroEsportivoCollection;
    }

    public void setCentroEsportivoCollection(Collection<CentroEsportivo> centroEsportivoCollection) {
        this.centroEsportivoCollection = centroEsportivoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.getfit.model.Usuario[ idUsuario=" + idUsuario + " ]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public Collection<Turma> getTurmasCentroCollection() {
        return turmaCollection;
    }

    public void setTurmasCentroCollection(Collection<Turma> turmaCollection) {
        this.turmaCollection = turmaCollection;
    }
    
}
