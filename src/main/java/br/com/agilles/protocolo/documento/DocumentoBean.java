package br.com.agilles.protocolo.documento;

import org.primefaces.context.RequestContext;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by jille on 09/06/2017.
 */
@Model
public class DocumentoBean implements Serializable {
    @Inject
    private Documento documento;
    @Inject
    private DocumentoDao dao;


    /**
     * Método que será chamado quando o usuário receber um novo documento e precisar inserir ele no sistema
     * @return
     */
    public String inserirDocumentoBandeja(){
        if (dao.inserirDocumentoBandeja(documento)) {
            return "";
        }
        return "";

    }








}
