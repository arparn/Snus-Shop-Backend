package ee.taltech.webpage.comments;

import ee.taltech.webpage.items.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @Column (name = "commentCreatorName")
    private String commentCreatorName;
    @Column (name = "comment")
    private String comment;
    @Column (name = "time")
    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "item_comment")
    private Item item;


    public Comment(String commentCreatorName, String comment, Item item) {
        this.commentCreatorName = commentCreatorName;
        this.comment = comment;
        this.item = item;
        time = LocalDateTime.now();
    }
}
