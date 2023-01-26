package pro.sky.controller.services;

import pro.sky.controller.model.Ingredient;
import pro.sky.controller.model.Recipe;

public interface RecipeService {

    Recipe getRecipe(int id);

    Recipe addRecipe(Recipe recipe);
}
