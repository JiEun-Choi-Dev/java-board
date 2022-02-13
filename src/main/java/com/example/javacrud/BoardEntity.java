package com.example.javacrud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BoardEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String content;
}
