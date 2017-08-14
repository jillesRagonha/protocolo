package br.com.agilles.protocolo.usuario;

import br.com.agilles.protocolo.utils.MensagemUtil;
import org.primefaces.context.RequestContext;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by jille on 22/05/2017.
 */
@RequestScoped
@Named
public class LoginBean implements Serializable {

    @Inject
    private UsuarioBean usuarioBean;

    private Usuario usuario = new Usuario();
    @Inject
    private UsuarioDao dao;


    private MensagemUtil msg = new MensagemUtil();


    /**
     * Método chamado quando o usuário clica no botão de entrar no sistema, na tela de login
     *
     * @return Página a ser direcionado ou permanece na mesma página caso não seja efetuado login com sucesso
     */
    public String fazerLogin() {
        if (dao.usuarioExiste(usuario)) { //verifica se o usuário existe no sistema
            usuarioBean.logar(usuario); //
            return "home?faces-redirect=true";
        } else {// caso não exista, exibe mensagem de erro para o usuário
            msg.criarMensagem(FacesContext.getCurrentInstance(), FacesMessage.SEVERITY_WARN, "Atenção", "Usuário ou senha incorreto");
            return "";
        }

    }

    public String deslogar() {
        this.usuarioBean.deslogar();
        return "login?faces-redirect=true";

    }
    //getters and setters
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }


}
