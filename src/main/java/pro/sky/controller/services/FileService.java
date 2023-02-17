package pro.sky.controller.services;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

public interface FileService {
    boolean saveToFile(String json);
    String readFromFile();
    boolean cleanDataFile();
    File getDataFileTxt();


    InputStreamResource exportFile()throws FileNotFoundException;

    void importFile(MultipartFile file)throws FileNotFoundException;

    Path getPath();
}
