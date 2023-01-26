package pro.sky.controller.services;

import pro.sky.controller.model.Ingredient;
public interface IngredientService {
    Ingredient getIngredient(int id);

    Ingredient addIngredient(Ingredient ingredient);
}
