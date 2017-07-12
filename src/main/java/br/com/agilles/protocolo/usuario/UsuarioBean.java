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
    @Inject
    private Usuario usuario;
    @Inject
    private UsuarioDao dao;




    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Método para jogar o usuário logado no sistema
     * @param u
     */
    public void logar(Usuario u) {
        u = dao.completarInformacoesUsuario(u);
        this.usuario = u;
    }

    /**
     * método para sair , ou efetuar o logout
     * @return
     */
    public String sair(){
        this.usuario = new Usuario();
        return "login?faces-redirect=true";
    }


}
