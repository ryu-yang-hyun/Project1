package com.example.springboot.type;

public enum ParameterScope implements EnumModel {

    BOT("Chatbot"),
    USER("User"),
    SCENARIO("Scenario");

    private String value;

    ParameterScope(String Value){
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
