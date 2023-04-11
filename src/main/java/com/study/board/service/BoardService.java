package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired //알아서 읽어왕서 밑에다가 주입해줌 (dependency injection)
    private BoardRepository boardRepository;

    //글 작성 처리
    public void write(Board board) {
        boardRepository.save(board);
    }

    //글 리스트 처리
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    //특정 게시글 불러오기
    public Board boardView(Integer id) {
        return boardRepository.findById(id).get();
    }

    public void delete(Integer id) {
        boardRepository.deleteById(id);
    }
}
