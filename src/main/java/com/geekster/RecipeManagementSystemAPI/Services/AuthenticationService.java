package com.geekster.RecipeManagementSystemAPI.Services;

import com.geekster.RecipeManagementSystemAPI.Model.AuthenticationToken;
import com.geekster.RecipeManagementSystemAPI.Repo.IAuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    IAuthRepo authRepo;

    public boolean authenticate(String email, String token) {
        AuthenticationToken authenticationToken = authRepo.findFirstByTokenValue(token);

        if(authenticationToken == null) {
            return  false;
        }
        String tokenConnectedEmail = authenticationToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }
}
