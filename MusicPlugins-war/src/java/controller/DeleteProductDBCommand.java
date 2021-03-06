/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Log;
import ejbs.ProductFacade;
import entities.Product;
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
public class DeleteProductDBCommand extends FrontCommand{
    private Log log;
    private HttpSession session;
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) { 
            Logger.getLogger(DeleteProductDBCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        log.setLog("DeleteProductDBCommand", "process");
        
        String id = request.getParameter("idDel");  
        
        try {
            ProductFacade pf = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/ProductFacade!ejbs.ProductFacade");
            pf.deleteProduct(Integer.parseInt(id));
            forward("/web/vstmanagement.jsp");
        } catch (NamingException ex) {
            Logger.getLogger(DeleteProductDBCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
    
        
    }
    
    
}
