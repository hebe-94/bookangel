package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.BoardVO;
import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.mappers.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    //    @Autowired
//    private BoardMapper mapper;
    private final BoardMapper mapper;

    public void register(BoardVO board){
        mapper.insertSelectKey_boardNum(board);
    }

    public BoardVO get(Long boardNum){
        return mapper.read(boardNum);
    }

    public boolean modify(BoardVO board){
        return mapper.update(board) == 1;
    }

    public boolean remove(Long boardNum){
        return mapper.remove(boardNum) == 1;
    }

    public List<BoardVO> getList(Criteria criteria){
        return mapper.getList(criteria);
    }

    public int getTotal(Criteria criteria){ return mapper.getTotal(criteria); }

    public boolean updateOk(Long boardNum) {return  mapper.updateOk(boardNum) == 1; }
}
