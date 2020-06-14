/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Log;
import ejbs.MidiFacade;
import entities.Midi;
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
public class DeleteMidiDBCommand extends FrontCommand{
    private Log log;
    private HttpSession session;
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) { 
            Logger.getLogger(DeleteMidiDBCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String idDel = request.getParameter("idDel");
        session.setAttribute(idDel,"idDel");
        //---------------------------     DELETE(id)    -----------------------------------
        
        MidiFacade mf;
        try {
            mf = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/MidiFacade!ejbs.MidiFacade");
            mf.delete(Integer.parseInt(idDel));
        } catch (NamingException ex) {
            Logger.getLogger(DeleteMidiDBCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        log.setLog("DeleteMidiDBCommand", "process");
    
        forward("/web/catalogue.jsp");
    }
    
    
}
