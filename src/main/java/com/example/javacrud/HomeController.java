package com.example.javacrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/")
    public String homePage(Model model, HttpSession session){

        model.addAttribute("name","zinna");
        List<BoardEntity> list = boardRepository.findAll();
        model.addAttribute("list",list);
        return "index";
    }

    /**
     *
     *글 생성 페이지 이동
     */
    @GetMapping(value = {"/manage","manage/{id}"})
    public String ManagePage(@PathVariable(required = false) Long id, Model model){

        BoardEntity boardEntity = new BoardEntity();

        if(id != null){
            boardEntity = boardRepository.findById(id)
                    .orElse(null);

            if(boardEntity == null){
                return "redirect:/manage";
            }
        }
        model.addAttribute("board", boardEntity);
        return "manage";
    }

    /**
     *
     * 게시판 글 생성
     */
    @PostMapping("/post")
    public String manage(BoardEntity boardEntity){
        boardRepository.save(boardEntity);
        return "redirect:/";
    }

    /**
     * 글 상세 보기
     */
    @GetMapping("/post/{id}")
    public  String detail(@PathVariable long id, Model model){
        BoardEntity board = boardRepository.findById(id)
                        .orElse(null);

        if(board == null){
            return "redirect:/";
        }

        model.addAttribute("title",board.getTitle());
        model.addAttribute("content", board.getContent());
        return "/detail";
    }

    /***
     * 글 삭제
     */

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardRepository.deleteById(id);
        return "redirect:/";
    }
}
