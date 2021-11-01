package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.AttachFileVO;

import java.util.List;

public interface AttachFileMapper {
    public void insert(AttachFileVO attachFileVO);
    public void delete(String uuid);
    public List<AttachFileVO> findByBoardNum(Long boardNum);
    public void deleteAll(Long BoardNum);
    public List<AttachFileVO> getOldFiles();
}