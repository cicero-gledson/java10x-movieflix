package com.movieflix.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter @Setter
@Entity
@Table (name="category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (length = 100, nullable = false)
    private String name;



}
