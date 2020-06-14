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
        
        String id = request.getParameter("id");
        String nameSearch = request.getParameter("name"); 
        String description = request.getParameter("description");
        String price = request.getParameter("price");
        MidiFacade midiDB;
        try {
                midiDB = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/MidiFacade!ejbs.MidiFacade");
                List<Midi> midis;
                if (nameSearch != null){
                    
                    midi.setName(nameSearch);
                    midi.setDescription(description);
                    midi.setPrice(Integer.parseInt(price));
                    
                    session.setAttribute(nameSearch,"nameSearch");
                    
                    midiDB.create(midi);
                    
                    midis = midiDB.findMidiByName(nameSearch);
                    
                    for(Midi midi: midis){
                        if(midis != null){
                            out.println("<p>"+midi.getName()+"</p>");
                        }
                    }
                }
            } catch (NamingException ex) {
                Logger.getLogger(SearchDBByNameCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        log.setLog("SearchDBByNameCommand", "process");
    
        forward("/web/catalogue.jsp");
    }
}
