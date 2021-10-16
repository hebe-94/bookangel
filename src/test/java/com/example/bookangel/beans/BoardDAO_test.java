package com.example.bookangel.beans;

import com.example.bookangel.beans.dao.BoardDAO;
import com.example.bookangel.beans.vo.BoardVO;
import com.example.bookangel.beans.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BoardDAO_test {
    @Autowired
    private BoardDAO boardDAO;

    @Test
    public void testRegister(){
        BoardVO board = new BoardVO();
        board.setBoardTitle("새로 작성하는 글3");
        board.setBoardContent("새로 작성하는 내용3");
        board.setBoardNum(3L);
        board.setMemberNum(2L);
        board.setSiteLink("www");

        boardDAO.register(board);
        log.info("-------------------------------");
        log.info(board.getBoardNum() + "");
        log.info("-------------------------------");
    }

    @Test
    public void testGet(){
        log.info(boardDAO.get(3L).toString());
    }

/*    @Test
    public void testModify() {
        if (boardDAO.get(3L) == null) {
            log.info("***********NO SUCH BOARD***********");
        } else {
            BoardVO board = new BoardVO();
            board.setBno(3L);
            board.setTitle("수정된 글 제목");
            board.setContent("수정된 글 내용");
            log.info("UPDATE : " + boardDAO.modify(board));
        }
    }*/

    @Test
    public void testRemove(){
        if (boardDAO.get(3L) == null) {
            log.info("***********NO SUCH BOARD***********");
        }else{
            log.info("REMOVE : " + boardDAO.remove(3L));
        }
    }

    @Test
    public void testGetList(){
        Criteria criteria = new Criteria();
        boardDAO.getList(criteria).forEach(board -> log.info(board.toString()));
    }
}
