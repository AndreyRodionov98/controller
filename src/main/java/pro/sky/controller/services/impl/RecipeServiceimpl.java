package pro.sky.controller.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.controller.model.Recipe;
import pro.sky.controller.services.RecipeService;

import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceimpl implements RecipeService {
    private final Map<Integer, Recipe> recipeMap=new HashMap<>();
    private static int id=0;
@Override
    public Recipe getRecipe(int id) {
    return recipeMap.getOrDefault(id, null);
}

    @Override
    public Recipe addRecipe(Recipe recipe) {
        recipeMap.put(id++, recipe);
        return recipe;
    }

}
