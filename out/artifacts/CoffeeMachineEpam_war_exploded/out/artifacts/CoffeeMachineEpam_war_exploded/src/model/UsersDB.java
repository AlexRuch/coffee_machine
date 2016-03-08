package model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alexey on 01/03/16.
 * jdbc:mysql://localhost:3306/coffee_machine_v2
 */
@Entity(name = "usersEntity")
@Table(name = "usersDB")
public class UsersDB {

    public UsersDB(){

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String userName;
    @Column
    private String userEmail;
    @Column
    private String userPassword;
    @Column
    private float  userAccount;
    @Column
    private String userGroup;

    @OneToMany(mappedBy = "user")
    private List<OrdersDB> order;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userNname) {
        this.userName = userNname;
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

    public float getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(float userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }
}
