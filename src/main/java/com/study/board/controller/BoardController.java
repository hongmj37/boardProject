package com.study.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    @GetMapping("/") //\
    @ResponseBody
    public String main() {
        return "Hello Spring !!";
        //GetMapping의 주소로 들어왔을 때, ResponseBody로 return에 해당하는 글자를 띄워주는 역할을 함 !
    }
}
