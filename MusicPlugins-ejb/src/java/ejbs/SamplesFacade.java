/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Samples;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zuzu
 */
@Stateless
public class SamplesFacade extends AbstractFacade<Samples> {

    @PersistenceContext(unitName = "MusicPlugins-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SamplesFacade() {
        super(Samples.class);
    }
    
    public List<Samples> findSampleByName(String name) {
    return em.createQuery("SELECT s FROM Samples s WHERE s.name LIKE :samplename")
            .setParameter("samplename", name).getResultList();
    }
}
