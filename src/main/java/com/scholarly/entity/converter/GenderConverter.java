package com.scholarly.entity.converter;


import com.scholarly.entity.Gender;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class GenderConverter implements AttributeConverter<Gender, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Gender gender) {
        if (gender == null) {
            return null;
        }
        return gender.code;
    }

    @Override
    public Gender convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Gender.fromCode(code);
    }
}
