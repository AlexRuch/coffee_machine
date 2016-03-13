package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexey on 06/03/16.
 */
@Entity
public class OrdersDB implements Serializable{
    public OrdersDB(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "userId")
    private UsersDB user;

    @ManyToMany(mappedBy = "order")
    @XmlTransient
    private List<OrderedProductsDB> product;

    public List<OrderedProductsDB> getProduct() {
        return product;
    }

    public void setProduct(List<OrderedProductsDB> product) {
        this.product = product;
    }

    public UsersDB getUser() {
        return user;
    }

    public void setUser(UsersDB user) {
        this.user = user;
    }

}
