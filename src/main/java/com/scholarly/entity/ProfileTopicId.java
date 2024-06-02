package com.scholarly.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProfileTopicId implements Serializable {

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "topic_id")
    private Long topicId;
}