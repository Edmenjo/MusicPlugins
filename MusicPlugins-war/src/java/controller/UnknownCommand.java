/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.servlet.ServletException;

/**
 *
 * @author zuzu
 */
public class UnknownCommand extends FrontCommand {

    private Log log;
    @Override
    public void process() throws ServletException, IOException {
        
                File file = new File("C:\\Users\\zuzu\\Documents\\NetBeansProjects\\MusicPlugins\\MusicPlugins-war\\src\\java\\controller\\log.txt");
        FileWriter fileWriter = new FileWriter("C:\\Users\\zuzu\\Documents\\NetBeansProjects\\MusicPlugins\\MusicPlugins-war\\src\\java\\controller\\log.txt");
        
        
        try (PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("El comando " + request.getParameter("command") + " no existe.");

        }
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("UnknownCommand", "process");
        forward("/unknown.jsp");
    }
    
}
