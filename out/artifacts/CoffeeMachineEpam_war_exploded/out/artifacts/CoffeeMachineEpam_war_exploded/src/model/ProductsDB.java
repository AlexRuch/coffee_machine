package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by alexey on 02/03/16.
 */
@Entity(name = "productsEntity")
@Table(name = "productsEntity")
public class ProductsDB implements Serializable{
    public ProductsDB(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;



    @Column
    private String productName;
    @Column
    private String productGroup;
    @Column
    private float  productPrice;
    @Column
    private int    productQuantity;

    @OneToMany(mappedBy = "productDB")
    private List<OrderedProductsDB> orderedProducts;

    public List<OrderedProductsDB> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<OrderedProductsDB> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public long getId() {return id;}

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
