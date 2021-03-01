package com.example.springboot.type;

public enum ParameterType implements EnumModel {

    STRING("문자열"),
    NUMBER("숫자형"),
    ARRAY("배열");

    private String value;

    ParameterType(String Value){
        this.value = value;
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
