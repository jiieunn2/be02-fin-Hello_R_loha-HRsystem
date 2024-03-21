package com.HelloRolha.HR.feature.board.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BoardListDto {
    private Integer id;
    private String name;
    private String filename;
    private String title;
    private String date;
    private String text;

}