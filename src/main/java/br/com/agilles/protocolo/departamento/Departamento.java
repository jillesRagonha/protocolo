package br.com.agilles.protocolo.departamento;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by jille on 23/05/2017.
 */
@Entity
public class Departamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Preencha o nome do departamento a ser criado")
    private String nomeDepartamento;
    @NotEmpty(message = "Preencher a Pessoa Responsável Pelo Departamento")
    private String pessoaResponsavel;
    @NotEmpty(message = "Preencha o email do departamento ou do responsável")
    @Email(message = "Preencha corretamente o campo Email")
    private String email;


    public int getId() {
        return id;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setId(int idDepartamento) {
        this.id = idDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public String getEmail() {
        return email;
    }

    public String getPessoaResponsavel() {
        return pessoaResponsavel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPessoaResponsavel(String pessoaResponsavel) {
        this.pessoaResponsavel = pessoaResponsavel;
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nomeDepartamento='" + nomeDepartamento + '\'' +
                ", pessoaResponsavel='" + pessoaResponsavel + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departamento that = (Departamento) o;

        if (id != that.id) return false;
        if (nomeDepartamento != null ? !nomeDepartamento.equals(that.nomeDepartamento) : that.nomeDepartamento != null)
            return false;
        if (pessoaResponsavel != null ? !pessoaResponsavel.equals(that.pessoaResponsavel) : that.pessoaResponsavel != null)
            return false;
        return email != null ? email.equals(that.email) : that.email == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nomeDepartamento != null ? nomeDepartamento.hashCode() : 0);
        result = 31 * result + (pessoaResponsavel != null ? pessoaResponsavel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
