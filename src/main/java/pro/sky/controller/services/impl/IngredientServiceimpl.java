package pro.sky.controller.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import pro.sky.controller.model.Ingredient;
import pro.sky.controller.services.FileService;
import pro.sky.controller.services.IngredientService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceimpl implements IngredientService {
    private Map<Integer, Ingredient>ingredientMap=new HashMap<>();
    private static int id=0;
    private final FileService fileService;
    public IngredientServiceimpl(@Qualifier("IngredientFileService") FileService ingredientFileService){
        this.fileService = ingredientFileService;


    }


    @Override
    public Ingredient getIngredient(int id) {
        if(ingredientMap.containsKey(id)){
            throw new NotFoundException("ингредиен с заданным id не найден");
        }
        return ingredientMap.get(id);
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
         ingredientMap.put(id++,ingredient) ;
        saveToFileIngredient();
        return ingredient;
    }
    @Override
    public Collection<Ingredient>getAll(){
        return ingredientMap.values();
    }
    @Override
    public Ingredient removeIngredient(int id){
        saveToFileIngredient();
        return ingredientMap.remove(id);
    }
    @Override
    public Ingredient  updateIngredient(int id, Ingredient ingredient){
       ingredientMap.put(id,ingredient);
        saveToFileIngredient();
        return ingredient;

    }
    @PostConstruct
    private void initIngredient() throws RuntimeException {
        readFromFileIngredient();
    }
    private void readFromFileIngredient() throws RuntimeException {
        try {

            String json = fileService.readFromFile();
            if (json == null){
                System.out.println(" файл не существует!");

            }else {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Ingredient>>() {
            });}
        }catch (JsonProcessingException e){
            throw new RuntimeException("файл не прочитан");
        }
    }
    private void saveToFileIngredient()throws RuntimeException{
        try{
            String json=new ObjectMapper().writeValueAsString(ingredientMap);
            fileService.saveToFile(json);
        }catch (JsonProcessingException e){
            throw new RuntimeException("файл не сохранен");
        }
    }




}
