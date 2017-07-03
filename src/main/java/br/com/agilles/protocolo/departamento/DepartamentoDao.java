package br.com.agilles.protocolo.departamento;

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
 * Created by jille on 23/05/2017.
 */
public class DepartamentoDao implements Serializable {
    @Inject
    private EntityManager manager;


    public boolean criarDepartamento(Departamento departamento) {
        boolean salvo = false;
        try {
            manager.getTransaction().begin();
            manager.persist(departamento);
            salvo = true;
            manager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            salvo = false;
        }

        return salvo;
    }

    public List<Departamento> listarTodosDepartamentos() {
        List<Departamento> departamentos = new ArrayList<>();
        try {

            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Departamento> q = builder.createQuery(Departamento.class);
            Root<Departamento> root = q.from(Departamento.class);
            q.select(root);
            q.orderBy(builder.desc(root.get("id")), builder.asc(root.get("id")));
            TypedQuery<Departamento> typedQuery = manager.createQuery(q);
            departamentos = typedQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return departamentos;
    }

    public boolean alterarDepartamento(Departamento departamentoSelecionado) {
        boolean alterado = false;
        try {
            manager.getTransaction().begin();
            manager.merge(departamentoSelecionado);
            manager.getTransaction().commit();
            alterado = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alterado;
    }

    public boolean removerDepartamento(Departamento departamentoSelecionado) {
        boolean apagado = false;
        Departamento departamentoQueSeraRemovido;

        try {
            manager.getTransaction().begin();
            departamentoQueSeraRemovido = manager.find(Departamento.class, departamentoSelecionado.getId());
            manager.merge(departamentoQueSeraRemovido);
            manager.remove(departamentoQueSeraRemovido);
            manager.getTransaction().commit();
            apagado = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return apagado;
    }

    public Departamento buscarDepartamentoPorId(int id) {
        Departamento departamento = manager.find(Departamento.class, id);
        return departamento;
    }
}
