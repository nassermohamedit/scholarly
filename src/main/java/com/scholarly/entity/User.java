package com.scholarly.entity;

import com.scholarly.entity.converter.GenderConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "username is required, length between 2 and 255 characters"
    )
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "firstname is required, length between 2 and 255 characters"
    )
    @Column(nullable = false)
    private String firstname;

    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "lastname is required, length between 2 and 255 characters"
    )
    @Column(nullable = false)
    private String lastname;

    @NotNull
    @Column(nullable = false)
    @Convert(converter = GenderConverter.class)
    private Gender gender;

    @NotNull
    private String country;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Topic> ownedTopics;
}
