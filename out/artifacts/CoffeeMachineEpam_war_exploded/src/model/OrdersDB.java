package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by alexey on 06/03/16.
 */
@Entity
@Table
public class OrdersDB implements Serializable{
    public OrdersDB(){

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private UsersDB user;

    @ManyToMany
    @JoinTable(name = "ORDERS_PRODUCTS",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id")
    )
    private Set<ProductsDB> products;

    public Set<ProductsDB> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductsDB> products) {
        this.products = products;
    }

    public UsersDB getUser() {
        return user;
    }

    public void setUser(UsersDB user) {
        this.user = user;
    }

}
