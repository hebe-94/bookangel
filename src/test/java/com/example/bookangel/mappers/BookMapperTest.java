package com.example.bookangel.mappers;

import com.example.bookangel.beans.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Slf4j
@SpringBootTest
public class BookMapperTest {


    @Autowired
    private BookMapper bookMapper;

    @Test
    public void getListTest(){

        Criteria criteria = new Criteria();
        criteria.setKeyword("아");
        criteria.setAmount(10);
        criteria.setPageNum(1);
        bookMapper.getList(criteria).forEach(bookVO -> log.info(bookVO.toString()));
    }
}
