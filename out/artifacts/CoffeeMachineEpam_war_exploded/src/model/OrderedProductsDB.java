package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexey on 12/03/16.
 */
@Entity
@Table(name = "orderedProducts")
public class OrderedProductsDB implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    public long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "productId")
    private ProductsDB productDB;

    @ManyToMany
    @JoinTable(name = "ORDER_PRODUCT")
    private List<OrdersDB> order;

    public List<OrdersDB> getOrder() {
        return order;
    }

    public void setOrder(List<OrdersDB> order) {
        this.order = order;
    }

    public ProductsDB getProductDB() {
        return productDB;
    }

    public void setProductDB(ProductsDB productDB) {
        this.productDB = productDB;
    }
}
