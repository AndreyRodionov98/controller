package pro.sky.controller.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pro.sky.controller.model.Recipe;
import pro.sky.controller.services.RecipeService;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipesController {
    public final RecipeService recipeService;
    @GetMapping(path="*/id")
    Recipe getRecipe(@PathVariable int id){
        return recipeService.getRecipe(id);
    }
    @PostMapping
    Recipe addRecipe(@RequestBody Recipe recipe){
        return recipeService.addRecipe(recipe);
    }

}
