package com.example.bookangel.services;

import com.example.bookangel.beans.dao.AttachFileDAO;
import com.example.bookangel.beans.dao.BoardDAO;
import com.example.bookangel.beans.vo.AttachFileVO;
import com.example.bookangel.beans.vo.BoardVO;
import com.example.bookangel.beans.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImple implements BoardService{

    private final BoardDAO boardDAO;
    private final AttachFileDAO attachFileDAO;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(BoardVO board) {
        boardDAO.register(board);
        if(board.getAttachList() == null || board.getAttachList().size() == 0){
            return;
        }

        board.getAttachList().forEach(attach -> {
            attach.setBoardNum(board.getBoardNum());
            attachFileDAO.insert(attach);
        });
    }

    @Override
    public BoardVO get(Long boardNum) {
        return boardDAO.get(boardNum);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean modify(BoardVO board) {
        boolean boardModifyResult = false;

        attachFileDAO.deleteAll(board.getBoardNum());
        boardModifyResult = boardDAO.modify(board);

        if(boardModifyResult && board.getAttachList() != null && board.getAttachList().size() != 0){
            board.getAttachList().forEach(attach -> {
                attach.setBoardNum(board.getBoardNum());
                attachFileDAO.insert(attach);
            });
        }
        return boardModifyResult;
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

    @Override
    public List<AttachFileVO> getAttachList(Long boardNum) {
        return attachFileDAO.findByBoardNum(boardNum);
    }
}
