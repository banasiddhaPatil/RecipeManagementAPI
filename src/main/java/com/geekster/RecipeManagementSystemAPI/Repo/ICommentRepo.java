package com.geekster.RecipeManagementSystemAPI.Repo;

import com.geekster.RecipeManagementSystemAPI.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICommentRepo extends JpaRepository<Comment,Integer> {
    Optional<Comment> findCommentByCommentId(Integer commentId);
}
