package com.geekster.RecipeManagementSystemAPI.Services;

import com.geekster.RecipeManagementSystemAPI.Model.Recipe;
import com.geekster.RecipeManagementSystemAPI.Model.User;
import com.geekster.RecipeManagementSystemAPI.Repo.IRecipeRepo;
import com.geekster.RecipeManagementSystemAPI.Repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    IRecipeRepo recipeRepo;

    @Autowired
    IUserRepo userRepo;

    public String createRecipe(Recipe recipe, String email) {
        User recipeOwner = userRepo.findFirstByUserEmail(email);
        if (recipeOwner != null) {
            recipe.setRecipeOwner(recipeOwner);
            recipeRepo.save(recipe);
            return "Recipe Posted!!!";
        }else {
            return "User not found";
        }
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepo.findAll();
    }

    public List<Recipe> getRecipesByName(String recipeName) {
        return recipeRepo.findByRecipeNameContainingIgnoreCase(recipeName);
    }

    public Object updateRecipe(Recipe recipe, String email) {
        User recipeOwner = userRepo.findFirstByUserEmail(email);
        if(recipeOwner != null) {
            Optional<Recipe> existingRecipe = recipeRepo.findRecipeByRecipeId(recipe.getRecipeId());

            if (existingRecipe.isPresent()) {
                Recipe newRecipe = existingRecipe.get();

                if(newRecipe.getRecipeOwner().equals(recipeOwner)) {
                    newRecipe.setRecipeName(recipe.getRecipeName());
                    newRecipe.setIngredients(recipe.getIngredients());
                    newRecipe.setInstructions(recipe.getInstructions());


                    return recipeRepo.save(newRecipe);
                }else {
                    return "Recipe does not belong to the user!!";
                }
            }else {
                return "Recipe not found!!";
            }
        }else {
            return "User not found!!";
        }
    }

    public boolean deleteRecipeByName(String recipeName, String email) {
        Recipe recipe = recipeRepo.findByRecipeName(recipeName);

        if (recipe.getRecipeOwner().getUserEmail().equals(email)) {
            recipeRepo.delete(recipe);
            return true; // Deletion successful
        }else {
            return false; // Recipe not found or doesn't belong to the user
        }
      }

    public boolean validateRecipe(Recipe recipe) {
        return (recipe!=null && recipeRepo.existsById(recipe.getRecipeId()));
    }
}

