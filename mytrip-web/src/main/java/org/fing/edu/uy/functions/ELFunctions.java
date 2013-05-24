package org.fing.edu.uy.functions;

import java.util.Date;
import org.fing.edu.uy.context.Context;

/**
 * @author JORGE
 */
public final class ELFunctions {
    
    public static long dateDiff(Date arg0, Date arg1){
        long diff = arg1.getTime() - arg0.getTime();
        return diff / (24 * 60 * 60 * 1000);
    }
    
    public static String splitLinks(String links){
        StringBuilder builder = new StringBuilder();
        String[] linksAux = links.split(";");
        String enter = "";
        for(String aux : linksAux){
            builder.append(enter);
            builder.append("<a href=\"");
            builder.append(aux.trim());
            builder.append("\" target=\"_blank\">");
            builder.append("Link Referencia");
            builder.append("</a>");
            enter = " ; ";
        }
        return builder.toString();
    }
    
    public static String remoteUser(){
        if(Context.get() != null){
            return Context.get().getUser().getUsername();
        } else {
            return "guest";
        }
    }
    
    public static boolean ifNoneGranted(String role){
        return false;//!Context.get().getUser().getRoles();
    }
    
}
