package com.scholarly.entity;


import com.scholarly.entity.converter.MembershipPowerConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "memberships")
public class Membership {

    @EmbeddedId
    private ProfileTopicId id;

    @Convert(converter = MembershipPowerConverter.class)
    private Power power;


    public enum Power {

        NONE(1),
        READ(2),
        WRITE(4),
        MODERATOR(8),
        OWNER(16);

        public final int code;

        Power(int code) {
            this.code = code;
        }

        public static Power fromCode(int code) {
            for (Power power: values()) {
                if (power.code == code) {
                    return power;
                }
            }
            throw new IllegalArgumentException("Invalid power code: " + code);
        }
    }
}
