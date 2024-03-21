package com.HelloRolha.HR.feature.board.controller;

import com.HelloRolha.HR.common.dto.BaseRes;
import com.HelloRolha.HR.feature.board.model.Board;
import com.HelloRolha.HR.feature.board.model.dto.BoardDto;
import com.HelloRolha.HR.feature.board.model.dto.BoardListDto;
import com.HelloRolha.HR.feature.board.service.BoardNotFoundException;
import com.HelloRolha.HR.feature.board.service.BoardService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {

    BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @PostMapping("/create")
    public ResponseEntity create(@RequestPart BoardDto boardDto,
                                 @RequestPart MultipartFile[] uploadFiles) {
        Board board = boardService.create(boardDto);

        for (MultipartFile uploadFile : uploadFiles) {
            String uploadPath = boardService.uploadFile(uploadFile);
            boardService.saveFile(board.getId(), uploadPath);
        }

        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("공지사항 생성 성공")
                .isSuccess(true)
                .result(board)
                .build();
        return ResponseEntity.ok().body(response);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/check")
    public ResponseEntity<BaseRes> list() {
        List<BoardListDto> boardLists = boardService.list();
        BaseRes response = BaseRes.builder()
                .code(1200)
                .message("공지사항 확인 성공")
                .isSuccess(true)
                .result(boardLists)
                .build();
        return ResponseEntity.ok().body(response);
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