package br.com.agilles.protocolo.documento;

import br.com.agilles.protocolo.departamento.Departamento;
import br.com.agilles.protocolo.usuario.UsuarioBean;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.print.Doc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jille on 12/06/2017.
 */
public class DocumentoDao implements Serializable {
    @Inject
    EntityManager manager;
    @Inject
    UsuarioBean usuarioBean;


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
     * Método que traz todos os documentos já cadastrados no banco de dados de acordo com o departamento do usuario logado
     *
     * @return
     */
    public List<Documento> listarTodosDocumentosParaDepartamentoLogado() {
        List<Documento> documentos = new ArrayList<>();
        try {
            TypedQuery<Documento> typedQuery = manager.createQuery("SELECT d from Documento d where d.departamento = :pDepartamento and (d.statusDocumento = :pstatus or d.statusDocumento = :pstatus2)", Documento.class);
            typedQuery.setParameter("pDepartamento", usuarioBean.getUsuario().getDepartamento());
            typedQuery.setParameter("pstatus", StatusDocumento.RECEBIDO);
            typedQuery.setParameter("pstatus2", StatusDocumento.DESPACHADO);

            documentos = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentos;
    }

    /**
     * método que será chamado para despachar um documento
     *
     * @param documentoSelecionado
     * @param departamentoParaDespacho
     * @return
     */
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

    public List<Documento> listarTodosDocumentosPendentes() {
        List<Documento> documentos = new ArrayList<>();
        try {
            TypedQuery<Documento> typedQuery = manager.createQuery("SELECT d from Documento d where d.departamento = :pDepartamento and d.statusDocumento = :pstatus", Documento.class);
            typedQuery.setParameter("pDepartamento", usuarioBean.getUsuario().getDepartamento());
            typedQuery.setParameter("pstatus", StatusDocumento.PENDENTE);
            documentos = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return documentos;
    }

    public boolean aceitarPendencia(Documento documentoPendenteSelecionado) {
        boolean resolvido = false;
        Documento doc = new Documento();
        try {
            manager.getTransaction().begin();
            doc = manager.find(Documento.class, documentoPendenteSelecionado.getIdDocumento());
            doc.setStatusDocumento(StatusDocumento.DESPACHADO);
            manager.merge(doc);
            manager.getTransaction().commit();
            resolvido = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resolvido;
    }
}
