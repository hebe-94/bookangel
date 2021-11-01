package com.example.bookangel.beans.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class BoardVO {
    private Long boardNum;
    private Long memberNum;
    private String boardTitle;
    private String boardContent;
    private String siteLink;
    private String cBoardDate;
    private String cBoardApplyOk;
    private int rowNum;
    private String memberName;
    private List<AttachFileVO> attachList;
}
