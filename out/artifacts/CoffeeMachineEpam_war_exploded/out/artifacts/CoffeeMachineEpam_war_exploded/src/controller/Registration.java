package controller;

import interaction.InteractionUsersDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.transaction.*;

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


    public String SaveUser() throws HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {

        interactionUsersDB = new InteractionUsersDB();

            interactionUsersDB.addUser(userName, userEmail, userPassword);
            registrationResult = "Success!";

        return "registration_result";
    }

}
