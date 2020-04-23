// DB Table representation

import java.io.Serializable;
// This is the Bean
public class Product implements Serializable {
    private long id;
    private String title;
    private long priceInCents;
    private String description;

//  zero argument constructor
    public Product() {
    }

    public long getId() {
        return id;
    }
//  Getter and Setters
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(long priceInCents) {
        this.priceInCents = priceInCents;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
