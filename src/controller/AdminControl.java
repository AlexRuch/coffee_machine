package controller;

import interaction.InteractionProductsDB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by alexey on 23/03/16.
 */
@ManagedBean
@ApplicationScoped
public class AdminControl {

    public AdminControl(){

    }

    InteractionProductsDB interactionProductsDB = new InteractionProductsDB();

    public void increaseQuantity(long productId){
        interactionProductsDB.increaceProductQuantity(productId);
    }
}
