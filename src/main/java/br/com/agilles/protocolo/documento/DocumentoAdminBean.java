package br.com.agilles.protocolo.documento;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Model
public class DocumentoAdminBean {

    @Inject
    private DocumentoDao dao;

    private List<Documento> todosDocumentos = new ArrayList<>();


    @PostConstruct
    public void inicialiar(){
        if (this.todosDocumentos == null) {
            this.todosDocumentos = new ArrayList<>();
        }
        todosDocumentos = dao.listarTodosDocumentos();
    }

    public List<Documento> getTodosDocumentos() {
        return todosDocumentos;
    }

    public void setTodosDocumentos(List<Documento> todosDocumentos) {
        this.todosDocumentos = todosDocumentos;
    }
}
