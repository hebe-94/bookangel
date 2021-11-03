package com.example.bookangel.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BookBasketServiceTest {

    @Autowired
    private BookBasketService bookBasketService;

    // 책가방에 담기
    @Test
    public void addBookBasket(){
        log.info("[Service]-책가방 담기...............");
        log.info(bookBasketService.addBookBasket("15from.jpg", 21L) + "");
    }

    // bookNum으로 책가방에 담기
    @Test
    public void addBookBasketToBookNum(){
        log.info("[Service]-책가방 담기...............");
        log.info(bookBasketService.addBookBasketToBookNum(10l, 21l) + "");
    }

    // 책가방에 담겨있는지 확인
    @Test
    public void isExist(){
        log.info("[Service]-책가방 담겨있는지 확인...............");
        log.info(bookBasketService.isExist("15from.jpg", 21L) + "");
    }

    // bookNum으로 책가방에 담겨있는지 확인
    @Test
    public void isExistToBookNum(){
        log.info("[Service]-책가방 담겨있는지 확인...............");
        log.info(bookBasketService.isExistToBookNum(92l, 21L) + "");
    }

    // 책가방 몇개 담겨 있는지
    @Test
    public void myBasketCNT(){
        log.info("[Service]-책가방 담겨있는지 확인...............");
        log.info(bookBasketService.myBasketCNT(21L) + "");
    }
}
