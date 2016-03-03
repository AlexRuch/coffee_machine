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
public class interactionProductsDB {

    private List<ProductsDB> listOfCoffee;
    private List<ProductsDB> listOfTopping;
    private String  coffeeQuery= "SELECT * FROM productsDB WHERE PRODUCTGROUP  LIKE 'coffee'";
    private String  toppingQuery= "SELECT * FROM productsDB WHERE PRODUCTGROUP  LIKE 'topping'";
    EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();




    public List<ProductsDB> coffee(){

        listOfCoffee = entityManager.createQuery("from productsEntity u").getResultList();


        return listOfCoffee;
    }

//    public List<ProductsDB> topping(){
//        entityManager.getTransaction().begin();
//        listOfTopping = (List<ProductsDB>)entityManager.createNativeQuery(toppingQuery,ProductsDB.class).getResultList();
//        entityManager.getTransaction().commit();
//
//        return listOfTopping;
//    }



}
