/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Log;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/**
 *
 * @author zuzu
 */
public class VstManagementCommand extends FrontCommand{
    private Log log;
    
    @Override
    public void process() throws ServletException, IOException {
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {  
            Logger.getLogger(VstManagementCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("VstManagementCommand", "process");
        
        forward("/web/vstmanagement.jsp");
    }
}
