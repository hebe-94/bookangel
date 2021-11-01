package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.AttachFileVO;
import com.example.bookangel.mappers.AttachFileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachFileDAO {
    @Autowired
    private AttachFileMapper attachFileMapper;

    public void insert(AttachFileVO attachFileVO){
        attachFileMapper.insert(attachFileVO);
    }

    public void delete(String uuid){
        attachFileMapper.delete(uuid);
    }

    public List<AttachFileVO> findByBoardNum(Long boardNum){
        return attachFileMapper.findByBoardNum(boardNum);
    }

    public void deleteAll(Long boardNum){attachFileMapper.deleteAll(boardNum);}

    public List<AttachFileVO> getOldFiles() {return attachFileMapper.getOldFiles();}
}
