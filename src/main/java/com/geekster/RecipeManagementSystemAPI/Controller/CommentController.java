package com.geekster.RecipeManagementSystemAPI.Controller;

import com.geekster.RecipeManagementSystemAPI.Model.Comment;
import com.geekster.RecipeManagementSystemAPI.Services.AuthenticationService;
import com.geekster.RecipeManagementSystemAPI.Services.CommentService;
import com.geekster.RecipeManagementSystemAPI.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("Comment")
public class CommentController {
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @PostMapping("/newComment")
    public String addComment(@Valid @RequestBody Comment comment, @Valid @RequestParam String commenterEmail, @RequestParam String commenterToken) {
        if (authenticationService.authenticate(commenterEmail,commenterToken)) {
            return userService.addComment(comment,commenterEmail);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PutMapping("/Edit-comment")
    public Object updateComment(@Valid @RequestBody Comment comment, @Valid @RequestParam String commenterEmail, @RequestParam String commenterToken) {
        if(authenticationService.authenticate(commenterEmail,commenterToken)) {
            return commentService.updateComment(comment,commenterEmail);
        }else {
            return "Not an Authenticated user activity!!!";
        }
    }
    @DeleteMapping("/comment")
    public String removeInstaComment(@RequestParam Integer commentId, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.removeRecipeComment(commentId,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
}
