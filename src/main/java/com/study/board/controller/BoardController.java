package com.study.board.controller;

import com.study.board.entity.Board;
import com.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String boardWriteForm() {//localhost:8080/board/write에 접속하면 boardWrite.html에 접속하겠다는 의미 +    public String boardWriteForm() {

        return "boardwrite";
    }

    @PostMapping("/board/writePro") //form action의 url과 일치해야 함
    public String boardWritePro(Board board, Model model, MultipartFile file) throws Exception { //매개변수로 view의 name을 받을 경우 문제가 생길 수 있어 엔티티를 매개변수로 받아주는 것이 명확 !
        boardService.write(board, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page=0, size=10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, //page는 디폴드 값, size는 한페이지당 개수, sort는 정렬기준컬럼, direction은 정렬순서
                            String searchKeyword) {

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        } else {
            list = boardService.boardSearch(searchKeyword, pageable);
        }


        int nowPage = list.getPageable().getPageNumber() + 1; //현재 페이지 가져옴 (pageable.getPageNumber() 도 가져옴) -> 첫 값이 0이라 +1 필요
        int startPage = Math.max(nowPage - 4, 1); //Math.max는 앞 뒤 값 비교해서 더 큰값으로 출력 ! -> 해주는 이유는 현재 페이지가 1인 경우 마이너스가 나오면 1이 되도록 !
        int endPage = Math.min(nowPage + 5, list.getTotalPages()); // -> nowPage에 +5를 했을 때, 기존의 페이지보다 커질 경우를 대비하여 넘어가면 해당 리스트의 페이지 수가 끝 페이지가 되도록 !

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardlist";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1
    public String boardView(Model model, Integer id) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Integer id) {
        boardService.delete(id);
        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Integer id,
                              Model model) {

        model.addAttribute("board", boardService.boardView(id));

        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Integer id, Board board, Model model, MultipartFile file) throws Exception{

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp, file);

        model.addAttribute("message", "글 수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/board/list");

        return "message";

    }
}

