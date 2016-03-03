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
    private String userConfirmPassword;
    private String registrationResult;
    InteractionUsersDB interactionUsersDB;

    public Registration(){

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

    public String getUserConfirmPassword() {
        return userConfirmPassword;
    }

    public void setUserConfirmPassword(String userConfirmPassword) {
        this.userConfirmPassword = userConfirmPassword;
    }

    public String SaveUser() throws HeuristicRollbackException, HeuristicMixedException, NotSupportedException, RollbackException, SystemException {

        interactionUsersDB = new InteractionUsersDB();

        if(userPassword.equals(userConfirmPassword)){
            interactionUsersDB.addUser(userName, userEmail, userPassword);
            registrationResult = "Success!";
        }
        else {
            registrationResult = "Registration filed! Passwords mismatch";
        }


        return "registration_result";
    }

}
