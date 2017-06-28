package br.com.agilles.protocolo.documento;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jille on 12/06/2017.
 */
public class DocumentoDao implements Serializable {
    @Inject
    EntityManager manager;


    /**
     * Documento que será inserido na bandeja -
     * Método responsável por abrir uma transação com o banco de dados
     * validar as informações e inseri-las no banco
     * @param documento
     * @return boolean, para saber se foi salvo com sucesso ou não
     */
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentos;
    }

    public List<Documento> listarProcessosParaDashBoard() {
        List<Documento> processos = new ArrayList<>();
        Documento tipoD = new Documento();
        try {
            TypedQuery<Documento> query = manager.createQuery("select d from Documento d where d.tipoDocumento = :pTipoDocumento order by d.numeroDocumento desc ", Documento.class);
            query.setParameter("pTipoDocumento", tipoD.getTipoDocumento().PROCESSO);

            processos = query.setMaxResults(4).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processos;

    }

    public List<Documento> listarRelatoriosParaDashBoard() {
        List<Documento> relatorios = new ArrayList<>();
        Documento tipoD = new Documento();
        try {
            TypedQuery<Documento> query = manager.createQuery("select d from Documento d where d.tipoDocumento = :pTipoDocumento order by d.numeroDocumento desc", Documento.class);
            query.setParameter("pTipoDocumento", tipoD.getTipoDocumento().RC);
            relatorios = query.setMaxResults(4).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relatorios;

    }

    public List<Documento> listarOficiosParaDashBoard() {
        List<Documento> oficios = new ArrayList<>();
        Documento tipoD = new Documento();
        try {
            TypedQuery<Documento> query = manager.createQuery("select d from Documento d where d.tipoDocumento = :pTipoDocumento order by d.numeroDocumento desc", Documento.class);
            query.setParameter("pTipoDocumento", tipoD.getTipoDocumento().OFICIO);
            oficios = query.setMaxResults(4).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return oficios;

    }
}
