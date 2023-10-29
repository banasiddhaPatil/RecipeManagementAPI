package com.geekster.RecipeManagementSystemAPI.Services;

import com.geekster.RecipeManagementSystemAPI.Model.*;
import com.geekster.RecipeManagementSystemAPI.Repo.IAuthRepo;
import com.geekster.RecipeManagementSystemAPI.Repo.ICommentRepo;
import com.geekster.RecipeManagementSystemAPI.Repo.IUserRepo;
import com.geekster.RecipeManagementSystemAPI.Services.utility.EmailHandler;
import com.geekster.RecipeManagementSystemAPI.Services.utility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    IAuthRepo authRepo;

    @Autowired
    RecipeService recipeService;
    
    @Autowired
    CommentService commentService;

    @Autowired
    ICommentRepo commentRepo;

    public SignUpOutput signUpUser(User user) throws NoSuchAlgorithmException {
        boolean signUpStatus = true;
        String signUpStatusMessage = "User Registered successfully";

        String userEmail = user.getUserEmail();

        User existingUser = userRepo.findFistByUserEmail(userEmail);

        if(existingUser != null){
            signUpStatusMessage = "User already Exist";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
        String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());
        user.setUserPassword(encryptedPassword);
        userRepo.save(user);
        return new SignUpOutput(signUpStatus,signUpStatusMessage);
    }

    public String signInUser(SignInInput signInInput) {
            String signInStatusMessage = null;

            String signInEmail = signInInput.getEmail();

            if(signInEmail == null)
            {
                signInStatusMessage = "Invalid email";
                return signInStatusMessage;

            }

            //check if this user email already exists ??
            User existingUser = userRepo.findFirstByUserEmail(signInEmail);

            if(existingUser == null)
            {
                signInStatusMessage = "Email not registered!!!";
                return signInStatusMessage;

            }

            //match passwords :

            //hash the password: encrypt the password
            try {
                String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
                if(existingUser.getUserPassword().equals(encryptedPassword))
                {
                    //session should be created since password matched and user id is valid
                    AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                    authRepo.save(authToken);

                    EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                    return "Token sent to your email";
                }
                else {
                    signInStatusMessage = "Invalid credentials!!!";
                    return signInStatusMessage;
                }
            }
            catch(Exception e)
            {
                signInStatusMessage = "Internal error occurred during sign in";
                return signInStatusMessage;
            }

        }

    public String signOutUser(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        if (user != null) {
            authRepo.delete(authRepo.findFirstByUser(user));
            return "User Signed out Successfully";
        } else {
            return "User not found!";
        }
    }


    public String addComment(Comment comment, String commenterEmail) {
        boolean recipeValid = recipeService.validateRecipe(comment.getRecipe());
        if (recipeValid) {
            User commenter = userRepo.findFirstByUserEmail(commenterEmail);
            comment.setCommenter(commenter);
            return commentService.addComment(comment);
        }else {
            return "Cannot comment on Invalid Post!!!";
        }
    }

    public String removeRecipeComment(Integer commentId, String email) {
        commentRepo.deleteById(commentId);
        return "Comment deleted successfully";
    }
}

