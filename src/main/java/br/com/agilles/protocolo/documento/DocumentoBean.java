package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.departamento.Departamento;
import br.com.agilles.protocolo.departamento.DepartamentoDao;
import br.com.agilles.protocolo.usuario.UsuarioBean;
import br.com.agilles.protocolo.utils.MensagemUtil;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

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
    @Inject
    private DepartamentoDao deptoDao;

    private List<Departamento> departamentos = new ArrayList<>();


    private int qtdeOficio = 0;
    private int qtdeRelatorio = 0;
    private int qtdeProcesso = 0;
    private int qtdePendencias = 0;

    private List<Documento> todosDocumentos = new ArrayList<>();
    private List<Documento> documentosPendentes = new ArrayList<>();
    private Documento documentoPendenteSelecionado = new Documento();

    private Documento documentoSelecionado = new Documento();

    private List<Documento> oficiosDashBoard = new ArrayList<>();  // coleçao para exibir os ultimos 5 oficios na tela inicial
    private List<Documento> processosDashBoard = new ArrayList<>(); // coleçao para exibir os ultimos 5 processos na tela inicial
    private List<Documento> relatoriosDashBoard = new ArrayList<>(); // coleçao para exibir os ultimos 5 relatorios na tela inicial


    private TipoDocumento[] tipoDocumentos;
    private MensagemUtil msg = new MensagemUtil();
    private Departamento departamentoParaDespacho;


    /**
     * Método que será chamado quando o usuário receber um novo documento e precisar inserir ele no sistema
     *
     * @return
     */
    public String inserirDocumentoBandeja() {
        documento.setDepartamento(usuarioBean.getUsuario().getDepartamento());
        documento.setStatusDocumento(StatusDocumento.RECEBIDO);
        documento.setDataProtocolo(new Date());
        Long protocolo = null;
        protocolo = dao.pegarUltimoProtocolo(documento);
        if (protocolo > 0)
            documento.setNumProtocolo(protocolo++);
            if (dao.inserirDocumentoBandeja(documento)) {

                msg.criarMensagemSweet("swal({title: 'Pronto', type: 'success', html: 'Documento Protocolado conforme número que segue: <b>PROTOCOLO: " + documento.getNumProtocolo()  + "</b> '})");
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
        this.todosDocumentos = dao.listarTodosDocumentosParaDepartamentoLogado();
    }


    /**
     * Método para carregar a lista contendo todos os departamentos
     * Carrega quantidade de cada documento para exibir na dashboard
     */
    @PostConstruct
    public void inicializarListaDocumentos() {
        if (this.todosDocumentos == null) {
            this.todosDocumentos = new ArrayList<>();
        }
        if (departamentos == null) {
            this.departamentos = new ArrayList<>();
        }
        if (todosDocumentos.isEmpty()) {
            this.todosDocumentos = dao.listarTodosDocumentosParaDepartamentoLogado();
            for (Documento d : todosDocumentos) {
                if (d.getTipoDocumento().equals(TipoDocumento.PROCESSO)) {
                    if (processosDashBoard.size() < 4) {
                        processosDashBoard.add(d);
                    }
                    qtdeProcesso++;
                }
                if (d.getTipoDocumento().equals(TipoDocumento.OFICIO)) {
                    if (oficiosDashBoard.size() < 4) {
                        oficiosDashBoard.add(d);
                    }

                    qtdeOficio++;
                }
                if (d.getTipoDocumento().equals(TipoDocumento.RC)) {
                    if (relatoriosDashBoard.size() < 4) {
                        relatoriosDashBoard.add(d);
                    }
                    qtdeRelatorio++;
                }
            }


        }
        if (departamentos.isEmpty()) {
            this.departamentos = deptoDao.listarTodosDepartamentos();
        }

        if (this.documentosPendentes == null) {
            documentosPendentes = new ArrayList<>();
        }
        if (documentosPendentes.isEmpty()) {
            documentosPendentes = dao.listarTodosDocumentosPendentes();
            qtdePendencias = documentosPendentes.size();
        }

//
//     qtdePendencias = dao.listarTodosDocumentosPendentes().size();
    }

    /**
     * Método a ser chamado quando o usuário for despachar um documento para outro departamento
     *
     * @return
     */
    public String despacharDocumento() {
        if (dao.despacharDocumento(documentoSelecionado, departamentoParaDespacho)) {
            todosDocumentos = new ArrayList<>();
            todosDocumentos = dao.listarTodosDocumentosParaDepartamentoLogado();
            criarMensagemDespacho("swal({title: 'Pronto', type: 'success', html: 'O documento foi despachado'})");
        }
        this.documentoSelecionado = new Documento();
        this.documento = new Documento();
        return "";
    }

    public String aceitarPendencia() {
        if (dao.aceitarPendencia(documentoPendenteSelecionado)) {
            criarMensagemDespacho("swal({title: 'Pronto', type: 'success', html: 'Pendência pronta'})");
            this.documentoPendenteSelecionado = new Documento();
            this.documentosPendentes = new ArrayList<>();
            this.documentosPendentes = dao.listarTodosDocumentosPendentes();
            this.qtdePendencias = documentosPendentes.size();
        }
        return "";
    }

    private void criarMensagemDespacho(String s) {
        msg.criarMensagemSweet(s);
    }


    /**
     * GETTERS AND SETTERs
     */
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

    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    public List<Documento> getOficiosDashBoard() {
        return oficiosDashBoard;
    }

    public void setOficiosDashBoard(List<Documento> oficiosDashBoard) {
        this.oficiosDashBoard = oficiosDashBoard;
    }

    public List<Documento> getProcessosDashBoard() {
        return processosDashBoard;
    }

    public void setProcessosDashBoard(List<Documento> processosDashBoard) {
        this.processosDashBoard = processosDashBoard;
    }

    public List<Documento> getRelatoriosDashBoard() {
        return relatoriosDashBoard;
    }

    public void setRelatoriosDashBoard(List<Documento> relatoriosDashBoard) {
        this.relatoriosDashBoard = relatoriosDashBoard;
    }

    public int getQtdeOficio() {
        return qtdeOficio;
    }

    public void setQtdeOficio(int qtdeOficio) {
        this.qtdeOficio = qtdeOficio;
    }

    public int getQtdeRelatorio() {
        return qtdeRelatorio;
    }

    public void setQtdeRelatorio(int qtdeRelatorio) {
        this.qtdeRelatorio = qtdeRelatorio;
    }

    public int getQtdeProcesso() {
        return qtdeProcesso;
    }

    public void setQtdeProcesso(int qtdeProcesso) {
        this.qtdeProcesso = qtdeProcesso;
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    public Departamento getDepartamentoParaDespacho() {
        return departamentoParaDespacho;
    }

    public void setDepartamentoParaDespacho(Departamento departamentoParaDespacho) {
        this.departamentoParaDespacho = departamentoParaDespacho;
    }

    public int getQtdePendencias() {
        return qtdePendencias;
    }

    public void setQtdePendencias(int qtdePendencias) {
        this.qtdePendencias = qtdePendencias;
    }

    public DepartamentoDao getDeptoDao() {
        return deptoDao;
    }

    public void setDeptoDao(DepartamentoDao deptoDao) {
        this.deptoDao = deptoDao;
    }

    public List<Documento> getDocumentosPendentes() {
        return documentosPendentes;
    }

    public void setDocumentosPendentes(List<Documento> documentosPendentes) {
        this.documentosPendentes = documentosPendentes;
    }

    public Documento getDocumentoPendenteSelecionado() {
        return documentoPendenteSelecionado;
    }

    public void setDocumentoPendenteSelecionado(Documento documentoPendenteSelecionado) {
        this.documentoPendenteSelecionado = documentoPendenteSelecionado;
    }
}
