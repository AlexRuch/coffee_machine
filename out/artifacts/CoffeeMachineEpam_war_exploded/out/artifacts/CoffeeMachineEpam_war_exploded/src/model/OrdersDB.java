package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexey on 06/03/16.
 */
@Entity
@Table
public class OrdersDB implements Serializable{
    public OrdersDB(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;


//    @ElementCollection
//    private List<ProductsDB> listOfProducts;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private UsersDB user;



    @ManyToMany
    @JoinTable
    private List<ProductsDB> listOfProducts = new ArrayList<>();

    public List<ProductsDB> getListOfProducts() {
        return listOfProducts;
    }

    public void setListOfProducts(List<ProductsDB> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public UsersDB getUser() {
        return user;
    }

    public void setUser(UsersDB user) {
        this.user = user;
    }

}
