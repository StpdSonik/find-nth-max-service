package com.ksy.nthmax.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    /**
     * Метод чтения чисел из файла Excel
     *
     * @param file файл
     * @return список чисел из файла
     * @throws IOException ошибка при работе с файлом
     */
    public List<Integer> readNumbersFromExcel(File file) throws IOException {
        List<Integer> numbers = new ArrayList<>();
        FileInputStream fis = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                numbers.add((int) cell.getNumericCellValue());
            }
        }
        workbook.close();
        fis.close();
        return numbers;
    }
}
