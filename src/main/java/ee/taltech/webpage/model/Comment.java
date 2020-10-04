package ee.taltech.webpage.model;

import ee.taltech.webpage.model.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String comment;
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "item_comment")
    private Item item;


    public Comment(String firstName, String lastName, String comment, Item item, Long id) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.comment = comment;
        this.item = item;
        time = LocalDateTime.now();
    }
}
