/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author zuzu
 */
@Singleton
@LocalBean
public class Counter {

    @Resource
    TimerService timer;
    
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    Log log;
    
    @Schedule(second="*/5",minute="*",hour="*")
    public void scheduleTimer(Timer timer) {
        System.out.println("Counter::scheduleTimer - @Schedule");
        newProduct("Counter::scheduleTimer - @Schedule");
    }
    
    @Timeout
    public void timerService(Timer time) {
        System.out.println("Counter::timerService - @Timeout");
        newProduct("Counter::timerService - @Timeout");
        timer.createSingleActionTimer(10000, new TimerConfig());
    }
    
    public void newUser(String id){
        map.put(id,0);
    }
    
    public void newProduct(String id){
        Integer n = map.get(id);
        map.put(id, n+1);
    }
    
    @Lock(LockType.READ)
    public HashMap<String, Integer> getMap() {
        return map;
    }
    
    @PostConstruct
    public void init() {
        System.out.println("Counter::init - @PostConstruct en Singleton");
        newUser("Counter::scheduleTimer - @Schedule");
        newUser("Counter::timerService - @Timeout");
        timer.createSingleActionTimer(10000, new TimerConfig());
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(Catalogue.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        log.setLog("Counter", "postConstruct");
    }
    
    @PreDestroy
    public void destroy() {
        System.out.println("Counter::destroy - @PreDestroy en Singleton");
    }
}
