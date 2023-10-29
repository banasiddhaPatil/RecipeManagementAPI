package com.geekster.RecipeManagementSystemAPI.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recipe_table")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recipeId;
    @NotBlank
    @Column(nullable = false)
    private String recipeName;

    @NotBlank
    @Column(nullable = false, length = 2000)
    private String ingredients;

    @NotBlank
    @Column(nullable = false, length = 5000)
    private String instructions;

    @ManyToOne
   // @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JoinColumn(name = "fk_user_id")
    private User recipeOwner;

    @OneToMany
    private List<Comment> comments;
}

