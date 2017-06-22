package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.usuario.UsuarioBean;
import br.com.agilles.protocolo.utils.MensagemUtil;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jille on 09/06/2017.
 */
@Model
public class DocumentoBean implements Serializable {
    @Inject
    private Documento documento;
    @Inject
    private DocumentoDao dao;
    @Inject
    UsuarioBean usuarioBean;

    private List<Documento> todosDocumentos = new ArrayList<>();
    private Documento documentoSelecionado = new Documento();


    private TipoDocumento[] tipoDocumentos;
    private MensagemUtil msg = new MensagemUtil();

    /**
     * Método que será chamado quando o usuário receber um novo documento e precisar inserir ele no sistema
     *
     * @return
     */
    public String inserirDocumentoBandeja() {
        documento.setDepartamento(usuarioBean.getUsuario().getDepartamento());
        if (dao.inserirDocumentoBandeja(documento)) {
            msg.criarMensagemSweet("swal({title: 'Pronto', type: 'success', html: 'O <b>" + documento.getTipoDocumento().getDescricao() + " nº " + documento.getNumeroDocumento() + "</b> foi colocado em sua bandeja'})");
            this.documento = new Documento();
            return "";
        }
        return "";


    }

    /**
     * Metodo chamado para carregar novamente a lista de documentos
     * geralmente usado também após a inserção / remoção de departamentos
     */
    private void listarTodosDepartamentos() {
        this.todosDocumentos = new ArrayList<>();
        this.todosDocumentos = dao.listarTodosDocumentos();
    }


    /**
     * Método para carregar a lista contendo todos os departamentos
     */
    @PostConstruct
    public void inicializarListaDepartamentos() {
        if (this.todosDocumentos == null) {
            this.todosDocumentos = new ArrayList<>();
        }
        if (todosDocumentos.isEmpty()) {
            this.todosDocumentos = dao.listarTodosDocumentos();
        }
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

    public List<Documento> getTodosDocumentos() {
        return todosDocumentos;
    }

    public void setTodosDocumentos(List<Documento> todosDocumentos) {
        this.todosDocumentos = todosDocumentos;
    }

    public void setTipoDocumentos(TipoDocumento[] tipoDocumentos) {
        this.tipoDocumentos = tipoDocumentos;
    }

    public MensagemUtil getMsg() {
        return msg;
    }

    public void setMsg(MensagemUtil msg) {
        this.msg = msg;
    }

    public Documento getDocumentoSelecionado() {
        return documentoSelecionado;
    }

    public void setDocumentoSelecionado(Documento documentoSelecionado) {
        this.documentoSelecionado = documentoSelecionado;
    }
}
