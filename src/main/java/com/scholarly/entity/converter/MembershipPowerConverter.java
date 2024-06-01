package com.scholarly.entity.converter;

import com.scholarly.entity.Membership;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MembershipPowerConverter implements AttributeConverter<Membership.Power, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Membership.Power power) {
        if (power == null) {
            return null;
        }
        return power.code;
    }

    @Override
    public Membership.Power convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }
        return Membership.Power.fromCode(code);
    }
}
