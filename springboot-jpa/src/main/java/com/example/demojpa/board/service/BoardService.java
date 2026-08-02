package com.example.demojpa.board.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demojpa.board.entity.Board;
import com.example.demojpa.board.repository.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Board getBoardById(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, Board updatedBoard) {
        Board existingBoard = getBoardById(id);
        existingBoard.setTitle(updatedBoard.getTitle());
        existingBoard.setContent(updatedBoard.getContent());
        existingBoard.setAuthor(updatedBoard.getAuthor());
        return boardRepository.save(existingBoard);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }
}