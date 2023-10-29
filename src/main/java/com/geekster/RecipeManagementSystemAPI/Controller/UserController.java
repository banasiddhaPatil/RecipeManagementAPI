package com.geekster.RecipeManagementSystemAPI.Controller;

import com.geekster.RecipeManagementSystemAPI.Model.SignInInput;
import com.geekster.RecipeManagementSystemAPI.Model.SignUpOutput;
import com.geekster.RecipeManagementSystemAPI.Model.User;
import com.geekster.RecipeManagementSystemAPI.Services.AuthenticationService;
import com.geekster.RecipeManagementSystemAPI.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@Validated
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authService;
    @PostMapping("/signUp")
    public SignUpOutput signUpUser(@RequestBody User user) throws NoSuchAlgorithmException {
        return userService.signUpUser(user);
    }
    @PostMapping("/signIn")
    public String signInUser(@RequestBody  @Valid SignInInput signInInput){
        return userService.signInUser(signInInput);
    }

    @DeleteMapping("/signOut")
    public String signOutUser(@RequestParam String email, @RequestParam String token) {
        if (authService.authenticate(email, token)) {
            return userService.signOutUser(email);
        } else {
            return "Sign out not allowed for non-authenticated user.";
        }
    }


}
