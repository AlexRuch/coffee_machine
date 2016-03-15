package interaction;

import controller.SignIn;
import model.OrderedProductsDB;
import model.OrdersDB;
import model.ProductsDB;
import model.UsersDB;

import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 06/03/16.
 */
@ManagedBean
public class InteractionOrdersDB {
    public InteractionOrdersDB(){}

/*
    <CONFIRM USER ORDER>
     */

    private static SignIn signIn = new SignIn();
    public static void setListOfProducts(List<ProductsDB> listOfProducts) {
        InteractionOrdersDB.listOfProducts = listOfProducts;
    }

    private static List<ProductsDB> listOfProducts;

    public static void confirmOrder(){
        EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();

        OrdersDB ordersDB;
        OrderedProductsDB orderedProductsDB;
        List<OrderedProductsDB> listOfOrderedProducts = new ArrayList<>();

        for (ProductsDB product : listOfProducts){

            orderedProductsDB = new OrderedProductsDB();


            entityManager.getTransaction().begin();

            orderedProductsDB.setProductDB(product);
            product.getOrderedProducts().add(orderedProductsDB);

            entityManager.persist(orderedProductsDB);
            entityManager.merge(product);

            entityManager.getTransaction().commit();

            listOfOrderedProducts.add(orderedProductsDB);
        }

        entityManager.getTransaction().begin();
        ordersDB = new OrdersDB();
        entityManager.persist(ordersDB);
        UsersDB user = signIn.getUser();
        //user = (entityManager.createQuery("select u from usersEntity u where u.id = ?1 ", UsersDB.class).setParameter(1, 1).getResultList().get(0));
        ordersDB.setUser(user);

        for(OrderedProductsDB orderedProduct : listOfOrderedProducts) {

            ordersDB.getProduct().add(orderedProduct);
            orderedProduct.getOrder().add(ordersDB);

            entityManager.merge(ordersDB);
            entityManager.merge(orderedProduct);
        }
        entityManager.getTransaction().commit();
    }
/*
    </CONFIRM USER ORDER>
     */



}
