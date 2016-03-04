package controller;

/**
 * Created by alexey on 03/03/16.
 */

import model.ProductsDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigInteger;
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
    EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();

    private List<ProductsDB> listOfUserOrder = new ArrayList<>();
    private List<ProductsDB> listForGetProduc = new ArrayList<>();
    private long counter = 0;
    private BigInteger productId;
    private List<Long> lid = new ArrayList<>();

    //listOfUserOrder = new ArrayList<>();
    public long getCounter() {
        return counter;
    }

    public List<ProductsDB> getListOfUserOrder() {
        return listOfUserOrder;
    }

    public void addProductInOrder(Long productId){

        this.productId.valueOf(productId);
        //listForGetProduct = new ArrayList<>();

        //this.productId.valueOf(productId);
        //listForGetProduct = entityManager.createQuery("from productsEntity p where p.id = " + this.productId).getResultList().get(0);
        listOfUserOrder.add(entityManager.createQuery("select p from productsEntity p where p.id = ?1 ", ProductsDB.class).setParameter(1, productId).getResultList().get(0));
        //counter = listOfUserOrder.size();
    }

    public List<ProductsDB> testCount(){
        for (ProductsDB p : listOfUserOrder){
           listForGetProduc.add(p);
        }
        return listForGetProduc;
    }
}
