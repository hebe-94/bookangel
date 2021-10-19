package com.example.bookangel.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class BoardControllerTest {

    //    가짜 MVC
//    마치 브라우저에서 URL을 요청한 것처럼 환경을 만들어준다.
    private MockMvc mockMvc;

    //    요청을 처리해주는 WebApplicationContext를 불러온다.
    @Autowired
    private WebApplicationContext webApplicationContext;

    //    하위의 모든 테스트가 실행 전에 실행되도록 한다.
    @BeforeEach
    public void setUp(){
//        가짜 MVC에 WebApplicationContext를 전달한 후 환경을 생성한다.
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testList() throws Exception {
        log.info(
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                        .param("pageNum", "1")
                        .param("amount", "10")
                        .param("type", "T")
                        .param("keyword", "테스트")
                )
                        .andReturn().getModelAndView().getModelMap().toString());
    }

    @Test
    public void testRegister() throws Exception {
        String boardNum = mockMvc.perform(
                MockMvcRequestBuilders.post("/board/register")
                        .param("boardTitle", "테스트 새 글 제목")
                        .param("boardContent", "테스트 새 글 내용")
                        .param("memberNum", "31")
        ).andReturn().getFlashMap().toString();

        log.info(boardNum);
    }

    @Test
    public void testRead() throws Exception {
        String board = mockMvc.perform(
                MockMvcRequestBuilders.get("/board/read")
                        .param("boardNum", "23")
        ).andReturn().getModelAndView().getViewName(); /*getModelMap().toString();*/

        log.info(board);
    }

    @Test
    public void testModify() throws Exception {
        String result = mockMvc.perform(
                MockMvcRequestBuilders.post("/board/modify")
                        .param("boardNum", "23")
                        .param("boardTitle", "수정된 테스트 새 글 제목")
                        .param("boardContent", "수정된 테스트 새 글 내용")
                        .param("memberNum", "41")
        ).andReturn().getModelAndView().getModelMap().toString();
        log.info(result);
    }

    @Test
    public void testRemove() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("boardNum", "44"))
                .andReturn().getFlashMap().toString();
        log.info(result);
    }

    @Test
    public void testGoRegister() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/register"))
                .andReturn().getModelAndView().getViewName());
    }

    @Test
    public void testGoModify() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/board/modify").param("boardNum", "2"))
                .andReturn().getModelAndView().getModelMap().toString();
        log.info(result);
    }

    @Test
    public void updateOk() throws Exception{
        log.info("===================================");
        String result = mockMvc.perform(MockMvcRequestBuilders.post("/board/read").param("boardNum", "70"))
                .andReturn().getFlashMap().toString();
        log.info("=========test======"+result);
    }
}