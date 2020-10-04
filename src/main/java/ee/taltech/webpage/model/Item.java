package ee.taltech.webpage.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public String addComment(Comment comment) {
        comments.add(comment);
        return id + " " + comments + name;
    }

}
