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
    private Double ratingsCount = 0.0;
    private Double ratingsSum = 0.0;
    private Double rating = 0.0;
    private Integer strength;
    @ManyToMany
    private List<Comment> comments = new LinkedList<>();

    public Item(String name, String url, Double price, String description, Integer strength) {
        this.name = name;
        this.url = url;
        this.price = price;
        this.description = description;
        this.strength = strength;
    }

    public Double addGrade(Integer grade){
        ratingsSum += Double.valueOf(grade);
        ratingsCount ++;
        rating = (double)  Math.round(ratingsSum / ratingsCount * 10) / 10;
        return rating;
    }


    public void addComment(Comment comment) {
        comments.add(comment);
    }

}
