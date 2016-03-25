package interaction;


import model.ProductsDB;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by alexey on 02/03/16.
 */
@ManagedBean
public class InteractionProductsDB {

    private List<ProductsDB> listOfCoffee;
    private List<ProductsDB> listOfTopping;

    EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();

    public List<ProductsDB> coffee(){

        listOfCoffee = entityManager.createQuery("select p from productsEntity p where p.productGroup like 'coffee' and p.productQuantity > 0").getResultList();

        return listOfCoffee;
    }

    public List<ProductsDB> topping(){

        listOfTopping = entityManager.createQuery("select p from productsEntity p where p.productGroup like 'topping'").getResultList();

        return listOfTopping;
    }

    private ProductsDB product;
    public void increaceProductQuantity(long productId){

        product = entityManager.createQuery("select p from productsEntity p where p.id =?1", ProductsDB.class)
                .setParameter(1, productId)
                .getResultList().get(0);

        entityManager.getTransaction().begin();

        product.setProductQuantity(product.getProductQuantity() + 1);
        entityManager.merge(product);

        entityManager.getTransaction().commit();
    }
}
