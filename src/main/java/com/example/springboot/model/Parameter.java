package com.example.springboot.model;

import com.example.springboot.common.AuditableEntity;
import com.example.springboot.type.ParameterScope;
import com.example.springboot.type.ParameterType;

import javax.persistence.*;

@Entity
public class Parameter extends AuditableEntity {

    private static final long serialVersionUID = 1844962091181804606L;
    @Id
    private String id;

    @Column(name = "PARMA_NAME")
    private String name;

    @Column(name = "PARMA_KEY", length = 50)
    private String key;

    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "PARAM_SCOPE", length = 20)
    private ParameterScope scope;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "PARAM_TYPE", length = 20)
    private ParameterType type;

    @Column(name = "IS_DEFAULT")
    private Boolean defaultFlag;

    @Column(name = "DEFALUT_VALUE")
    private String defaultValue;

    @Column(name = "MIN_VALUE")
    private Integer minValue;

    @Column(name = "MAX_VALUE")
    private Integer maxValue;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParameterScope getScope() {
        return scope;
    }

    public void setScope(ParameterScope scope) {
        this.scope = scope;
    }

    public ParameterType getType() {
        return type;
    }

    public void setType(ParameterType type) {
        this.type = type;
    }

    public Boolean getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Boolean defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }
}
