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
public class AddProductToDBCommand extends FrontCommand{

    private HttpSession session;
    private Log log;
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(AddProductToDBCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String id = request.getParameter("id");
        String nameAdd = request.getParameter("name");
        String typeAdd = request.getParameter("description");
        String priceAdd = request.getParameter("price");
        
        
  
            
            try {
                ProductFacade productDB = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/ProductFacade!ejbs.ProductFacade");
                    
                productDB.createProduct(Integer.parseInt(id),nameAdd,typeAdd,Integer.parseInt(priceAdd));
                    
                 forward("/web/vstmanagemente.jsp");
            } catch (NamingException ex) {
                Logger.getLogger(AddProductToDBCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
                log.setLog("AddProductToDBCommand", "process");
            
        
        
    
        
    }

}
