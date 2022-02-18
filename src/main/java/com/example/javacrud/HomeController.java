package com.example.javacrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @GetMapping("/")
    public String homePage(
            Model model,
            Authentication authentication,
            @RequestParam(required = false) String q,
            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

            ){

        TypedQuery<BoardEntity> query =
                em.createQuery("select vo from BoardEntity vo Where vo.title like concat('%', :like, '%') or vo.content like concat('%', :like, '%')" ,BoardEntity.class);

        query.setParameter("like", q);

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());


        model.addAttribute("name","zinna");
        List<BoardEntity> list = query.getResultList();
        model.addAttribute("list",list);
        model.addAttribute("authentication", authentication);
        model.addAttribute("q", q);
        return "pages/index";
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
        return "pages/manage";
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
        return "pages/detail";
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
