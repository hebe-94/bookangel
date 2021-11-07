package com.example.bookangel.beans.dao;


import com.example.bookangel.beans.vo.BookBasketVO;
import com.example.bookangel.beans.vo.BookVO;
import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.mappers.BookBasketMapper;
import com.example.bookangel.mappers.CouponMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookBasketDAO {

    private final BookBasketMapper bookBasketMapper;

    // 책가방에 담기
    public boolean addBookBasket(String imgSrc, Long memberNum){
        log.info("[DAO]-책가방 담기...............");
        return bookBasketMapper.addBookBasket(imgSrc, memberNum) == 1L;
    }

    // bookNum으로 책가방에 담기
    public boolean addBookBasketToBookNum(long bookNum, Long memberNum){
        log.info("[DAO]-bookNum으로 책가방 담기...............");
        return bookBasketMapper.addBookBasketToBookNum(bookNum, memberNum) == 1L;
    }

    // 책가방에 담겨있는지 확인
    public long isExist(String imgSrc, Long memberNum){
        log.info("[DAO]-책가방 담겨있는지 확인...............");
        return bookBasketMapper.isExist(imgSrc, memberNum);
    }

    // 책가방에 담겨있는지 확인
    public long isExistToBookNum(long bookNum, Long memberNum){
        log.info("[DAO]-bookNum으로 책가방 담겨있는지 확인...............");
        return bookBasketMapper.isExistToBookNum(bookNum, memberNum);
    }

    // 회원이 가지고 있는 basket정보
    public List<BookVO> myBasket(long memberNum){
        log.info("[DAO]-책가방 리스트...............");
        return bookBasketMapper.myBasket(memberNum);
    }

    // 회원이 가지고 있는 basket 리스트 개수
    public long myBasketCNT(long memberNum){
        log.info("[DAO]-책가방 리스트 개수...............");
        return bookBasketMapper.myBasketCNT(memberNum);
    }

    // 책가방에서 제거
    public boolean delete(long memberNum, long bookNum){
        log.info("[DAO]-책가방에서 삭제...............");
        return bookBasketMapper.delete(memberNum, bookNum) == 1;
    }

}
