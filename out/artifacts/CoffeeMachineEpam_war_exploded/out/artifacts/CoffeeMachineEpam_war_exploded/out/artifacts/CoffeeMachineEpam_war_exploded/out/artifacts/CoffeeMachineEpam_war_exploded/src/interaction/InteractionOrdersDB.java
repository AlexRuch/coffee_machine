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
    private OrdersDB ordersDB;
    private ProductsDB productsDB;

    public void confirmOrder(List<ProductsDB> listOfProducts){

        entityManager.getTransaction().begin();

        for (ProductsDB productsDB: listOfProducts){
            ordersDB = new OrdersDB();
            ordersDB.getProduct().add(productsDB);
        }
        entityManager.getTransaction().commit();

    }
}
