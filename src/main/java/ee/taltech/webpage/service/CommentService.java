package ee.taltech.webpage.service;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ItemsService itemsService;

    public Comment addComment(String firstName, String lastName, String comment, Item item){
        Comment newComment = new Comment(firstName, lastName, comment);
        commentRepository.save(newComment);
        item.addComment(commentRepository.findAll().stream().filter(x->x.getId().equals(newComment.getId())).findFirst().get());
        commentRepository.save(item.getComments().get(0));
        itemsService.update(item);
        return newComment;
    }
}
