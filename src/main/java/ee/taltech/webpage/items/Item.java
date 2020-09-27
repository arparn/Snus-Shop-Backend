package ee.taltech.webpage.items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;
    private Double price;
    private String description;
    private Double rating;
    private Integer strength;

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(Long id, String name, String url, Double price, String description, Double rating, Integer strength) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
        this.rating = rating;
        this.strength = strength;
    }

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public Item setUrl(String url) {
        this.url = url;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Item setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Item setRating(Double rating) {
        this.rating = rating;
        return this;
    }
}
