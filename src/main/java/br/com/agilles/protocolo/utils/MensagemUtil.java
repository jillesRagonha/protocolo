package br.com.agilles.protocolo.utils;

import org.primefaces.context.RequestContext;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created by jille on 22/05/2017.
 */
public class MensagemUtil {

    public void criarMensagem(FacesContext context, FacesMessage.Severity severity, String summary, String detail) {
        context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(severity, summary, detail);
        context.addMessage(null, message);
    }

    public void criarMensagemSweet(String s) {
        RequestContext.getCurrentInstance().execute(s);
    }
}
