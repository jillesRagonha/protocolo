package br.com.agilles.protocolo.departamento;

import br.com.agilles.protocolo.utils.MensagemUtil;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jille on 23/05/2017.
 */
@Model
public class DepartamentoBean implements Serializable {

    @Inject
    private Departamento departamento;
    @Inject
    private DepartamentoDao dao;
    private Departamento departamentoSelecionado = new Departamento();
    private List<Departamento> todosDepartamentos = new ArrayList<>();
    private MensagemUtil msg = new MensagemUtil();


    /**
     * Getters and Setters
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public DepartamentoDao getDao() {
        return dao;
    }

    public void setDao(DepartamentoDao dao) {
        this.dao = dao;
    }

    public List<Departamento> getTodosDepartamentos() {
        return todosDepartamentos;
    }

    public void setTodosDepartamentos(List<Departamento> todosDepartamentos) {
        this.todosDepartamentos = todosDepartamentos;
    }

    public Departamento getDepartamentoSelecionado() {
        return departamentoSelecionado;
    }

    public void setDepartamentoSelecionado(Departamento departamentoSelecionado) {
        this.departamentoSelecionado = departamentoSelecionado;
    }

    /**
     * Método chamado quando o usuário precisar criar um novo departamento
     *
     * @return mensagem de sucesso ou falha
     */
    public String criarDepartamento() {
        if (dao.criarDepartamento(departamento)) { // caso tudo ocorra bem >D
            msg.criarMensagemSweet("swal({title: 'Pronto', type: 'success', html: 'O departamento <b> " + departamento.getNomeDepartamento() + "</b> foi criado'})");
            limparDepartamento(departamento);
            return "";
        } else {// caso não funcione algo, exibe mensagem de erro para o usuário
            msg.criarMensagem(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_WARN, "Atenção", "Problemas ao cadastrar novo Departamento");
            return "";
        }

    }

    /**
     * Método chamado para limpar / inicializar um novo departamento
     *
     * @param departamento que será limpado
     */
    private void limparDepartamento(Departamento departamento) {
        this.departamento = new Departamento();
        this.msg = new MensagemUtil();
        this.departamentoSelecionado = new Departamento();
    }

    /**
     * Método que eu chamo para alterar o departamento escolhido
     * @return
     */
    public String alterarDepartamento() {
        try {
            if (dao.alterarDepartamento(this.departamentoSelecionado)) {
                criarMensagemAtualizacao();
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Método que será chamado quando o usuário escolher apagar um departamento do banco de dados
     */
    public String removerDepartamento() {
        if (dao.removerDepartamento(departamentoSelecionado)) {
            criarMensagemRemoção();
            limparDepartamento(departamentoSelecionado);
            listarTodosDepartamentos();
            return "";
        }

        return "";

    }

    /**
     * Metodo chamado para carregar novamente a lista de departamentos
     * geralmente usado após a inserção / remoção de departamentos
     */
    private void listarTodosDepartamentos() {
        this.todosDepartamentos = new ArrayList<>();
        this.todosDepartamentos = dao.listarTodosDepartamentos();
    }

    /**
     * Método que exibe uma mensagem estilo sweet alert para o usuário
     * Usado no evento de remoção de departamento
     */
    private void criarMensagemRemoção() {
        msg.criarMensagemSweet("swal({title: 'Sucesso', type: 'success', html: 'O departamento foi excluído'})");
        limparDepartamento(departamentoSelecionado);
    }

    /**
     * Método que exibe uma mensagem estilo sweet alert para o usuário
     * Usado no evento de atualização / alteração de departamento
     */
    private void criarMensagemAtualizacao() {
        msg.criarMensagemSweet("swal({title: 'Sucesso', type: 'success', html: 'O departamento <b> " + departamentoSelecionado.getNomeDepartamento() + "</b> foi alterado'})");
        limparDepartamento(departamentoSelecionado);

    }

    /**
     * Método para carregar a lista contendo todos os departamentos
     */
    @PostConstruct
    public void inicializarListaDepartamentos() {
        if (this.todosDepartamentos == null) {
            this.todosDepartamentos = new ArrayList<>();
        }
        if (todosDepartamentos.isEmpty()) {
            this.todosDepartamentos = dao.listarTodosDepartamentos();

        }


    }


}





