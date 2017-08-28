package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.departamento.Departamento;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by jille on 23/05/2017.
 */
@Entity
public class Documento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;
    @NotNull(message = "Escolha qual o tipo de documento")
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;

    private String numeroDocumento;

    @NotNull(message = "Por favor, insira a data do documento")
    private Date dataDocumento;

    private Date dataProtocolo;

    @NotEmpty(message = "Insira a origem do Documento")
    private String origem;

    @NotEmpty(message = "Informe o assunto do Documento")
    private String assunto;
    private Long numProtocolo;
    @NotEmpty(message = "Insira o nome do remetente")
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

        return idDocumento.equals(documento.idDocumento);
    }

    @Override
    public int hashCode() {
        return idDocumento.hashCode();
    }

    public Long getNumProtocolo() {
        return numProtocolo;
    }

    public void setNumProtocolo(Long numProtocolo) {
        this.numProtocolo = numProtocolo;
    }

    public Date getDataProtocolo() {
        return dataProtocolo;
    }

    public void setDataProtocolo(Date dataProtocolo) {
        this.dataProtocolo = dataProtocolo;
    }


}



