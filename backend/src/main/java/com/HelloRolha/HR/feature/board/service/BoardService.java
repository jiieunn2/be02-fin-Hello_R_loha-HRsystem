package com.HelloRolha.HR.feature.board.service;



import com.HelloRolha.HR.feature.board.model.Board;
import com.HelloRolha.HR.feature.board.model.BoardFile;
import com.HelloRolha.HR.feature.board.model.dto.BoardDto;
import com.HelloRolha.HR.feature.board.model.dto.BoardListDto;
import com.HelloRolha.HR.feature.board.repository.BoardFileRepository;
import com.HelloRolha.HR.feature.board.repository.BoardRepository;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    private final AmazonS3 s3;



    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public Board create(BoardDto boardDto) {
        LocalDateTime now = LocalDateTime.now();


        // 파일 처리 후 보드 생성
        Board board = Board.builder()
                .name(boardDto.getName())
                .title(boardDto.getTitle())
                .text(boardDto.getText())
                .build();

        board.setDate(now);

        // 파일 처리를 여기서 수행

        return boardRepository.save(board);

    }

    public List<BoardListDto> list() {
        List<Board> boards = boardRepository.findAll();
        List<BoardListDto> boardListDtos = new ArrayList<>();

        for (Board board : boards) {
            if (board != null) {
                BoardListDto boardListDto = BoardListDto.builder()
                        .id(board.getId())
                        .name(board.getName())
                        .text(board.getText())
//                      .filename(boardListDtos)
                        .title(board.getTitle())
                        .date(board.getDate())
                        .build();
                boardListDtos.add(boardListDto);
            }
        }
        return boardListDtos;
    }



    public BoardDto read(Integer id) {
        Board board = boardRepository.findById(id).orElseThrow(()->new BoardNotFoundException(""));

        return BoardDto.builder()
                .id(board.getId())
                .name(board.getName())
                .text(board.getText())
                .title(board.getTitle())
                .date(board.getDate())
                .build();
    }

    public void update(Integer id, BoardDto boardDto) {
        Optional<Board> result = boardRepository.findById(id);
        if(result.isPresent()) {
            Board board = result.get();
            board.setText(boardDto.getText());
            board.setTitle(boardDto.getTitle());
            boardRepository.save(board);
        }
    }
    public void delete(Integer id) {
        boardRepository.delete(Board.builder().id(id).build());
    }

    public String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);

        return folderPath;
    }


    public String uploadFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String saveFileName = folderPath + File.separator + uuid + "_" + originalName;
        InputStream input = null;
        try {
            input = file.getInputStream();
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());


            s3.putObject(bucket, saveFileName.replace(File.separator, "/"), input, metadata);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return s3.getUrl(bucket, saveFileName.replace(File.separator, "/")).toString();
    }

    public void saveFile(Integer id, String uploadPath) {
        boardFileRepository.save(BoardFile.builder()
                .board(Board.builder().id(id).build())
                .filename(uploadPath)
                .build());
    }
}