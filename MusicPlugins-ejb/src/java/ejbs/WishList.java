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
import javax.ejb.Stateful;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zuzu
 */
@Stateful
@LocalBean
public class WishList {
    ArrayList<VST> products = new ArrayList();
    Log log;
    
    public void addProduct(VST product) {
        products.add(product);
    }

    public ArrayList<VST> getProducts() {
        return products;
    }
    
    @Remove
    public void remove() {
        System.out.println("WishList::remove - @Remove del Stateful");
    }
    
    @PostConstruct
    public void init() {
        System.out.println("WishList::init - @Init del Stateful");
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("WishList", "postConstruct");
    }
    
    @PreDestroy
    public void destroy() {
        System.out.println("WishList::destroy - @Destroy del Stateful");
    }
    
    @PostActivate
    public void postActivate() {
        System.out.println("WishList::postActivate - @PostActivate del Stateful");
    }
    
    @PrePassivate
    public void prePassivate() {
        System.out.println("WishList::prePassivate - @Remove del Stateful");
    }
}
