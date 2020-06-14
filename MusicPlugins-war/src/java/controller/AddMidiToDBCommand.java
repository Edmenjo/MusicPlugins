/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Log;
import ejbs.MidiFacade;
import entities.Midi;
import entities.Rate;
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
public class AddMidiToDBCommand extends FrontCommand{

    private HttpSession session;
    private Log log;
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(AddMidiToDBCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String id = request.getParameter("id");
        String nameAdd = request.getParameter("name");
        String descriptionAdd = request.getParameter("description");
        String priceAdd = request.getParameter("price");
        String rateStr = request.getParameter("rate");
                Rate rate = new Rate (Integer.parseInt(rateStr));
        
        
        MidiFacade midiDB;

                
            
            try {
                midiDB = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/MidiFacade!ejbs.MidiFacade");
                if (nameAdd != null){
                    Midi midi = new Midi();
                    //midi.setId(Integer.parseInt(id));
                    midi.setName(nameAdd);
                    midi.setDescription(descriptionAdd);
                    midi.setPrice(Integer.parseInt(priceAdd));
                    midi.setRate(rate);
                    
                    midiDB.create(midi);
                    
                    
                }
            } catch (NamingException ex) {
                Logger.getLogger(AddMidiToDBCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
                
            
        
        log.setLog("AddMidiToDBCommand", "process");
    
        forward("/web/catalogue.jsp");
    }

}
