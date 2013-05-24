package org.fing.edu.uy.utils;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class FacesUtils {

    public static void addInfo(String message) {
        addMessage(FacesMessage.SEVERITY_INFO, message);
    }

    public static void addWarn(String message) {
        addMessage(FacesMessage.SEVERITY_WARN, message);
    }

    public static void addSev(String message) {
        addMessage(FacesMessage.SEVERITY_ERROR, message);
    }

    public static void addMessage(Severity sev, String message) {
        FacesMessage msg = new FacesMessage(sev, message, "");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
