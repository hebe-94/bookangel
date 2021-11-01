package com.example.bookangel.beans.dao;


import com.example.bookangel.beans.vo.BookBasketVO;
import com.example.bookangel.beans.vo.CouponVO;
import com.example.bookangel.mappers.BookBasketMapper;
import com.example.bookangel.mappers.CouponMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
    // 책가방에 담겨있는지 확인
    public long isExist(String imgSrc, Long memberNum){
        log.info("[DAO]-책가방 담겨있는지 확인...............");
        return bookBasketMapper.isExist(imgSrc, memberNum);
    }

}
