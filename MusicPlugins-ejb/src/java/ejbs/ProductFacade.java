/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author zuzu
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "MusicPlugins-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
    
    public List<Product> findProductByName(String name) {
    return em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :productname")
            .setParameter("productname", name).getResultList();
    }
    
    public List<Product> findByNameCriteria(String name, int index) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        ParameterExpression<String> p = cb.parameter(String.class);

        cq.select(product)
                .where(
                        cb.like(product.get("name"), p),
                        cb.isNull(product.get("name")),
                        cb.isNull(product.get("idSolicitude"))
                )
                .orderBy(cb.asc(product.get("price")));

        TypedQuery<Product> q = em.createQuery(cq);
        q.setParameter(p, "%" + name + "%");
        q.setFirstResult(index);
        q.setMaxResults(3);
        return q.getResultList();
    }
    

    
}
