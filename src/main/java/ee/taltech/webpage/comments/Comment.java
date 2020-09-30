package ee.taltech.webpage.comments;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

public class Comment {
    @Id
    @GeneratedValue
    private String commentCreatorName;
    private String comment;
    private LocalDateTime time;


    public Comment(String commentCreatorName, String comment) {
        this.commentCreatorName = commentCreatorName;
        this.comment = comment;
        time = LocalDateTime.now();
    }
}
