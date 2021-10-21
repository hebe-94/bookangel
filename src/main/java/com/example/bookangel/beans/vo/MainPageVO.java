package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MainPageVO {
    private Long boardNum;
    private Long memberNum;
    private String boardTitle;
    private String boardContent;
    private String siteLink;
    private String cBoardApplyOk;
    private int rowNum;
    private String memberName;
    private String memberTel;
}
