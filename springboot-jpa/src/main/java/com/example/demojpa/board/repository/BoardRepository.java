package com.example.demojpa.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demojpa.board.entity.Board;

// JpaRepository: CRUD 작업을 쉽게 처리할 수 있도록 기본 메서드를 제공함
public interface BoardRepository extends JpaRepository<Board, Long> {
}