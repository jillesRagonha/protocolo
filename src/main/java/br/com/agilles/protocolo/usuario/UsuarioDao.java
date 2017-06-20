package br.com.agilles.protocolo.usuario;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;

/**
 * Created by jille on 22/05/2017.
 */
public class UsuarioDao implements Serializable {
    @Inject
    private EntityManager manager;

    /**
     * Método que recebe o usuario digitado na tela de login
     * e consulta no banco de dados se existe
     * @param usuario
     * @return boolean
     */
    public boolean usuarioExiste(Usuario usuario) {
        Query q = manager.createQuery("SELECT u from Usuario u where u.ra = :pRa and u.senha = :pSenha")
                .setParameter("pRa", usuario.getRa())
                .setParameter("pSenha", usuario.getSenha());
        return !q.getResultList().isEmpty();
    }
}
