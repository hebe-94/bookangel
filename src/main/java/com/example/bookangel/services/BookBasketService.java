package com.example.bookangel.services;

import com.example.bookangel.beans.dao.BookBasketDAO;
import com.example.bookangel.beans.vo.BookVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookBasketService {

    private final BookBasketDAO bookBasketDAO;

    // 책가방에 담기
    public boolean addBookBasket(String imgSrc, Long memberNum){
        log.info("[Service]-책가방 담기...............");
        return bookBasketDAO.addBookBasket(imgSrc, memberNum);
    }

    // 책가방에 담겨있는지 확인
    public long isExist(String imgSrc, Long memberNum){
        log.info("[Service]-책가방 담겨있는지 확인...............");
        return bookBasketDAO.isExist(imgSrc, memberNum);
    }

    // 회원이 가지고 있는 basket정보
    public List<BookVO> myBasket(long memberNum){
        log.info("[Service]-책가방 리스트...............");
        return bookBasketDAO.myBasket(memberNum);
    }

    // 회원이 가지고 있는 basket 리스트 개수
    public long myBasketCNT(long memberNum){
        log.info("[Service]-책가방 리스트 개수...............");
        return bookBasketDAO.myBasketCNT(memberNum);
    }
}
