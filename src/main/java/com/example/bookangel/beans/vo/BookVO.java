package com.example.bookangel.beans.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BookVO {
    private long bookNum; //private Long bno;
    private String bookName; //private String title;
    private String bookImageSrc;
    private String bookAuthor; //private String writer;
    private String bookPublisher;
    private String bookContent; //private String content;
}