package com.example.springboot.service.parameter;

import com.example.springboot.dto.ExcelDto;
import com.example.springboot.model.Parameter;
import com.example.springboot.service.ExcelUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class ParameterServiceImpl implements ParameterService {


    @Override
    public Long parameterCount(String key) {
        return null;
    }

    @Override
    public Optional<Parameter> parameterExcelSave() {
        return Optional.empty();
    }

    @Override
    public List<ExcelDto> uploadExcelFile(MultipartFile excelFile) {
        return null;
    }
}
