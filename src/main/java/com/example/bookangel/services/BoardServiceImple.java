package com.example.bookangel.services;

import com.example.bookangel.beans.dao.BoardDAO;
import com.example.bookangel.beans.vo.BoardVO;
import com.example.bookangel.beans.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{

    private final BoardDAO boardDAO;

    @Override
    public void register(BoardVO board) {
        boardDAO.register(board);
    }

    @Override
    public BoardVO get(Long boardNum) {
        return boardDAO.get(boardNum);
    }

    @Override
    public boolean modify(BoardVO board) {
        return boardDAO.modify(board);
    }

    @Override
    public boolean remove(Long boardNum) {
        return boardDAO.remove(boardNum);
    }

    @Override
    public List<BoardVO> getList(Criteria criteria) { return boardDAO.getList(criteria); }

    @Override
    public int getTotal(Criteria criteria) { return boardDAO.getTotal(criteria); }

    @Override
    public boolean updateOk(Long boardNum) {  return boardDAO.updateOk(boardNum); }
}
