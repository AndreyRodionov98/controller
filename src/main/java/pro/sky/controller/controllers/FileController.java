package pro.sky.controller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.controller.services.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/files")
@Tag(name="Files",description ="CRUD-операции для работы с файлами")
public class FileController {
    private final FileService RecipeFileService;
    private final FileService IngredientFileService;


    public FileController(@Qualifier("RecipeFileService") FileService RecipeFileService, @Qualifier("IngredientFileService") FileService IngredientFileService) {
        this.RecipeFileService = RecipeFileService;
        this.IngredientFileService=IngredientFileService;


    }
    @GetMapping("/ingredient/export")
    @Operation(description = "экспорт файла  ингредиентов")
    public ResponseEntity<InputStreamResource>downloadIngredientFile()throws IOException{
        InputStreamResource inputStreamResource= IngredientFileService.exportFile();
        return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_JSON).
                contentLength(Files.size(IngredientFileService.getPath())).
                header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\"Ingredient.json\"").
                body(inputStreamResource);

    }
@PostMapping(value = "/ingredient/import",consumes = MediaType. MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "импорт файла из ингредиентов")
    public ResponseEntity<Void>uploadDateFileIngredient(@RequestParam MultipartFile file)throws FileNotFoundException{
        IngredientFileService.importFile(file);
        return ResponseEntity.ok().build();
}
    @GetMapping("/recipe/export")
    @Operation(description = "экспорт файла  рецептов")
    public ResponseEntity<InputStreamResource>downloadRecipeFile()throws IOException{
        InputStreamResource inputStreamResource= RecipeFileService.exportFile();
        return ResponseEntity.ok().
                contentType(MediaType.APPLICATION_JSON).
                contentLength(Files.size(RecipeFileService.getPath())).
                header(HttpHeaders.CONTENT_DISPOSITION,"attachment; fileName=\"Recipe.json\"").
                body(inputStreamResource);

    }
    @PostMapping(value = "/recipe/import",consumes = MediaType. MULTIPART_FORM_DATA_VALUE)
    @Operation(description = "импорт файла из рецептов")
    public ResponseEntity<Void>uploadDateFileRecipe(@RequestParam MultipartFile file)throws FileNotFoundException{
        RecipeFileService.importFile(file);
        return ResponseEntity.ok().build();
    }


}
