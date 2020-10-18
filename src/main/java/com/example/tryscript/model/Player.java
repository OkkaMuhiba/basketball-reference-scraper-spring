package com.example.tryscript.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Player {
    @Id
    private String id;

    private String name;

    private String pos;

    private String team;

    private String season;

    private Integer gp;

    private Integer mp;

    private Integer pts;

    private Integer fg;

    private Integer fga;

    private Integer fg3;

    private Integer fg3a;

    private Integer fg2;

    private Integer fg2a;

    private Integer ft;

    private Integer fta;

    private Integer orb;

    private Integer drb;

    private Integer trb;

    private Integer ast;

    private Integer stl;

    private Integer blk;

    private Integer tov;

    private Integer pf;
}
