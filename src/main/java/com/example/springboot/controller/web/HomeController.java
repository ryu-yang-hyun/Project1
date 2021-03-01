package com.example.springboot.controller.web;

import com.example.springboot.dto.ExcelDto;
import com.example.springboot.model.Parameter;
import com.example.springboot.service.ExcelUploadService;
import com.example.springboot.service.parameter.ParameterService;
import com.example.springboot.type.ParameterScope;
import com.example.springboot.type.ParameterType;
import org.apache.poi.ss.usermodel.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/test")
public class HomeController {
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private ExcelUploadService excelUploadService;
    private ParameterService parameterService;

    @GetMapping
    public String list() {
        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        String test = "2020-11-10 15:19:50";
        String test2 = "2020-11-10 15:19:50";

        LocalDateTime a = LocalDateTime.parse(test,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime b = LocalDateTime.parse(test2,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        log.info("localDateTime : {}", a.toString().replace("T", " "));
        log.info("localDateTime : {}", b.toString().replace("T", " "));


        if ( a.isEqual(b)) {
            log.info("test");
        } else {
            log.info("test1");
        }





        return "test";
    }


    @PostMapping("/excelupload")
    public void excelUploadFile(MultipartHttpServletRequest request, Model model, HttpServletResponse response) {

        log.info("@@@@@@@@@@@@@@ request : {}", request.getFileNames());
        response.setCharacterEncoding("UTF-8");
        try {

            PrintWriter printWriter = response.getWriter();
            MultipartFile file = null;
            JSONObject jsonObject = new JSONObject();

            Iterator<String> iterator = request.getFileNames();
            if (iterator.hasNext()) {
                file = request.getFile(iterator.next());
            }
            List<ExcelDto> list = excelUploadService.uploadExcelFile(file);

            if ( list != null ) {
                jsonObject.put("rs","0000");
            } else {
                jsonObject.put("rs", "@@@@@@");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/exceluploads")
    private ResponseEntity parseTableExcel(@RequestParam MultipartFile file,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@file : {}",file);

        List<ExcelDto> excelVOList = new ArrayList<ExcelDto>();
        try {
            Workbook wbs = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = (Sheet) wbs.getSheetAt(0);

            int lastCellNum = 0;

            if(sheet.getLastRowNum() < 1){
                throw new Exception("업로드할 데이터가 없습니다.");
            }

            for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (i == 0) {
                    lastCellNum = row.getLastCellNum();
                    log.info("lastCellNum@@ : {}", lastCellNum);
                    if (lastCellNum != 8) { throw new Exception("양식이 잘못되었습니다."); }

                    Cell cell = row.getCell(0);
                    if (!cell.getRichStringCellValue().getString().trim().equals("Name")) {
                        throw new Exception("[Name] 헤더가 일치 하지 않습니다." + cell.getRichStringCellValue().equals("Name"));
                    }

                    cell = row.getCell(1);
                    if (!cell.getRichStringCellValue().getString().trim().equals("Key")) {
                        throw new Exception("[Key] 헤더가 일치 하지 않습니다." + cell.getRichStringCellValue().equals("Key"));
                    }

                    cell = row.getCell(2);
                    if (!cell.getRichStringCellValue().getString().trim().equals("Type(적용범위)")) {
                        throw new Exception("[Type(적용범위)] 헤더가 일치 하지 않습니다." + cell.getRichStringCellValue().equals("Type(적용범위)"));
                    }

                    cell = row.getCell(3);
                    if (!cell.getRichStringCellValue().getString().trim().equals("기본값")) {
                        throw new Exception("[기본값] 헤더가 일치 하지 않습니다." + cell.getRichStringCellValue().equals("기본값"));
                    }

                    cell = row.getCell(4);
                    if (!cell.getRichStringCellValue().getString().trim().equals("Data유형")) {
                        throw new Exception("[Data유형] 헤더가 일치 하지 않습니다." + cell.getRichStringCellValue().equals("Data유형"));
                    }

                    cell = row.getCell(5);
                    if (!cell.getRichStringCellValue().getString().trim().equals("최소값")) {
                        throw new Exception("[최소값] 헤더가 일치 하지 않습니다." + cell.getRichStringCellValue().equals("최소값"));
                    }

                    cell = row.getCell(6);
                    if (!cell.getRichStringCellValue().getString().trim().equals("최대값")) {
                        throw new Exception("[최대값] 헤더가 일치 하지 않습니다." + cell.getRichStringCellValue().equals("최대값"));
                    }

                    cell = row.getCell(7);
                    if (!cell.getRichStringCellValue().getString().trim().equals("설명")) {
                        throw new Exception("[설명] 헤더가 일치 하지 않습니다." + cell.getRichStringCellValue().equals("설명"));
                    }

                } else {
                    // 필수항목 체크
                    if (!StringUtils.isEmpty(cellValue(row.getCell(0)))) {
                        ExcelDto excelDto = new ExcelDto();
                        excelDto.setName(cellValue(row.getCell(0)));

                        if ( !StringUtils.isEmpty(cellValue(row.getCell(1))) ) {
                            Optional<Parameter> parameterOptional;
                            excelDto.setKey(cellValue(row.getCell(1)));
                        }
                        if ( !StringUtils.isEmpty(row.getCell(2)) ) {
                            excelDto.setScope(ParameterScope.valueOf(cellValue(row.getCell(2)).toUpperCase()));
                        }
                        if ( !StringUtils.isEmpty(row.getCell(3)) ) {
                            excelDto.setFormat(cellValue(row.getCell(3)));
                        }
                        if ( !StringUtils.isEmpty(row.getCell(4)) ) {
                            excelDto.setType(ParameterType.valueOf(cellValue(row.getCell(4)).toUpperCase()));
                        }

                        if ( row.getCell(5) != null && !"".equals(row.getCell(5)) ) {
                            excelDto.setMin(Integer.parseInt(cellValue(row.getCell(5))));
                        }

                        if ( row.getCell(6) != null && !"".equals(row.getCell(6)) ) {
                            excelDto.setMax(Integer.parseInt(cellValue(row.getCell(6))));
                        }

                        if ( !StringUtils.isEmpty(row.getCell(7))) {
                            excelDto.setDescription(cellValue(row.getCell(7)));
                        }
                        excelVOList.add(excelDto);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("오류가 있는 데이터가 있습니다." + e.getMessage());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    public String cellValue(Cell cell) {

        String value = null;

        if (cell == null) {
            value = "";
        }
        else {
            switch (cell.getCellType()) { //cell 타입에 따른 데이타 저장
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //you should change x`this to your application date format
                        SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        value = "" + objSimpleDateFormat.format(cell.getDateCellValue());
                    } else {
                        value = "" + String.format("%.0f", new Double(cell.getNumericCellValue()));
                    }
                    break;
                case STRING:
                    value = "" + cell.getStringCellValue();
                    break;
                case BLANK:
                    //value=""+cell.getBooleanCellValue();
                    value = "";
                    break;
                case ERROR:
                    value = "" + cell.getErrorCellValue();
                    break;
                default:
            }
        }

        return value.trim();
    }
}
