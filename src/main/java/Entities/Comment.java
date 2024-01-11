package Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private int id;
    private String content;
    private Date date;
    private int rating;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;




    public Comment() {
    }

    public Comment(String content, Date date, int rating, Product product) {
        this.content = content;
        this.date = date;
        this.rating = rating;
        this.product = product;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }



    @Override
    public String toString() {
        return String.format("- Commentaire n°%d sur le produit n°%d le %s : %s - %d étoiles\n",
                id, product.getId(), date, content, rating);
    }
}
