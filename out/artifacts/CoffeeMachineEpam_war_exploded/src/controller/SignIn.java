package controller;

import interaction.InteractionUsersDB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by alexey on 06/03/16.
 */
@ManagedBean
@SessionScoped
public class SignIn {
    public SignIn(){

    }

    private String userEmail;
    private String userPassword;
    InteractionUsersDB interactionUsersDB;

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void checkUser(){
        interactionUsersDB = new InteractionUsersDB();
        interactionUsersDB.checkUser(userEmail, userPassword);

    }
}
