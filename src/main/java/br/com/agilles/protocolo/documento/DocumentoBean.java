package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.utils.MensagemUtil;
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

    private TipoDocumento[] tipoDocumentos;
    private MensagemUtil msg = new MensagemUtil();
    /**
     * Método que será chamado quando o usuário receber um novo documento e precisar inserir ele no sistema
     * @return
     */
    public String inserirDocumentoBandeja(){
        if (dao.inserirDocumentoBandeja(documento)) {
            msg.criarMensagemSweet("swal({title: 'Pronto', type: 'success', html: 'O <b>" +documento.getTipoDocumento().getDescricao() + " nº "+ documento.getNumeroDocumento() + "</b> foi colocado em sua bandeja'})");
            this.documento = new Documento();
            return "";
        }
        return "";


    }

    public Documento getDocumento() {
        return documento;
    }

    public DocumentoDao getDao() {
        return dao;
    }

    public void setDao(DocumentoDao dao) {
        this.dao = dao;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public TipoDocumento[] getTipoDocumentos() {
        return TipoDocumento.values();
    }
}
