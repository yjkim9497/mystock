package com.stock.mystock.domain.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    private String password;

    protected String name;
    protected String email;

    private Long point;

    public void updateEmail(String email) {
        this.email = email;
    }
    public void updateName(String name) {
        this.name = name;
    }
    public void updatePoint(Long point) {
        this.point = point;
    }
}
