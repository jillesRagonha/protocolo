package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.departamento.Departamento;
import br.com.agilles.protocolo.departamento.DepartamentoDao;
import br.com.agilles.protocolo.usuario.UsuarioBean;
import br.com.agilles.protocolo.utils.MensagemUtil;

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
    @Inject
    private DepartamentoDao deptoDao;

    private List<Departamento> departamentos = new ArrayList<>();


    private int qtdeOficio = 0;
    private int qtdeRelatorio = 0;
    private int qtdeProcesso = 0;
    private int qtdePendencias = 0;

    private List<Documento> todosDocumentos = new ArrayList<>();
    private Documento documentoSelecionado = new Documento();

    private List<Documento> oficiosDashBoard = new ArrayList<>();  // coleçao para exibir os ultimos 5 oficios na tela inicial
    private List<Documento> processosDashBoard = new ArrayList<>(); // coleçao para exibir os ultimos 5 processos na tela inicial
    private List<Documento> relatoriosDashBoard = new ArrayList<>(); // coleçao para exibir os ultimos 5 relatorios na tela inicial


    private TipoDocumento[] tipoDocumentos;
    private MensagemUtil msg = new MensagemUtil();
    private Departamento departamentoParaDespacho;


    public void verDocumentoSelecionado(Documento processo) {
        msg.criarMensagemSweet("swal({title: 'Pronto', type: 'success', html: 'O <b>" + processo.getTipoDocumento().getDescricao() + " nº " + processo.getNumeroDocumento() + "</b> Esta aqui'})");

    }

    /**
     * Método que será chamado quando o usuário receber um novo documento e precisar inserir ele no sistema
     *
     * @return
     */
    public String inserirDocumentoBandeja() {
        documento.setDepartamento(usuarioBean.getUsuario().getDepartamento());
        documento.setStatusDocumento(StatusDocumento.RECEBIDO);
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
                    processosDashBoard.add(d);
                    qtdeProcesso++;
                }
                if (d.getTipoDocumento().equals(TipoDocumento.OFICIO)) {
                    oficiosDashBoard.add(d);
                    qtdeOficio++;
                }
                if (d.getTipoDocumento().equals(TipoDocumento.RC)) {
                    relatoriosDashBoard.add(d);
                    qtdeRelatorio++;
                }
                if (d.getStatusDocumento().equals(StatusDocumento.PENDENTE)) {
                    qtdePendencias++;
                }

            }
        }
        if (departamentos.isEmpty()) {
            this.departamentos = deptoDao.listarTodosDepartamentos();
        }
    }

    public void despacharDocumento() {
        if (dao.despacharDocumento(documentoSelecionado, departamentoParaDespacho)) {
            msg.criarMensagemSweet("swal({title: 'Pronto', type: 'success', html: 'O documento foi despachado'})");
        }
        this.documentoSelecionado = new Documento();
        this.documento = new Documento();
        this.departamentoParaDespacho = new Departamento();
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
}
