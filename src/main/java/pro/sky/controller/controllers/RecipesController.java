package pro.sky.controller.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.web.bind.annotation.*;
import pro.sky.controller.model.Recipe;
import pro.sky.controller.services.RecipeService;

import java.lang.annotation.Retention;
import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipesController {
    public final RecipeService recipeService;


    @GetMapping(path="/{id}")
    ResponseEntity<Recipe> getRecipe(@PathVariable int id){
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }
    @GetMapping("/all")
    ResponseEntity<Collection<Recipe>>getRecipes(){
        return ResponseEntity.ok(recipeService.getAll());
    }

    @PostMapping
    ResponseEntity <Recipe> addRecipe(@RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeService.addRecipe(recipe));
    }
    @PutMapping
    ResponseEntity<Recipe>updateRecipe(@PathVariable int id, @Valid @RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeService.updateRecipe(id,recipe));
    }
    @DeleteMapping("/{id}")
    ResponseEntity<Recipe>removeRecipe(@PathVariable int id){
        return ResponseEntity.ok(recipeService.removeRecipe(id));
    }



}
