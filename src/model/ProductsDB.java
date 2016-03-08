package model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by alexey on 02/03/16.
 */
@Entity(name = "productsEntity")
@Table(name = "productsDB")
public class ProductsDB {
    public ProductsDB(){

    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String productName;
    @Column
    private String productGroup;
    @Column
    private float  productPrice;
    @Column
    private int    productQuantity;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "products")
    private Set<OrdersDB> orders;

    public Set<OrdersDB> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrdersDB> orders) {
        this.orders = orders;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
