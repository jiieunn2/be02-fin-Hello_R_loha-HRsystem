package com.HelloRolha.HR.feature.board.repository;
import com.HelloRolha.HR.feature.board.model.Board;
import com.HelloRolha.HR.feature.board.model.BoardFile;
import com.HelloRolha.HR.feature.goout.model.Goout;
import com.HelloRolha.HR.feature.goout.model.GooutFile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BoardFileRepository  extends JpaRepository<BoardFile, Integer> {
    List<BoardFile> findAllByBoardId(Integer id);
    void deleteAllByBoard(Board board);
}