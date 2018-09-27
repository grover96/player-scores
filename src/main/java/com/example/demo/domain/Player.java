package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {

    private long id;
    private String name;
    private String team;
    private Integer numberOfMatchesPlayed;
    private Float averageScore;

}
