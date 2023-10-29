package com.geekster.RecipeManagementSystemAPI.Repo;

import com.geekster.RecipeManagementSystemAPI.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends JpaRepository<User,Integer> {
    User findFistByUserEmail(String userEmail);

    User findFirstByUserEmail(String signInEmail);

}
