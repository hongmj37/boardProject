package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {


    @GetMapping("/board/write")
    public String boardWriteForm() {//localhost:8080/board/write에 접속하면 boardWrite.html에 접속하겠다는 의미 +    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writePro")
    public String boardWritePro(String title, String content) { //매개변수로 String형의 title과 content를 받는다는 의미
        System.out.println("제목 : " + title);
        System.out.println("내용 : " + content);
        return "";
    }
}

