package com.example.bookangel.mappers;


import com.example.bookangel.beans.vo.BookVO;
import com.example.bookangel.beans.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//    Persistence Tier - 영속 계층, 데이터 계층
//    데이터의 CRUD 연산을 수행하는 Mapper와 결과를 객체로 리턴하는 DAO까지 영속 계층에 속한다.

@Mapper
public interface BookMapper {
    //    책 목록
    public List<BookVO> getList(Criteria criteria);
    //    게시글 전체 개수
    public int getTotal(Criteria criteria);
    //    게시글 상세보기(특정 게시글 정보)
    public BookVO read(long bookNum);
}
