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
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zuzu
 */
@Stateful
public class vstCart implements vstCartLocal {

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
        System.out.println("Cart::remove - @Remove del Stateful");
    }

    @PostConstruct
    public void init() {
        System.out.println("Cart::init - @Init del Stateful");
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("vstCart", "postConstruct");
    }
    
    @PreDestroy
    public void destroy() {
        System.out.println("Cart::destroy - @Destroy del Stateful");
    }
    
    @PostActivate
    public void postActivate() {
        System.out.println("Cart::postActivate - @PostActivate del Stateful");
    }
    
    @PrePassivate
    public void prePassivate() {
        System.out.println("Cart::prePassivate - @Remove del Stateful");
    }
}
