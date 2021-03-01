package com.example.springboot.service;

import com.example.springboot.dto.ExcelDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ExcelUploadService {
    public List<ExcelDto> uploadExcelFile(MultipartFile excelFile);
}
