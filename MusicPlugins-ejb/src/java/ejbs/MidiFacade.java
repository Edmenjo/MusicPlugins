/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Midi;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zuzu
 */
@Stateless
public class MidiFacade extends AbstractFacade<Midi> {

    @PersistenceContext(unitName = "MusicPlugins-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MidiFacade() {
        super(Midi.class);
    }
    
    public List<Midi> findMidiByName(String midiName) {
    return em.createQuery("SELECT m FROM Midi m WHERE m.name LIKE :midiname")
            .setParameter("midiname", midiName).getResultList();
    }
    
    public void delete(Integer id) {
        em.createQuery("DELETE FROM Midi m WHERE .id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
