package pro.sky.controller.services;


import pro.sky.controller.model.Recipe;

import java.util.Collection;

public interface RecipeService {

    Recipe getRecipe(int id);

    Recipe addRecipe(Recipe recipe);

    Recipe removeRecipe(int id);

    Collection<Recipe> getAll();

    Recipe updateRecipe(int id, Recipe recipe);
}
