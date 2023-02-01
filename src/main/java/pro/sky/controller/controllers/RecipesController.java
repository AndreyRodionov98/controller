package pro.sky.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.controller.model.Recipe;
import pro.sky.controller.services.RecipeService;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
@RequiredArgsConstructor
@Tag(name="Рецепты",description ="CRUD-операции для работы с рецептами")
public class RecipesController {
    public final RecipeService recipeService;


    @GetMapping(path = "/{id}")
    @Operation(
            description = "поиск рецептов по id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Рецепт найден ", content =
            @Content(mediaType = "application/json", schema = @Schema(implementation = Recipe.class))
            )})
    @Parameters(value = {@Parameter(name = "id", example = "1")})
    ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        return ResponseEntity.ok(recipeService.getRecipe(id));
    }

    @GetMapping("/all")
    @Operation(summary = "получение всех рецептов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "рецепты получены",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Recipe.class))})
    })
    ResponseEntity<Collection<Recipe>> getRecipes() {
        return ResponseEntity.ok(recipeService.getAll());
    }

    @Operation(summary = "добавление рецепта")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "рецепт добавлен", content = {@Content(
                    mediaType = "application/json", schema = @Schema(implementation = Recipe.class)
            )}
            )
    })
    @PostMapping
    ResponseEntity<Recipe> addRecipe(@RequestBody Recipe recipe) {
        //if (StringUtils.isAllEmpty(recipe.getName()))
        //{return ResponseEntity.badRequest().body("название рецепта не может быть пустым");}
        //В ЧЕМ ЗДЕСЬ ОШИБКА???
            return ResponseEntity.ok(recipeService.addRecipe(recipe));
}


    @PutMapping
    @Operation(summary = "изменение рецептов по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "рецепт изменен",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Recipe.class))
            })
    })
    @Parameters(value = {@Parameter(name = "id",example = "2")})
    ResponseEntity<Recipe>updateRecipe(@PathVariable int id, @Valid @RequestBody Recipe recipe){
        return ResponseEntity.ok(recipeService.updateRecipe(id,recipe));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "удаление рецепта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "рецепт удален")
    })
    @Parameters(value = {@Parameter(name = "id",example = "2")})
    ResponseEntity<Recipe>removeRecipe(@PathVariable int id){
        return ResponseEntity.ok(recipeService.removeRecipe(id));
    }



}
