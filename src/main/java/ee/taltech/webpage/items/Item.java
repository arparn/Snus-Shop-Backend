package ee.taltech.webpage.items;

import ee.taltech.webpage.comments.Comment;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "item")
public class Item {

    @Id
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="url")
    private String url;
    @Column(name="price")
    private Double price;
    @Column(name="description")
    private String description;
    @Column(name="rating")
    private Double rating;
    @Column(name="strength")
    private Integer strength;

    @OneToMany(mappedBy = "item")
    private List<Comment> comments = new LinkedList<>();

    public Item(Long id, String name, String url, Double price, String description, Double rating, Integer strength) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.strength = strength;
    }
}
