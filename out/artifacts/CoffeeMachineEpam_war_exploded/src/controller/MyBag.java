package controller;

/**
 * Created by alexey on 03/03/16.
 */

import interaction.InteractionOrdersDB;
import model.ProductsDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@ManagedBean
@SessionScoped
public class MyBag {

    public MyBag(){

    }
    private EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();
    private List<ProductsDB> listOfUserOrder = new ArrayList<>();
    private InteractionOrdersDB interactionOrdersDB = new InteractionOrdersDB();
    private float orderCost;
    private int listSize =0;

    public int getListSize() {
        return listSize;
    }

    public float getOrderCost() {
        return orderCost;
    }

    // for show products in user bag
    public List<ProductsDB> getListOfUserOrder() {
        return listOfUserOrder;
    }

    public void addProductInOrder(Long productId){

        listOfUserOrder.add(entityManager.createQuery("select p from productsEntity p where p.id = ?1 ", ProductsDB.class).setParameter(1, productId).getResultList().get(0));
    }

    public String deleteProductInOrder(ProductsDB product){
        listOfUserOrder.remove(product);
        return "myBag";
    }

    public float totalCost(){
        orderCost = 0f;
        for (ProductsDB product : listOfUserOrder){
            orderCost += product.getProductPrice();
        }

        return orderCost;
    }

    public String confirmOrder(){

        interactionOrdersDB.confirmOrder(listOfUserOrder);

        return "index";
    }
}
