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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexey on 06/03/16.
 */
@ManagedBean
public class InteractionOrdersDB {
    public InteractionOrdersDB() {
    }

/*
    <CONFIRM USER ORDER>
     */

    private static SignIn signIn = new SignIn();

    public static void setListOfProducts(List<ProductsDB> listOfProducts) {
        InteractionOrdersDB.listOfProducts = listOfProducts;
    }

    private static List<ProductsDB> listOfProducts;
    private static Map<ProductsDB, Integer> productsCounterMap = new HashMap<>();


    public static void confirmOrder() {
        int productEnable = 0;
        /*
        * Products counter
        */
        for (ProductsDB productsDB : listOfProducts) {
            if (productsCounterMap.containsKey(productsDB)) {
                productsCounterMap.replace(productsDB, productsCounterMap.get(productsDB) + 1);
            } else {
                productsCounterMap.put(productsDB, 1);
            }
        }

// СДЕЛАТЬ ПЕРЕБОР ЭЛЕМЕНТОВ В ХЭШЕ И ПРОВЕРИТЬ КОЛИЧЕСВО ПРОДУКТА В ЗАКАЗЕ И ЕГО КОЛИЧЕСТВО В НАЛИЧИЕ
        ProductsDB productsDB;
        int statusOrder;
        int finalStatusOrder = 1;
        for (Map.Entry productsMap : productsCounterMap.entrySet()) {
            productsDB = (ProductsDB) productsMap.getKey();
            int quantityInOrder = (int) productsMap.getValue();
            if (quantityInOrder > productsDB.getProductQuantity()) {
                statusOrder = 0;
            } else {
                statusOrder = 1;
            }
            finalStatusOrder = finalStatusOrder * statusOrder;
        }
        if (finalStatusOrder == 1) {
            confirmedOrder();
        }
        productsCounterMap.clear();
    }

    private static void confirmedOrder() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();

        OrdersDB ordersDB;
        OrderedProductsDB orderedProductsDB;
        List<OrderedProductsDB> listOfOrderedProducts = new ArrayList<>();

        for (ProductsDB product : listOfProducts) {

            orderedProductsDB = new OrderedProductsDB();


            entityManager.getTransaction().begin();

            orderedProductsDB.setProductDB(product);
            product.getOrderedProducts().add(orderedProductsDB);
            product.setProductQuantity(product.getProductQuantity() - 1);

            entityManager.persist(orderedProductsDB);
            entityManager.merge(product);

            entityManager.getTransaction().commit();

            listOfOrderedProducts.add(orderedProductsDB);
        }

        entityManager.getTransaction().begin();
        ordersDB = new OrdersDB();
        entityManager.persist(ordersDB);
        UsersDB user;

        user = (entityManager.createQuery("select u from usersEntity u where u.id = ?1 ", UsersDB.class)
                .setParameter(1, SignIn.StaticUserId)
                .getResultList().get(0));
        ordersDB.setUser(user);

        for (OrderedProductsDB orderedProduct : listOfOrderedProducts) {

            user.setUserAccount(user.getUserAccount() - orderedProduct.getProductDB().getProductPrice());
            ordersDB.getProduct().add(orderedProduct);
            orderedProduct.getOrder().add(ordersDB);

            entityManager.merge(ordersDB);
            entityManager.merge(orderedProduct);
            entityManager.merge(user);
        }
        entityManager.getTransaction().commit();
    }
/*
    </CONFIRM USER ORDER>
     */


    /*
        <ORDERS IN ADMIN PANEL>
        */
    private List<OrderedProductsDB> orderedProductsList;
    public List<ProductsDB> productsInOrderList;

    public List<ProductsDB> getProductsInOrderList() {
        return productsInOrderList;
    }

    private int orderedProductSize;

    public int getOrderedProductSize() {
        return orderedProductSize;
    }

    EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();

    public List<OrdersDB> allOrders() {

        return entityManager.createQuery("select o from ordersEntity o", OrdersDB.class)
                .getResultList();
    }

    public String orderDetails(long orderId) {

        OrdersDB order = entityManager.createQuery("select o from ordersEntity o where o.id = ?1", OrdersDB.class)
                .setParameter(1, orderId)
                .getResultList().get(0);
        orderedProductsList = order.getProduct();
        orderedProductSize = orderedProductsList.size();

        productsInOrderList = new ArrayList<>();
        entityManager.getTransaction().begin();
        for (OrderedProductsDB orderedProductsDB : orderedProductsList) {
            productsInOrderList.add(orderedProductsDB.getProductDB());
        }
        entityManager.getTransaction().commit();
        return "adminOrderDetails";
//        return "adminOrders";
    }

/*
    <ORDERS IN ADMIN PANEL>
    */

}
