package com.example.springboot.service;

import com.example.springboot.dto.ExcelDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

    @Override
    public List<ExcelDto> uploadExcelFile(MultipartFile excelFile) {

        return null;
    }
}
