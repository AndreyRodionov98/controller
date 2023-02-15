package pro.sky.controller.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.controller.model.Recipe;
import pro.sky.controller.services.FileService;
import pro.sky.controller.services.RecipeService;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class RecipeServiceimpl implements RecipeService {
    private Map<Integer, Recipe> recipeMap=new HashMap<>();
    private static int id=0;
    private final FileService fileService;
    public  RecipeServiceimpl (@Qualifier("RecipeFileService") FileService fileService) {
        this.fileService = fileService;
    }
@Override
    public Recipe getRecipe(int id) {
    return recipeMap.getOrDefault(id, null);
}

    @Override
    public Recipe addRecipe(Recipe recipe) {
    recipeMap.put(id++, recipe);
        try {
            saveToFileRecipe();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return recipe;
    }
    @Override
    public Recipe removeRecipe(int id){
        try {
            saveToFileRecipe();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return recipeMap.remove(id);
    }
    @Override
    public Collection<Recipe> getAll() {
        return recipeMap.values();
    }
    @Override
    public Recipe updateRecipe(int id, Recipe recipe){
        recipeMap.put(id,recipe);
        try {
            saveToFileRecipe();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return recipe;
    }
    @PostConstruct
    private void initRecipe() throws JsonProcessingException {
        try {
            readFromFileRecipe();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void readFromFileRecipe() throws Exception {
        try {
            String json = fileService.readFromFile();
            if (json == null){
                System.out.println(" файл не существует!");

            }
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<HashMap<Integer, Recipe>>() {
            });
        }catch (JsonProcessingException e){
            throw new Exception();
        }
    }
    private void saveToFileRecipe()throws JsonProcessingException{
        try{
            String json=new ObjectMapper().writeValueAsString(recipeMap);
            fileService.saveToFile(json);
        }catch (JsonProcessingException e){
            throw new RuntimeException("файл не сохранен");
        }
    }


}
