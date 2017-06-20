package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.departamento.Departamento;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDocumento;

    @ElementCollection(targetClass = TipoDocumento.class)
    @Enumerated(EnumType.STRING)
    private List<TipoDocumento> tipoDocumentos;
    private String numeroDocumento;
    private Date dataDocumento;

    @OneToOne
    private Departamento departamento;

    public Long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Long idDocumento) {
        this.idDocumento = idDocumento;
    }

    public List<TipoDocumento> getTipoDocumentos() {
        return tipoDocumentos;
    }

    public void setTipoDocumentos(List<TipoDocumento> tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
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
}
