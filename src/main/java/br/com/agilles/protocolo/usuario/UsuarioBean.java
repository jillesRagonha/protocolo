package br.com.agilles.protocolo.usuario;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by jille on 22/05/2017.
 */
@Named
@SessionScoped
public class UsuarioBean implements Serializable {

    private Usuario usuario;
    @Inject
    private UsuarioDao dao;



    /**
     * Método para jogar o usuário logado no sistema
     *
     * @param u
     */
    public void logar(Usuario u) {
        u = dao.completarInformacoesUsuario(u);
        this.usuario = u;
    }


    /**
     * método para sair , ou efetuar o logout
     *
     * @return
     */
    public String sair() {
        this.usuario = null;
        return "login?faces-redirect=true";
    }

    public boolean isLogado() {
        return this.usuario != null;
    }

    public void deslogar() {
        this.usuario = null;

    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
