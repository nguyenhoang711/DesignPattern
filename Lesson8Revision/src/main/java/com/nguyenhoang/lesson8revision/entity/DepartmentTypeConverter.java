package com.nguyenhoang.lesson8revision.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DepartmentTypeConverter implements AttributeConverter<Type,String> {

    @Override
    public String convertToDatabaseColumn(Type type) {
        if(type == null) return null;
        else return type.getValue();
    }

    @Override
    public Type convertToEntityAttribute(String s) {
        if(s == null) return null;
        return Type.toEnum(s);
    }
}
