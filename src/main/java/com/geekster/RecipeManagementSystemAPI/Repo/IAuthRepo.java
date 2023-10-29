package com.geekster.RecipeManagementSystemAPI.Repo;

import com.geekster.RecipeManagementSystemAPI.Model.AuthenticationToken;
import com.geekster.RecipeManagementSystemAPI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findFirstByTokenValue(String token);

    AuthenticationToken findFirstByUser(User user);
}
