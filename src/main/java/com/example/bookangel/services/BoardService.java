package com.example.bookangel.services;

import com.example.bookangel.beans.vo.BoardVO;
import com.example.bookangel.beans.vo.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardService {
    public void register(BoardVO board);
    public BoardVO get(Long boardNum);
    public boolean modify(BoardVO board);
    public boolean remove(Long boardNum);
    public List<BoardVO> getList(Criteria criteria);
    public int getTotal(Criteria criteria);
    public boolean updateOk(Long boardNum);
}
