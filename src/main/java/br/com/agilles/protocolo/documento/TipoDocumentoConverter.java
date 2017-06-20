package br.com.agilles.protocolo.documento;

import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 * Created by jille on 23/05/2017.
 */
@Named
@FacesConverter(value = "tipoDocumentoConverter")
public class TipoDocumentoConverter extends EnumConverter {

    public TipoDocumentoConverter() {
        super(TipoDocumento.class);
    }
}
