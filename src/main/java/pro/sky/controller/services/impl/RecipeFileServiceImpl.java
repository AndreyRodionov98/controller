package pro.sky.controller.services.impl;

import jakarta.annotation.PostConstruct;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.controller.services.FileService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Service("RecipeFileService")
public class RecipeFileServiceImpl implements FileService {
    private Path path;
    @Value("${path.to.files}")
    private String dataFilePathIngredient;
    @Value("${name.of.recipe.file}")
    private String dataFileNameRecipe;
    @PostConstruct

    private void init(){
        path=Path.of(dataFilePathIngredient,dataFileNameRecipe);
    }
    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePathIngredient, dataFileNameRecipe), json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public String readFromFile() {
        if (Files.exists(Path.of(dataFilePathIngredient, dataFileNameRecipe))) {
            try {
                return Files.readString(Path.of(dataFilePathIngredient, dataFileNameRecipe));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public boolean cleanDataFile() {
        try {
            Path path= Path.of(dataFilePathIngredient,dataFileNameRecipe);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public File getDataFileTxt() {
        return new File(dataFileNameRecipe+"/"+dataFilePathIngredient);
    }

    @Override
    public InputStreamResource exportFile() throws FileNotFoundException {
        File file=getDataFileTxt();
        return new InputStreamResource (new FileInputStream(file));
    }

    @Override
    public void importFile(MultipartFile file) throws FileNotFoundException {
        cleanDataFile();
        FileOutputStream fos=new FileOutputStream(getDataFileTxt());
        try {
            IOUtils.copy(file.getInputStream(),fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public Path getPath() {
        return path;
    }


}
