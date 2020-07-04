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
import static java.lang.System.out;
import java.util.List;
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
public class PSearchDBByNameCommand extends FrontCommand{
    private HttpSession session;
    private Log log;
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(PSearchDBByNameCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        log.setLog("PSearchDBByNameCommand", "process");
        
       
        
        String nameSearch = request.getParameter("nameSearch");
        session.setAttribute("findName", nameSearch);
        
        
        Integer index = 0;
        session.setAttribute("indextofind", index);
        calculateNPags(nameSearch);
    
        forward("/web/vstmanagement.jsp");
    }
    
    private void calculateNPags(String name) {
        try {
            ProductFacade productDB = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/ProductFacade!ejbs.ProductFacade");
            Long countByName = productDB.countByName(name);

            if (countByName > 3) {
                countByName /= 3;

            } else {
                countByName = 0L;
            }

            session.setAttribute("indexes", countByName);
        } catch (NamingException ex) {
            Logger.getLogger(PSearchDBByNameCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
