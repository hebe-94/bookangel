package com.example.bookangel.services;

import com.example.bookangel.beans.dao.BookBasketDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
