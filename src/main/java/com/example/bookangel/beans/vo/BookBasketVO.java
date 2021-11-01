package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BookBasketVO {
    private long bookNum;
    private long memberNum;

    // 책가방 담기위해 전달할 내용
    private String imgSrc;
}
