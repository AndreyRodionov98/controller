package pro.sky.controller.services;

import pro.sky.controller.model.Ingredient;

import java.util.Collection;

public interface IngredientService {
    Ingredient getIngredient(int id);

    Ingredient addIngredient(Ingredient ingredient);

    Collection<Ingredient> getAll();

    Ingredient removeIngredient(int id);

    Ingredient  updateIngredient(int id, Ingredient ingredient);
}
