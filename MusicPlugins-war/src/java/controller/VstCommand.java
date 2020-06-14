/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Counter;
import ejbs.Log;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.util.logging.Level;
import java.util.logging.Logger;
import ejbs.VST;
import ejbs.vstCartLocal;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author zuzu
 */
public class VstCommand extends FrontCommand {
    
    
    Counter counter;
    
    private HttpSession session;
    private Log log;


    @Override
    public void process() throws IOException{
        session = request.getSession(true);
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(VstCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            counter = (Counter) InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Counter!ejbs.Counter");
        } catch (NamingException ex) {
            Logger.getLogger(VstCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("VstCommand", "process");
        getVST();
        try {
            forward("/web/vst.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(VstCommand.class.getName()).log(Level.SEVERE, null, ex); 
        }
        
    }
    
    private void getVST(){

        
            
            VST plugin = new VST(request.getParameter("product"));
            
            
            vstCartLocal cart = (vstCartLocal) session.getAttribute("Cart");
            
            if(cart == null) {
                try {
                    cart = (vstCartLocal) InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/vstCart!ejbs.vstCartLocal");
                    session.setAttribute("Cart", cart);
                    counter.newUser(session.getId());
                } catch (NamingException ex) {
                    Logger.getLogger(VstCommand.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            cart.addProduct(plugin);
            counter.newProduct(session.getId());
         }  
    }


