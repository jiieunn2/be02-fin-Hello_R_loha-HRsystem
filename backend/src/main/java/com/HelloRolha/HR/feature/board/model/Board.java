package com.HelloRolha.HR.feature.board.model;

import com.HelloRolha.HR.feature.goout.model.GooutFile;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String text;
    private String name;
    private String date; //공지 사항 등록 버튼을 누르면 시간을 자동으로 db에 저장

    @Builder
    public Board(Integer id, String title, String text, String name, String date){
        this.id = id;
        this.title = title;
        this.text = text;
        this.name = name;
        this.date = date;

    }
    public void setDate(LocalDateTime dateTime) {
        this.date = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getFormattedDate() {
        return date;
    }

    @OneToMany(mappedBy = "board")
    private List<BoardFile> boardFiles = new ArrayList<>();

}