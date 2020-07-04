/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Log;
import ejbs.ProductFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author zuzu
 */
public class UpdateIdCommand extends FrontCommand{
    
    private Log log;
    private HttpSession session;
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) { 
            Logger.getLogger(UpdateIdCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        log.setLog("UpdateIdCommand", "process");
        
        String id = request.getParameter("idUpd");  
        
        try {
            ProductFacade pf = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/ProductFacade!ejbs.ProductFacade");
            pf.updateProduct(Integer.parseInt(id));
            forward("/web/vstmanagement.jsp");
        } catch (NamingException ex) {
            Logger.getLogger(UpdateIdCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
