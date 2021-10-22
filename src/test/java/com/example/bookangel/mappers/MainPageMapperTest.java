package com.example.bookangel.mappers;


import com.example.bookangel.beans.vo.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MainPageMapperTest {

    @Autowired
    private MainPageMapper mapper;

    @Test
    public void testGetList() {
        Criteria cri = new Criteria();
        cri.setPageNum(1);
        cri.setAmount(10);
        mapper.getOkList(cri).forEach(board -> log.info(board.toString()));
    }
}