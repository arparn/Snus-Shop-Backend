package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Comment;
import ee.taltech.webpage.model.Item;
import ee.taltech.webpage.security.Roles;
import ee.taltech.webpage.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("comment")
@RestController
public class CommentController {

    @Autowired
    private ItemsService itemsService;

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable Long id) {
        return itemsService.getComments(id);
    }

    @PostMapping("{id}")
    public Comment addComment(@RequestBody Comment comment,
                              @PathVariable Long id) {
        return itemsService.addComment(comment, itemsService.getItemById(id));
    }
}
