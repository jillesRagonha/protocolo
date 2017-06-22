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

    public void logar(Usuario u) {
        u = dao.completarInformacoesUsuario(u);
        this.usuario = u;
    }
}
