package pro.sky.controller.controllers;

import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import pro.sky.controller.model.Ingredient;
import pro.sky.controller.services.IngredientService;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientsController {

    public final IngredientService ingredientService;
    //todo:construct
    @GetMapping("/id")
    Ingredient getIngredient(@PathVariable Integer id){
        return ingredientService.getIngredient(id);
    }
    @PostMapping
    Ingredient addIngredient(@Valid @RequestBody Ingredient ingredient){
        return ingredientService.addIngredient(ingredient);
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


