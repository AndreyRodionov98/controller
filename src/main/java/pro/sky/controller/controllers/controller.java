package pro.sky.controller.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {
    @GetMapping()
    public String helloWeb(){
        return "Приложение запущено !";
    }
    @GetMapping("/info")
    public String info() {
        return "Имя - Andrey" +
                " " +
                "\"Название проекта - recipe\"" +
                " " +
                "\"Дата создания - 23.01.2023\""
                ;
    }
}
