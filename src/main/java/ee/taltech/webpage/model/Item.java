package ee.taltech.webpage.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private Double ratingsCount;
    private Double ratingsSum;
    private Double rating;
    private Integer strength;
    @ManyToMany
    private List<Comment> comments = new LinkedList<>();

    public Item(Long id, String name, String url, Double price, String description, Double rating, Integer strength) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;
        this.description = description;
        this.rating = rating;
        this.strength = strength;
        ratingsSum = 0.0;
        ratingsCount = 0.0;
    }

    public void addGrade(Integer grade){
        ratingsSum += Double.valueOf(grade);
        ratingsCount += 1.0;
        rating = (double) ratingsSum / ratingsCount;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
