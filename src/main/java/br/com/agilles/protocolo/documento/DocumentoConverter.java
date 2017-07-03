package br.com.agilles.protocolo.documento;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import java.lang.annotation.Annotation;

/**
 * Created by jille on 03/07/2017.
 */
@Named
@FacesConverter(forClass = Documento.class)
@RequestScoped
public class DocumentoConverter implements Converter {
    @Inject
    DocumentoDao dao;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s!= null && !s.equals("")){
            Documento documento = new Documento();
            Long id = Long.valueOf(s);
            documento = dao.buscarDocumentoPorId(id);
            return documento;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Documento) {
            if(((Documento)o).getIdDocumento() != null){
                Documento documento = new Documento();
                documento = (Documento) o;
                String id = String.valueOf(documento.getIdDocumento());
                return id;
            }
        }
        return "";
    }
}
