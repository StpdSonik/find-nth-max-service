package com.ksy.nthmax.controller;

import com.ksy.nthmax.service.FileService;
import com.ksy.nthmax.service.MathService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@OpenAPIDefinition(
        info = @Info(title = "Math Operations API", version = "1.0", description = "API для выполнения различных математических операций с файлами"),
        servers = {
                @Server(url = "${server.servlet.context-path}", description = "Local server"),
        }
)
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "MathController", description = "Контроллер для выполнения математических операций")
public class MathController {

    private final FileService fileService;
    private final MathService mathService;

    @Operation(
            summary = "Найти N-ое максимальное число",
            description = "Принимает путь к файлу Excel и значение N, находит N-ое максимальное число в файле"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешное нахождение N-го максимального числа"),
            @ApiResponse(responseCode = "400", description = "Некорректное значение N или ошибка в файле")
    })
    @PostMapping("/find-max")
    public ResponseEntity<String> findNthMax(
            @RequestParam("filePath") String filePath,
            @RequestParam("n") int n) throws IOException {

        File file = new File(filePath);
        if (!file.exists()) {
            return ResponseEntity.badRequest().body("Файл не найден!");
        }

        List<Integer> numbers = fileService.readNumbersFromExcel(file);
        if (n > numbers.size() || n < 0) {
            return ResponseEntity.badRequest().body(
                    "Значение N некорректно" +
                            "Введите корректное от 1 и K, где K - кол-во числе в столбце");
        }

        int nthMax = mathService.findNthMaxNumber(numbers, n);
        return ResponseEntity.ok(String.valueOf(nthMax));
    }
}
