/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Toolkit;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zuzu
 */
@Stateless
public class ToolkitFacade extends AbstractFacade<Toolkit> {

    @PersistenceContext(unitName = "MusicPlugins-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ToolkitFacade() {
        super(Toolkit.class);
    }
    
}
