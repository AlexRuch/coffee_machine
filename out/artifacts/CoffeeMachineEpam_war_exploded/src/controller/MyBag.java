package controller;

/**
 * Created by alexey on 03/03/16.
 */

import interaction.InteractionOrdersDB;
import model.ProductsDB;
import model.UsersDB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@ManagedBean
@ApplicationScoped
public class MyBag {

    public MyBag(){

    }
    private EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();
    private List<ProductsDB> listOfUserOrder = new ArrayList<>();
    private float orderCost;
//    private int listSize =0;
//
//    public int getListSize() {
//        return listSize;
//    }
//
//    public float getOrderCost() {
//        return orderCost;
//    }

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

    private UIComponent buttonConfirmOrder;
    public UIComponent getButtonConfirmOrder() {
        return buttonConfirmOrder;
    }
    public void setButtonConfirmOrder(UIComponent buttonConfirmOrder) {
        this.buttonConfirmOrder = buttonConfirmOrder;
    }

    UsersDB user;
    public String confirmOrder(){

        user = (entityManager.createQuery("select u from usersEntity u where u.id = ?1 ", UsersDB.class).setParameter(1, SignIn.StaticUserId).getResultList().get(0));

        if(orderCost < user.getUserAccount()) {
            InteractionOrdersDB.setListOfProducts(listOfUserOrder);
            InteractionOrdersDB.confirmOrder();
            listOfUserOrder.clear();
            return "index_user";
        }
        else {
//            FacesMessage facesMessage = new FacesMessage("Not enough money");
//            FacesContext facesContext = FacesContext.getCurrentInstance();
//            facesContext.addMessage(buttonConfirmOrder.getClientId(facesContext), facesMessage);
            return "myBag";
        }

    }
    public UsersDB userInfo(){
        return user = (entityManager.createQuery("select u from usersEntity u where u.id = ?1 ", UsersDB.class)
                .setParameter(1, SignIn.StaticUserId)
                .getResultList().get(0));
    }
}
