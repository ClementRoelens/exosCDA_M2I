package Entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String reference;
    private String mark;
    private Date buyDate;
    private double price;
    private int stock;
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Image> images;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @ManyToMany(mappedBy = "products", cascade = CascadeType.REMOVE)
    private List<Command> commands;



    public Product() {
    }

    public Product(String reference, String mark, Date buyDate, double price, int stock) {
        this.mark = mark;
        this.reference = reference;
        this.buyDate = buyDate;
        this.price = price;
        this.stock = stock;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        StringBuilder returnedValue = new StringBuilder(String.format("Produit %d - %s de marque %s acheté le %s à %.2f€. %d en stock\n",
                id, reference, mark, buyDate, price, stock));
        if (!comments.isEmpty()){
            for (Comment comment : comments){
                returnedValue.append(comment);
            }
        }
        if (!images.isEmpty()){
            for (Image image : images){
                returnedValue.append(String.format("- Image n°%d - %s\n", image.getId(), image.getUrl()));
            }
        }
        return returnedValue.toString();
    }
}
