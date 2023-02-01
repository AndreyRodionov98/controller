package pro.sky.controller.services.impl;

import org.springframework.stereotype.Service;
import pro.sky.controller.model.Ingredient;
import pro.sky.controller.services.IngredientService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class IngredientServiceimpl implements IngredientService {
    private final Map<Integer, Ingredient>ingredientMap=new HashMap<>();
    private static int id=0;


    @Override
    public Ingredient getIngredient(int id) {
        return ingredientMap.get(id);
    }


    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
         ingredientMap.put(id++,ingredient) ;
        return ingredient;
    }
    @Override
    public Collection<Ingredient>getAll(){
        return ingredientMap.values();
    }
    @Override
    public Ingredient removeIngredient(int id){
        return ingredientMap.remove(id);
    }
    @Override
    public Ingredient  updateIngredient(int id, Ingredient ingredient){
       ingredientMap.put(id,ingredient);
        return ingredient;
    }


}
