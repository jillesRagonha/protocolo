package br.com.agilles.protocolo.documento;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by jille on 12/06/2017.
 */
public class DocumentoDao  implements Serializable{
    @Inject
    EntityManager manager;


    public boolean inserirDocumentoBandeja(Documento documento) {
        boolean inserido = false;
        try {
            manager.getTransaction().begin();
            manager.persist(documento);
            manager.getTransaction().commit();
            inserido = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inserido;
    }
}
