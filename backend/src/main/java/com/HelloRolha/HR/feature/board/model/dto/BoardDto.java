package com.HelloRolha.HR.feature.board.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
public class BoardDto {
    private final Integer id;
    private final String name;
    private final String title;
    private final String text;
    private final String date;
    private MultipartFile file; // 파일 필드 추가 ->  파일 주소 저장

}


