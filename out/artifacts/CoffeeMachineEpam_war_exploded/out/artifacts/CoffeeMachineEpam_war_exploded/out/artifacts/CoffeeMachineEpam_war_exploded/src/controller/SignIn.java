package controller;

import interaction.InteractionUsersDB;
import model.UsersDB;

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

    private UsersDB user;
    private String userEmail;
    private String userPassword;
    private String signInResult;
    InteractionUsersDB interactionUsersDB = new InteractionUsersDB();

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public String getUserPassword() {
        return userPassword;
    }

    private long userId;

    public long getUserId() {
        return userId;
    }

    public static long StaticUserId;

    public String checkUser(String userEmail, String userPassword){
        user = interactionUsersDB.checkUser(userEmail, userPassword);
        if(user != null){

            userId = user.getId();
            StaticUserId = userId;

            if(user.getUserGroup().equals("user")){
                return "index_user";
            }
            else {
                return "index_admin";
            }
        }
        else {
            return "signInErr";
        }
    }
}
