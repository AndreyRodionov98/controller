package pro.sky.controller.controllers;

import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pro.sky.controller.model.Ingredient;
import pro.sky.controller.services.IngredientService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
@Tag(name="Ингредиенты",description ="CRUD-операции для работы с игредиентами")
public class IngredientsController {

    public final IngredientService ingredientService;

    @GetMapping(path="/{id}")
    @Operation(
            description = "поиск ингредиентов по id")
    @ApiResponses( value = {
            @ApiResponse(
                    responseCode = "200",description = "Ингредиент найден ",content =
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Ingredient.class))
            )})
    @Parameters(value = {@Parameter(name = "id",example = "1")})
     ResponseEntity<Ingredient> getIngredient(@PathVariable Integer id){
        Ingredient ingredient=ingredientService.getIngredient(id);
        return ResponseEntity.ok(ingredient);
    }
    @Operation(summary = "добавление ингредиента")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",description = "ингредиент добавлен",content = {@Content(
                            mediaType = "application/json",schema = @Schema(implementation = Ingredient.class)
            )}
            )
    })
    @PostMapping

     ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }
    @GetMapping
    @Operation(summary = "получение всех ингредиентов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "ингредиенты получены",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Ingredient.class))})
    })
    public Collection<Ingredient> getAll(){
        return this.ingredientService.getAll();
    }
    @PutMapping("/{id}")
    @Operation(summary = "изменение ингредиентов по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "ингредиент изменен",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Ingredient.class))
            })
    })
    @Parameters(value = {@Parameter(name = "id",example = "2")})
    ResponseEntity<Ingredient> updateIngredient(@PathVariable int id, @Valid @RequestBody Ingredient ingredient){
        return  ResponseEntity.ok(ingredientService.updateIngredient(id,ingredient));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "удаление ингредиента  по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "ингредиент удален")
    })
    @Parameters(value = {@Parameter(name = "id",example = "2")})
    ResponseEntity<Ingredient> removeIngredient(@PathVariable int id){
        return ResponseEntity.ok(ingredientService.removeIngredient(id));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String ,String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);});

            return errors;
        }
    }


