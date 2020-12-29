package ee.taltech.webpage.controller;

import ee.taltech.webpage.model.Comment;
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

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("{id}")
    public Comment addComment(@RequestBody Comment comment,
                              @PathVariable Long id) {
        return itemsService.addComment(comment, itemsService.getItemById(id));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}/{commId}/prohibited_comment")
    public List<Comment> deleteComment(@PathVariable long id, @PathVariable int commId) {
        return itemsService.deleteComment(itemsService.getItemById(id), commId);
    }
}
//*ngIf="user !== null && (user.role === 'USER' || user.role === 'ADMIN')"
//*ngIf="user !== null && user.role === 'ADMIN'"