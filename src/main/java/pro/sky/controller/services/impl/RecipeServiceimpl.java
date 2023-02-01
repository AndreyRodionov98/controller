package pro.sky.controller.services.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.controller.model.Recipe;
import pro.sky.controller.services.RecipeService;

import java.util.Collection;
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
    @Override
    public Recipe removeRecipe(int id){
        return recipeMap.remove(id);
    }
    @Override
    public Collection<Recipe> getAll() {
        return recipeMap.values();
    }
    @Override
    public Recipe updateRecipe(int id, Recipe recipe){
        recipeMap.put(id,recipe);
        return recipe;
    }

}
