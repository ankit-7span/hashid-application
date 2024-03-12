package com.spingboot.hashid.entity;

import com.spingboot.hashid.hash.Hashids;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hashids(salt = "abc", minHashLength = 8, alphabet = "abcdefghijklmnopqrstuvwxyz")
    private Long id;

    private String name;
}
