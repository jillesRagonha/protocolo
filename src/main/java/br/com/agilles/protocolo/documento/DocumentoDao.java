package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.departamento.Departamento;
import org.primefaces.barcelona.domain.Document;

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
     *
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

    /**
     * Método que traz todos os documentos já cadastrados no banco de dados
     * @return
     */
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

    public boolean despacharDocumento(Documento documentoSelecionado, Departamento departamentoParaDespacho) {
        boolean despachado = false;
        Documento doc = new Documento();
        try {
            manager.getTransaction().begin();
            doc = manager.find(Documento.class, documentoSelecionado.getIdDocumento());
            doc.setDepartamento(departamentoParaDespacho);
            doc.setStatusDocumento(StatusDocumento.PENDENTE);
            manager.merge(doc);
            manager.getTransaction().commit();
            despachado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return despachado;
    }

    public Documento buscarDocumentoPorId(Long id) {
        Documento documento = manager.find(Documento.class, id);
        return documento;
    }
}
