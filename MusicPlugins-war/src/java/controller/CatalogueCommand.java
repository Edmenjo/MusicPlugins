/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Catalogue;
import ejbs.Counter;
import ejbs.Log;
import ejbs.vstCartLocal;
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
public class CatalogueCommand extends FrontCommand {
    
    private Catalogue catalogue;
    private HttpSession session;
    private Counter counter;
    private Log log;
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(CatalogueCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("CatalogueCommand", "process");
        showCatalogue();
        forward("/web/catalogue.jsp");
    }

    private void showCatalogue() {
        vstCartLocal cart = (vstCartLocal) session.getAttribute("Cart");
        if(session.getAttribute("catalogue") == null){
            try {
                catalogue = (Catalogue) InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Catalogue!ejbs.Catalogue");
                session.setAttribute("catalogue",catalogue);
            } catch (NamingException ex) {
                Logger.getLogger(CatalogueCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(cart == null) {
            try {
                cart = (vstCartLocal) InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/vstCart!ejbs.vstCartLocal");
                session.setAttribute("Cart", cart);
                counter = (Counter) InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Counter!ejbs.Counter");
                counter.newUser(session.getId());
            } catch (NamingException ex) {
                Logger.getLogger(CatalogueCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        catalogue.addToCatalogue();
        
            }
    }
    
}

