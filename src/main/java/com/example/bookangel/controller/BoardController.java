package com.example.bookangel.controller;


import com.example.bookangel.beans.vo.*;
import com.example.bookangel.services.BoardService;
import com.example.bookangel.services.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

@Controller
@Slf4j
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final CouponService couponService;

    @GetMapping("list")
    public String list(Criteria criteria, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        log.info("-------------------------------");
        log.info("list");
        log.info("-------------------------------");
        model.addAttribute("list", boardService.getList(criteria));
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        model.addAttribute("pageMaker", new PageDTO(boardService.getTotal(criteria), 10, criteria));
        return "board/list";
    }

    @PostMapping("register")
    public RedirectView register(BoardVO boardVO, RedirectAttributes rttr, Model model,HttpServletRequest request){
        log.info("-------------현재 멤버------------------");
        log.info("register : " + boardVO.toString());
        log.info("-------------------------------");

        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
        log.info("-------------멤버 확인------------------");
        log.info("memberNum : " + session.getAttribute("memberNum"));
        log.info("-------------------------------");


        if(boardVO.getAttachList() != null){
            boardVO.getAttachList().forEach(attach -> log.info(attach.toString()));
        }

        boardService.register(boardVO);


        rttr.addFlashAttribute("boardNum", boardVO.getBoardNum());
        log.info("------------------다시 뽑기----------------------");
        log.info("register : " + boardVO.toString());
        log.info("-------------------------------");
//        model.addAttribute("memberNum", boardVO.getMemberNum());

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

        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberName", session.getAttribute("memberName"));

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
    public RedirectView modify(BoardVO boardVO, RedirectAttributes rttr, HttpServletRequest request,Model model){
        log.info("-------------------------------");
        log.info("modify : " + boardVO.toString());
        log.info("-------------------------------");

        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberName", session.getAttribute("memberName"));


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

        List<AttachFileVO> attachList = boardService.getAttachList(boardNum);

        if (boardService.remove(boardNum)) {
            deleteFiles(attachList);
            rttr.addFlashAttribute("result", "success");
        } else {
            rttr.addFlashAttribute("result", "fail");
        }
        return new RedirectView("list");
    }


    private void deleteFiles(List<AttachFileVO> attachList){
        if(attachList == null || attachList.size() == 0){
            return;
        }

        log.info("delete attach files...........");
        log.info(attachList.toString());

        attachList.forEach(attach -> {
            try {
                Path file = Paths.get("C:/bookAngel/" + attach.getUploadPath() + "/" + attach.getUuid() + "_" + attach.getFileName());
                Files.delete(file);

                if(Files.probeContentType(file).startsWith("image")){
                    Path thumbnail = Paths.get("C:/bookAngel/" + attach.getUploadPath() + "/s_" + attach.getUuid() + "_" + attach.getFileName());
                    Files.delete(thumbnail);
                }
            } catch (Exception e) {
                log.error("delete file error " + e.getMessage());
            }
        });


    }

    @GetMapping("register")
    public void register(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberName", session.getAttribute("memberName"));
    }

    @PostMapping("read")
    public RedirectView updateOk(Long boardNum, BoardVO boardVO, RedirectAttributes rttr, HttpServletRequest request, Model model){
        log.info("-------------------------------");
        log.info("updateOk : " + boardNum);
        log.info("-------------------------------");

        HttpSession session = request.getSession();
        model.addAttribute("memberNum",session.getAttribute("memberNum"));
        model.addAttribute("sessionType", session.getAttribute("memberType"));
        model.addAttribute("memberName", session.getAttribute("memberName"));

        if(boardService.updateOk(boardNum)){
            rttr.addAttribute("boardNum", boardVO.getBoardNum());
            rttr.addAttribute("memberNum",boardVO.getMemberNum());
            try {
                makeCoupon(3l, request, rttr);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("쿠폰 만들기 오류!");
            }
//            rttr.addAttribute("result", "success");

        }
        return new RedirectView("read");
    }

    private void makeCoupon(long couponAmount , BoardVO boardVO, HttpServletRequest request, RedirectAttributes rttr) throws Exception {

        HttpSession session = request.getSession();

        CouponVO couponVO = new CouponVO();
        couponVO.setMemberNum((long) session.getAttribute("memberNum"));
//        couponVO.setMemberNum(boardVO.getMemberNum());

        log.info("쿠폰 생성하기 [생성 수량 : " + couponAmount + "개]");
        String data = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String tempCoupon = "";
        Random r = new Random();
        boolean result = false;
        int makeCnt = 0;

        //// 16자리의 쿠폰번호 만들기
        while (makeCnt < couponAmount){
            tempCoupon = "";

            // 쿠폰번호 생성
            for (int i = 0; i < 16; i++) {
                tempCoupon += data.charAt(r.nextInt(data.length()));
                if(i % 4 == 3 && i < 15){
                    tempCoupon += "-";
                }
            } // for

            // 중복 쿠폰번호 확인
            couponVO.setCouponName(tempCoupon);
            if(couponService.isExist(tempCoupon)){
                result = true; // 쿠폰 번호가 존재
                continue;
            }else{
                // 쿠폰 생성성
                if(couponService.makeCoupon(couponVO)){
                    // 쿠폰 생성 완료
                    makeCnt++;
                    log.info(tempCoupon);
                }else{
                    result = true; // 생성 실패
                    continue;
                }
            } // 쿠폰 생성

        } // while

        if(!result){
            rttr.addAttribute("result", "success");
        }
    }





    //    게시글 첨부파일
    @GetMapping(value = "getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AttachFileVO> getAttachList(Long boardNum){
        log.info("getAttachList " + boardNum);
        return boardService.getAttachList(boardNum);
    }
}
