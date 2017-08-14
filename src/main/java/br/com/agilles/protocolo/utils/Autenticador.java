package br.com.agilles.protocolo.utils;

import br.com.agilles.protocolo.usuario.LoginBean;
import br.com.agilles.protocolo.usuario.Usuario;
import br.com.agilles.protocolo.usuario.UsuarioBean;
import javassist.SerialVersionUID;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;

public class Autenticador implements PhaseListener {

    @Inject
    UsuarioBean usuarioLogado;

   @Inject
   Navigator navigator;


    @Override
    public void afterPhase(PhaseEvent phaseEvent) {
        FacesContext context = phaseEvent.getFacesContext();
        String pagina = context.getViewRoot().getViewId();
        if (pagina.equals("/login.xhtml")) {
            return;
        }else if (!pagina.contains("login.xhtml") && !usuarioLogado.isLogado()) {
          navigator.redirectTo("/login?faces-redirect=true");
        }
    }

    @Override
    public void beforePhase(PhaseEvent phaseEvent) {

    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}
