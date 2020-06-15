/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import ejbs.Log;
import ejbs.ProductFacade;
import entities.Product;
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
public class PSearchDBByNameCommand extends FrontCommand{
    private HttpSession session;
    private Log log;
    private Product product = new Product();
    
    @Override
    public void process() throws ServletException, IOException {
        session = request.getSession(true);
        
        try {
            log = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/Log!ejbs.Log");
        } catch (NamingException ex) {
            Logger.getLogger(PSearchDBByNameCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String id = request.getParameter("id");
        String nameSearch = request.getParameter("nameSearch"); 
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        ProductFacade productDB;
        try {
                productDB = InitialContext.doLookup("java:global/MusicPlugins/MusicPlugins-ejb/ProductFacade!ejbs.ProductFacade");
                List<Product> products;
                if (nameSearch != null){
                    
                    product.setName(nameSearch);
                    product.setType(type);
                    product.setPrice(Integer.parseInt(price));
                    
                    session.setAttribute(nameSearch,"nameSearch");
                    
                    productDB.create(product);
                    
                    products = productDB.findByName(nameSearch);
                    
                    for(Product product: products){
                        if(products != null){
                            out.println("<p>"+product.getName()+"</p>");
                        }
                    }
                }
            } catch (NamingException ex) {
                Logger.getLogger(PSearchDBByNameCommand.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        log.setLog("PSearchDBByNameCommand", "process");
    
        forward("/web/catalogue.jsp");
    }
}
