package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.beans.vo.MainPageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainPageMapper {
    //    게시글 목록
    public List<MainPageVO> getOkList(Criteria criteria);

    //    게시글 전체 개수
    public int getOkTotal(Criteria criteria);
}