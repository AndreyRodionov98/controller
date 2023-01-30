package pro.sky.controller.controllers;

import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
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

public class IngredientsController {

    public final IngredientService ingredientService;

    @GetMapping(path="/{id}")
    ResponseEntity<Ingredient> getIngredient(@PathVariable Integer id){
        Ingredient ingredient=ingredientService.getIngredient(id);
        return ResponseEntity.ok(ingredient);
    }
    @PostMapping
     ResponseEntity<Ingredient> addIngredient(@Valid @RequestBody Ingredient ingredient){
        return ResponseEntity.ok(ingredientService.addIngredient(ingredient));
    }
    @GetMapping
    public Collection<Ingredient> getAll(){
        return this.ingredientService.getAll();
    }
    @PutMapping("/{id}")
    ResponseEntity<Ingredient> updateIngredient(@PathVariable int id, @Valid @RequestBody Ingredient ingredient){
        return  ResponseEntity.ok(ingredientService.updateIngredient(id,ingredient));
    }
    @DeleteMapping("/{id}")
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


