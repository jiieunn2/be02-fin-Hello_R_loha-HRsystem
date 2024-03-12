package com.HelloRolha.HR.feature.board.controller;

import com.HelloRolha.HR.feature.board.model.dto.BoardDto;
import com.HelloRolha.HR.feature.board.service.BoardNotFoundException;
import com.HelloRolha.HR.feature.board.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {
    BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public ResponseEntity<Integer> create(@RequestBody BoardDto boardDto) {
        Integer boardId = boardService.create(boardDto);
        return ResponseEntity.ok().body(boardId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/read")
    public ResponseEntity read(Integer id) {
        return ResponseEntity.ok().body(boardService.read(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody BoardDto boardDto) {
        try {
            boardService.update(id, boardDto);
            return ResponseEntity.ok().body("공지 사항이 성공적으로 수정되었습니다.");
        } catch (BoardNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        boardService.delete(id);
        return ResponseEntity.ok().body("공지 사항이 성공적으로 삭제되었습니다.");
    }
}