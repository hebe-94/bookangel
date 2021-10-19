package com.example.bookangel.controller;


import com.example.bookangel.beans.vo.BoardVO;
import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.PageDTO;
import com.example.bookangel.services.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("list")
    public String list(Criteria criteria, Model model){
        log.info("-------------------------------");
        log.info("list");
        log.info("-------------------------------");
        model.addAttribute("list", boardService.getList(criteria));
        model.addAttribute("pageMaker", new PageDTO(boardService.getTotal(criteria), 10, criteria));
        return "board/list";
    }

    @PostMapping("register")
    public RedirectView register(BoardVO boardVO, RedirectAttributes rttr, Model model){
        log.info("-------------------------------");
        log.info("register : " + boardVO.toString());
        log.info("-------------------------------");

        boardService.register(boardVO);

//        쿼리 스트링으로 전달
//        rttr.addAttribute("boardNum", boardVO.getboardNum());
//        세션의 flash영역을 이용하여 전달
      /*  model.addAttribute("memberNum",boardVO.getMemberNum("41"));*/
        rttr.addFlashAttribute("boardNum", boardVO.getBoardNum());
//        RedirectView를 사용하면 redirect방식으로 전송이 가능하다.
        return new RedirectView("list");
    }

    //    여러 요청을 하나의 메소드로 받을 때에는 {}를 사용하여 콤마로 구분한다.
    @GetMapping({"read", "modify"})
    public void read(@RequestParam("boardNum") Long boardNum, Criteria criteria, Model model, HttpServletRequest request){
        String reqURI = request.getRequestURI();
        String reqType = reqURI.substring(reqURI.indexOf(request.getContextPath()) + 7);
        //read 요청 시 read 출력
        //modify 요청 시 modify 출력
        log.info("-------------------------------");
        log.info(reqType + " : " + boardNum);
        log.info("-------------------------------");

        model.addAttribute("board", boardService.get(boardNum));
        model.addAttribute("criteria", criteria);
    }

/*    @GetMapping("read")
    public void read(@RequestParam("boardNum") Long boardNum, Model model){
        log.info("-------------------------------");
        log.info("read" + " : " + boardNum);
        log.info("-------------------------------");

        model.addAttribute("board", boardService.get(boardNum));
    }*/



    //    /modify 요청을 처리할 수 있는 비지니스 로직 작성
//    수정 성공시 result에 "success"를 담아서 전달한다.
//    단위 테스트로 View에 전달할 파라미터를 조회한다.
    @PostMapping("modify")
    public RedirectView modify(BoardVO boardVO, RedirectAttributes rttr){
        log.info("-------------------------------");
        log.info("modify : " + boardVO.toString());
        log.info("-------------------------------");

        if(boardService.modify(boardVO)){
            rttr.addAttribute("result", "success");
            rttr.addAttribute("boardNum", boardVO.getBoardNum());
        }
        return new RedirectView("read");
    }

    //    /remove 요청을 처리할 수 있는 비지니스 로직 작성
//    삭제 성공 시 result에 "success"를 flash에 담아서 전달한다.
//    삭제 실패 시 result에 "fail"을 flash에 담아서 전달한다.
//    단위 테스트로 전달할 파라미터를 조회한다.
    @PostMapping("remove")
    public RedirectView remove(@RequestParam("boardNum") Long boardNum, RedirectAttributes rttr) {
        log.info("-------------------------------");
        log.info("remove : " + boardNum);
        log.info("-------------------------------");

        if (boardService.remove(boardNum)) {
            rttr.addFlashAttribute("result", "success");
        } else {
            rttr.addFlashAttribute("result", "fail");
        }
        return new RedirectView("list");
    }

    @GetMapping("register")
    public void register(){}

    @PostMapping("read")
    public RedirectView updateOk(Long boardNum,BoardVO boardVO, RedirectAttributes rttr){
        log.info("-------------------------------");
        log.info("updateOk : " + boardNum);
        log.info("-------------------------------");

        if(boardService.updateOk(boardNum)){
            rttr.addAttribute("result", "success");
            rttr.addAttribute("boardNum", boardVO.getBoardNum());
        }
        return new RedirectView("read");
    }
}
