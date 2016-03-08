package interaction;

import model.OrdersDB;
import model.ProductsDB;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

/**
 * Created by alexey on 06/03/16.
 */
public class InteractionOrdersDB {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();
    private OrdersDB ordersDB1;
    private ProductsDB productsDB;

    public void confirmOrder(List<ProductsDB> listOfProducts){

        entityManager.getTransaction().begin();
        ordersDB1 = new OrdersDB();
        entityManager.persist(ordersDB1);
        ordersDB1.getProducts().add(listOfProducts.get(0));
        ordersDB1.getProducts().add(listOfProducts.get(1));
        ordersDB1.getProducts().add(listOfProducts.get(2));
        entityManager.merge(ordersDB1);

        entityManager.getTransaction().commit();

    }
}
