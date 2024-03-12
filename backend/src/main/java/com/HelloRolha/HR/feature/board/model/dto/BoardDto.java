package com.HelloRolha.HR.feature.board.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
public class BoardDto {
    private final Integer id;
    private final String name;
    private final String title;
    private final String text;
    private final String date;
    //파일 첨부하기
    //filename

}
