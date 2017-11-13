package br.com.getfit.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Francisco
 */
public class ControllerBase {

    public void sendInfoMessage(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
        addMessageToJsfContext(facesMessage);
    }

    public void sendErrorMessage(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_ERROR, message);
        addMessageToJsfContext(facesMessage);
    }

    private FacesMessage createMessage(FacesMessage.Severity severity, String mensagemErro) {
        return new FacesMessage(severity, mensagemErro, mensagemErro);
    }

    private void addMessageToJsfContext(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    protected RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}
