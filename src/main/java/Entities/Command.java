package Entities;

import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_command")
    private int id;
    @Column(name = "total_price")
    private double totalPrice;
    private Date date;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "command_product",
    joinColumns = @JoinColumn(name = "command_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public Command() {
    }

    public Command(double totalPrice, Date date, List<Product> products, Address address) {
        this.totalPrice = totalPrice;
        this.date = date;
        this.products = products;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder returnedValue = new StringBuilder(String.format(
                "Commande n°%d passée le %s pour un total de %.2f€\nProduits : \n",
                id, date, totalPrice
        ));
        for (Product p : products){
            returnedValue.append(p.getReference() + "\n");
        }
        return returnedValue.toString();
    }
}
