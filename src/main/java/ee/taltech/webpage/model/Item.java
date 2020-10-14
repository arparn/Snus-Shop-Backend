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
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String url;
    private Double price;
    private String description;
    private Double ratingsCount = 0.0;
    private Double ratingsSum = 0.0;
    private Double rating = 0.0;
    private Integer strength;
    @ManyToMany
    private List<Comment> comments = new LinkedList<>();

    public Item(Long id, String name, String url, Double price, String description, Integer strength) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.price = price;
        this.description = description;
        this.strength = strength;
    }

    public void addGrade(Integer grade){
        ratingsSum += Double.valueOf(grade);
        ratingsCount ++;
        rating = (double) ratingsSum / ratingsCount;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
