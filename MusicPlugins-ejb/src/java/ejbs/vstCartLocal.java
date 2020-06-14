/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import java.util.ArrayList;
import javax.ejb.Local;

/**
 *
 * @author zuzu
 */
@Local
public interface vstCartLocal {
    public void addProduct(VST product);
    
    public ArrayList<VST> getProducts();
    
    public void remove();
}
