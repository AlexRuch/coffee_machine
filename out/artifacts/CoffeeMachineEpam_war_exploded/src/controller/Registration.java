package controller;

import interaction.InteractionUsersDB;
import model.UsersDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 01/03/16.
 */
@ManagedBean
@SessionScoped
public class Registration {

    private String userName;
    private String userEmail;
    private String userPassword;
    private String registrationResult;
    InteractionUsersDB interactionUsersDB;
    private List<UsersDB> usersDBList = new ArrayList<>();

    EntityManager entityManager = Persistence.createEntityManagerFactory("EPAM").createEntityManager();

    public Registration(){

    }

    public String getRegistrationResult() {
        return registrationResult;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String saveUser() throws HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {

        interactionUsersDB = new InteractionUsersDB();

        usersDBList = (entityManager.createQuery("select u from usersEntity u where u.userEmail = ?1 ", UsersDB.class)
                .setParameter(1, userEmail)
                .getResultList());

        if (usersDBList.size() > 0){
            registrationResult = "This Email already use";
        }
        else {
            interactionUsersDB.addUser(userName, userEmail, userPassword);
            registrationResult = "Success!";
        }
        return "registration_result";
    }

}
