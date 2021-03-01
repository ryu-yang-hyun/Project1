package com.example.springboot.service.parameter;

import com.example.springboot.dto.ExcelDto;
import com.example.springboot.model.Parameter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;


public interface ParameterService{

    Long parameterCount(String key);
    Optional<Parameter> parameterExcelSave();
    public List<ExcelDto> uploadExcelFile(MultipartFile excelFile);
}
