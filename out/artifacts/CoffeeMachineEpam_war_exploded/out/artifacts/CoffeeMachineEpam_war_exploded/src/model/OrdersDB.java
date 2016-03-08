package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexey on 06/03/16.
 */
@Entity
public class OrdersDB implements Serializable{
    public OrdersDB(){

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UsersDB user;

    @ManyToMany
    @JoinTable(name = "ORDER_PRODUCTS")
    private List<ProductsDB> product;

    public List<ProductsDB> getProduct() {
        return product;
    }

    public void setProduct(List<ProductsDB> product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public UsersDB getUser() {
        return user;
    }

    public void setUser(UsersDB user) {
        this.user = user;
    }

}
