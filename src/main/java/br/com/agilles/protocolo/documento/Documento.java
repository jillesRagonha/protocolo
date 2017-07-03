package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.departamento.Departamento;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jille on 23/05/2017.
 */
@Entity
public class Documento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    @NotEmpty(message = "Número de documento obrigatório")
    private String numeroDocumento;
    private Date dataDocumento;
    private String origem;
    private String assunto;
    private String remetente;
    @OneToOne
    private Departamento departamento;
    @Enumerated(EnumType.STRING)
    private StatusDocumento statusDocumento;


    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getDataDocumento() {
        return dataDocumento;
    }

    public void setDataDocumento(Date dataDocumento) {
        this.dataDocumento = dataDocumento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public StatusDocumento getStatusDocumento() {
        return statusDocumento;
    }

    public void setStatusDocumento(StatusDocumento statusDocumento) {
        this.statusDocumento = statusDocumento;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "idDocumento=" + idDocumento +
                ", tipoDocumento=" + tipoDocumento +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", dataDocumento=" + dataDocumento +
                ", origem='" + origem + '\'' +
                ", assunto='" + assunto + '\'' +
                ", remetente='" + remetente + '\'' +
                ", departamento=" + departamento +
                ", statusDocumento=" + statusDocumento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Documento documento = (Documento) o;

        if (idDocumento != null ? !idDocumento.equals(documento.idDocumento) : documento.idDocumento != null)
            return false;
        if (tipoDocumento != documento.tipoDocumento) return false;
        if (numeroDocumento != null ? !numeroDocumento.equals(documento.numeroDocumento) : documento.numeroDocumento != null)
            return false;
        if (dataDocumento != null ? !dataDocumento.equals(documento.dataDocumento) : documento.dataDocumento != null)
            return false;
        if (origem != null ? !origem.equals(documento.origem) : documento.origem != null) return false;
        if (assunto != null ? !assunto.equals(documento.assunto) : documento.assunto != null) return false;
        if (remetente != null ? !remetente.equals(documento.remetente) : documento.remetente != null) return false;
        if (departamento != null ? !departamento.equals(documento.departamento) : documento.departamento != null)
            return false;
        return statusDocumento == documento.statusDocumento;
    }

    @Override
    public int hashCode() {
        int result = idDocumento != null ? idDocumento.hashCode() : 0;
        result = 31 * result + (tipoDocumento != null ? tipoDocumento.hashCode() : 0);
        result = 31 * result + (numeroDocumento != null ? numeroDocumento.hashCode() : 0);
        result = 31 * result + (dataDocumento != null ? dataDocumento.hashCode() : 0);
        result = 31 * result + (origem != null ? origem.hashCode() : 0);
        result = 31 * result + (assunto != null ? assunto.hashCode() : 0);
        result = 31 * result + (remetente != null ? remetente.hashCode() : 0);
        result = 31 * result + (departamento != null ? departamento.hashCode() : 0);
        result = 31 * result + (statusDocumento != null ? statusDocumento.hashCode() : 0);
        return result;
    }
}



