/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zuzu
 */
@Stateless
@LocalBean
public class Catalogue {
    
    Log log;

    ArrayList<VST> catalogue;
    VST vst1;
    VST vst2;
    VST vst3;
    VST vst4;
    
    public void addToCatalogue(){
        catalogue.add(vst1);
        catalogue.add(vst2);
        catalogue.add(vst3);
        catalogue.add(vst4);
    }
    
    public ArrayList<VST> getCatalogue(){
        return catalogue;
    }
    
    @PostConstruct
    public void init() {
        System.out.println("Catalogue::init() - @PostConstruct");
        catalogue = new ArrayList<VST>();
        vst1 = new VST ("Ninety - MIDI Collection");
        vst2 = new VST ("Yellow - Hip Hop Sample Pack");
        vst3 = new VST ("Hero - Vocal Sample Pack");
        vst4 = new VST ("Soul - LoFi Toolkit");
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("Catalogue", "postConstruct");
    }
    
    @PreDestroy
    public void destroy(){
        System.out.println("Catalogue::destroy() - @PreDestroy");
    }
}
