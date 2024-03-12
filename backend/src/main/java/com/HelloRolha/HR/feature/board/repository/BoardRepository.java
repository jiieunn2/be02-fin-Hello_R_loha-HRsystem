package com.HelloRolha.HR.feature.board.repository;


import com.HelloRolha.HR.feature.board.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
