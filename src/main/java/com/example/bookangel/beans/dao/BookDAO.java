package com.example.bookangel.beans.dao;

import com.example.bookangel.beans.vo.BookVO;
import com.example.bookangel.beans.vo.Criteria;
import com.example.bookangel.mappers.BookMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BookDAO {
    //    @Autowired
//    private BoardMapper mapper;
    private final BookMapper mapper;



    public BookVO get(long bookNum){
        return mapper.read(bookNum);
    }


    public List<BookVO> getList(Criteria criteria){
        log.info("BookDAO : ");
        log.info("BookDAO : ");
        log.info("BookDAO : ");
        log.info("BookDAO : ");

        log.info("criteria : "+ criteria.getKeyword());
        return mapper.getList(criteria);
    }

    public int getTotal(Criteria criteria){ return mapper.getTotal(criteria); }
}


