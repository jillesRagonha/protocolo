package br.com.agilles.protocolo.documento;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
            inserido = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inserido;
    }

    public List<Documento> listarTodosDocumentos() {
        List<Documento> documentos = new ArrayList<>();
        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Documento> q = builder.createQuery(Documento.class);
            Root<Documento> root = q.from(Documento.class);
            q.select(root);
            TypedQuery<Documento> typedQuery = manager.createQuery(q);
            documentos = typedQuery.getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }
        return documentos;
    }
}
