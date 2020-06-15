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
public class SearchDBByNameCommand extends FrontCommand{
    private HttpSession session;
    private Log log;
    private Midi midi = new Midi();
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(SearchDBByNameCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String nameSearch = request.getParameter("name"); 
        MidiFacade midiDB;
        try {
                midiDB = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/MidiFacade!ejbs.MidiFacade");
                List<Midi> midis;
                if (nameSearch != null){
                    
                    
                    session.setAttribute(nameSearch,"nameSearch");
                    
                    
                    midis = midiDB.findMidiByName(nameSearch);
                    
                }
            } catch (NamingException ex) {
                Logger.getLogger(SearchDBByNameCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        log.setLog("SearchDBByNameCommand", "process");
    
        forward("/web/catalogue.jsp");
    }
}
