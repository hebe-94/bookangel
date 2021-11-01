package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.BookBasketVO;
import com.example.bookangel.beans.vo.BookVO;
import com.example.bookangel.beans.vo.CouponVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookBasketMapper {
    // 책가방에 담기
    public long addBookBasket(String imgSrc, Long memberNum);

    // 책가방에 담겨있는지 확인
    public long isExist(String imgSrc, Long memberNum);

    // 회원이 가지고 있는 basket정보
    public List<BookVO> myBasket(long memberNum);
}
