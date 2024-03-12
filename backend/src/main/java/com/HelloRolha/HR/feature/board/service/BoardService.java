package com.HelloRolha.HR.feature.board.service;



import com.HelloRolha.HR.feature.board.model.Board;
import com.HelloRolha.HR.feature.board.model.dto.BoardDto;
import com.HelloRolha.HR.feature.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BoardService {
    BoardRepository boardRepository;



    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Integer create(BoardDto boardDto) {
        LocalDateTime now = LocalDateTime.now();

        Board board = Board.builder()
                .name(boardDto.getName())
                .title(boardDto.getTitle())
                .text(boardDto.getText())
                //파일 첨부
                .build();

        board.setDate(now); // LocalDateTime을 String 형식으로 저장

        Board savedBoard = boardRepository.save(board);
        return savedBoard.getId();
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
}