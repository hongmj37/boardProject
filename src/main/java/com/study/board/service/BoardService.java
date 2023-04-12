package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired //알아서 읽어왕서 밑에다가 주입해줌 (dependency injection)
    private BoardRepository boardRepository;

    //글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; //프로젝트의 경로를 담아줌 (파일을 저장할 경로 생성)

        UUID uuid = UUID.randomUUID(); //식별자 랜덤으로 생성

        String fileName = uuid + "_" + file.getOriginalFilename(); //랜덤식별자_원래파일이름 으로 저장될 파일 이름 생성

        File saveFile = new File(projectPath, fileName); // 파일을 생성해줄건데, 이 경로에 담길거고, 이름은 "xx"이야 ! (들어온 file을 넣어줄 껍데기 생성)

        file.transferTo(saveFile);

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
