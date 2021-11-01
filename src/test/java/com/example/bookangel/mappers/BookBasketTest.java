package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.CouponVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BookBasketTest {

    @Autowired
    private BookBasketMapper bookBasketMapper;

    @Test
    public void addBookBasket(){
        log.info("-----------------------------------");
        log.info("책가방 담기 : " + bookBasketMapper.addBookBasket("09promise.jpg",21L));
    }

    @Test
    public void myBasket(){
        log.info("-----------------------------------");
        log.info("책가방 리스트 : ");
        bookBasketMapper.myBasket(21L).forEach(bookVO -> log.info(bookVO + ""));
    }
}
