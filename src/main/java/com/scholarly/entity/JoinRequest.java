package com.scholarly.entity;


import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "join_requests")
public class JoinRequest {

    @EmbeddedId
    private ProfileTopicId id;
}
