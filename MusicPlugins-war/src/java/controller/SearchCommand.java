/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import ejbs.Log;
import ejbs.VST;
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
public class SearchCommand extends FrontCommand{

    private HttpSession session;
    VST vst1;
    private Log log;
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(SearchCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("SearchCommand", "process");
        checkSearch();
        forward("/web/vst.jsp");
    }
    
    private void checkSearch(){
        String product = request.getParameter("product");
        vst1  = new VST ("Ninety - MIDI Collection");
        
        if(product.equals("Ninety") || product.equals("ninety")){
            session.setAttribute("vst", vst1);
        } else {
            session.setAttribute("vst", null);
        }
    }
}
