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
    @GeneratedValue(strategy = GenerationType.AUTO)
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
}
