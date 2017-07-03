package br.com.agilles.protocolo.departamento;

import br.com.agilles.protocolo.documento.Documento;
import br.com.agilles.protocolo.documento.DocumentoDao;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by jille on 03/07/2017.
 */
@Named
@FacesConverter(value = "departamentoConverter")
@RequestScoped
public class DepartamentoConverter implements Converter {
    @Inject
    DepartamentoDao dao;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s!= null && !s.equals("")){
            Departamento departamento = new Departamento();
            int id = Integer.valueOf(s);
            departamento = dao.buscarDepartamentoPorId(id);
            return departamento;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Departamento) {
            if(((Departamento)o).getId() != 0){
                Departamento departamento = new Departamento();
                departamento = (Departamento) o;
                String id = String.valueOf(departamento.getId());
                return id;
            }
        }
        return "";
    }
}
