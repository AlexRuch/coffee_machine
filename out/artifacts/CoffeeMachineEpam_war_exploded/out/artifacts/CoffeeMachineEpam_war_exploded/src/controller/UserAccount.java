package controller;

import model.UsersDB;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by alexey on 14/04/16.
 */
@ManagedBean
@ApplicationScoped
public class UserAccount {

    public UserAccount(){

    }
    private EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();

    UsersDB user;

    public void addMoney(){
        user = (entityManager.createQuery("select u from usersEntity u where u.id = ?1 ", UsersDB.class)
                .setParameter(1, SignIn.StaticUserId)
                .getResultList().get(0));
        entityManager.getTransaction().begin();
        user.setUserAccount(user.getUserAccount() + 100);
        entityManager.getTransaction().commit();
    }

    public float userMoney(){
        user = (entityManager.createQuery("select u from usersEntity u where u.id = ?1 ", UsersDB.class)
                .setParameter(1, SignIn.StaticUserId)
                .getResultList().get(0));
        return user.getUserAccount();
    }
}
