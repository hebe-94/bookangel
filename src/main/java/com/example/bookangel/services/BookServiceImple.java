package com.example.bookangel.services;


import com.example.bookangel.beans.dao.BookDAO;
import com.example.bookangel.beans.vo.BookVO;
import com.example.bookangel.beans.vo.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//    비지니스 계층
//    프레젠테이션 계층(View)와 영속 계층(DBMS)의 중간다리 역할을 한다.
//    영속 계층은 DBMS를 기준으로, 비지니스 계층은 로직을 기준으로 처리한다.
//    예를 들어 쇼핑몰에서 상품 구매 시 포인트 적립을 한다고 가정한다면,
//    영속 계층의 설계는 '상품', '회원'으로 나누어 설계하지만 비지니스 계층은
//    상품 영역과 회원 영역을 동시에 사용해서 하나의 로직을 처리하게 된다.

//    일반적으로 비지니스 영역에 있는 객체들은 서비스(Service)라는 용어를 많이 사용한다.

@Service
@RequiredArgsConstructor
public class BookServiceImple implements BookService{

    private final BookDAO bookDAO;

    @Transactional(rollbackFor = Exception.class)


    @Override
    public BookVO get(long bookNum) {
        return bookDAO.get(bookNum);
    }





    @Override
    public List<BookVO> getList(Criteria criteria) { return bookDAO.getList(criteria); }

    @Override
    public int getTotal(Criteria criteria) { return bookDAO.getTotal(criteria); }

}
