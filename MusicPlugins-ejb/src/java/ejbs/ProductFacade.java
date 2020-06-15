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
    
    
    
    
    //CRITERIA API WHERE, LIKE, ORDER BY
    public List<Product> findByNameCriteria(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> product = cq.from(Product.class);
        ParameterExpression<String> p = cb.parameter(String.class);

        cq.select(product)
                .where(
                        cb.like(product.get("name"), p),
                        cb.isNull(product.get("name")),
                        cb.isNull(product.get("id"))
                )
                .orderBy(cb.asc(product.get("price")));

        TypedQuery<Product> q = em.createQuery(cq);
        q.setParameter(p, "%" + name + "%");
        q.setMaxResults(3);
        return q.getResultList();
    }
    
    //JPQL WHERE, LIKE, ORDER BY   |||   PAGINACION
    public List<Product> findByName(String name) {
        return em.createQuery("SELECT p FROM Product p "
                + "WHERE p.name IS NOT NULL AND p.name "
                + "LIKE :name ORDER BY p.price")
                .setParameter("name", "%" + name + "%")
                .setMaxResults(3)
                .getResultList();
    }
    
    //JPQL INSERT
    public void createProduct(int id, String name, String type, int price) {
        em.createNativeQuery("INSERT INTO Product (ID,NAME,TYPE,PRICE) VALUES(?, ?,?,?)")
                .setParameter(1, id)
                .setParameter(2, name)
                .setParameter(3, type)
                .setParameter(4, price)
                .executeUpdate();
    }
    
    // JPQL DELETE
    public void delete(Integer id) {
        em.createQuery("DELETE FROM Product p WHERE .id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
    
    //JPQL UPDATE
    public void removeProduct(Integer id) {
        em.createQuery("UPDATE Product p SET p.id =null WHERE p.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
